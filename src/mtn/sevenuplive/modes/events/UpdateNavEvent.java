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

package mtn.sevenuplive.modes.events;

public class UpdateNavEvent implements Event {

	private int slot;
	
	public static final String UPDATE_NAV_EVENT = "UPDATE_NAV_EVENT";
	
	public UpdateNavEvent() {}
	
	/**
	 * Event sent to cause a view to update their nav display.
	 * The current slot is given as a hint to the view as to which
	 * pattern slot is active
	 * @param slot slot to update or -1 means update All
	 */
	public UpdateNavEvent(int slot) {
		this.slot = slot;
	}
	
	public int getSlot() {
		return this.slot;
	}
	
	public String getType() {
		return UPDATE_NAV_EVENT;
	}
}
