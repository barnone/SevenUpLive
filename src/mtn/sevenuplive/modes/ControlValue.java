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

/**
 * Class for control values that have an ID and value assigned
 * @author Adam Ribaudo
 *
 */
public class ControlValue {
	
		private int id; //Ctrl ID
		private int value; //Ctrl value
		
		public ControlValue(int id, int value)
		{
			this.id = id;
			this.value = value;
		}
		
		public int getId(){
			return this.id;
		}
		
		public int getValue(){
			return this.value;
		}
	
}
