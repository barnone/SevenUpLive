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

import jklabs.monomic.Monome;
import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.modes.events.MenuFocusEvent;

public class DisplayGrid {

	protected int start_column;
	protected int start_row;
	protected int grid_width;
	protected int grid_height;
	protected int grid_index;
	protected int totalGrids;

	protected int curDisplayGrid[][];
	protected int navGrid[];
	protected int pressedButtonsLength[][];

	public static final int NAVCOL = 7;

	///////////////////////////////////////// 
	//Blink-speed members
	/////////////////////////////////////////
	protected static final int FRAMES = 10;
	public static final int FASTBLINK = 2;
	public static final int SLOWBLINK = 1;
	public static final int SOLID = 3;
	public static final int OFF = 0;
	public static final int UNDEFINED = -1;
	protected static final  int SLOWBLINKFRAME = 10;
	protected static final int FASTBLINKFRAME = 1;

	////////////////////////////////////
	//Interface modes
	////////////////////////////////////
	protected int menuLevel;
	protected final static int MAINMENU = 0;
	protected final static int SUBMENU = 1;

	protected int curMode;
	protected int frmCount;

	protected Monome monome;
	protected AllModes allmodes;
	
	protected Mode defaultMode;

	public DisplayGrid(Monome monome, AllModes allmodes, int start_column, int start_row, int grid_width, int grid_height, Mode defaultMode, int grid_index, int totalGrids) {
		this.start_row = start_row;
		this.start_column = start_column;
		this.grid_width = grid_width;
		this.grid_height = grid_height;
		this.grid_index = grid_index;
		this.totalGrids = totalGrids;
		
		this.monome = monome;
		this.allmodes = allmodes;
		menuLevel = SUBMENU;

		//Pressed buttons
		pressedButtonsLength = new int[grid_width][grid_height];

		this.defaultMode = defaultMode;
		
		curDisplayGrid = AllModes.startup.getDisplayGrid();
		navGrid = AllModes.startup.getNavGrid();
		curMode = AllModes.startup.getMyNavRow();
	}

	public Mode getDefaultMode() {
		return defaultMode;
	}

	public int translateX(int x) {
		return x-start_column;
	}

	public int translateY(int y) {
		return y-start_row;
	}

	public boolean hitTest(int x, int y) {
		if (x >= start_column && x < start_column + grid_width) {
			if (y >= start_row && y < start_row + grid_height)
				return true;
		}
		return false;
	}
	
	protected void checkForStartup() {
		
		//Very fast int compare here to not slow us down
		if (curMode == StartupMode.STARTUP_MODE) {
			
			if (AllModes.startup.isFinished()) {
				// If we press a key in startup mode, then change to default mode
				curDisplayGrid = defaultMode.getDisplayGrid();
				navGrid = defaultMode.getNavGrid();
				curMode = defaultMode.getMyNavRow();
				return; // don't do anything else
			}
			
			AllModes.startup.nextSequence();
		}
			
	}

	/**
	 * Force will draw even if internal state does not indicate needed
	 * @param force
	 */
	protected void drawDisplay(boolean force) {
		//draw the display grid
		for (int x = 0; x<grid_width-1;x++)
		{
			for (int y = 0; y<grid_height;y++)
			{
				switch(curDisplayGrid[x][y])
				{
				case 0:
					if(force || monome.isLit(x+start_column, y+start_row))
						monome.setValue(x+start_column, y+start_row, 0);
					break;
				case 3:  
					if(force || !monome.isLit(x+start_column, y+start_row))
						monome.setValue(x+start_column, y+start_row, 1);

					break;

				case 2:   
					if(force || frmCount % FASTBLINKFRAME == 0) 
					{
						monome.setValue(x+start_column, y+start_row, !monome.isLit(x+start_column, y+start_row));
					} 
					break;
				case 1:
					if(force || frmCount % SLOWBLINKFRAME == 0) 
					{
						monome.setValue(x+start_column, y+start_row, !monome.isLit(x+start_column, y+start_row));
					} 
					break;

				}      
			}
		}
	
	}
	
	/**
	 * Force will draw even if internal state does not indicate needed
	 * @param force
	 */
	protected void drawNav(boolean force) {
		//Draw navbar
		for (int y = 0; y<grid_height;y++)
		{
			switch(navGrid[y])
			{
			case 0:
				if(force || monome.isLit(NAVCOL+start_column, y+start_row))
					monome.setValue(NAVCOL+start_column, y+start_row, 0);
				break;
			case 3:  
				if(force || !monome.isLit(NAVCOL+start_column, y+start_row))
					monome.setValue(NAVCOL+start_column, y+start_row, 1);

				break;

			case 2:   
				if(force || frmCount % FASTBLINKFRAME == 0) 
				{
					monome.setValue(NAVCOL+start_column, y+start_row, !monome.isLit(NAVCOL+start_column, y+start_row));
				} 
				break;
			case 1:
				if(force || frmCount % SLOWBLINKFRAME == 0) 
				{
					monome.setValue(NAVCOL+start_column, y+start_row, !monome.isLit(NAVCOL+start_column, y+start_row));
				} 
				break;
			}
		}
	}
	
	public void draw(boolean force)
	{
		//Very fast int compare here to not slow us down
		if (curMode == StartupMode.STARTUP_MODE)
			checkForStartup();
		
		drawDisplay(force);
		drawNav(force);
		
		//Loop through pressedNotesLength and increment the number of frames each pressed note has been held
		for(int i=0;i<grid_width;i++)
			for(int j=0;j<grid_height;j++)
			{
				if(pressedButtonsLength[i][j] >= 1)
				{
					pressedButtonsLength[i][j]++;
					//if the button has been held longer than 4 seconds then trigger a held event
						
					triggerButtonHeld(i, j, pressedButtonsLength[i][j]);
				}
			}

		frmCount++;
		if (frmCount == FRAMES) frmCount = 0;
	}

	public void monomeAfterTouch(int x, int y, float value) {
		// @TODO record aftertouch if possible
	}

	public void monomePressed(int x, int y) {
		monomeXPressed(x, y, Note.DEFAULT_VELOCITY);
	}
	
	public void monomeXPressed(int x, int y, int velocity) {
		
		// Very fast int compare here to not slow us down
		if (curMode == StartupMode.STARTUP_MODE) {
			// If we press a key in startup mode, then change to default mode
			curDisplayGrid = defaultMode.getDisplayGrid();
			navGrid = defaultMode.getNavGrid();
			curMode = defaultMode.getMyNavRow();
			return; // don't do anything else
		}
		
		//Flag current button as pressed
	    pressedButtonsLength[x][y] = 1;
	   
		//Handle pressing navbar button
		if (x == NAVCOL)
		{
			if(menuLevel == MAINMENU)
			{
				//If they hit the same menu mode then move back to submenu
				if (y == curMode)
				{
					navGrid = new int[grid_height];
					navGrid[curMode] = DisplayGrid.SOLID;
					menuLevel = SUBMENU;

					if (curMode == ModeConstants.PATTERN_MODE) {
						navGrid = allmodes.getPatternizerView(grid_index).getNavGrid();
					} else if (curMode == ModeConstants.CONTROL_MODE) {
						navGrid = allmodes.getControllerView(grid_index).getNavGrid();
					} else if (curMode == ModeConstants.SEQ_MODE) {
						navGrid = allmodes.getSequencer().getNavGrid();
					} else if (curMode == ModeConstants.LOOP_MODE) {
						navGrid = allmodes.getLooper().getNavGrid();
					} else if (curMode == ModeConstants.LOOP_RECORD_MODE) {
						navGrid = allmodes.getLoopRecorder().getNavGrid();
					} else if (curMode == ModeConstants.MELODY_MODE) {
						// Send focus change event
						allmodes.getMelodizer1Model().sendEvent(new MenuFocusEvent(MenuFocusEvent.eMenuFocusEvent.MENU_FOCUS_CHANGE_ABORTED, curMode, curMode));
						navGrid = allmodes.getMelodizer1View(grid_index).getNavGrid();
					} else if (curMode == ModeConstants.MELODY2_MODE) {
						// Send focus change event
						allmodes.getMelodizer2Model().sendEvent(new MenuFocusEvent(MenuFocusEvent.eMenuFocusEvent.MENU_FOCUS_CHANGE_ABORTED, curMode, curMode));
						navGrid = allmodes.getMelodizer2View(grid_index).getNavGrid();
					}	
				}
				//Else they are changing modes
				else
				{
					navGrid = new int[8];
					int oldMode = curMode;
					curMode = y;
					menuLevel = SUBMENU;
					
					if(y == ModeConstants.PATTERN_MODE)
					{
						curDisplayGrid = allmodes.getPatternizerView(grid_index).getDisplayGrid();
						navGrid = allmodes.getPatternizerView(grid_index).getNavGrid();
					}
					else if(y == ModeConstants.CONTROL_MODE)
					{
						curDisplayGrid = allmodes.getControllerView(grid_index).getDisplayGrid();
						navGrid = allmodes.getControllerView(grid_index).getNavGrid();
					}
					else if(y == ModeConstants.SEQ_MODE)
					{
						curDisplayGrid = allmodes.getSequencer().getDisplayGrid();
						navGrid = allmodes.getSequencer().getNavGrid();
					}
					else if(y == ModeConstants.LOOP_MODE)
					{
						allmodes.getLooper().updateDisplayGrid();
						curDisplayGrid = allmodes.getLooper().getDisplayGrid();
						navGrid = allmodes.getLooper().getNavGrid();
					}
					else if(y == ModeConstants.LOOP_RECORD_MODE)
					{
						allmodes.getLoopRecorder().updateDisplayGrid();
						allmodes.getLoopRecorder().updateNavGrid();
						curDisplayGrid = allmodes.getLoopRecorder().getDisplayGrid();
						navGrid = allmodes.getLoopRecorder().getNavGrid();
					}
					else if(y == ModeConstants.MELODY_MODE)
					{
						allmodes.getMelodizer1Model().sendEvent(new MenuFocusEvent(MenuFocusEvent.eMenuFocusEvent.MENU_FOCUS_COMMITTED, oldMode, curMode));
						allmodes.getMelodizer1View(grid_index).updateDisplayGrid();
						curDisplayGrid = allmodes.getMelodizer1View(grid_index).getDisplayGrid();
						navGrid = allmodes.getMelodizer1View(grid_index).getNavGrid();
					}
					else if(y == ModeConstants.MELODY2_MODE)
					{
						allmodes.getMelodizer2Model().sendEvent(new MenuFocusEvent(MenuFocusEvent.eMenuFocusEvent.MENU_FOCUS_COMMITTED, oldMode, curMode));
						allmodes.getMelodizer2View(grid_index).updateDisplayGrid();
						curDisplayGrid = allmodes.getMelodizer2View(grid_index).getDisplayGrid();
						navGrid = allmodes.getMelodizer2View(grid_index).getNavGrid();
					}
					else if(y == ModeConstants.MASTER_MODE)
					{
						allmodes.getMasterizer().updateDisplayGrid();
						curDisplayGrid = allmodes.getMasterizer().getDisplayGrid();
						navGrid = allmodes.getMasterizer().getNavGrid();
					}
				}
			}
			else if(menuLevel == SUBMENU)
			{
				//if they hit the curMenuPoint button again, change back main menu
				if (y == curMode)
				{
					navGrid = new int[grid_height];
					navGrid[curMode] = DisplayGrid.SOLID;
					menuLevel = MAINMENU;
					
					
					if (y == ModeConstants.MELODY_MODE)
					{
						allmodes.getMelodizer1Model().sendEvent(new MenuFocusEvent(MenuFocusEvent.eMenuFocusEvent.MENU_FOCUS_CHANGE_CUED, curMode, curMode));
					}
					else if (y == ModeConstants.MELODY2_MODE)
					{
						allmodes.getMelodizer1Model().sendEvent(new MenuFocusEvent(MenuFocusEvent.eMenuFocusEvent.MENU_FOCUS_CHANGE_CUED, curMode, curMode));
					}
				}
				//Else they are moving between sub-menu items
				else
				{
					if(curMode == ModeConstants.PATTERN_MODE)
					{
						allmodes.getPatternizerView(grid_index).press(x, y);
						curDisplayGrid = allmodes.getPatternizerView(grid_index).getDisplayGrid();
					}
					else if(curMode == ModeConstants.CONTROL_MODE)
					{
						allmodes.getControllerView(grid_index).press(x, y);
					}
					else if(curMode == ModeConstants.SEQ_MODE)
					{
						allmodes.getSequencer().press(x, y);
						curDisplayGrid = allmodes.getSequencer().getDisplayGrid();
					}
					else if(curMode == ModeConstants.LOOP_MODE)
					{
						allmodes.getLooper().press(x, y);
					}
					else if(curMode == ModeConstants.LOOP_RECORD_MODE)
					{
						allmodes.getLoopRecorder().press(x, y);
					}
					else if(curMode == ModeConstants.MELODY_MODE)
					{
						allmodes.getMelodizer1View(grid_index).press(x, y, velocity);
					}
					else if(curMode == ModeConstants.MELODY2_MODE)
					{
						allmodes.getMelodizer2View(grid_index).press(x, y, velocity);
					}
					else if(curMode == ModeConstants.MASTER_MODE)
						allmodes.getMasterizer().press(x, y);
				}
			}
		}

		////////////////////////////////////
		//Handle pressing the displayed grid
		////////////////////////////////////
		else
		{
			if (curMode == ModeConstants.PATTERN_MODE)
			{
				allmodes.getPatternizerView(grid_index).press(x, y);
			}
			else if(curMode == ModeConstants.CONTROL_MODE)
			{
				allmodes.getControllerView(grid_index).press(x, y);
				for(int i=0;i < totalGrids;i++ )
					allmodes.getControllerView(i).updateDisplayGrid();
			}
			// seq mode
			else if(curMode == ModeConstants.SEQ_MODE)
			{
				allmodes.getSequencer().press(x, y);
			}
			else if(curMode == ModeConstants.LOOP_MODE)
				allmodes.getLooper().press(x, y);
			else if(curMode == ModeConstants.LOOP_RECORD_MODE)
				allmodes.getLoopRecorder().press(x, y);
			else if (curMode == ModeConstants.MELODY_MODE)
			{
				allmodes.getMelodizer1View(grid_index).press(x, y, velocity);
				for(int i=0;i < totalGrids;i++ )
					allmodes.getMelodizer1View(i).updateDisplayGrid();
			}
			else if (curMode == ModeConstants.MELODY2_MODE)
			{
				allmodes.getMelodizer2View(grid_index).press(x, y, velocity);
				for(int i=0;i < totalGrids;i++ )
					allmodes.getMelodizer2View(i).updateDisplayGrid();
			}
			else if(curMode == ModeConstants.MASTER_MODE)
				allmodes.getMasterizer().press(x, y);
		}
	}

	public void monomeReleased(int x, int y) {

		if(curMode == ModeConstants.PATTERN_MODE)
		{
			allmodes.getPatternizerView(grid_index).release(x, y);
		}
		//If user releases within the melodizer play area
		else if(curMode == ModeConstants.MELODY_MODE && x != NAVCOL)
		{
			if(allmodes.getMelodizer1View(grid_index).isNote(y))
				allmodes.getMelodizer1View(grid_index).release(x, y);
			for(int i=0;i < totalGrids;i++ )
				allmodes.getMelodizer1View(i).updateDisplayGrid();
		}
		else if(curMode == ModeConstants.MELODY2_MODE && x != NAVCOL)
		{
			if(y<6 || allmodes.getMelodizer2View(grid_index).isNote(y))
				allmodes.getMelodizer2View(grid_index).release(x, y);
			for(int i=0;i < totalGrids;i++ )
				allmodes.getMelodizer2View(i).updateDisplayGrid();
		}
		else if (curMode == ModeConstants.LOOP_MODE && x != NAVCOL) {
			allmodes.getLooper().release(x, y);
		}

		triggerButtonReleased(x, y);
	}

	public void triggerButtonHeld(int x, int y, int length)
	{
		if(x == NAVCOL && y != curMode)
		{
			if (curMode == ModeConstants.PATTERN_MODE) {
				if(length >= 4 * FRAMES)
				{
					allmodes.getPatternizerView(grid_index).triggerButtonHeld(x, y);
					curDisplayGrid = allmodes.getPatternizerView(grid_index).getDisplayGrid();
					pressedButtonsLength[x][y] = 0;
				}	
			} else if (curMode == ModeConstants.CONTROL_MODE) {
				if(length >= 1 * FRAMES)
				{
					allmodes.getControllerView(grid_index).triggerButtonHeld(x, y);
					curDisplayGrid = allmodes.getControllerView(grid_index).getDisplayGrid();
				}
			}
		}
	}
	
	public void triggerButtonReleased(int x, int y) {
		pressedButtonsLength[x][y] = 0;
		
		if(x == NAVCOL && y != curMode)
		{
			// @TODO the trigger functions should be refactored to an interface
			if (curMode == ModeConstants.CONTROL_MODE) {
				allmodes.getControllerView(grid_index).triggerButtonReleased(x, y);
			}
		}
	}
	
	
	public void displayCursor() {
		
		//Only show the beat blips in current pattern mode
        if ((curMode == ModeConstants.PATTERN_MODE && allmodes.getSequencer().isPatternPlaying(allmodes.getPatternizerView(grid_index).selectedPattern)) )
        {
        	for (int x = start_column; x < start_column + grid_width; x++) {
        		if (monome.isLit(x, AllModes.patternizerModel.curPatternRow + start_row)) { 
        			monome.setValue(x, AllModes.patternizerModel.curPatternRow + start_row, 0);  
        		} else {
        			monome.setValue(x, AllModes.patternizerModel.curPatternRow + start_row, 1);
        		}
        	}
        	// @TODO clloyd fix this 
        	//monome.invertRowByte(allmodes.getPatternizer().curPatternRow + start_row, Math.abs(start_column / 8)); 
        }
        else if(curMode == ModeConstants.SEQ_MODE && allmodes.getPatternizerModel().curPatternRow % 4 == 0)
        {
        	for (int x = start_column; x < start_column + grid_width; x++) {
        		if (monome.isLit(x, AllModes.sequencer.curSeqRow + start_row)) { 
        			monome.setValue(x, AllModes.sequencer.curSeqRow + start_row, 0);  
        		} else {
        			monome.setValue(x, AllModes.sequencer.curSeqRow + start_row, 1);
        		}
        	}
        	//@TODO clloyd fix this
        	//monome.invertRowByte(allmodes.getSequencer().curSeqRow + start_row, Math.abs(start_column / 8)); 	
        }
        
        if(curMode == ModeConstants.MASTER_MODE)
        	allmodes.getMasterizer().updateDisplayGrid();
	}
	
	public int getGridIndex()
	{
		return grid_index;
	}
	

}
