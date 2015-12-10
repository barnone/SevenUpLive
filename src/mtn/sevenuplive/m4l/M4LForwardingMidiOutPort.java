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

public class M4LForwardingMidiOutPort implements M4LMidiOut {

	private M4LMidiOut forwardingPort;
	public void setForwardingPort(M4LMidiOut forwardingPort) {
		this.forwardingPort = forwardingPort;
	}

	public M4LMidiOut getForwardingPort() {
		return forwardingPort;
	}

	public M4LForwardingMidiOutPort(M4LMidiOut forwardingPort) {
		this.forwardingPort = forwardingPort;
	}
	
	public void sendController(M4LController controller) {
		if (forwardingPort != null)
			forwardingPort.sendController(controller);
	}

	public void sendNoteOff(Note note) {
		Note noteoff = note;
		if (forwardingPort != null) {
			// Make sure note has 0 velocity
			if (note.getVelocity() != 0) {
				noteoff = new Note(note.getPitch(), 0, note.getLength(), note.getStatus());
			}
				
			forwardingPort.sendNoteOff(noteoff);
		}	
	}

	public void sendNoteOn(Note note) {
		if (forwardingPort != null)
			forwardingPort.sendNoteOn(note);
	}
	
}
