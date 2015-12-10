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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jklabs.monomic.MonomeListener;
import jklabs.monomic.MonomeOSC;
import mtn.sevenuplive.m4l.M4LMidi;
import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.max.mxj.SevenUpClock;
import mtn.sevenuplive.modes.AllModes;
import mtn.sevenuplive.modes.ControllerModel;
import mtn.sevenuplive.modes.ControllerView;
import mtn.sevenuplive.modes.DisplayGrid;
import mtn.sevenuplive.modes.Displays;
import mtn.sevenuplive.modes.LoopRecorder;
import mtn.sevenuplive.modes.Looper;
import mtn.sevenuplive.modes.MantaV2DisplayGrid;
import mtn.sevenuplive.modes.Masterizer;
import mtn.sevenuplive.modes.MelodizerModel;
import mtn.sevenuplive.modes.MelodizerView;
import mtn.sevenuplive.modes.ModeConstants;
import mtn.sevenuplive.modes.MultiLevelDisplayGrid;
import mtn.sevenuplive.modes.MultiValueDisplayGrid;
import mtn.sevenuplive.modes.PatternizerModel;
import mtn.sevenuplive.modes.PatternizerView;
import mtn.sevenuplive.modes.Sequencer;
import mtn.sevenuplive.modes.StartupMode;
import mtn.sevenuplive.modes.Displays.GridCoordinateTarget;
import mtn.sevenuplive.scales.Scale;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

public final class MonomeUp extends MonomeOSC implements MonomeListener, SevenUpClock {

	private ArrayList<Element> xmlPatches;
	private int curPatchIndex=0;
	private String patchTitle = "";
	
	// Monome 
	public static final int MONOME_64 = 0;
	public static final int MONOME_128H = 1;
	public static final int MONOME_128V = 2;
	public static final int MONOME_256 = 3;

	public static final int EMPTY = -1;
	public static final int STOPPED = 0;
	public static final int PLAYING = 1;
	public static final int CUED = 2;
	public static final int RECORDING = 3;
	public static final int CUEDSTOP = 4;
	////////////////////////////////////////

	/** Dirty flag for changes to the patch */
	private boolean dirty; 

	/////////////////////////////////////
	//CONTROLLER
	/////////////////////////////////////
	private static final int STARTING_CONTROLLER = 40;
	/////////////////////////////////////

	public static enum eDeviceType {Monome, Manta, MultiColorDevice}; 
	
	//Pitches
	//Uh, these are probably wrong
	public static final  int C7 = 108;
	public static final  int ESHARP7 = 109;
	public static final  int E7 = 112;
	public static final  int F7 = 113;
	public static final  int CSHARP4 = 73;
	public static final  int D4 = 74;
	public static final  int DSHARP4 = 75;
	public static final  int E4 = 76;
	public static final  int F4 = 77;
	public static final  int FSHARP4 = 78;
	public static final int G2 = 55;
	public static final  int G4 = 79;
	public static final  int A4 = 58;
	public static final int C1 = 36;
	public static final int C2 = 48;
	public static final int C3 = 60;
	public static final int CSHARP3 = 61;
	public static final int C4 = 72;

	/** Class that holds all our mode instances */
	private AllModes allmodes;

	//////////////////////////////////////
	//LOOPER
	/////////////////////////////////////
	private M4LMidiOut midiLoopOut[];
	private Boolean areLoopsGated = false;
	////////////////////////////////////////

	////////////////////////////////////////
	//MELODIZER
	////////////////////////////////////////
	private M4LMidiOut midiMelodyOut[];
	private M4LMidiOut midiMelody2Out[];
	/////////////////////////////////////

	/////////////////////////////////////
	//CONTROLLERS
	/////////////////////////////////////
	private M4LMidiOut midiControllerOut[];
	/////////////////////////////////////

	////////////////////////////////////////
	//Midi members
	////////////////////////////////////////
	private M4LMidiOut midiStepperOut[];
	////////////////////////////////////

	private ConnectionSettings sevenUpConnections;

	public static final int GRID_WIDTH = 8;
	public static final int GRID_HEIGHT = 8;
	////////////////////////////////////

	private DisplayGrid[] grids;
	private M4LMidi midiIO;

	MonomeUp (MonomeUp.eDeviceType deviceType, int x_grids, int y_grids, ConnectionSettings _sevenUpConnections, Scale monomeScale, M4LMidi midiIO) {
		super(x_grids, y_grids);
		sevenUpConnections = _sevenUpConnections;

		xmlPatches = new ArrayList<Element>();
		
		int totalGrids = x_grids * y_grids;
		System.out.println("X grids = " + x_grids + ", Y grids = " + y_grids + ", Total Grids = " + totalGrids);
		
		// Init midi communications
		this.midiIO = midiIO;
		initializeMidi();

		ControllerModel controllerModel = new ControllerModel(ModeConstants.CONTROL_MODE, midiControllerOut, STARTING_CONTROLLER, GRID_WIDTH, GRID_HEIGHT, sevenUpConnections.enabledADCports);

		//Create the same number of views as there are grids
		PatternizerModel patternizerModel = new PatternizerModel(ModeConstants.PATTERN_MODE, midiStepperOut, GRID_WIDTH, GRID_HEIGHT);
		PatternizerView[] patternizerViews = new PatternizerView[totalGrids];

		ControllerView[] controllerViews = new ControllerView[totalGrids];
		
		MelodizerModel melodyModel1 = new MelodizerModel(ModeConstants.MELODY_MODE,midiMelodyOut, GRID_WIDTH, GRID_HEIGHT); // Melodizer 1 
		MelodizerModel melodyModel2 = new MelodizerModel(ModeConstants.MELODY2_MODE,midiMelody2Out, GRID_WIDTH, GRID_HEIGHT); // Melodizer 2

		MelodizerView[] melodizerViews1 = new MelodizerView[totalGrids];
		MelodizerView[] melodizerViews2 = new MelodizerView[totalGrids];

		for(int i=0;i<patternizerViews.length;i++)
		{
			patternizerViews[i] = new PatternizerView(ModeConstants.PATTERN_MODE, GRID_WIDTH, GRID_HEIGHT, patternizerModel);
			controllerViews[i] = new ControllerView(ModeConstants.CONTROL_MODE, GRID_WIDTH, GRID_HEIGHT, controllerModel);
			melodizerViews1[i] = new MelodizerView(ModeConstants.MELODY_MODE, GRID_WIDTH, GRID_HEIGHT, melodyModel1);
			melodizerViews2[i] = new MelodizerView(ModeConstants.MELODY2_MODE, GRID_WIDTH, GRID_HEIGHT, melodyModel2);
		}

		allmodes = new AllModes(patternizerModel, patternizerViews, 
				controllerModel, controllerViews,
				new Sequencer(ModeConstants.SEQ_MODE, GRID_WIDTH, GRID_HEIGHT), 
				melodyModel1, melodizerViews1,
				melodyModel2, melodizerViews2,
				new Looper(ModeConstants.LOOP_MODE, midiLoopOut, this, GRID_WIDTH, GRID_HEIGHT), 
				new LoopRecorder(ModeConstants.LOOP_RECORD_MODE, this, GRID_WIDTH, GRID_HEIGHT), 
				new Masterizer(ModeConstants.MASTER_MODE, midiControllerOut, this, GRID_WIDTH, GRID_HEIGHT),
				new StartupMode(totalGrids, 75, 2));

		//Set initial display grids
		grids = new DisplayGrid[totalGrids];
		for(int i=0;i<grids.length;i++)
		{
			int startCol = 0;
			int startRow;
			
			//Check for 256
			if(x_grids > 1 && y_grids > 1) //256
			{
				//Setup first row of display grids
				if(i < x_grids)
				{
					startRow = 0;
					startCol = i * 8;
				}
				//Second row
				else
				{
					startRow = 8;
					startCol = (i - x_grids) * 8;
				}
			}
			else if(x_grids > 1) //128H
			{
				startRow = 0;
				startCol = i * 8;
			}
			else
			{
				//Determine startCol and startRow assuming all vertical monomes
				startRow = i * 8;
			}

			if (deviceType == MonomeUp.eDeviceType.MultiColorDevice)
				grids[i] = new MultiValueDisplayGrid(this, allmodes, startCol, startRow, 8, 8, allmodes.getPatternizerView(i), i, totalGrids);
			else if (deviceType == MonomeUp.eDeviceType.Manta)
				grids[i] = new MantaV2DisplayGrid(this, allmodes, startCol, startRow, 8, 8, allmodes.getPatternizerView(i), i, totalGrids);
			else
			{
				System.out.println("Creating a grid with startCol = " + startCol + " and startRow = " + startRow);
				
				// Determine mode as regular blink or multi-led level support
				if(sevenUpConnections.multilevel)
					grids[i] = new MultiLevelDisplayGrid(this, allmodes, startCol, startRow, 8, 8, allmodes.getPatternizerView(i), i, totalGrids);
				else
				    grids[i] = new DisplayGrid(this, allmodes, startCol, startRow, 8, 8, allmodes.getPatternizerView(i), i, totalGrids);
				
			}
		}

		// Turn on to debug monome OSC connection */
		//this.setDebug(Monome.FINE);
	} 

	private void initializeMidi()
	{
		//Create 8 channels (0-8) for controller out, ch 8 (index 7) sends a wider range of CCs for all pads on one channel + master channel. ch 9 (index 8) sends ADC values
		midiControllerOut = new M4LMidiOut[9];
		for(int i = 0; i<midiControllerOut.length; i++)
		{
			midiControllerOut[i] = midiIO.getMidiOut(i, sevenUpConnections.controllerOutputDeviceName);
		}

		//Create 7 channels (0-6) for stepper out
		midiStepperOut = new M4LMidiOut[7];
		for(int i = 0; i<midiStepperOut.length; i++)
		{
			midiStepperOut[i] = midiIO.getMidiOut(i, sevenUpConnections.stepperOutputDeviceName);
		}

		//Create 7 channels (0-6) for looper out
		midiLoopOut = new M4LMidiOut[7];
		for(int i = 0; i<midiLoopOut.length; i++)
		{
			midiLoopOut[i] = midiIO.getMidiOut(i, sevenUpConnections.looperOutputDeviceName);
		}

		//Create 7 channels (0-6) for melody out
		midiMelodyOut = new M4LMidiOut[7];
		for(int i = 0; i<midiMelodyOut.length; i++)
		{
			midiMelodyOut[i] = midiIO.getMidiOut(i, sevenUpConnections.melod1OutputDeviceName);
		}

		//Create 7 channels (0-6) for melody2 out
		midiMelody2Out = new M4LMidiOut[7];
		for(int i = 0; i<midiMelody2Out.length; i++)
		{
			midiMelody2Out[i] = midiIO.getMidiOut(i, sevenUpConnections.melod2OutputDeviceName);
		}

		panic();
	}
	
	public void draw(int curFrame) {
		for (DisplayGrid grid : grids) {
			grid.draw(false);
		}
	}

	public void panic()
	{ 
		//TODO get this to work
		/*
		    //PANIC!!!
		     for(int j=0;j<8;j++) 
			 for(int i=0;i<128;i++)
			 {
				 midiMelodyOut[j].sendNoteOff(new Note(i, 0, 0));
			 }	   
		 */ 
	}

	public void monomePressed(int raw_x, int raw_y)
	{
		// Dirty flag for any action on a patch
		if (!isDirty()) {
			setDirty(true);
		}

		GridCoordinateTarget targetd = Displays.translate(grids, raw_x, raw_y);
		int x = targetd.getX_translated();
		int y = targetd.getY_translated();

		targetd.getDisplay().monomePressed(x, y);
	}

	/* (non-Javadoc)
	 * @see jklabs.monomic.MonomeOSC#monomeXPressed(int, int, int)
	 */
	public void monomeXPressed(int raw_x, int raw_y, int velocity)
	{
		// Dirty flag for any action on a patch
		if (!isDirty()) {
			setDirty(true);
		}

		GridCoordinateTarget targetd = Displays.translate(grids, raw_x, raw_y);
		int x = targetd.getX_translated();
		int y = targetd.getY_translated();

		targetd.getDisplay().monomeXPressed(x, y, velocity);
	}

	/* (non-Javadoc)
	 * @see jklabs.monomic.MonomeOSC#monomeAfterTouch(int, int, float)
	 */
	public void monomeAfterTouch(int raw_x, int raw_y, float value)
	{
		// Dirty flag for any action on a patch
		if (!isDirty()) {
			setDirty(true);
		}

		GridCoordinateTarget targetd = Displays.translate(grids, raw_x, raw_y);
		int x = targetd.getX_translated();
		int y = targetd.getY_translated();

		targetd.getDisplay().monomeAfterTouch(x, y, value);
	}
	
	/* (non-Javadoc)
	 * @see jklabs.monomic.MonomeListener#monomeRefresh()
	 */
	public void monomeRefresh() {
		for (DisplayGrid grid : grids) {
			grid.draw(true); // This forces display refresh
		}
	}

	public void monomeReleased(int raw_x, int raw_y)
	{
		GridCoordinateTarget targetd = Displays.translate(grids, raw_x, raw_y);
		int x = targetd.getX_translated();
		int y = targetd.getY_translated();

		targetd.getDisplay().monomeReleased(x, y);
	}
	
	public void monomeAdc(int x, float value) {
		allmodes.getControllerModel().monomeAdc(x, value);
	}

	void clipLaunch(int pitch, int vel, int channel)
	{
		//Does pressing stop send a midi note?
		//System.out.println("CLIP LAUNCH PITCH: " + pitch + " VEL: " + vel + " CHAN: " + channel);
		allmodes.getMelodizer1Model().clipLaunch(pitch, vel, channel);
	}

	/***
	 * 
	 * @param xmlDoc
	 * @return Returns whether or not the xml file loaded is a PatchPack (as opposed to a single patch)
	 */

	@SuppressWarnings("unchecked")
	public boolean loadXML(Document xmlDoc)
	{
		if(xmlDoc.getRootElement().getName().equals("SevenUpPatch"))
		{
			loadXMLPatch(xmlDoc.getRootElement());
			return false;
		}
		else if(xmlDoc.getRootElement().getName().equals("SevenUpPatchPack"))
		{
			System.out.println("Loading patch pack.");
			xmlPatches = new ArrayList<Element>();
			Element Patch;
			String patchName;
			Iterator<Element> itr = xmlDoc.getRootElement().getChildren().iterator();
			while (itr.hasNext()) {
				Patch = (Element)itr.next();
				patchName = Patch.getAttributeValue("patchName");
				System.out.println("Appending patch: " + patchName);
				xmlPatches.add(Patch);
			}

			//Load the first patch in the group
			loadXMLPatch(xmlPatches.get(0));
			return true;
		}
		else
		{
			System.out.println("**Badly formed XML");
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	public void loadXMLPatch(Element patch)
	{
		Element xmlState = patch;

		areLoopsGated = "true".equals((xmlState.getAttributeValue("areLoopsGated")));
		try
		{
			patchTitle = xmlState.getAttributeValue("patchName");
		}
		catch(Exception e)
		{
			System.out.println("No valid patch title in XML");
			patchTitle = "";
		}

		List<Element> xmlStateChildren = xmlState.getChildren();

		for  (Element xmlStateChild: xmlStateChildren) {

			if(xmlStateChild.getName().equals("patternizer"))
			{
				System.out.println("Loading PATTERNIZER...");
				allmodes.getPatternizerModel().loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("sequencer"))
			{
				System.out.println("Loading SEQUENCER...");
				allmodes.getSequencer().loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("looper"))
			{
				System.out.println("Loading LOOPER...");
				allmodes.getLooper().loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("loopRecorder"))
			{
				System.out.println("Loading LOOPRECORDER...");
				allmodes.getLoopRecorder().loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("melodizer"))
			{
				System.out.println("Loading MELODIZER...");
				allmodes.getMelodizer1Model().loadXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("melodizer2"))
			{
				System.out.println("Loading MELODIZER2...");
				allmodes.getMelodizer2Model().loadXMLElement(xmlStateChild);
			}
		}

	}

	public void setMelody1Scale(Scale newScale)
	{
		allmodes.getMelodizer1Model().setScale(newScale);
	}

	public void setMelody2Scale(Scale newScale)
	{
		allmodes.getMelodizer2Model().setScale(newScale);
	}

	public Scale getMelody1Scale()
	{
		return allmodes.getMelodizer1Model().getScale();
	}

	public Scale getMelody2Scale()
	{
		return allmodes.getMelodizer2Model().getScale();
	}

	public void setLoopChoke(int loopNum, int chokeGroup)
	{
		allmodes.getLooper().getLoop(loopNum).setChokeGroup(chokeGroup);
	}

	public int getLoopChokeGroup(int loopNum)
	{
		return allmodes.getLooper().getLoop(loopNum).getChokeGroup();
	}

	public void setMel1TransposeGroup(int slotNum, int group)
	{
		allmodes.getMelodizer1Model().setTransposeGroup(slotNum, group);
	}

	public void setMel2TransposeGroup(int slotNum, int group)
	{
		allmodes.getMelodizer2Model().setTransposeGroup(slotNum, group);
	}

	public int getMel1TransposeGroup(int slotNum)
	{
		return allmodes.getMelodizer1Model().getTransposeGroup(slotNum);
	}

	public int getMel2TransposeGroup(int slotNum)
	{
		return allmodes.getMelodizer2Model().getTransposeGroup(slotNum);
	}
	
	public boolean getMel1TransposeSustain(int slotNum)
	{
		return allmodes.getMelodizer1Model().getTransposeSustain(slotNum);
	}
	
	public boolean getMel2TransposeSustain(int slotNum)
	{
		return allmodes.getMelodizer2Model().getTransposeSustain(slotNum);
	}
	
	public void setMel1TransposeSustain(int slotNum, boolean value)
	{
		allmodes.getMelodizer1Model().setTransposeSustain(slotNum, value);
	}
	
	public void setMel2TransposeSustain(int slotNum, boolean value)
	{
		allmodes.getMelodizer2Model().setTransposeSustain(slotNum, value);
	}

	public Document toXMLDocument(String fileName)
	{
		//Add logic to convert all grids to XML data here
		Element xmlState = new Element("SevenUpPatch");
		xmlState.setAttribute(new Attribute("areLoopsGated", areLoopsGated.toString()));		
		xmlState.setAttribute(new Attribute("patchName", fileName));	

		//Create PATTERNIZER
		Element xmlPatternizer = allmodes.getPatternizerModel().toJDOMXMLElement();

		//Create SEQUENCER
		Element xmlSequencer = allmodes.getSequencer().toJDOMXMLElement();

		//Create LOOPER
		Element xmlLooper = allmodes.getLooper().toJDOMXMLElement();

		//Create LoopRecorder
		Element xmlLoopRecorder = allmodes.getLoopRecorder().toJDOMXMLElement();

		//Create CHOPPER
		//XMLElement xmlChopper = chopper.toXMLElement();

		//Create MELODIZER1
		Element xmlMelodizer = allmodes.getMelodizer1Model().toXMLElement("melodizer");

		//Create MELODIZER2
		Element xmlMelodizer2 = allmodes.getMelodizer2Model().toXMLElement("melodizer2");

		//Add modes to xmlState
		xmlState.addContent(xmlPatternizer);
		xmlState.addContent(xmlSequencer);
		xmlState.addContent(xmlLooper);
		xmlState.addContent(xmlLoopRecorder);
		xmlState.addContent(xmlMelodizer);
		xmlState.addContent(xmlMelodizer2);

		return new Document(xmlState);
	}

	protected void finalize() {

	}

	public void setGateLoopChokes(boolean _gateLoopChokes) {
		allmodes.getLooper().setGateLoopChokes(_gateLoopChokes);
		System.out.println("mtn: gated loops set to " + _gateLoopChokes);
	}

	public boolean getGateLoopChokes()
	{
		// @TODO this looks hackish, why are we setting in a getter?
		return allmodes.getLooper().getGateLoopChokes();
	}

	public int loadPrevPatch() {
		if(curPatchIndex > 0)
		{
			loadXMLPatch(xmlPatches.get(curPatchIndex-1));
			curPatchIndex -=1;
		}
		return curPatchIndex;
	}

	public int loadNextPatch() {
		if(curPatchIndex < xmlPatches.size()-1)
		{
			loadXMLPatch(xmlPatches.get(curPatchIndex+1));
			curPatchIndex+=1;
		}
		return curPatchIndex;
	}

	public int getPatchPackSize()
	{
		return xmlPatches.size();
	}

	public String getPatchTitle() {
		return patchTitle;
	}

	public void setPatchTitle(String patchTitle) {
		this.patchTitle = patchTitle;
	}

	public void setLooperMute(boolean mute) {
		allmodes.getLooper().setLooperMute(mute);
	}

	public void setMel1RecMode(int melRecMode) {
		allmodes.getMelodizer1Model().setRecMode(melRecMode);
	}

	public void setMel2RecMode(int melRecMode) {
		allmodes.getMelodizer2Model().setRecMode(melRecMode);
	}

	public int getMel1RecMode() {
		return allmodes.getMelodizer1Model().getRecMode();
	}

	public int getMel2RecMode() {
		return allmodes.getMelodizer2Model().getRecMode();
	}

	public void setLoopLength(int loopNum, float length) {
		allmodes.getLooper().getLoop(loopNum).setLength(length);
	}

	public void setLoopType(int loopNum, int type) {
		allmodes.getLooper().getLoop(loopNum).setType(type);
	}

	public void extNoteOn(Note note, int channel) {
		allmodes.getMelodizer2Model().extNoteOn(note, channel);
	}

	public float getLoopLength(int loopNum) {
		return allmodes.getLooper().getLoop(loopNum).getLength();
	}

	public int getLoopType(int loopNum) {
		return allmodes.getLooper().getLoop(loopNum).getType();
	}
	
	public void setMelody1ClipMode(boolean b) {
		if (b) {
			AllModes.getInstance().getMelodizer1Model().setCurrentMode(MelodizerModel.eMelodizerMode.CLIP);
		} else {
			AllModes.getInstance().getMelodizer1Model().setCurrentMode(MelodizerModel.eMelodizerMode.KEYBOARD);
		}
		
	}
	
	public void setMelody1Mode(MelodizerModel.eMelodizerMode mode) {
		AllModes.getInstance().getMelodizer1Model().setCurrentMode(mode);
	}
	
	public MelodizerModel.eMelodizerMode getMelody1Mode()
	{
		return AllModes.getInstance().getMelodizer1Model().getCurrentMode();
	}
	
	public void setMelody2Mode(MelodizerModel.eMelodizerMode mode) {
		AllModes.getInstance().getMelodizer2Model().setCurrentMode(mode);
	}
	
	public MelodizerModel.eMelodizerMode getMelody2Mode()
	{
		return AllModes.getInstance().getMelodizer2Model().getCurrentMode();
	}
	
	public void setMelody1Transpose(boolean transpose) {
		AllModes.getInstance().getMelodizer1Model().setTranspose(transpose);
	}

	public boolean getMelody1Transpose() {
		return AllModes.getInstance().getMelodizer1Model().getTranspose();
	}
	
	public void setMelody1AltMode(MelodizerModel.eMelodizerMode mode) {
		AllModes.getInstance().getMelodizer1Model().setAltMode(mode);
	}
	
	public MelodizerModel.eMelodizerMode getMelody1AltMode()
	{
		return AllModes.getInstance().getMelodizer1Model().getAltMode();
	}
	
	public void setMelody2Transpose(boolean transpose) {
		AllModes.getInstance().getMelodizer2Model().setTranspose(transpose);
	}

	public boolean getMelody2Transpose() {
		return AllModes.getInstance().getMelodizer2Model().getTranspose();
	}

	public void setMelody2AltMode(MelodizerModel.eMelodizerMode mode) {
		AllModes.getInstance().getMelodizer2Model().setAltMode(mode);
	}
	
	public MelodizerModel.eMelodizerMode getMelody2AltMode()
	{
		return AllModes.getInstance().getMelodizer2Model().getAltMode();
	}

	/**
	 * Turn on or off calibration mode.
	 * After mode is turned off, calibration is done 
	 * as long as a full range of ADC data was being fed
	 * during the time calibration was turned on. 
	 * @param on
	 */
	public void setADCCalibrateMode(boolean on) {
		allmodes.getControllerModel().setADCCalibrationMode(on);
		
		if (protocol.version == ProtocolVersion.serialosc) { // for serialosc we need to turn on automagically
			this.setADC(0, allmodes.getControllerModel().shouldADCBeOn());
			this.setADC(1, allmodes.getControllerModel().shouldADCBeOn());
			this.setADC(2, allmodes.getControllerModel().shouldADCBeOn());
		}
	}
	
	/**
	 * Turn on or off listening to ADC messages
	 * @param on
	 */
	public void setADCActive(boolean on) {
		allmodes.getControllerModel().setADCActive(on);
		
		if (protocol.version == ProtocolVersion.serialosc) { // for serialosc we need to turn on automagically
			this.setADC(0, allmodes.getControllerModel().shouldADCBeOn());
			this.setADC(1, allmodes.getControllerModel().shouldADCBeOn());
			this.setADC(2, allmodes.getControllerModel().shouldADCBeOn());
		}
	}
	
	public void reset() {
		for(int i=0;i<7;i++)
		{
			//If the loop is already playing, set the step to 0
			if(allmodes.getLooper().isLoopPlaying(i))
			{
				allmodes.getLooper().getLoop(i).setStep(0);
				allmodes.getLooper().sendCtrlVal(i, 0);
			}

			if(allmodes.getLoopRecorder().isLoopSequencePlaying(i))
			{
				allmodes.getLoopRecorder().stopLoopSequence(i);
				allmodes.getLoopRecorder().playLoopSequence(i);
			} 

			if(allmodes.getMelodizer1Model().getSeqStatus(i) == MonomeUp.PLAYING)
			{
				allmodes.getMelodizer1Model().stopSeq(i);
				allmodes.getMelodizer1Model().playSeq(i);
			}	

			if(allmodes.getMelodizer2Model().getSeqStatus(i) == MonomeUp.PLAYING)
			{
				allmodes.getMelodizer2Model().stopSeq(i);
				allmodes.getMelodizer2Model().playSeq(i);
			}	
		}

		allmodes.getPatternizerModel().curPatternRow = 0;
		allmodes.getSequencer().curSeqRow = 0;
	}
	
	public Document toJDOMXMLDocument(String fileName)
	{
		setPatchTitle(fileName);
		return toXMLDocument(fileName);
	}
	
	public boolean loadJDOMXMLDocument(Document XMLDoc)
	{
		return loadXML(XMLDoc);
	}
	
	/**
	 * Receive notes from Live that tell SevenUp where the beat is
	 * @param note
	 */
	
	// @TODO We need this connected still to receive external midi events
	void noteOn(int noteOnPitch)
	{
		if(noteOnPitch == E4)
		{
			loadPrevPatch();
		}
		else if(noteOnPitch == F4)
		{
			loadNextPatch();
		}
		//Reset all modes
		else if(noteOnPitch == FSHARP4)
		{
			allmodes.getSequencer().reset();
			allmodes.getLooper().reset();
			allmodes.getMelodizer1Model().reset();
			allmodes.getMelodizer2Model().reset();
		}
		//Start loops
		else if(noteOnPitch >= C2 && noteOnPitch < G2){
			int loopNum = noteOnPitch - C2;
			AllModes.getInstance().getLooper().playLoop(loopNum, 0);
			AllModes.getInstance().getLooper().stepOneLoop(loopNum);

		}
		//Stop loops
		else if(noteOnPitch >= G2 && noteOnPitch <= CSHARP3){
			int loopNum = noteOnPitch - G2;
			AllModes.getInstance().getLooper().stopLoop(loopNum);
		}
	}
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public boolean isDirty() {
		return dirty;
	}


	//////////////////////////////
	// SevenUpClock interface
	
	public void startClock() {
		// Do nothing
	}
	
	public void stopClock() {
		reset();
	}

	public void sendFirstLocator() {
		allmodes.getMelodizer1Model().locatorEvent();
		allmodes.getMelodizer2Model().locatorEvent();
		allmodes.getMasterizer().firstLocatorEvent();
		allmodes.getMasterizer().updateDisplayGrid();
	}

	public void pumpSequencerHeart() {
		for (DisplayGrid grid : grids) {
			grid.displayCursor();
		}
		// Make sure we only step once
		allmodes.getSequencer().step();
	}

	public void sendSecondLocator() {
		allmodes.getMasterizer().secondLocatorEvent();
		allmodes.getMasterizer().updateDisplayGrid();
	}
	
	public void sendLocatorRecord() {
		allmodes.getMasterizer().recordLocatorEvent();
		allmodes.getMasterizer().updateDisplayGrid();
	}

	public void pump64th() {
		allmodes.getMelodizer1Model().heartbeat();
		allmodes.getMelodizer2Model().heartbeat();
		
		// Loop recorder needs smaller ticks to setup next step before taking it
		allmodes.getLoopRecorder().tick();
	}

	public void pumpLooperHeart() {
		allmodes.getLoopRecorder().updateDisplayGrid();
		allmodes.getLooper().step();
		allmodes.getLoopRecorder().step();  
	}
	
}