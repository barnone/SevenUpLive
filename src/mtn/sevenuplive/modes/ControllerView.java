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

import mtn.sevenuplive.modes.events.Event;
import mtn.sevenuplive.modes.events.UpdateDisplayEvent;
import mtn.sevenuplive.modes.events.UpdateNavEvent;

public class ControllerView extends Mode {

	private int curBank = 0;
	private ControllerModel controllerModel;
	
	public ControllerView(int _navRow,int grid_width, int grid_height, ControllerModel controllerModel) {
		super(_navRow, grid_width, grid_height);
		this.controllerModel = controllerModel;
		
		// Subscribe to the events we want to receive
		controllerModel.subscribe(new UpdateDisplayEvent(), this);
		controllerModel.subscribe(new UpdateNavEvent(), this);
		
	    updateDisplayGrid();
	    updateNavGrid();
	}
	
	public void onEvent(Event e) {
		if (e.getType().equals(UpdateDisplayEvent.UPDATE_DISPLAY_EVENT)) {
			updateDisplayGrid(); 
		} else if (e.getType().equals(UpdateNavEvent.UPDATE_NAV_EVENT)) {
			updateNavGrid(); 
		}
	}
	
	public void updateDisplayGrid()
	{
		super.clearDisplayGrid();
		//Loop through the controls in a control bank and set the y coordinate on the display grid
		for(int i=0;i<7;i++)
		{
			//Only set the display if the control is > 0
			if(controllerModel.controls[curBank][i] > 0)
				for(int j=7;j>=8-controllerModel.controls[curBank][i];j--)
					displayGrid[i][j] = DisplayGrid.SOLID;
		}
	}
	
	private void updateNavGrid()
	{
		clearNavGrid();
		navGrid[myNavRow] = DisplayGrid.SOLID;
		navGrid[getYCoordFromSubMenu(curBank)] = DisplayGrid.FASTBLINK;
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
	public void press(int x, int y)
	{
		if(x == DisplayGrid.NAVCOL)
			pressNavCol(y);
		else
			controllerModel.press(x, y, curBank);
		
		updateDisplayGrid();
		updateNavGrid();
	}
	
	private void pressNavCol(int y) {
		//If changing to a different sequence
		if(curBank != getSubMenuFromYCoord(y))
			curBank = getSubMenuFromYCoord(y);

	}
	
	public void triggerButtonHeld(int x, int y)
	{
		controllerModel.holdBank(getSubMenuFromYCoord(y));
	}
	
	public void triggerButtonReleased(int x, int y)
	{
		controllerModel.releaseBank(getSubMenuFromYCoord(y));
	}
	
	
}
