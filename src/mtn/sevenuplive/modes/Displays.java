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

public class Displays {

	public static GridCoordinateTarget translate(DisplayGrid[] displays, int x, int y) {
		for (DisplayGrid grid: displays) {
			if (grid.hitTest(x, y)) {
				return new GridCoordinateTarget(grid, grid.translateX(x), grid.translateY(y));
			}
		}
		return null;
	}
	
	public static class GridCoordinateTarget {

		public GridCoordinateTarget(DisplayGrid display, int x_translated, int y_translated) {
			this.display = display;
			this.x_translated = x_translated;
			this.y_translated = y_translated;
		}
		
		public DisplayGrid getDisplay() {
			return display;
		}

		public void setDisplay(DisplayGrid display) {
			this.display = display;
		}

		public int getX_translated() {
			return x_translated;
		}

		public void setX_translated(int x_translated) {
			this.x_translated = x_translated;
		}

		public int getY_translated() {
			return y_translated;
		}

		public void setY_translated(int y_translated) {
			this.y_translated = y_translated;
		}

		private DisplayGrid display;
		private int x_translated;
		private int y_translated;
		
	}
}
