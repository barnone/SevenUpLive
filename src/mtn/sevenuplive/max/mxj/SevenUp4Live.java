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

package mtn.sevenuplive.max.mxj;

import java.util.ArrayList;
import java.util.List;

import jklabs.monomic.MonomeOSC;

import mtn.sevenuplive.m4l.M4LMidi;
import mtn.sevenuplive.m4l.M4LMidiIn;
import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.m4l.M4LMidiSystem;
import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.main.ConnectionSettings;
import mtn.sevenuplive.main.MonomeUp;
import mtn.sevenuplive.main.SevenUpEnvironment;
import mtn.sevenuplive.modes.AllModes;
import mtn.sevenuplive.modes.MelodizerModel;
import mtn.sevenuplive.modes.MelodizerModel.eMelodizerMode;
import mtn.sevenuplive.scales.Scale;
import mtn.sevenuplive.scales.ScaleName;

import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

public class SevenUp4Live extends MaxObject {
	
	// There can be only one of these
	private static SevenUp4Live instance;
	private SevenUpEnvironment environment;
	
	private M4LMidi midiIO;
	
	private SevenUp4LivePatchManager pmanage;
	private SevenUp4LiveMelodizerClient[] melodizer1;
	private SevenUp4LiveMelodizerClient[] melodizer2;
	private SevenUp4LiveStepperClient[] stepper;
	private SevenUp4LiveLooperClient[] looper;
	private SevenUp4LiveControllerClient[] controller;
	private ConnectionSettings settings = new ConnectionSettings();
	
	public static enum eOutletCategories {connections, looper, melodizer1, melodizer2, controller, tilt};
	public static enum eOutlets {MelodizerMidiOutlet, StepperMidiOutlet, LooperMidiOutlet, ControllerMidiOutlet, PatchDataOutlet, InitializationDataOutlet, LifecycleDataOutlet};
	public static enum eLifecycle {started, stopped, dirty};
	
	private static final String[] INLET_ASSIST = new String[]{
		"messages (initialize, shutdown, monome (0,1,2..etc), oscprefix, hostaddress (127.0.0.1), protocol, multilevel, grid, listenport, hostport, looper, melodizer1, melodizer2, tilt)",
		"Midi In",
		"clock in (0=C4,1=D#4,2=C7,3=E7,4=F7)"
	};
	private static final String[] OUTLET_ASSIST = new String[]{
		"Melodizer Midi Out",
		"Stepper Midi Out",
		"Looper Midi Out",
		"Controller Midi Out",
		"Patch Data Out",
		"Data Initialization Out",
		"Lifecycle Out"
	};
	
	public enum eMonomeChoices {Monome64,
		Monome128H,
		Monome128V,
		Monome3x64,
		Monome256,
		Monome2x256,
		Monome3x256,
		Monome5x64,
		Monome6x64,
		Monome7x64,
		Monome8x64,
		Monome9x64,
		Monome10x64,
		MultiColorDevice,
		MultiColor256,
		Manta_v2};
		
	public SevenUp4Live(Atom[] args)
	{
		// If MAX is creating a new instance, then we need to 
		// point to the new one, and shutdown old one
		if (instance != null) {
			post("7up instance already exists...transferring control to new instance");
			/*post("Shutting down old instance...");
			try {
				instance.shutdown();
			} catch (Throwable t) {
				post(t.toString());
			} 
			post("Old instance shutdown");*/
			
			post("Transferring environment to new instance...");
			this.environment = instance.getEnvironment();
		}
		
		declareInlets(new int[]{DataTypes.ALL, DataTypes.MESSAGE, DataTypes.INT});
		declareOutlets(new int[]{
				DataTypes.MESSAGE, 
				DataTypes.MESSAGE,
				DataTypes.MESSAGE,
				DataTypes.MESSAGE,
				DataTypes.MESSAGE,
				DataTypes.MESSAGE,
				DataTypes.MESSAGE
				});
		
		setInletAssist(INLET_ASSIST);
		setOutletAssist(OUTLET_ASSIST);
		
		midiIO = new M4LMidiSystem(this); 
		init();
		
		// Only create a new environment if there isn't already one
		if (environment == null)
			environment = new SevenUpEnvironment(midiIO, settings);
		
		// Switch instance to this
		instance = this;
		post("New 7up instance creation complete");
	}
	
	protected void bang() {
		post("Hello world!! This is SevenUp in MAX");
		post("Initializing UI...");
		initializeUI();
		post("ready!");
	}

	protected void loadbang() {
		// forces data refresh
		bang();
	}
	
	private void init() {
		pmanage = new SevenUp4LivePatchManager(this); 
		melodizer1 = new SevenUp4LiveMelodizerClient[16];
		melodizer2 = new SevenUp4LiveMelodizerClient[16];
		stepper = new SevenUp4LiveStepperClient[16];
		looper = new SevenUp4LiveLooperClient[16];
		controller = new SevenUp4LiveControllerClient[16];
		
		// Wire up our ports
		for (int i = 0; i < 15; i++) {
			melodizer1[i] = new SevenUp4LiveMelodizerClient(this, 1, i);
			melodizer2[i] = new SevenUp4LiveMelodizerClient(this, 2, i);
			stepper[i] = new SevenUp4LiveStepperClient(this, 1, i);
			looper[i] = new SevenUp4LiveLooperClient(this, 1, i);
			controller[i] = new SevenUp4LiveControllerClient(this, 1, i);
		}
	}
	
	public SevenUpEnvironment getEnvironment() {
		return environment;
	}

	public M4LMidiOut getMelodizerOutput(int ch, int instance) {
		switch (instance) {
		case 1:
			if (ch < 16)
				return melodizer1[ch];
		break;
		case 2:
			if (ch < 16)
				return melodizer2[ch];
		break;
		}
		return null;
	}
	
	public M4LMidiOut getStepperOutput(int ch, int instance) {
		if (ch < 16) 
			return stepper[ch];
		else
			return null;
	}
	
	public M4LMidiOut getLooperOutput(int ch, int instance) {
		if (ch < 16) 
			return looper[ch];
		else
			return null;
	}
	
	public M4LMidiOut getControllerOutput(int ch, int instance) {
		if (ch < 16) 
			return controller[ch];
		else
			return null;
	}
	
	/**
	 * Initializes SevenUp with the current connection settings and starts it's heart 
	 */
	public void initialize() {
		if (!environment.isStarted())
			environment.startSevenUp();
		
		// You can only push UI settings back to Seven Up after it has started, so these events are critical
		// For the UI to be able to know when it can configure 7up
		outlet(eOutlets.LifecycleDataOutlet.ordinal(), Atom.newAtom(eLifecycle.started.toString()));
	}
    
	/**
	 * Shuts down SevenUps heart and releases the OSC ports 
	 */
	public void shutdown() {
		dirty();	
		
		environment.stopSevenUp();
		
		outlet(eOutlets.LifecycleDataOutlet.ordinal(), Atom.newAtom(eLifecycle.stopped.toString()));
	}
	
	/**
	 * Send this message to echo back a dirty message on the Lifecycle channel if the current patch is dirty...has changes
	 */
	public void dirty() {
		if (environment.getMonome() != null && environment.getMonome().isDirty())
			outlet(eOutlets.LifecycleDataOutlet.ordinal(), Atom.newAtom(eLifecycle.dirty.toString()));
	}
    
	public void inlet(int i)
	{
		int inletNum = getInlet();
		//post("I got an integer in inlet "+ inletNum);
		
		switch (inletNum) {
			case 2:
				switch (i) {
				case -1:
					//post("CLOCK STOP");
					if (environment.getClock() != null)
						environment.getClock().stopClock();
					break;
				case -2:
					//post("CLOCK START");
					if (environment.getClock() != null)
						environment.getClock().startClock();
					break;	
				case 0:
					//post("C4");
					if (environment.getClock() != null)
						environment.getClock().sendFirstLocator();
					break;
				case 1:
					//post("D#4");
					if (environment.getClock() != null)
						environment.getClock().sendSecondLocator();
					break;
				case 2:
					//post("C7");
					if (environment.getClock() != null)
						environment.getClock().pumpSequencerHeart();
					break;
				case 3:
					//post("F7");
					if (environment.getClock() != null)
						environment.getClock().pumpLooperHeart();
					break;
				case 4:
					//post("E7");
					if (environment.getClock() != null)
						environment.getClock().pump64th();
					break;
				default:
					post("Clock does not understand " + i);
				}
				break;
			default:	
		}
	}
    
	public void monome(Atom[] list)
	{
		if (list.length > 0) {
			int type = list[0].getInt();
			if (type < 0)
				type = 0;
			post("Setting monome via atom to type [" + Integer.toString(type) + "]");
			settings.monomeType = type;
		}
	}
	
	public void tilt(Atom[] list)
	{
		if (!environment.isStarted())
			return; // We can't send OSC is nt started
			
		MonomeUp m = environment.getMonome();
		
		if (m == null) // Nothing to do
			return;
		
		if (list.length > 0) {
			String type = list[0].getString();
			if (type.equals("calibrate") && list.length > 1) {
				int calibrate = list[1].getInt();
				if (calibrate == 1) { // Turn calibrate mode ON
					m.setADCCalibrateMode(true);
				} else { // Turn calibrate mode OFF
					m.setADCCalibrateMode(false);
				}
			} else if (type.equals("adc") && list.length > 1) {
				int on = list[1].getInt();
				if (on == 1) { // Turn ADC mode ON
					m.setADCActive(true);
				} else { // Turn ADC mode OFF
					m.setADCActive(false);
				}
			}
		}
	}
	
	public void hostaddress(Atom[] list)
	{
		if (list.length > 0) {
			String address = list[0].getString();
			if (address == null)
				return;
			
			post("Setting monome hostaddress atom to [" + address + "]");
			settings.oscHostAddress = address;
		}
	}
	
	public void oscprefix(Atom[] list)
	{
		if (list.length > 0) {
			String prefix = list[0].getString();
			if (prefix == null)
				return;
			
			post("Setting monome prefix atom to [" + prefix + "]");
			settings.oscPrefix = prefix;
		}
	}
	
	public void listenport(Atom[] list)
	{
		if (list.length > 0) {
			int listenport = list[0].getInt();
			if (listenport < 0)
				listenport = 0;
			post("Setting monome listenport to  [" + Integer.toString(listenport) + "]");
			settings.oscListenPort = listenport;		
		}
	}
	
	public void hostport(Atom[] list)
	{
		if (list.length > 0) {
			int hostport = list[0].getInt();
			if (hostport < 0)
				hostport = 0;
			post("Setting monome hostport to  [" + Integer.toString(hostport) + "]");
			settings.oscHostPort = hostport;		
		}
	}
	
	public void protocol(Atom[] list)
	{
		if (list.length > 0) {
			int ver = list[0].getInt();
			switch (ver) {
				case 0:
					settings.protocolVersion = MonomeOSC.ProtocolVersion.classic;
					break;
				case 1:
				default:
					settings.protocolVersion = MonomeOSC.ProtocolVersion.serialosc;
			}
			post("Setting monome protocol to  [" + settings.protocolVersion.name() + "]");
		}
	}
	
	public void multilevel(Atom[] list)
	{
		if (list.length > 0) {
			int level = list[0].getInt();
			switch (level) {
				case 0:
					settings.multilevel = false;
					break;
				case 1:
				default:
					settings.multilevel = true;
			}
			post("Setting monome multilevel mode to [" + settings.multilevel + "]");
		}
	}
	
	public void grid(Atom[] list)
	{
		if (list.length > 0) {
			int xgrid = list[0].getInt();
			int ygrid = list[1].getInt();
			post("Setting gridsize to [" + xgrid + " x " + ygrid + "]");
			settings.gridx = Math.abs(xgrid / 8);
			settings.gridy = Math.abs(ygrid / 8);
		}
	}
	
	public void writepatch(Atom[] patchparams) {
		post("writepatch() called with " + patchparams.length + " params");
		if (patchparams.length > 0) {
			String filepath = patchparams[0].toString();
			post("Writing patch [" + filepath + "]");
			pmanage.savePatch(filepath);
		}
	}
	
	public void readpatch(Atom[] patchparams) {
		post("readpatch() called with " + patchparams.length + " params");
		if (patchparams.length > 0) {
			String filepath = patchparams[0].toString();
			post("Reading patch [" + filepath + "]");
			pmanage.loadPatch(filepath);
		}
	}
	
	private void initializeUI() {
		initializeMonomeChoices();
		initializeScales();
		initializeMelodizerTools();
	}
	
	private void initializeMonomeChoices() {
		List<eMonomeChoices> monomeList = new ArrayList<eMonomeChoices>();
		
		for (eMonomeChoices monomeName : eMonomeChoices.values()) {
			monomeList.add(monomeName);
		
		}
		Atom[] atoms = new Atom[monomeList.size() + 2];
		atoms[0] = Atom.newAtom("connections");
		atoms[1] = Atom.newAtom("monomes");
		
		for (int i = 0; i < monomeList.size(); i++) {
			atoms[i + 2] = Atom.newAtom(monomeList.get(i).toString());
		}
		
		// Send initialization data
		outlet(eOutlets.InitializationDataOutlet.ordinal(), atoms);
	}
	
	private void initializeScales() {
		List<ScaleName> scaleList = new ArrayList<ScaleName>();
		
		for (ScaleName scaleName : ScaleName.values()) {
			scaleList.add(scaleName);
		}
		Atom[] atoms = new Atom[scaleList.size() + 2];
		atoms[0] = Atom.newAtom(eOutletCategories.melodizer1.toString());
		atoms[1] = Atom.newAtom("scales");
		
		for (int i = 0; i < scaleList.size(); i++) {
			atoms[i + 2] = Atom.newAtom(scaleList.get(i).toString());
		}
		
		// Send initialization data
		outlet(eOutlets.InitializationDataOutlet.ordinal(), atoms);
		
		atoms[0] = Atom.newAtom(eOutletCategories.melodizer2.toString());
		
		// Send initialization data
		outlet(eOutlets.InitializationDataOutlet.ordinal(), atoms);
	}
	
	private void initializeMelodizerTools() {
		List<String> modelList = new ArrayList<String>();
		
		modelList.add(MelodizerModel.eMelodizerMode.KEYBOARD.toString());
		modelList.add(MelodizerModel.eMelodizerMode.POSITION.toString());
		modelList.add(MelodizerModel.eMelodizerMode.NONE.toString());
		modelList.add(MelodizerModel.eMelodizerMode.CLIP.toString());
		modelList.add(MelodizerModel.eMelodizerMode.KEYBOARD.toString() + "/" + MelodizerModel.eMelodizerMode.POSITION.toString());
		modelList.add(MelodizerModel.eMelodizerMode.KEYBOARD.toString() + "/" + MelodizerModel.eMelodizerMode.NONE.toString());
		modelList.add(MelodizerModel.eMelodizerMode.POSITION.toString() + "/" + MelodizerModel.eMelodizerMode.NONE.toString());
		
		// Melodizer 1
		Atom[] atoms = new Atom[modelList.size() + 2];
		atoms[0] = Atom.newAtom(eOutletCategories.melodizer1.toString());
		atoms[1] = Atom.newAtom("toolmodes");
		
		
		for (int i = 0; i < modelList.size(); i++) {
			atoms[i + 2] = Atom.newAtom(modelList.get(i).toString());
		}
		
		// Send initialization data
		outlet(eOutlets.InitializationDataOutlet.ordinal(), atoms);
		
		// Melodizer 2
		
		// No clip mode in Melodizer1
		modelList.remove(MelodizerModel.eMelodizerMode.CLIP.toString());
		atoms = new Atom[modelList.size() + 2];
		atoms[0] = Atom.newAtom(eOutletCategories.melodizer2.toString());
		atoms[1] = Atom.newAtom("toolmodes");
		
		for (int i = 0; i < modelList.size(); i++) {
			atoms[i + 2] = Atom.newAtom(modelList.get(i).toString());
		}
		
		// Send initialization data
		outlet(eOutlets.InitializationDataOutlet.ordinal(), atoms);
	}
	
	//////////////////// Monome Settings /////////////////////////////
	
	public void melodizer1(Atom[] atoms) {
		MonomeUp m = environment.getMonome();
		if (atoms != null && atoms.length > 1 && m != null) {
			String operation = atoms[0].toString();
			if (operation.equals("scalename")) {
				if (atoms[1] != null)
					m.setMelody1Scale(new Scale(ScaleName.valueOf(atoms[1].toString())));
			} else if (operation.equals("recmode")) {
				if (atoms[1] != null && atoms[1].isInt()) {
					int mode = atoms[1].toInt();
					m.setMel1RecMode(mode);
				}
			} else if (operation.equals("toolmode")) {
				if (atoms[1] != null && atoms[1].isString()) {
					String toolmode[] = atoms[1].toString().split("/");
					if (toolmode.length == 1) {
						m.setMelody1Mode(eMelodizerMode.valueOf(toolmode[0]));
						m.setMelody1AltMode(eMelodizerMode.valueOf(toolmode[0]));
					} else if (toolmode.length == 2) {
						m.setMelody1Mode(eMelodizerMode.valueOf(toolmode[0]));
						m.setMelody1AltMode(eMelodizerMode.valueOf(toolmode[1]));
					}
					
				}
			} else if (operation.equals("transpose")) {
				if (atoms[1] != null && atoms[1].isInt()) {
					int transpose = atoms[1].toInt();
					m.setMelody1Transpose(transpose == 0 ? false : true);
				}
			} else if (operation.equals("transposegroup")) {
				if (atoms.length > 2 && atoms[1] != null && atoms[1].isInt() && atoms[2] != null && atoms[2].isInt()) {
					int slot = atoms[1].toInt();
					int group = atoms[2].toInt();
					m.setMel1TransposeGroup(slot, group - 1);
				}
			} else if (operation.equals("sustainmode")) {
				if (atoms.length > 2 && atoms[1] != null && atoms[1].isInt() && atoms[2] != null && atoms[2].isInt()) {
					int slot = atoms[1].toInt();
					int mode = atoms[2].toInt();
					if (mode == 0) {
						m.setMel1TransposeSustain(slot, false);
					} else{
						m.setMel1TransposeSustain(slot, true);
					}
				}
			} 
		}
	}
	
	public void melodizer2(Atom[] atoms) {
		MonomeUp m = environment.getMonome();
		if (atoms != null && atoms.length > 1 && m != null) {
			String operation = atoms[0].toString();
			if (operation.equals("scalename")) {
				if (atoms[1] != null)
					m.setMelody2Scale(new Scale(ScaleName.valueOf(atoms[1].toString())));
			} else if (operation.equals("recmode")) {
				if (atoms[1] != null && atoms[1].isInt()) {
					int mode = atoms[1].toInt();
					m.setMel2RecMode(mode);
				}
			} else if (operation.equals("toolmode")) {
				if (atoms[1] != null && atoms[1].isString()) {
					String toolmode[] = atoms[1].toString().split("/");
					if (toolmode.length == 1) {
						m.setMelody2Mode(eMelodizerMode.valueOf(toolmode[0]));
						m.setMelody2AltMode(eMelodizerMode.valueOf(toolmode[0]));
					} else if (toolmode.length == 2) {
						m.setMelody2Mode(eMelodizerMode.valueOf(toolmode[0]));
						m.setMelody2AltMode(eMelodizerMode.valueOf(toolmode[1]));
					}
					
				}
			} else if (operation.equals("transpose")) {
				if (atoms[1] != null && atoms[1].isInt()) {
					int transpose = atoms[1].toInt();
					m.setMelody2Transpose(transpose == 0 ? false : true);
				}
			} else if (operation.equals("transposegroup")) {
				if (atoms.length > 2 && atoms[1] != null && atoms[1].isInt() && atoms[2] != null && atoms[2].isInt()) {
					int slot = atoms[1].toInt();
					int group = atoms[2].toInt();
					m.setMel2TransposeGroup(slot, group - 1);
				}
			} else if (operation.equals("sustainmode")) {
				if (atoms.length > 2 && atoms[1] != null && atoms[1].isInt() && atoms[2] != null && atoms[2].isInt()) {
					int slot = atoms[1].toInt();
					int mode = atoms[2].toInt();
					if (mode == 0) {
						m.setMel2TransposeSustain(slot, false);
					} else{
						m.setMel2TransposeSustain(slot, true);
					}
				}
			} 
		}
	}
	
	public void looper(Atom[] atoms) {
		MonomeUp m = environment.getMonome();
		if (atoms != null && atoms.length > 1 && m != null) {
			String operation = atoms[0].toString();
			if (operation.equals("gatechokedloops")) {
				if (atoms[1] != null && atoms[1].isInt()) {
					int gatechokedloops = atoms[1].toInt();
					AllModes.getInstance().getLooper().setGateLoopChokes(gatechokedloops == 0 ? false : true);
				}
			} else if (operation.equals("chokegroup")) {
				if (atoms.length > 2 && atoms[1] != null && atoms[1].isInt() && atoms[2] != null && atoms[2].isInt()) {
					int slot = atoms[1].toInt();
					int group = atoms[2].toInt();
					m.setLoopChoke(slot, group - 1);
				}
			} else if (operation.equals("loopmode")) {
				if (atoms.length > 2 && atoms[1] != null && atoms[1].isInt() && atoms[2] != null && atoms[2].isInt()) {
					int slot = atoms[1].toInt();
					int type = atoms[2].toInt();
					m.setLoopType(slot, type);
				}
			} else if (operation.equals("looplength")) {
				if (atoms.length > 2 && atoms[1] != null && atoms[1].isInt() && atoms[2] != null && (atoms[2].isFloat() || atoms[2].isInt())) {
					int slot = atoms[1].toInt();
					float length;
					if (atoms[2].isFloat()) {
						length = atoms[2].toFloat(); 
					} else {
						length = atoms[2].toInt();
					}
					//post("setting loop length to " + length);
					m.setLoopLength(slot, length);
				}
			}  
		}
	}
	
	public void inlet(float f)
	{
	}
    
    
	public void list(Atom[] atoms)
	{
		int inletNum = getInlet();
		//post("Got atoms on inlet #" + inletNum);
		
		M4LMidiIn min = null;
		Note note = null;
		
		// Midi IN Notes come as Pitch,Velocity, Channel
		if (inletNum == 1) { // This is MIDI_IN inlet 
			if (atoms != null) {
				if (atoms.length == 3) { // We need 3 elements [Pitch,Velocity, Channel] in that order
					min = midiIO.getMidiIn(atoms[2].toInt() - 1 , settings.midiInputDeviceName);
					note = new Note(atoms[0].toInt(), atoms[1].toInt(), 0, 144);
					if (min != null) {
						if (atoms[1].toInt() == 0) {
							min.sendNoteOff(note);
						} else {
							min.sendNoteOn(note);
						}
					}
				}
			}
		}
	}

	/////////////////////////////////////////////
	// Non MXJ Operations
	
	public static SevenUp4Live getInstance() {
		return instance;
	}
		
}
