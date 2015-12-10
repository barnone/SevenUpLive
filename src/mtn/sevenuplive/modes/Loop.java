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

import org.jdom.Attribute;
import org.jdom.Element;

public class Loop {
	
	/** Loop types */
	
	/** Regular loop that keeps looping */
	public static final int LOOP = 0; 
	
	/**
	 * Step mode is actually the original loop mode in SevenUp.
	 * It loops the sample by retriggering the sample at each step
	 * using an appropriate sample offset.
	 */
	public static final int STEP = 1;    
	
	/** Loop that stops at end of one iteration. Steps like a regular loop and can be choked or killed. */
	public static final int SHOT = 2;
	
	/** Triggers a loop and keeps stepping loop until button is released. */
	public static final int MOMENTARY = 3;  

	/** 
	 * Triggers the sample mapped to a step and plays till end of that sample regardless of key release. Super useful for multi
	 * samples such as drums where 8 samples are mapped across the velocity range 
	 * Can also be used with multi-samples that are much longer.
	 */
	public static final int HIT = 4;    
	
	/** 
	 * Triggers the sample mapped to a step and plays till end of that step regardless of key release. Super useful for triggering
	 * just a sample slice where you want it to play just that slice.
	 */
	public static final int SLICE = 5;    
	
	
	/** Value indicating the loop is not playing */ 
	public static final int NOT_PLAYING = -1;
	
	/** Value indicating choke group is off */ 
	public static final int NO_CHOKE_GROUP = -1;
	
	/** Loop resolution which can be from (1 to 32) measures long */
	private int resolution = DEFAULT_RESOLUTION;
	private final static int DEFAULT_RESOLUTION = 2;

	/** Current step index or {@link #NOT_PLAYING} */
	private int step;
	private int iteration = 0;
	private int startOffset;
	private int resCounter;
	/** Determines if a step has been retriggered  */
	private boolean[] trigger;
	/** Last step that was triggered */
	private int lastTriggeredStep = -1;
	
	/** The type of loop it is */
	private int type = LOOP;
	
	private int pressedRow;
	
	/** Current chokeGroup or {@link #NO_CHOKE_GROUP} */
	private int chokeGroup;
	
	public Loop()
	{
		step = NOT_PLAYING; //not playing currently
		chokeGroup = NO_CHOKE_GROUP; //Default to no choke group
		trigger = new boolean[8];
	}
	
	public boolean isPlaying()
	{
		return step != NOT_PLAYING;
	}
	
	public int getIteration()
	{
		return iteration;
	}
	
	public int getStep()
	{
		return step;
	}
	
	public boolean getTrigger(int step)
	{
		return trigger[step];
	}
	
	public void setTrigger(int step, boolean value)
	{
		trigger[step] = value;
		if (value == true)
			lastTriggeredStep = step;
	}
	
	public int getLastTriggedStep()
	{
		return lastTriggeredStep;
	}
	
	public void stop()
	{
		for (int i = 0; i < 7; i++) {
			setTrigger(i, false);
		}
		step = NOT_PLAYING;
		iteration = 0;
	}

	public int getResolution()
	{
		return resolution;
	}
	
	/**
	 * Increment to the next step.
	 * This sets the current step to the new value.
	 * and also resets resCounter to 0
	 * @param step
	 */
	public void nextStep() {
		// When we change a step, res needs to reset to 0.
		resCounter = 0;
		
		step++;
		if(this.step > 7) {
			this.step = 0;
			iteration++;
		}	
	}
	
	/**
	 * Sets the current step
	 * resets resCounter to 0
	 * @param step
	 */
	public void setStep(int step)
	{
		// Check for out of bounds
		if (step > 7)
			throw new IndexOutOfBoundsException("Step [" + Integer.toString(step) + "] is out of range: [0-" + Integer.toString(7) + "]"); 
		
		// When we change a step, res needs to reset to 0.
		resCounter = 0;	
		this.step = step;
	}
	
	public int getStartOffset()
	{
		return startOffset;
	}
	
	public void setStartOffset(int startOffset)
	{
		this.startOffset = startOffset;
	}
	
	public int getResCounter() {
		return resCounter;
	}
	
	public int getChokeGroup()
	{
		return chokeGroup;
	}
	
	public void setChokeGroup(int chokeGroup)
	{
		this.chokeGroup = chokeGroup;
	}
	
	/**
	 * Go to the next resCount.
	 * This will cycle back to zero when we hit the end.
	 */
	public void nextResCount()
	{
		resCounter++;
		if (resCounter % (resolution) == 0)
		{
			// Move to next step which also resets resCounter to 0
			nextStep();
		}
	}

	/**
	 * Is this the last resolution tick in the loop 
	 */
	public boolean isLastResStep()
	{
		int testRes = resCounter + 1;
		
		if (testRes % (resolution) == 0 && this.step == 7) 
			return true;
		
		return false;
	}
	
	/**
	 * Is this the last resolution tick in the step 
	 */
	public boolean isLastResInStep()
	{
		int testRes = resCounter + 1;
		
		if (testRes % (resolution) == 0) 
			return true;
		
		return false;
	}

	public void setLength(Float length) {
		//resolution of 0 + 1 = 1/2 measure
		//Meaning, stepping every 1 16th note will produce 1/2 measure in 8 steps
		this.resolution = new Float(length * 2).intValue();
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	public int getPressedRow()
	{
		return pressedRow;
	}
	
	public void setPressedRow(int pressedRow)
	{
		this.pressedRow = pressedRow;
	}

	
	public Element toJDOMXMLElement()
	{
		Element xmlLoop = new Element("loop");
		xmlLoop.setAttribute(new Attribute("type", ((Integer)type).toString()));
		xmlLoop.setAttribute(new Attribute("resolution", ((Integer)resolution).toString()));
		xmlLoop.setAttribute(new Attribute("startOffset",((Integer)startOffset).toString()));
		xmlLoop.setAttribute(new Attribute("resCounter",((Integer)resCounter).toString()));
		xmlLoop.setAttribute(new Attribute("pressedRow",((Integer)pressedRow).toString()));
		xmlLoop.setAttribute(new Attribute("chokeGroup", ((Integer)chokeGroup).toString()));
		
		return xmlLoop;
	}
	
	public void loadJDOMXMLElement(Element xmlLoop)
	{
		step = NOT_PLAYING; //not playing currently
		type = xmlLoop.getAttributeValue("type") == null ? LOOP : Integer.parseInt(xmlLoop.getAttributeValue("type"));
		resolution = xmlLoop.getAttributeValue("resolution") == null ? DEFAULT_RESOLUTION : Integer.parseInt(xmlLoop.getAttributeValue("resolution"));
		startOffset = xmlLoop.getAttributeValue("startOffset") == null ? ModeConstants.NOT_SET : Integer.parseInt(xmlLoop.getAttributeValue("startOffset"));
		resCounter = xmlLoop.getAttributeValue("resCounter") == null ? ModeConstants.NOT_SET : Integer.parseInt(xmlLoop.getAttributeValue("resCounter"));
		pressedRow = xmlLoop.getAttributeValue("pressedRow") == null ? ModeConstants.NOT_SET : Integer.parseInt(xmlLoop.getAttributeValue("pressedRow"));
		chokeGroup = xmlLoop.getAttributeValue("chokeGroup") == null ? NO_CHOKE_GROUP: Integer.parseInt(xmlLoop.getAttributeValue("chokeGroup"));
	}

	public float getLength() {
		return new Float(this.resolution / 2.0);
	}
}
