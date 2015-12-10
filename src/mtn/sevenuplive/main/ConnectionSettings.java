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

package mtn.sevenuplive.main;

import jklabs.monomic.MonomeOSC;
import mtn.sevenuplive.m4l.M4LMidiSystem;

public class ConnectionSettings {
	
	public int monomeType=0; // look at SevenUp4Live eMonomeType
	public int gridx=-1; // if set > 0 then monome will be sized by this value x 8. This overrides monomeType 
	public int gridy=-1; // if set > 0 then monome will be sized by this value x 8. This overrides monomeType 
	public String oscPrefix = "7up";
	public int oscHostPort = 8080;
	public int oscListenPort = 8000;
	public MonomeOSC.ProtocolVersion protocolVersion = MonomeOSC.ProtocolVersion.classic; /** classic is v1 protocol, serialosc is v2 protocol */
	public boolean multilevel = true; // use multiple led levels rather than multiple blink modes
	public String oscHostAddress = "127.0.0.1";
	public String midiInputDeviceName = M4LMidiSystem.eSevenUp4InputDevices.SevenUpMidiControl.toString();
	public String controllerOutputDeviceName = M4LMidiSystem.eSevenUp4OutputDevices.Controller.toString();
	public String stepperOutputDeviceName = M4LMidiSystem.eSevenUp4OutputDevices.Stepper.toString();
	public String looperOutputDeviceName = M4LMidiSystem.eSevenUp4OutputDevices.Looper.toString();
	public String melod1OutputDeviceName = M4LMidiSystem.eSevenUp4OutputDevices.Melodizer1.toString();
	public String melod2OutputDeviceName = M4LMidiSystem.eSevenUp4OutputDevices.Melodizer2.toString();
	
	// We ignore input from disabled ports
	public boolean[] enabledADCports = new boolean[]{true, true, true, true, true, true, true, true};
}
