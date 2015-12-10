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
 * Interface for constants shared 
 * between the different modes.
 */
public interface ModeConstants {

	////////////////////////////////////
	//Interface modes
	 
	public static final int STARTUP_MODE = -1;  
	public static final int PATTERN_MODE = 0;
	public static final int SEQ_MODE = 1;
	public static final int CONTROL_MODE = 2;
	public static final int LOOP_MODE = 3;
	public static final int LOOP_RECORD_MODE = 4;
	public static final int MELODY_MODE = 5;
	public static final int MELODY2_MODE = 6;
	public static final int MASTER_MODE = 7;
	public static final int SAMPLE_MODE = 31;
	public static final int CHOPPER_MODE = 41;
	public static final int MEL_ON_BUTTON_PRESS = 0;
	public static final int MEL_QUANTIZED = 1;
	
	/** Initial default value when there is no explicit default  */ 
	public static final int NOT_SET = -1;

}
