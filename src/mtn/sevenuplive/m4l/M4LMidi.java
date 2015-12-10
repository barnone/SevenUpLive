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

package mtn.sevenuplive.m4l;

public interface M4LMidi {
	
	public int numberOfInputDevices();
	
	public String getInputDeviceName(int device);

	/** @TODO is this even needed? */
	public void closePorts();
	
	public int numberOfOutputDevices();
	
	public String getOutputDeviceName(int device);
	
	public M4LMidiOut getMidiOut(int ch, String deviceName);
	
	public M4LMidiIn getMidiIn(int ch, String deviceName);
	
	public void plug(processing.core.PApplet core, String event, String deviceName, int ch);
	
	public void closeInput(String deviceName);
	
	public void closeOutputs();
	
	public void printDevices(); // @TODO print to system out
}
