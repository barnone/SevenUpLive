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

import java.util.List;

import mtn.sevenuplive.m4l.M4LController;
import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.main.MonomeUp;
import mtn.sevenuplive.modes.events.Event;
import mtn.sevenuplive.modes.events.UpdateDisplayEvent;
import mtn.sevenuplive.modes.events.UpdateNavEvent;

import org.jdom.Attribute;
import org.jdom.Element;

public class Looper extends Mode {
	
	private Loop[] loops;
	
	private final static int OFFSET_START_CTRL = 96;
	
	private M4LMidiOut[] midiOut;
	private Boolean gateLoopChokes = true;
	private boolean muteNotes = false;

	public boolean[] stopLoopsOnNextStep;
	
	public Looper(int _navRow, M4LMidiOut[] _midiOut, mtn.sevenuplive.main.MonomeUp _m, int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		
		stopLoopsOnNextStep = new boolean[7];
		
		loops = new Loop[7];
		for(int i=0;i<loops.length;i++)
			loops[i] = new Loop();
		
		midiOut = _midiOut;
		
		// Subscribe to the events we want to receive
		subscribe(new UpdateDisplayEvent(), this);
		subscribe(new UpdateNavEvent(), this);
	}
	
	public void onEvent(Event e) {
		if (e.getType().equals(UpdateDisplayEvent.UPDATE_DISPLAY_EVENT)) {
			updateDisplayGrid(); 
		} else if (e.getType().equals(UpdateNavEvent.UPDATE_NAV_EVENT)) {
			updateNavGrid(); 
		}
	}
	
	public int getNumLoops()
	{
		return loops.length;
	}
	
	public Loop getLoop(int index)
	{
		return loops[index];
	}
	
	private void updateNavGrid()
	{
		clearNavGrid();
		navGrid[myNavRow] = DisplayGrid.SOLID;
	   //Iterate through the loops and set them all to their coordesponding values
 	   for(int i=0;i<loops.length;i++)
       {
     			 if(loops[i].isPlaying())
     				  navGrid[getYCoordFromSubMenu(i)] = DisplayGrid.FASTBLINK;
     			 else 
     				  navGrid[getYCoordFromSubMenu(i)] = DisplayGrid.OFF;
       }
	}
	
	public void updateDisplayGrid()
	{
		clearDisplayGrid();
		
		for(int i=0;i<loops.length;i++)
	    {
		      if(loops[i].isPlaying())
				   displayGrid[i][loops[i].getStep()] = DisplayGrid.SOLID;
	    }
	}
	
	public int[] getNavGrid()
	{
		updateNavGrid();
		return navGrid;
	}
	
	public void press(int x, int y)
	{
	  
		if(x == DisplayGrid.NAVCOL)
		{
			pressNavCol(y);
			updateNavGrid();
		}
		else
			pressDisplay(x,y);

		updateDisplayGrid();
		//updateNavGrid(); // @TODO clloyd not needed, done in play and stop functions
	}
	
	/**
	 * Use this operation when user is causing the change in offset or when playing back loop recorder
	 * @param loopNum
	 * @param step the step we are on
	 */
	public void sendCtrlVal(int loopNum, int step)
	{
		int ctrlVal = step * 16;
		midiOut[loopNum].sendController(new M4LController(OFFSET_START_CTRL+loopNum, ctrlVal));
		
		// This is a special NOTE that is sent immediately. The old loop rack ignores it
		// New components that want to detect that a trigger was fired WHEN it was fired,
		// can intercept it.
		midiOut[loopNum].sendNoteOn(new Note(MonomeUp.C2+loopNum, ctrlVal + 1, 0));
		midiOut[loopNum].sendNoteOff(new Note(MonomeUp.C2+loopNum, 0, 0));
	}
	
	private void pressNavCol(int y)
	{
		int loopIndex = getSubMenuFromYCoord(y);
		//Inverse the mode of the corresponding loop
		if(AllModes.getInstance().getLoopRecorder().isLoopSequencePlaying(loopIndex) || loops[loopIndex].isPlaying())
		{
			loops[loopIndex].stop();
			midiOut[loopIndex].sendNoteOff(new Note(MonomeUp.C3+loopIndex,127, 0));
		}
		else
		{
			playLoop(loopIndex, 0);
		}
		
		
	}
	
	public boolean isLoopPlaying(int loopNum)
	{
		return loops[loopNum].isPlaying();
	}
	
	public void stopLoop(int loopNum)
	{
		loops[loopNum].stop();
		updateNavGrid();
		AllModes.loopRecorder.updateNavGrid();
		if (loops[loopNum].getType() != Loop.HIT)
			midiOut[loopNum].sendNoteOff(new Note(MonomeUp.C3+loopNum,127, 0));
	}
	
	public void setLoopStopOnNextStep(int loopNum)
	{
		stopLoopsOnNextStep[loopNum] = true;
	}
	
	public void playLoop(int loopNum, int step)
	{
		loops[loopNum].setTrigger(step, true);
		loops[loopNum].setStep(step);
		loops[loopNum].setPressedRow(step);
		
		// For MindShuffler this really needs to come a tick earlier
		sendCtrlVal(loopNum, step);
		
		updateNavGrid();
		AllModes.loopRecorder.updateNavGrid();
	}
	
	private void pressDisplay(int x, int y)
	{
			//Choke loops in the same choke group
			int curChokeGroup = loops[x].getChokeGroup();
			if(curChokeGroup > -1)
			{
				for(int i=0; i<7;i++)
					if(loops[i].getChokeGroup() == curChokeGroup && i != x)
					{
						if(gateLoopChokes)
							stopLoop(i);
						else
							stopLoopsOnNextStep[i] = true;
					}
			}
			
			stopLoopsOnNextStep[x] = false;
			
			playLoop(x, y);
	}
	
	public void release(int x, int y)
	{
		if (loops[x].isPlaying() && loops[x].getType() == Loop.MOMENTARY && loops[x].getLastTriggedStep() == y) {
			stopLoop(x);
		}
	}
	
	public void step()
	{
		updateDisplayGrid();
		
		for(int i=0;i<7;i++)
		{
			if(stopLoopsOnNextStep[i])
			{
				stopLoop(i);
				loops[i].setPressedRow(-1);
				stopLoopsOnNextStep[i] = false;
			}
			else		
				stepOneLoop(i);
		}	
	}
	
	

	public void stepOneLoop(int loopNum)
	{
		int pressedRow;
		int resCounter;
		int step;
		int i = loopNum;
			
			if(loops[i].isPlaying())
        	{
        		pressedRow = loops[i].getPressedRow();
        		resCounter = loops[i].getResCounter();
        		step = loops[i].getStep();
        		
        		//In Buzz you have to send the controller AFTER the note is played
        		int loopCtrlValue = (loops[i].getStep() * 16);
        		
        		// Only send the controller if we are changing position. This allows the sample to play smoothly and linearly.
        		if (pressedRow > -1) {
        			switch (loops[i].getType()) {
        				case Loop.HIT: // Hits we let it run to the end of the sample and don't send a noteOff on release
        					if (loops[i].getTrigger(step) == true) {
        						//midiOut[i].sendController(new M4LController(OFFSET_START_CTRL+i, loopCtrlValue));
        						if(!muteNotes)
        							midiOut[i].sendNoteOn(new Note(MonomeUp.C3+i,pressedRow * 16  +1, 0));
        						loops[i].setTrigger(step, false);
        					} else {
        						stopLoop(i);
        						pressedRow = -1;
        	  			}
        					break;
        				case Loop.MOMENTARY:
        				case Loop.SLICE:
        					if (resCounter == 0 || loops[i].getTrigger(step)) {
        						if(!muteNotes)
        							midiOut[i].sendNoteOn(new Note(MonomeUp.C3+i,pressedRow * 16 +1, 0));
    							loops[i].setTrigger(step, false);
        					}
        				case Loop.SHOT:
        				case Loop.LOOP:
        				case Loop.STEP:
        				default:
        						//Send note every time looprow is 0 or at it's offset
        	        		if((resCounter == 0) && (step == 0 || pressedRow > -1))
        	        		{
        	        			if (!muteNotes) {
        	        				boolean sendNote = false;
        	        				if(loops[i].getTrigger(step) == true) { 
	        	        				loops[i].setTrigger(step, false);
	        	        				sendNote = true;
	        	        			}	
	        	        			
        	        				// We only want to retrigger when necessary to avoid additional microfades or minor timing issues.
	        	        			if (resCounter == 0) {
	        	        				if (loops[i].getType() == Loop.STEP) { // Else we are stepping in Loop.STEP mode and we retrigger every step
	        	        					sendNote = true;
	        	        				} else if (step == 0 && loops[i].getIteration() > 0) { // We only retrigger at step 0 in other modes
	        	        					sendNote = true;
	        	        				}
	        	        			}	
	        	        			
	        	        			if (sendNote)
	        	        			{
	        	        				midiOut[i].sendNoteOn(new Note(MonomeUp.C3+i,pressedRow * 16 +1, 0));
	        	        			}
        	        			}
        	        			pressedRow = -1;
        	        			
        	        		}
        	        		
    	        			break;
        					
        			};
        		}	
        		
        		//If in slice mode, stop it once it reaches its resolution
        		if (loops[i].getType() == Loop.SLICE && (loops[i].isLastResInStep() || loops[i].getResolution() == 1) )
        			stopLoopsOnNextStep[i]=true;
        		
        		//After the note is sent, increase the res counter
    			//Set the new offset to prepare it for the next note send
        		loops[i].nextResCount();
    			loopCtrlValue = (loops[i].getStep() * 16);
				midiOut[i].sendController(new M4LController(OFFSET_START_CTRL+i, loopCtrlValue));
			
				//If it's a one shot loop, then we stop after the first iteration
        		if (loops[i].getType() == Loop.SHOT && loops[i].getIteration() > 0)
        			stopLoopsOnNextStep[i]=true;
        	}
   
	}
	
	public Element toJDOMXMLElement()
	{
		Element xmlLooper = new Element("looper");
		
		xmlLooper.setAttribute(new Attribute("gateLoopChokes", gateLoopChokes.toString()));
		
		Element xmlLoop;

		for(Integer i=0;i<loops.length;i++)
		{
			xmlLoop = loops[i].toJDOMXMLElement();
			xmlLoop.setAttribute(new Attribute("index", i.toString()));
			xmlLooper.addContent(xmlLoop);
		}
		
		return xmlLooper;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlLooper)
	{	
		List<Element> xmlLoops;
		Integer loopIndex;
		
		gateLoopChokes = xmlLooper.getAttributeValue("gateLoopChokes") == null ? gateLoopChokes : Boolean.parseBoolean(xmlLooper.getAttributeValue("gateLoopChokes"));
		
		xmlLoops = xmlLooper.getChildren();
		
		for (Element xmlLoop : xmlLoops)
		{
			loopIndex = xmlLoop.getAttributeValue("index") == null ? NOT_SET : Integer.parseInt(xmlLoop.getAttributeValue("index"));
			if (loopIndex != NOT_SET)
				loops[loopIndex].loadJDOMXMLElement(xmlLoop);		
		}
	}
	
	public void setGateLoopChokes(boolean _gateLoopChokes)
	{
		gateLoopChokes = _gateLoopChokes;
	}
	
	public boolean getGateLoopChokes()
	{
		return gateLoopChokes;
	}

	public void reset() {
		for(int i=0;i<7;i++)
			stopLoop(i);
	}

	public void setLooperMute(boolean mute) {
		muteNotes = mute;
		
	}
}


