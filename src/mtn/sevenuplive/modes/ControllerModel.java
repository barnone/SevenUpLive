/*
	Copyright 2009 Adam Ribaldo 
	 
	Developed by Adam Ribaldo, Chris Lloyd
    
    This file is part of SevenUpLive.
    http://www.makingthenoise.com/sevenup/

    SevenUpLive is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SevenUpLive is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with SevenUpLive.  If not, see <http://www.gnu.org/licenses/>.
*/

package mtn.sevenuplive.modes;
import mtn.sevenuplive.m4l.M4LController;
import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.modes.events.Event;
import mtn.sevenuplive.modes.events.EventDispatcher;
import mtn.sevenuplive.modes.events.EventListener;

public class ControllerModel extends Mode implements EventListener, EventDispatcher {

	private M4LMidiOut midiControlOut[];
	public Integer controls[][];
	private Integer startingController;
	
	/** Turn calibration mode ON for ADC controllers */ 
	private boolean adcCalibrateMode = false;
	private boolean adcActive = false;
	
	boolean resetADCCalibration = true;
	private float[] adcScalar = new float[8];
	private float[] adcCenterOffset = new float[8];
	private float[] adcMin = new float[8];
	private float[] adcMax = new float[8];
	
	private boolean[] banksHeld = new boolean[] {false, false, false, false, false, false, false};
	private boolean[] enabledADCports;
	
	public ControllerModel(int _navRow, M4LMidiOut[] _midiControlOut, int _startingController, int grid_width, int grid_height, boolean[] enabledADCports) {
		super(_navRow, grid_width, grid_height);
		this.enabledADCports = enabledADCports;
		midiControlOut = _midiControlOut;
		controls = new Integer[7][7];
		startingController = _startingController;
		
		//Initialize controls to -1 for "not set"
		for(int j=0;j<7;j++)
			for(int i=0;i<7;i++)
				controls[j][i] = -1;
		
		// Need to start with a blank slate
		resetADCCalibration();
	}
	
	/*
	 * [8] //y=0
	 * [7] //y=1
	 * [6] //y=2
	 * [5] //y=3
	 * [4] //y=4
	 * [3] //y=5
	 * [2] //y=6
	 * [1] //y=7
	 * [0] // Send control value = 0
	 * [-1] // Disabled, do not send a control value
	 */
	public void press(int x, int y, int curBank)
	{
		   //If they hit the bottom row twice, clear the column
	       if(y==7 && controls[curBank][x] == 1)
	       {
	    	   controls[curBank][x] = 0;
	       }
	       //Otherwise set the value to 8-y
	       else
	       { 
	    	   controls[curBank][x] = 8-y;
	       }
	       
	       //Send the control value for the previous selected column
	       sendMidiControls(curBank, x);
	}

	private void sendMidiControls(int bank, int x)
	 {
		   int controlValue = controls[bank][x] * 16;
		   //Can not send 128 as a value
		   if(controlValue == 128)
			   controlValue = 127;
		   
		   //send controlvalue to control corresponding to the current control grid and the column
		   midiControlOut[bank].sendController(new M4LController(startingController + x, controlValue));
		   midiControlOut[7].sendController(new M4LController(startingController + x + (bank * 7), controlValue));
	 }

	public boolean bankHasValues(int bank) {
		if(bank < 7) //Only check 7 banks (0-6)
		for(int j =0; j<7;j++)
		{
			if(controls[bank][j] > -1)return true;
		}
		
		return false;
	}

	public void sendAllBankValues(int bank) {
		for(int i =0; i<7;i++)
		{
			if(controls[bank][i] > -1)
				sendMidiControls(bank, i);
		}		
	}


	@Override
	public void onEvent(Event e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Put us into ADC calibration mode
	 * @param on
	 */
	public void setADCCalibrationMode(boolean on) {
		if (adcCalibrateMode == on)
			return; // Nothing to do
		
		if (on) {
			resetADCCalibration();
		} else {
			finalizeCalibration();
		}
		adcCalibrateMode = on;
	}
	
	/**
	 * If we are either calibrating OR in ADC On mode then yes ADC should be on 
	 * @return
	 */
	public boolean shouldADCBeOn() {
		if (adcCalibrateMode|| adcActive)
			return true;
		else
			return false;
	}
	
	public void setADCActive(boolean on) {
		adcActive = on;
	}
	
	public void holdBank(int bank) {
		banksHeld[bank] = true;
	}
	
	public void releaseBank(int bank) {
		banksHeld[bank] = false;
	}
	
	public void monomeAdc(int x, float value) {
		if (!adcActive)
			return;
		
		// Filter out ADC we are not handling
		if (x < enabledADCports.length) {
			if (!enabledADCports[x])
				return;
		} else {
			return;
		}
		
		// We work in the range of 0 - 127
		float scaledValue = value * 127;
		scaledValue = Math.min(127, scaledValue);
		scaledValue = Math.max(0, scaledValue);
		
		if (adcCalibrateMode) {
			calibrate(x, scaledValue); // provide input for calibration 
		} else {
			scaledValue = normalizeADC(x, scaledValue); // Scale that value based on calibration data, else return original value if not calibrated
		}
		
		for (int bank = 0; bank < 7; bank++) {
			// ch 9 (index 8) is the ADC channel
			if (banksHeld[bank]) // Send the controller if the bank is being held
				midiControlOut[8].sendController(new M4LController(startingController + x + (bank * 8), new Float(scaledValue).intValue())); // 8 possible ADC indexes * 7 banks
		}
	}
	
	/**
	 * Analyze range of values for proper centering and scaling
	 * @param x
	 * @param value
	 */
	private void calibrate(int x, float value) {
		// This is a crazy gs128 firmware bug (tested both serialosc and monomeserial).
		// There is one spurious value that messes up the actual tilt range.
		// Here we simply filter it out.
		if ((value - 3.4862745) < .1) {
			return;
		}
		
		if (resetADCCalibration) {
			resetADCCalibration();
			resetADCCalibration = false;
		}
			
		// Get new minimum
		if (adcMin[x] == -1f || adcMin[x] > value) {
			adcMin[x] = value;
		} 
		
		// Get new maximum
		if (adcMax[x] == -1f || adcMax[x] < value) {
			adcMax[x] = value;
		} 
	}
	
	/**
	 * Finalize the calibration.
	 * We compute the proper scaling and center offset to get us 
	 * a maximum range from 0-127
	 */
	private void finalizeCalibration() {
		float center = new Float(63.5);
		float rawCenter;
		
		for (int i = 0; i < 8; i++) {
			// Only calibrate if we have observed Min and Max values
			if (adcMin[i] != -1f && adcMax[i] != -1f) {
				rawCenter =  ((adcMax[i] - adcMin[i]) / 2) + adcMin[i]; // Find the center of the unscaled values
				adcCenterOffset[i] =  rawCenter - center;
				
				// Compute proper scalar for full range scaling
				adcScalar[i] = 127 / (adcMax[i] - adcMin[i]); 
				/*System.out.println("center offset " + adcCenterOffset[i]);
				System.out.println("min " + adcMin[i]);
				System.out.println("max " + adcMax[i]);
				System.out.println("scalar " + adcScalar[i]);*/
			} else {
				enabledADCports[i] = false; // Turn off this port
			}
		}
		resetADCCalibration = true;
	}
	
	private int normalizeADC(int i, float value) {
		float center = new Float(63.5);
		float scaledValue;
		
		if (adcMin[i] != -1f && adcMax[i] != -1f) {
			scaledValue = value + adcCenterOffset[i];
				
			// Scale offset from center
			scaledValue = ((scaledValue - center) * adcScalar[i]) + center;
			
			scaledValue = Math.min(scaledValue, 127);
			scaledValue = Math.max(scaledValue, 0);
			
			return new Float(scaledValue).intValue();
 		}
		
		// Return original as nearest integer
		return new Float(value).intValue();
	}
	
	/**
	 * Reset Calibration Data
	 */
	private void resetADCCalibration() {
		for (int i = 0; i < 8; i++) {
			adcScalar[i] = 1;
			adcCenterOffset[i] = 0;
			adcMin[i] = -1f; // not set
			adcMax[i] = -1f; // not set
		}
	}
}
