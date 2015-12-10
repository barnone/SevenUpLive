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


import java.util.List;

import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.main.MonomeUp;
import mtn.sevenuplive.modes.events.EventDispatcherImpl;

import org.jdom.Attribute;
import org.jdom.Element;

public class PatternizerModel extends EventDispatcherImpl {
	
	public int patternGrids[][][];
	private int basePitch = MonomeUp.C3;
	private int pressedNavButtons[];
	public int selectedPattern = 0;
	public int curPatternRow = 0;
	private M4LMidiOut[] midiSampleOut;

	public PatternizerModel(int _navRow, M4LMidiOut[] _midiSampleOut, int grid_width, int grid_height) {
		midiSampleOut = _midiSampleOut;
		patternGrids = new int[7][7][8];
	}

	public void press(int x, int y)
	{
		if(x != DisplayGrid.NAVCOL)
		{
			//If pressed, change from off to fast, or fast to solid, or solid to off
			 if (patternGrids[selectedPattern][x][y] == DisplayGrid.OFF)
				 patternGrids[selectedPattern][x][y] = DisplayGrid.FASTBLINK;
			 else if (patternGrids[selectedPattern][x][y] == DisplayGrid.FASTBLINK)
				 patternGrids[selectedPattern][x][y] = DisplayGrid.SOLID;
			 else if (patternGrids[selectedPattern][x][y] == DisplayGrid.SOLID)
				 patternGrids[selectedPattern][x][y] = DisplayGrid.OFF;
		}
	}
	
	public void release(int x, int y)
	{
		pressedNavButtons[y] = 0;
	}
	
	
	
	public void clearPattern(int patternNum)
	{
		patternGrids[patternNum] = new int[7][8];
	}
	
	/***
	 * 
	 * @param curPatterns array of booleans indicating which patterns are to step
	 * @return true if the pattern starts over, false if not
	 */
	public boolean step(int patternNum)
	{
		int sendPitch;
		int sendVel;
		Note noteSend;
		
			//Play pattern samples
	        for(int x = 0; x < 7; x++)
	        {
	          if(patternGrids[patternNum][x][curPatternRow] != DisplayGrid.OFF)
	          {
	            //set pitch
	            switch(x)
	            {
	              case 0: sendPitch = basePitch; break;
	              case 1: sendPitch = basePitch + 1; break;
	              case 2: sendPitch = basePitch + 2; break;
	              case 3: sendPitch = basePitch + 3; break;
	              case 4: sendPitch = basePitch + 4; break;
	              case 5: sendPitch = basePitch + 5; break;
	              case 6: sendPitch = basePitch + 6; break;
	              default: sendPitch = basePitch;
	            }
	
	            if (patternGrids[patternNum][x][curPatternRow] == DisplayGrid.FASTBLINK)
	            	sendVel = 42;
	            else sendVel = 126;
	
	            noteSend = new Note(sendPitch,sendVel, 0);
	            
	            midiSampleOut[patternNum].sendNoteOn(noteSend);
	          } 
	        }
        curPatternRow++;
        if(curPatternRow == 8)
        {
        	curPatternRow = 0;
        	return true;
        }
        else
        	return false;
	}

	public Element toJDOMXMLElement()
	{
		 Element xmlPatternizer = new Element("patternizer");
		 Element xmlPattern;
		 Element xmlKeyPress;
		 
		 xmlPatternizer.setAttribute(new Attribute("selectedPattern", (((Integer)selectedPattern).toString())));
	 	
	 	for(Integer i=0;i<patternGrids.length;i++)
	 	{
	 			xmlPattern = new Element("pattern");
	 			xmlPattern.setAttribute(new Attribute("patternNum", i.toString()));
	 			
		 		for(int j=0;j<patternGrids[i].length;j++)
		 		{
		 			for(int k=0;k<patternGrids[i][j].length;k++)
		 			{
		 				if(patternGrids[i][j][k] != DisplayGrid.OFF)
		 				{
		 					xmlKeyPress = new Element("keyPress");
		 					xmlKeyPress.setAttribute(new Attribute("col", ((Integer)j).toString()));
		 					xmlKeyPress.setAttribute(new Attribute("row", ((Integer)k).toString()));
		 					Integer value = patternGrids[i][j][k];
		 					xmlKeyPress.setAttribute(new Attribute("value", value.toString()));
		 					xmlPattern.addContent(xmlKeyPress);
		 				}
		 			}
		 		}
		 		
		 		xmlPatternizer.addContent(xmlPattern);
	 	}

		return xmlPatternizer;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlPatternizer)
	{
		//Clear current values
		patternGrids = new int[7][7][8];
		
		List<Element> xmlPatterns;
		List<Element> xmlKeyPresses;
		Integer col;
		Integer row;
		Integer value;
		 
		selectedPattern = Integer.parseInt(xmlPatternizer.getAttribute("selectedPattern").getValue());
		 
		xmlPatterns = xmlPatternizer.getChildren();
		for (Element xmlPattern : xmlPatterns)
		{
			Integer patternNum = Integer.parseInt(xmlPattern.getAttribute("patternNum").getValue());
			
			xmlKeyPresses = xmlPattern.getChildren();
			for(Element xmlKeyPress : xmlKeyPresses)
			{
				col = Integer.parseInt(xmlKeyPress.getAttributeValue("col"));
				row = Integer.parseInt(xmlKeyPress.getAttributeValue("row"));
				value = Integer.parseInt(xmlKeyPress.getAttributeValue("value"));
				
				patternGrids[patternNum][col][row] = value;
			}
		}
	}

}
