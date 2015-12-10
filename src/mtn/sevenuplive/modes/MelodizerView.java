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

import java.util.ArrayList;

import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.main.MonomeUp;
import mtn.sevenuplive.modes.MelodizerModel.GridPosition;
import mtn.sevenuplive.modes.events.ClearDisplayEvent;
import mtn.sevenuplive.modes.events.ClearNavEvent;
import mtn.sevenuplive.modes.events.Event;
import mtn.sevenuplive.modes.events.KeyTransposeGroupEvent;
import mtn.sevenuplive.modes.events.PositionTransposeGroupEvent;
import mtn.sevenuplive.modes.events.UpdateDisplayEvent;
import mtn.sevenuplive.modes.events.UpdateNavEvent;

public class MelodizerView extends Mode {
	
	/** The model the view depends on */
	private MelodizerModel model;

	public int curSeqBank = 0;
	
	public MelodizerView(int _navRow, int grid_width, int grid_height, MelodizerModel model) {
		super(_navRow, grid_width, grid_height);
		
		this.model = model;
		
		// Subscribe to the events we want to receive
		model.subscribe(new UpdateDisplayEvent(), this);
		model.subscribe(new UpdateNavEvent(), this);
		model.subscribe(new ClearDisplayEvent(), this);
		model.subscribe(new ClearNavEvent(), this);
		model.subscribe(new KeyTransposeGroupEvent(), this);
		model.subscribe(new PositionTransposeGroupEvent(), this);
		
		updateNavGrid();
		updateDisplayGrid();
	}
	
	public void onEvent(Event e) {
		
		if (e.getType().equals(UpdateDisplayEvent.UPDATE_DISPLAY_EVENT)) {
			UpdateDisplayEvent ude = (UpdateDisplayEvent)e;
			if (ude.getSlot() == curSeqBank || ude.getSlot() == -1) {
				updateDisplayGrid();
			} 
		} else if (e.getType().equals(UpdateNavEvent.UPDATE_NAV_EVENT)) {
			UpdateNavEvent une = (UpdateNavEvent)e;
			if (une.getSlot() == curSeqBank || une.getSlot() == -1) {
				updateNavGrid();
			}
		} else if (e.getType().equals(ClearDisplayEvent.CLEAR_DISPLAY_EVENT)) {
			clearDisplayGrid();
		} else if (e.getType().equals(ClearNavEvent.CLEAR_NAV_EVENT)) {
			clearNavGrid();
		} else if (e.getType().equals(KeyTransposeGroupEvent.KEY_TRANSPOSE_GROUP_EVENT)) {
			if (model.getCurrentMode() != MelodizerModel.eMelodizerMode.CLIP) {
				KeyTransposeGroupEvent ktge = (KeyTransposeGroupEvent)e;
				for (int seqbank = 0; seqbank < 7; seqbank++) {
					if (ktge.getGroup() == model.getTransposeGroup(seqbank)) {
						changeKey(false, seqbank, ktge.getKeyX(), ktge.getKeyY(), ktge.getVelocity());
					}
				}
			}
		} else if (e.getType().equals(PositionTransposeGroupEvent.POSITION_TRANSPOSE_GROUP_EVENT)) {
			if (model.getCurrentMode() != MelodizerModel.eMelodizerMode.CLIP) {
				PositionTransposeGroupEvent ptge = (PositionTransposeGroupEvent)e;
				for (int seqbank = 0; seqbank < 7; seqbank++) {
					if (ptge.getGroup() == model.getTransposeGroup(seqbank)) {
						changePosition(false, seqbank, ptge.getPosition(), ptge.getVelocity());
					}
				}
			}
		}  
	}
	
	public void updateDisplayGrid()
	{
		//System.out.println("Update melodizer display grid");
		clearDisplayGrid();

		if(model.currentMode == MelodizerModel.eMelodizerMode.KEYBOARD)
		{
			//Show keys
			displayGrid[0][7] = DisplayGrid.FASTBLINK; // C
			displayGrid[1][6] = DisplayGrid.FASTBLINK; // C#
			displayGrid[1][7] = DisplayGrid.FASTBLINK; // D
			displayGrid[2][6] = DisplayGrid.FASTBLINK; // D#
			displayGrid[2][7] = DisplayGrid.FASTBLINK; // E
			displayGrid[3][7] = DisplayGrid.FASTBLINK; // F
			displayGrid[4][6] = DisplayGrid.FASTBLINK; // F#
			displayGrid[4][7] = DisplayGrid.FASTBLINK; // G
			displayGrid[5][6] = DisplayGrid.FASTBLINK; // G#
			displayGrid[5][7] = DisplayGrid.FASTBLINK; // A
			displayGrid[6][6] = DisplayGrid.FASTBLINK; // A#
			displayGrid[6][7] = DisplayGrid.FASTBLINK; // B	

			//Set current key
			switch(model.key[curSeqBank])
			{
			case 0: displayGrid[0][7] = DisplayGrid.SOLID; // C
			break;
			case 1: displayGrid[1][6] = DisplayGrid.SOLID; // C#
			break;
			case 2: displayGrid[1][7] = DisplayGrid.SOLID; // D
			break;
			case 3: displayGrid[2][6] = DisplayGrid.SOLID; // D#
			break;
			case 4: displayGrid[2][7] = DisplayGrid.SOLID; // E
			break;
			case 5: displayGrid[3][7] = DisplayGrid.SOLID; // F
			break;
			case 6: displayGrid[4][6] = DisplayGrid.SOLID; // F#
			break;
			case 7: displayGrid[4][7] = DisplayGrid.SOLID; // G
			break;
			case 8: displayGrid[5][6] = DisplayGrid.SOLID; // G#
			break;
			case 9: displayGrid[5][7] = DisplayGrid.SOLID; // A
			break;
			case 10: displayGrid[6][6] = DisplayGrid.SOLID; // A#
			break;
			case 11: displayGrid[6][7] = DisplayGrid.SOLID; // B	
			break;
			}

		} else if (model.currentMode == MelodizerModel.eMelodizerMode.POSITION) {

			//Show keys
			displayGrid[0][7] = DisplayGrid.FASTBLINK; 
			displayGrid[1][7] = DisplayGrid.FASTBLINK; 
			displayGrid[2][7] = DisplayGrid.FASTBLINK; 
			displayGrid[3][7] = DisplayGrid.FASTBLINK; 
			displayGrid[4][7] = DisplayGrid.FASTBLINK; 
			displayGrid[5][7] = DisplayGrid.FASTBLINK; 
			displayGrid[6][7] = DisplayGrid.FASTBLINK; 	

			// Set current position
			switch(model.offset[curSeqBank])
			{
			case 0: displayGrid[0][7] = DisplayGrid.SOLID; 
			break;
			case 1: displayGrid[1][7] = DisplayGrid.SOLID; 
			break;
			case 2: displayGrid[2][7] = DisplayGrid.SOLID; 
			break;
			case 3: displayGrid[3][7] = DisplayGrid.SOLID; 
			break;
			case 4: displayGrid[4][7] = DisplayGrid.SOLID; 
			break;
			case 5: displayGrid[5][7] = DisplayGrid.SOLID;
			break;
			case 6: displayGrid[6][7] = DisplayGrid.SOLID; 
			break;
			case 7: displayGrid[7][7] = DisplayGrid.SOLID; 
			break;
			}
		}

		if(model.currentMode != MelodizerModel.eMelodizerMode.CLIP)
		{
			//Light this note if not in clipMode
			//Loop through entire grid.  Find each note value. Light it if it's held
			int noteStatus;
			for(int j=0;j<7;j++)
			{
				for(int k=0;k<8;k++)
				{
					noteStatus = model.displayNote[curSeqBank][model.convertGridPositionToNote(j, k, curSeqBank)];

					if (model.currentMode == MelodizerModel.eMelodizerMode.KEYBOARD && k < 7) {
						if(noteStatus != DisplayGrid.OFF)
							displayGrid[j][k] = noteStatus;
					} else if (model.currentMode == MelodizerModel.eMelodizerMode.POSITION && k < 8) {
						if(noteStatus != DisplayGrid.OFF)
							displayGrid[j][k] = noteStatus;
					} else if (model.currentMode == MelodizerModel.eMelodizerMode.NONE) {
						if(noteStatus != DisplayGrid.OFF)
							displayGrid[j][k] = noteStatus;
					}
				}
			}	
		}
		else 
		{
			//Display clip status based on incoming MIDI notes from Live
			int noteStatus;
			for(int j=0;j<7;j++)
			{
				for(int k=0;k<8;k++)
				{
					noteStatus = model.clipNotes[curSeqBank][model.convertGridPositionToClipNote(j, k)];
					if(noteStatus != DisplayGrid.OFF)
						displayGrid[j][k] = noteStatus;
				}
			}	
		}
	}
	
	public void updateNavGrid()
	{
		clearNavGrid();

		navGrid[myNavRow] = DisplayGrid.SOLID;

		if(model.isRecording(curSeqBank))
			navGrid[getYCoordFromSubMenu(curSeqBank)] = DisplayGrid.SLOWBLINK;
		else
			navGrid[getYCoordFromSubMenu(curSeqBank)] = DisplayGrid.FASTBLINK;
	}

	public void press(int x, int y, int velocity)
	{

		if(x == DisplayGrid.NAVCOL)
		{
			pressNavCol(y);
			updateNavGrid();
		}
		else
			pressDisplay(x,y, velocity);

		updateDisplayGrid();
	}

	public void release(int x, int y)
	{
		int melodyPitch;
		melodyPitch = model.convertGridPositionToNote(x, y, curSeqBank);
		model.heldNote[curSeqBank][melodyPitch] = false;
		if(model.currentMode != MelodizerModel.eMelodizerMode.CLIP)
			model.displayNote[curSeqBank][melodyPitch] = DisplayGrid.OFF;
		Note melodyRelease;
		melodyRelease = new Note(melodyPitch,0, 0);
		model.midiMelodyOut[curSeqBank].sendNoteOff(melodyRelease);
		model.addEvent(curSeqBank, melodyRelease);
		updateDisplayGrid();
	}

	private void pressNavCol(int y)
	{
		int pressedSeq = getSubMenuFromYCoord(y);

		//If they press the current bank and it is not already recording, begin cuing in that bank
		if (pressedSeq == curSeqBank && model.isRecording(curSeqBank) == false)
		{
			if(model.getSeqStatus(pressedSeq) == MonomeUp.PLAYING)
			{
				ArrayList<Note> noteList;
				Note note;
				noteList = model.sequences.get(pressedSeq).getHeldNotesAtPlayedPitch(pressedSeq);
				//Loop through heldnotes and send note off for each
				for(int i=0;i<noteList.size();i++)
				{
					note = noteList.get(i);
					model.midiMelodyOut[pressedSeq].sendNoteOff(note);
					if(pressedSeq == curSeqBank)
						model.displayNote[curSeqBank][note.getPitch()] = DisplayGrid.OFF;
				}
			}
			//Reset displayNote container
			model.setIsRecording(curSeqBank, true);
			model.resetDisplayNotes(curSeqBank);
			model.beginCue(pressedSeq);
			
		}
		//Stop recording
		else if(pressedSeq == curSeqBank && model.isRecording(curSeqBank) == true)
		{
			model.setIsRecording(curSeqBank, false);
			model.resetDisplayNotes(curSeqBank);
			model.endRecording(curSeqBank);
			
			//immediately begin playing the recorded sequence
			model.playSeq(pressedSeq);
		}
		else if(pressedSeq != curSeqBank)
		{
			curSeqBank = pressedSeq;
			
			//Reset displayNote container
			model.resetDisplayNotes(curSeqBank);
		}

		updateNavGrid();
	}

	/**
	 * Determines if the row represents a note row or not
	 * @param y
	 * @return 
	 */
	public boolean isNote(int y) {
		return (y < 6 && model.currentMode == MelodizerModel.eMelodizerMode.KEYBOARD) || 
		model.currentMode == MelodizerModel.eMelodizerMode.NONE || 
		model.currentMode == MelodizerModel.eMelodizerMode.CLIP || 
		(y < 7 && model.currentMode == MelodizerModel.eMelodizerMode.POSITION);
	}

	private void pressDisplay(int x, int y, int velocity)
	{
		//User is pressing a note button (as opposed to changing key)
		if (isNote(y))
		{
			//Calculate the pitch by the octave (y) and the transposed scale
			int melodyPitch;
			if (model.currentMode == MelodizerModel.eMelodizerMode.CLIP)
				melodyPitch = model.convertGridPositionToClipNote(x, y);
			else	
				melodyPitch = model.convertGridPositionToNote(x, y, curSeqBank);
			
			//System.out.println("Press note at " +  Integer.toString((x + offset[curSeqBank]) % melodyScale.Degrees.length) +  " degrees");
			
			Note melodySend;
			melodySend = new Note(melodyPitch,velocity, 0);
			model.heldNote[curSeqBank][melodyPitch] = true;
			if(model.currentMode != MelodizerModel.eMelodizerMode.CLIP)
				model.displayNote[curSeqBank][melodyPitch] = DisplayGrid.SOLID;
			model.midiMelodyOut[curSeqBank].sendNoteOn(melodySend);
			
			/* 
			 If we are in clip mode then we want to immediately send our matching noteOff in case
			 some instruments happen to be listening, otherwise we'd get a stuck note
			*/
			if (model.currentMode == MelodizerModel.eMelodizerMode.CLIP)
				model.midiMelodyOut[curSeqBank].sendNoteOff(melodySend);
			
			model.addEvent(curSeqBank, melodySend);
		}
		//User is pressing a button in the key area
		else if (model.currentMode == MelodizerModel.eMelodizerMode.KEYBOARD)
		{
			changeKey(true, curSeqBank, x, y, velocity); // Change directly
			
			// If we are in a group then send a group transpose event
			if (model.getTransposeGroup(curSeqBank) != -1) {
				AllModes.melody1Model.sendEvent(new KeyTransposeGroupEvent(model.getTransposeGroup(curSeqBank), x, y, velocity));
				AllModes.melody2Model.sendEvent(new KeyTransposeGroupEvent(model.getTransposeGroup(curSeqBank), x, y, velocity));
			}
		} else if (model.currentMode == MelodizerModel.eMelodizerMode.POSITION) {
			
			changePosition(true, curSeqBank, x, velocity); // Change directly
			
			// If we are in a group then send a group transpose event
			if (model.getTransposeGroup(curSeqBank) != -1) {
				AllModes.melody1Model.sendEvent(new PositionTransposeGroupEvent(model.getTransposeGroup(curSeqBank), x, velocity));
				AllModes.melody2Model.sendEvent(new PositionTransposeGroupEvent(model.getTransposeGroup(curSeqBank), x, velocity));
			}
		}
		
	}
	
	private void changeKey(boolean direct, int seqbank, int x, int y, int velocity) {
		// If we are already here then don't transpose further
		if (!direct && model.key[seqbank] == model.getKeyFromCoords( x, y))
			return;
		
		//User is changing keys
		for(int i=0; i<128;i++)
		{
			// If transposing we want the old pitch here
			if (model.transpose) 
				model.displayNote[seqbank][i] = DisplayGrid.OFF;
			
		}

		int curKeyValue = model.key[seqbank];

		//Set new key
		model.key[seqbank] = model.getKeyFromCoords( x, y);
		int keyDif = curKeyValue - model.key[seqbank];
		if (model.transpose) {
			model.transposeDirty[seqbank] = true;
			model.markStartTransposeOffsets(seqbank);
		}
		//Release notes from old key
		for(int i=0; i<128;i++)
		{ 
			if(model.heldNote[seqbank][i])
			{
				Note melodyRelease;
				melodyRelease = new Note(i,0, 0);
				model.midiMelodyOut[seqbank].sendNoteOff(melodyRelease);
				model.addEvent(seqbank, melodyRelease);
				model.heldNote[seqbank][i] = false;
				
				// If !transposing we want the new pitch here
				if (!model.transpose) 
					model.displayNote[seqbank][i] = DisplayGrid.OFF;
				
				if ((i-keyDif) >= 0)
					model.newHeldNote[seqbank][i-keyDif] = true;

				//System.out.println("  Killing " + i);
			}
		}
		
		//Send notes for new key
		for(int i=0; i<128;i++)
		{ 
			if(model.newHeldNote[seqbank][i])
			{		
				Note melodySend;
				melodySend = new Note(i, velocity, 0);
				model.midiMelodyOut[seqbank].sendNoteOn(melodySend);
				model.addEvent(seqbank, melodySend);
				model.heldNote[seqbank][i] = true;
				model.displayNote[seqbank][i] = DisplayGrid.SOLID;
				model.newHeldNote[seqbank][i] = false;
				//System.out.println("Sending " + i);
			}
		}
		
		if (!direct)
			updateDisplayGrid();
	}
	
	private void changePosition(boolean direct, int seqbank, int x, int velocity) {
		// If already here then do nothing
		if (!direct && model.offset[seqbank] == x)
			return;
		
		GridPosition[] oldPositions = new GridPosition[128]; 

		// Mark all old positions
		for(int i=0; i<128;i++)
		{
			oldPositions[i] = model.convertNoteToGridPosition(i, seqbank);
			
			// If transposing we want the old pitch here
			if (model.transpose) 
				model.displayNote[seqbank][i] = DisplayGrid.OFF;
			
		}

		//Set new offset
		model.offset[seqbank] = x;
		if (model.transpose) {
			model.transposeDirty[seqbank] = true;
			model.markStartTransposeOffsets(seqbank);
		}
		//Release notes from old offset
		for(int i=0; i<128;i++)
		{ 
			if(model.heldNote[seqbank][i])
			{
				Note melodyRelease;
				melodyRelease = new Note(i,0, 0);
				model.midiMelodyOut[seqbank].sendNoteOff(melodyRelease);
				model.addEvent(seqbank, melodyRelease);
				model.heldNote[seqbank][i] = false;
				
				// If !transposing we want the new pitch here
				if (!model.transpose)
					model.displayNote[seqbank][i] = DisplayGrid.OFF;
				
				// Can't compute if we don't know the original position
				if (oldPositions[i] != null) { 
					// Because offset has changed, new note will be a different note from same grid position
					int newNote = model.convertGridPositionToNote(oldPositions[i].x, oldPositions[i].y, seqbank);
					model.newHeldNote[seqbank][newNote] = true;
				}
			}
		}

		//Send notes for new key
		for(int i=0; i<128;i++)
		{ 
			if(model.newHeldNote[seqbank][i])
			{		
				Note melodySend;
				melodySend = new Note(i, velocity, 0);
				model.midiMelodyOut[seqbank].sendNoteOn(melodySend);
				model.addEvent(seqbank, melodySend);
				model.heldNote[seqbank][i] = true;
				model.displayNote[seqbank][i] = DisplayGrid.SOLID;
				model.newHeldNote[seqbank][i] = false;
			}
		}
		
		if (!direct)
			updateDisplayGrid();
	}

}
