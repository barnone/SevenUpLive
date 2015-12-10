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

import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.main.MonomeUp;
import mtn.sevenuplive.modes.events.Event;
import mtn.sevenuplive.modes.events.UpdateDisplayEvent;

public class Masterizer extends Mode {
	//SEQUENCER
	private final static int SEQUENCER_COL = 0;
	int sequencerRows[];
	
	//CONROLLER
	private final static int CONTROLER_COL = 1;
	int ctrlSequenceRows[]; 
	
	//LOOPING
	private final static int LOOPER_COL = 2;
	int looperRows[]; 
	
	//LOOP RECORDER SEQUENCES
	private final static int LOOPRECORDER_COL = 3;
	int loopRecorderRows[]; 
	
	//MELODY
	private final static int MELODY_COL = 4;
	private int melodyRows[];
	private boolean mel1Cue[];

	//MELODY2
	private int melody2Col = 5;
	private int melody2Rows[];
	private boolean mel2Cue[];
	
	//TRANSPORT LOCATOR
	private final static int LOCATOR_COL = 6;
	private final static int PLAYMODE = 1;
	private final static int RECMODE = 2;
	private int locatorRows[];
	private int locatorMode = PLAYMODE;
	
	//MASTER
	private M4LMidiOut[] midiMasterOut;
	
	public Masterizer(int _navRow, M4LMidiOut[] _midiMasterOut, mtn.sevenuplive.main.MonomeUp _m,  int grid_width, int grid_height)
	{
		super(_navRow, grid_width, grid_height);
		displayGrid = new int[7][8];
		
		sequencerRows = new int[8];
		
		//CONTROLLER
		ctrlSequenceRows = new int[8];
		
		//LOOPER
		looperRows = new int[8];
		
		//LOOPRECORDER
		loopRecorderRows = new int[8];
		
		//MELODY
		melodyRows = new int[8];
		mel1Cue = new boolean[8];
		
		//MELODY2
		melody2Rows = new int[8];
		mel2Cue = new boolean[8];
		
		//MASTER
		midiMasterOut = _midiMasterOut;
		
		//LOCATOR
		locatorRows = new int[8];
		
		// Subscribe to the events we want to receive
		this.subscribe(new UpdateDisplayEvent(), this);
		
	}
	
	public void onEvent(Event e) {
		
		if (e.getType().equals(UpdateDisplayEvent.UPDATE_DISPLAY_EVENT)) {
			UpdateDisplayEvent ude = (UpdateDisplayEvent)e;
			updateDisplayGrid(); 
		}
	}
	
	public void press(int x, int y)
	{
		int seqStatus;
		
		if(x == myNavRow)
		{
			//Send live MIDI notes that can be used for transport control or anything else
			//Force users to double tap the button to ensure no accidental changes of transport controls
			if(navGrid[y] == DisplayGrid.OFF)
				navGrid[y] = DisplayGrid.SLOWBLINK;
			else if(navGrid[y] == DisplayGrid.SLOWBLINK)
			{
				navGrid[y] = DisplayGrid.OFF;
				
				// Channel 8 is the master channel
				midiMasterOut[7].sendNoteOn(new Note(MonomeUp.C4 + y,127, 0));
			}
		}
		else if(x == SEQUENCER_COL)
		{
			//Ignore 7th row
			if(y < 7)
			{
				if(y != AllModes.sequencer.curSequenceBank)
					AllModes.sequencer.nextSequence = y;
				else
				{
						AllModes.sequencer.curSeqRow = 0;
						//Play pattern from beginning
						AllModes.patternizerModel.curPatternRow = 0;
				}
			}
		}
		else if(x == LOOPRECORDER_COL && y < 7)
		{
			seqStatus = AllModes.loopRecorder.getSeqStatus(y);
   		 	if(seqStatus == MonomeUp.PLAYING)
   		 	{
	 			AllModes.loopRecorder.stopLoopSequence(y);
   		 	}
   		 	else if(seqStatus == MonomeUp.STOPPED)
   		 	{
	 			AllModes.loopRecorder.playLoopSequence(y);
		 	}
		}
		else if(x == LOOPER_COL)
		{
			if(y < AllModes.looper.getNumLoops())
			{
				//TODO: test if loop recorder is playing this loop?
				if(AllModes.looper.isLoopPlaying(y))
				{
					AllModes.looper.stopLoop(y);
				}
				else
				{
					AllModes.looper.playLoop(y, 0);
				}
			}
		}
		else if(x == MELODY_COL)
   	 	{
			seqStatus = AllModes.melody1Model.getSeqStatus(y);
   		 	if(seqStatus == MonomeUp.PLAYING)
   		 	{
   		 		if(AllModes.melody1Model.getRecMode() == ModeConstants.MEL_ON_BUTTON_PRESS)
   		 			stopMel1Seq(y);
   		 		else
   		 			mel1Cue[y] = true;
   		 	}
   		 	else if(seqStatus == MonomeUp.STOPPED)
   		 	{
   		 		if(AllModes.melody1Model.getRecMode() == ModeConstants.MEL_ON_BUTTON_PRESS)
   		 			AllModes.melody1Model.playSeq(y);
		 		else
		 			mel1Cue[y] = true;
   		 	}
   		 		
   	 	}
		else if(x == melody2Col)
   	 	{
			seqStatus = AllModes.melody2Model.getSeqStatus(y);
   		 	if(seqStatus == MonomeUp.PLAYING)
   		 	{	
   		 		if(AllModes.melody2Model.getRecMode() == ModeConstants.MEL_ON_BUTTON_PRESS)
		 			stopMel2Seq(y);
		 		else
		 			mel2Cue[y] = true;
   		 	}
   		 	else if(seqStatus == MonomeUp.STOPPED)
   		 	{
   		 		if(AllModes.melody2Model.getRecMode() == ModeConstants.MEL_ON_BUTTON_PRESS)
   		 			AllModes.melody2Model.playSeq(y);
		 		else
		 			mel2Cue[y] = true;
   		 	}
   	 	}
		else if(x == CONTROLER_COL && y < 7)
		{
			if(AllModes.controllerModel.bankHasValues(y))
				AllModes.controllerModel.sendAllBankValues(y);
		}
		updateDisplayGrid();
	}
	
	private void stopMel2Seq(int y) {
		AllModes.melody2Model.stopSeq(y);
	}

	private void stopMel1Seq(int y) {
	 		AllModes.melody1Model.stopSeq(y);
	}

	public void updateDisplayGrid()
	{
		int seqStatus;
		
		//CONTROLLER
		for(int i=0;i<7;i++)
		{
			if(AllModes.controllerModel.bankHasValues(i))
				ctrlSequenceRows[i] = DisplayGrid.FASTBLINK;
			else
				ctrlSequenceRows[i] = DisplayGrid.OFF;
		}
				
		//SEQUENCER
		//Light up the current sequence
		for(int i=0;i<8;i++)
		{
			//Slow blink the next sequence. Make the current seq solid
			if(i == AllModes.sequencer.nextSequence || i == AllModes.sequencer.curSequenceBank)
			{
				if(i == AllModes.sequencer.nextSequence)
					sequencerRows[i] = DisplayGrid.SLOWBLINK;
				if(i == AllModes.sequencer.curSequenceBank)
					sequencerRows[i] = DisplayGrid.SOLID;
			}
			else
				sequencerRows[i] = DisplayGrid.OFF;
		}
		
		//LOOPER
		for(int i=0;i<AllModes.looper.getNumLoops();i++)
		{
			if(AllModes.looper.isLoopPlaying(i))
				looperRows[i] = DisplayGrid.SOLID;
			else
				looperRows[i] = DisplayGrid.OFF;
		}
		
		//LOOP RECORDER
		for(int i=0;i<7;i++)
		{
			loopRecorderRows[i] = DisplayGrid.OFF;
			
			if(AllModes.loopRecorder.loopSequenceExists(i))
				loopRecorderRows[i] = DisplayGrid.FASTBLINK;
			
			if(AllModes.loopRecorder.getSeqStatus(i) == MonomeUp.PLAYING)
				loopRecorderRows[i] = DisplayGrid.SOLID;
		}

		//MELODY
		for(int i=0;i<8;i++)
		{
			seqStatus = AllModes.melody1Model.getSeqStatus(i);
			if(seqStatus == MonomeUp.STOPPED)
				melodyRows[i] = DisplayGrid.FASTBLINK;
			else if(seqStatus == MonomeUp.PLAYING)
				melodyRows[i] = DisplayGrid.SOLID;
			else melodyRows[i] = DisplayGrid.OFF;
			
			if(RECMODE == ModeConstants.MEL_QUANTIZED && mel1Cue[i])
				melodyRows[i] = DisplayGrid.SLOWBLINK;
		}
		
		//MELODY2
		for(int i=0;i<8;i++)
		{
			seqStatus = AllModes.melody2Model.getSeqStatus(i);
			if(seqStatus == MonomeUp.STOPPED)
				melody2Rows[i] = DisplayGrid.FASTBLINK;
			else if(seqStatus == MonomeUp.PLAYING)
				melody2Rows[i] = DisplayGrid.SOLID;
			else melody2Rows[i] = DisplayGrid.OFF;
			
			if(RECMODE == ModeConstants.MEL_QUANTIZED && mel2Cue[i])
				melody2Rows[i] = DisplayGrid.SLOWBLINK;
		}
		
		displayGrid[SEQUENCER_COL] = sequencerRows;
		displayGrid[LOCATOR_COL] = locatorRows;
		displayGrid[LOOPER_COL] = looperRows;
		displayGrid[LOOPRECORDER_COL] = loopRecorderRows;
		displayGrid[MELODY_COL] = melodyRows;
		displayGrid[melody2Col] = melody2Rows;
		displayGrid[CONTROLER_COL] = ctrlSequenceRows;
		
	}
	
	public void recordLocatorEvent()
	{
		//Begin a record mode (length of record shows by speed of steps)
		locatorMode = RECMODE;
		locatorRows = new int[8];
		locatorRows[0] = DisplayGrid.FASTBLINK;
	}
	
	public void secondLocatorEvent()
	{
		int currentStep = -1;
		
		//Step in current mode
		//Find the current step
		for(int i =0; i<locatorRows.length;i++)
			if(locatorRows[i]!=DisplayGrid.OFF)
				currentStep = i;
		
		//Only if the locator knows where the current step is do we step forward
		if(currentStep > -1)
		{	
			locatorRows = new int[8];
			if(locatorMode == PLAYMODE)
				locatorRows[(currentStep + 1) % 8] = DisplayGrid.SOLID;
			else if(locatorMode == RECMODE)
				locatorRows[(currentStep + 1) % 8] = DisplayGrid.FASTBLINK;
		}
	}
	
	public void firstLocatorEvent()
	{
		//Begin play for locator mode
		locatorMode = PLAYMODE;
		locatorRows = new int[8];
		locatorRows[0] = DisplayGrid.SOLID;
		
		//If cued, trigger melodizer start or stop
		for(int i=0;i<8;i++)
		{
			if(mel1Cue[i] == true)
			{
				if(AllModes.melody1Model.getSeqStatus(i) == MonomeUp.PLAYING)
					stopMel1Seq(i);
				else
					AllModes.melody1Model.playSeq(i);
				mel1Cue[i]=false;
				
			}
		
			if(mel2Cue[i] == true)
			{
				if(AllModes.melody2Model.getSeqStatus(i) == MonomeUp.PLAYING)
					stopMel2Seq(i);
				else
					AllModes.melody2Model.playSeq(i);
				
				mel2Cue[i] = false;
			}
		}
	}

}
