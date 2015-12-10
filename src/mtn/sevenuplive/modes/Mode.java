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
import mtn.sevenuplive.modes.events.EventDispatcherImpl;
import mtn.sevenuplive.modes.events.EventListener;


public abstract class Mode extends EventDispatcherImpl implements ModeConstants, EventListener {
	
	protected int myNavRow;
	protected int displayGrid[][];
	protected int navGrid[];
	
	protected int grid_width;
	protected int grid_height;
	
	/**
	 * Abstract class for each mode
	 * @param navRow The row to use for navigation
	 * @param the mode  
	 * @param grid_width total width of the monome grid
	 * @param grid_height total height of the monome grid
	 */
	public Mode(int navRow, int grid_width, int grid_height)
	{
		this.grid_width = grid_width;
		this.grid_height = grid_height;
		
		this.myNavRow = navRow;
		displayGrid = new int[grid_width - 1][grid_height];
		navGrid = new int[grid_height];
		
		// startup uses -1 as nav row
		if (navRow > 0 && navRow < grid_height)
			navGrid[myNavRow] = DisplayGrid.SOLID;
		
	}
	
	public int getMyNavRow() {
		return myNavRow;
	}

	public int[][] getDisplayGrid()
	{
		return displayGrid;
	}
	
	public int[] getNavGrid()
	{
		return navGrid;
	}
	
	/**
	 * Must override
	 */
	public abstract void onEvent(Event e);
	
	protected void clearDisplayGrid()
	{
		for(int i=0;i<displayGrid.length;i++)
			for(int j=0;j<displayGrid[0].length;j++)
				displayGrid[i][j] = DisplayGrid.OFF;
	}
	
	protected void clearNavGrid()
	{
		for(int i=0;i<navGrid.length;i++)
			navGrid[i] = DisplayGrid.OFF;
	}
	
	protected int getYCoordFromSubMenu(int subMenuNum)
	{
		if(subMenuNum >= myNavRow)
			return subMenuNum + 1;
		else return subMenuNum;
	}
	
	protected int getSubMenuFromYCoord(int ycoord)
	{
		if(ycoord >= myNavRow)
			return ycoord - 1;
		else return ycoord;
	}

}
