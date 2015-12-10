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

package mtn.sevenuplive.m4l;

import java.util.HashMap;

import mtn.sevenuplive.max.mxj.SevenUp4Live;
import processing.core.PApplet;

public class M4LMidiSystem implements M4LMidi {

	private SevenUp4Live app;
	
	public enum eSevenUp4OutputDevices {Melodizer1, Melodizer2, Stepper, Looper, Controller};
	public enum eSevenUp4InputDevices {SevenUpMidiControl};
	
	private HashMap<String, HashMap<Integer, M4LMidiIn>> inputDeviceMap = new HashMap<String, HashMap<Integer, M4LMidiIn>>();
	private HashMap<String, HashMap<Integer, M4LMidiOut>> outputDeviceMap = new HashMap<String, HashMap<Integer, M4LMidiOut>>();
	
	public M4LMidiSystem(SevenUp4Live app) {
		this.app = app;
	}
	
	public void closePorts() {
		// TODO Auto-generated method stub
	}

	public String getInputDeviceName(int device) {
		if (eSevenUp4InputDevices.values().length <= device)
			return null;
		
		return (eSevenUp4InputDevices.values())[device].toString();
	}

	public String getOutputDeviceName(int device) {
		if (eSevenUp4InputDevices.values().length <= device)
			return null;
		
		return (eSevenUp4InputDevices.values())[device].toString();
	}

	public int numberOfInputDevices() {
		return eSevenUp4InputDevices.values().length;
	}

	public int numberOfOutputDevices() {
		return eSevenUp4OutputDevices.values().length;
	}

	public M4LMidiIn getMidiIn(int ch, String deviceName) {
		HashMap<Integer, M4LMidiIn> map = inputDeviceMap.get(deviceName);
		if (map == null) {
			map = new HashMap<Integer, M4LMidiIn>();
			inputDeviceMap.put(deviceName, map);
		}
		return map.get(ch);
	}

	public M4LMidiOut getMidiOut(int ch, String deviceName) {
		HashMap<Integer, M4LMidiOut> map = outputDeviceMap.get(deviceName);
		if (map == null) {
			map = new HashMap<Integer, M4LMidiOut>();
			outputDeviceMap.put(deviceName, map);
		}
		
		M4LMidiOut device = map.get(ch);
		
		if (device != null)
			return device;
		
		eSevenUp4OutputDevices deviceType = deviceName == null ? null : eSevenUp4OutputDevices.valueOf(deviceName);
		
		if (deviceType != null ) {
			if (deviceType == eSevenUp4OutputDevices.Melodizer1) {
				device = new M4LForwardingMidiOutPort(app.getMelodizerOutput(ch, 1));
			} else if (deviceType == eSevenUp4OutputDevices.Melodizer2) {
				device = new M4LForwardingMidiOutPort(app.getMelodizerOutput(ch, 2));
			} else if (deviceType == eSevenUp4OutputDevices.Stepper) {
				device = new M4LForwardingMidiOutPort(app.getStepperOutput(ch, 1));
			} else if (deviceType == eSevenUp4OutputDevices.Looper) {
				device = new M4LForwardingMidiOutPort(app.getLooperOutput(ch, 1));
			} else if (deviceType == eSevenUp4OutputDevices.Controller) {
				device = new M4LForwardingMidiOutPort(app.getControllerOutput(ch, 1));
			}
		}  
		
		if (device == null)
			device = new M4LForwardingMidiOutPort(null);
		
		map.put(ch, device);
		return device;
	}

	public void printDevices() {
		for (eSevenUp4OutputDevices device: eSevenUp4OutputDevices.values()) {
			System.out.println(device);
		}
		for (eSevenUp4InputDevices device: eSevenUp4InputDevices.values()) {
			System.out.println(device);
		}
	}

	public void closeInput(String deviceName) {
		// TODO Auto-generated method stub
	}

	public void closeOutputs() {
		// TODO Auto-generated method stub
	}

	public void plug(PApplet core, String event, String deviceName, int ch) {
		M4LMidiIn device = new M4LForwardingMidiInPort(ch, event, core);
		HashMap<Integer, M4LMidiIn> map = inputDeviceMap.get(deviceName);
		if (map == null) {
			map = new HashMap<Integer, M4LMidiIn>();
			inputDeviceMap.put(deviceName, map);
		}
		map.put(ch, device);
	}
	
}
