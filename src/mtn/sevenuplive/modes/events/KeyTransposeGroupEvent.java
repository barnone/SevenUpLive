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

public class KeyTransposeGroupEvent implements Event {
	
	public static final String KEY_TRANSPOSE_GROUP_EVENT = "KEY_TRANSPOSE_GROUP_EVENT";

	private int group;
	private int key_x;
	private int key_y;
	private int velocity;
	
	public int getGroup() {
		return group;
	}

	public int getKeyX() {
		return key_x;
	}
	
	public int getKeyY() {
		return key_y;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public KeyTransposeGroupEvent() {}
		
	public KeyTransposeGroupEvent(int group, int key_x, int key_y, int velocity) {
		this.group = group;
		this.key_x = key_x;
		this.key_y = key_y;
	}
	
	public String getType() {
		return KEY_TRANSPOSE_GROUP_EVENT;
	}

}
