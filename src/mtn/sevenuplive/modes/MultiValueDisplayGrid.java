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

public class MultiValueDisplayGrid extends DisplayGrid {

	public MultiValueDisplayGrid(Monome monome, AllModes allmodes,
			int start_column, int start_row, int grid_width, int grid_height,
			Mode defaultMode, int grid_index, int totalGrids) {
		super(monome, allmodes, start_column, start_row, grid_width, grid_height,
				defaultMode, grid_index, totalGrids);
	}
	
	/**
	 * Override to use multiple color or intensity values rather than blinking
	 */
	@Override
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
					if(force || monome.getValue(x+start_column, y+start_row) != 1)
						monome.setValue(x+start_column, y+start_row, 1);

					break;

				case 2:   
					if(force || monome.getValue(x+start_column, y+start_row) != 2)
						monome.setValue(x+start_column, y+start_row, 2);
					break;
				case 1:
					if(force || frmCount % SLOWBLINKFRAME == 0) 
					{
						monome.setValue(x+start_column, y+start_row, monome.isLit(x+start_column, y+start_row) ? 0 : 3);
					}
					break;
				}      
			}
		}
	
	}

	/**
	 * Override to use multiple color or intensity values rather than blinking
	 */
	@Override
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
				if(force || monome.getValue(NAVCOL+start_column, y+start_row) != 1)
					monome.setValue(NAVCOL+start_column, y+start_row, 1);
				break;

			case 2:   
				if(force || monome.getValue(NAVCOL+start_column, y+start_row) != 2)
					monome.setValue(NAVCOL+start_column, y+start_row, 2);
				break;
			case 1:
				if(force || frmCount % SLOWBLINKFRAME == 0) 
				{
					monome.setValue(NAVCOL+start_column, y+start_row, monome.isLit(NAVCOL+start_column, y+start_row) ? 0 : 3);
				}
				break; 
			}
		}
	}
}
