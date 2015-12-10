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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import processing.core.PApplet;

public class M4LForwardingMidiInPort implements M4LMidiIn {

	private PApplet forwardingPort;
	private Method callbackOnNoteMethod;
	private Method callbackOnControllerMethod;
	
	public M4LForwardingMidiInPort(int ch, String callbackFunction, PApplet forwardingPort) {
		this.forwardingPort = forwardingPort;
		
		try {
			if (forwardingPort != null) {
				callbackOnControllerMethod = forwardingPort.getClass().getMethod(callbackFunction, M4LController.class);
			}	
		} catch (SecurityException e) {
			// Do nothing
		} catch (NoSuchMethodException e) {
			// Do nothing
		}
		
		try {
			if (forwardingPort != null) {
				callbackOnNoteMethod = forwardingPort.getClass().getMethod(callbackFunction, Note.class);
			}	
		} catch (SecurityException e) {
			// Do nothing
		} catch (NoSuchMethodException e) {
			// Do nothing
		}
	}
	
	public PApplet getForwardingPort() {
		return forwardingPort;
	}

	public void setForwardingPort(PApplet forwardingPort) {
		this.forwardingPort = forwardingPort;
	}

	public void sendController(M4LController controller) {
		if (forwardingPort == null || callbackOnControllerMethod == null)
			return;
		
		try {
			callbackOnControllerMethod.invoke(forwardingPort, controller);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void sendNoteOff(Note note) {
		if (forwardingPort == null || callbackOnNoteMethod == null)
			return;
		
		try {
			callbackOnNoteMethod.invoke(forwardingPort, note);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void sendNoteOn(Note note) {
		if (forwardingPort == null || callbackOnNoteMethod == null)
			return;
		
		try {
			callbackOnNoteMethod.invoke(forwardingPort, note);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
