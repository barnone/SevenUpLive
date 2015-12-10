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

import mtn.sevenuplive.modes.events.ClearDisplayEvent;
import mtn.sevenuplive.modes.events.ClearNavEvent;
import mtn.sevenuplive.modes.events.Event;
import mtn.sevenuplive.modes.events.UpdateDisplayEvent;

/***
 * The patternizer view allows different windows into the same patternizer.  Each grid can view a different pattern at the same time.
 * @author Adam Ribaudo
 */
public class PatternizerView extends Mode {
	
	private int pressedNavButtons[];
	public int selectedPattern = 0;
	private PatternizerModel patternizerModel;

	public PatternizerView(int _navRow, int grid_width, int grid_height, PatternizerModel patternizerModel) {
		super(_navRow, grid_width, grid_height);
		
		pressedNavButtons = new int[8];
		this.patternizerModel = patternizerModel;
		
		// Subscribe to the events we want to receive
		patternizerModel.subscribe(new UpdateDisplayEvent(), this);
		patternizerModel.subscribe(new ClearDisplayEvent(), this);
		patternizerModel.subscribe(new ClearNavEvent(), this);
		
		updateDisplayGrid();
	}
	
	public void onEvent(Event e) {
		
		if (e.getType().equals(UpdateDisplayEvent.UPDATE_DISPLAY_EVENT)) {
			UpdateDisplayEvent ude = (UpdateDisplayEvent)e;
			if (ude.getSlot() == selectedPattern || ude.getSlot() == -1) {
				
				updateDisplayGrid();
			} 
		} else if (e.getType().equals(ClearDisplayEvent.CLEAR_DISPLAY_EVENT)) {
			clearDisplayGrid();
		} else if (e.getType().equals(ClearNavEvent.CLEAR_NAV_EVENT)) {
			clearNavGrid();
		} 
	}


	public void updateDisplayGrid()
	{
		//Update navcol
		super.clearNavGrid();
		navGrid[getYCoordFromSubMenu(selectedPattern)] = DisplayGrid.FASTBLINK;
		navGrid[myNavRow] = DisplayGrid.SOLID;
		
		//Update display grid
		displayGrid = patternizerModel.patternGrids[selectedPattern];
	}
	
	public void press(int x, int y)
	{
		if(x == DisplayGrid.NAVCOL)
			pressNavCol(y);
		else
		{
			//If pressed, change from off to fast, or fast to solid, or solid to off
			 if (patternizerModel.patternGrids[selectedPattern][x][y] == DisplayGrid.OFF)
				 patternizerModel.patternGrids[selectedPattern][x][y] = DisplayGrid.FASTBLINK;
			 else if (patternizerModel.patternGrids[selectedPattern][x][y] == DisplayGrid.FASTBLINK)
				 patternizerModel.patternGrids[selectedPattern][x][y] = DisplayGrid.SOLID;
			 else if (patternizerModel.patternGrids[selectedPattern][x][y] == DisplayGrid.SOLID)
				 patternizerModel.patternGrids[selectedPattern][x][y] = DisplayGrid.OFF;
		}
	}
	
	public void release(int x, int y)
	{
		pressedNavButtons[y] = 0;
	}
	
	private void pressNavCol(int y)
	{
		pressedNavButtons[y] = 1;

		//Handle copying a pattern to another bank
   	 	if (getSubMenuFromYCoord(y) != selectedPattern && pressedNavButtons[getYCoordFromSubMenu(selectedPattern)] >=1 )
   	 	{
   	 		System.out.println("Copying " + selectedPattern + " to " + getSubMenuFromYCoord(y));
   			 //Copy pattern
   		 	for(int i=0;i<7;i++)
   		 		for(int j=0;j<8;j++)
   		 			patternizerModel.patternGrids[getSubMenuFromYCoord(y)][i][j] = patternizerModel.patternGrids[selectedPattern][i][j];
   	 	}
   	  
		//If they are changing patterns unselect current and select the new pattern
		if(selectedPattern != getSubMenuFromYCoord(y))
		{
			navGrid[getYCoordFromSubMenu(selectedPattern)] = DisplayGrid.OFF;
			selectedPattern = getSubMenuFromYCoord(y);
	        updateDisplayGrid();
		}
	}
	
	public void triggerButtonHeld(int x, int y)
	{
		patternizerModel.clearPattern(getSubMenuFromYCoord(y));
		selectedPattern = getSubMenuFromYCoord(y);
		updateDisplayGrid();
	}
}
