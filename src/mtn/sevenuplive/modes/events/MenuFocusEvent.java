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

public class MenuFocusEvent implements Event {
	public enum eMenuFocusEvent {MENU_FOCUS_CHANGE_CUED, MENU_FOCUS_COMMITTED, MENU_FOCUS_CHANGE_ABORTED};
	
	public eMenuFocusEvent type;
	
	public static final String MENU_FOCUS_EVENT = "MENU_FOCUS_EVENT";
	
	public int oldIndex;
	public int newIndex;
	
	public MenuFocusEvent() {}
	
	public MenuFocusEvent(eMenuFocusEvent type, int oldIndex, int newIndex) {
		this.type = type;
		this.oldIndex = oldIndex;
		this.newIndex = newIndex;
	}
	
	public String toString() {
		return "MenuFocusEvent->" + type + " oldIndex:" + Integer.toString(oldIndex) + " newIndex:" + Integer.toString(newIndex);
	}

	public String getType() {
		return MENU_FOCUS_EVENT;
	}
}