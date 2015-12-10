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
import mtn.sevenuplive.m4l.M4LMidi;
import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.max.mxj.SevenUpClock;
import mtn.sevenuplive.modes.DisplayGrid;

public class SevenUpApplet extends processing.core.PApplet implements SevenUpClock
{
	private static final long serialVersionUID = 1L;
	private MonomeUp m;
	
	private boolean isRunning = false;
	private static int FRAMERATE = 25;
	
	private M4LMidi midiIO;
	private ConnectionSettings sevenUpConnections;
	
	public SevenUpApplet(MonomeUp m, ConnectionSettings sevenUpConnections, M4LMidi midiIO)
	{
		this.sevenUpConnections = sevenUpConnections;
		this.m = m;
		this.midiIO = midiIO;
		init();
	}
	
	public void setup()
	{
	   frameRate(FRAMERATE);
	   
	   m.startup(sevenUpConnections.oscPrefix, sevenUpConnections.oscHostAddress, sevenUpConnections.oscHostPort, sevenUpConnections.oscListenPort, sevenUpConnections.protocolVersion, sevenUpConnections.multilevel);
	   this.online = false;
	   
	   midiIO.printDevices();
	   
	   // Ch8 is the channel we listen for midi to control certain actions in SevenUp
	   midiIO.plug(this, "noteOn", sevenUpConnections.midiInputDeviceName, 7);
	   
	   //Channels 1-7 used to listen to Clip Launch events.
	   midiIO.plug(this, "noteOnCh1", sevenUpConnections.midiInputDeviceName, 0);
	   midiIO.plug(this, "noteOnCh2", sevenUpConnections.midiInputDeviceName, 1);
	   midiIO.plug(this, "noteOnCh3", sevenUpConnections.midiInputDeviceName, 2);
	   midiIO.plug(this, "noteOnCh4", sevenUpConnections.midiInputDeviceName, 3);
	   midiIO.plug(this, "noteOnCh5", sevenUpConnections.midiInputDeviceName, 4);
	   midiIO.plug(this, "noteOnCh6", sevenUpConnections.midiInputDeviceName, 5);
	   midiIO.plug(this, "noteOnCh7", sevenUpConnections.midiInputDeviceName, 6);
	   
	   //Channels 9-15 used to listen to external instruments for melodizer
	   midiIO.plug(this, "noteOnCh9", sevenUpConnections.midiInputDeviceName, 8);
	   midiIO.plug(this, "noteOnCh10", sevenUpConnections.midiInputDeviceName, 9);
	   midiIO.plug(this, "noteOnCh11", sevenUpConnections.midiInputDeviceName, 10);
	   midiIO.plug(this, "noteOnCh12", sevenUpConnections.midiInputDeviceName, 11);
	   midiIO.plug(this, "noteOnCh13", sevenUpConnections.midiInputDeviceName, 12);
	   midiIO.plug(this, "noteOnCh14", sevenUpConnections.midiInputDeviceName, 13);
	   midiIO.plug(this, "noteOnCh15", sevenUpConnections.midiInputDeviceName, 14);
	   
	   isRunning = true;
	}
	
	public void stop() {
		super.stop();
		isRunning = false;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void draw()
	{
		m.draw(this.frameCount % FRAMERATE);
	}
	
	public void finalize()
	{
		m.lightsOff();
		m = null;
	}
		
	public void noteOn(Note note){
		//status 144 = noteOn
		//status 128 = noteOff? note 123 = stop?
		if(note.getPitch() == 123 && note.getStatus() == 128)
			m.reset();
		else if (note.getStatus() == 144)
			m.noteOn(note.getPitch());
	}
	
	public void noteOnCh1(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity() , 0);
	}
	public void noteOnCh2(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 1);
	}
	public void noteOnCh3(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 2);
	}
	public void noteOnCh4(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 3);
	}
	public void noteOnCh5(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 4);
	}
	public void noteOnCh6(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 5);
	}
	public void noteOnCh7(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 6);
	}
	
	public void noteOnCh9(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 8);
	}
	
	public void noteOnCh10(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 9);
	}

	public void noteOnCh11(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 10);
	}

	public void noteOnCh12(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 11);
	}
	
	public void noteOnCh13(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 12);
	}

	public void noteOnCh14(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 13);
	}
	
	public void noteOnCh15(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 14);
	}
	
	public void quit()
	{
		//Redundant
		midiIO.closeInput(sevenUpConnections.midiInputDeviceName);
		midiIO.closeOutputs();
	}

	public void close() {
		midiIO.closeInput(sevenUpConnections.midiInputDeviceName);
		midiIO.closeOutputs();
	}

	public void sendFirstLocator() {
		m.sendFirstLocator();
	}

	public void pumpSequencerHeart() {
		m.pumpSequencerHeart();
	}

	public void sendSecondLocator() {
		m.sendSecondLocator();
	}
	
	public void sendLocatorRecord() {
		m.sendLocatorRecord();
	}

	public void pump64th() {
		m.pump64th();	
	}

	public void pumpLooperHeart() {
		m.pumpLooperHeart();
	}

	public void startClock() {
		m.startClock();
	}

	public void stopClock() {
		m.stopClock();
	}
	
}
		