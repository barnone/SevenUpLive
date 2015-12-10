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

import org.jdom.Attribute;
import org.jdom.Element;

public class SequenceBank {
	
	private boolean rowPatterns[][];

	public SequenceBank()
	{
		rowPatterns = new boolean[8][7];
		//Set default to first pattern for each row
		for(int i=0;i<8;i++)
			enablePatternAtRow(i, 0);
	}
	
	public void enablePatternAtRow(int rowNum, int patNum)
	{
		rowPatterns[rowNum][patNum] = true;
	}
	
	public void disablePatternAtRow(int rowNum, int patNum)
	{
		rowPatterns[rowNum][patNum] = false;
	}
	
	public void switchPatternAtRow(int rowNum, int patNum)
	{
		rowPatterns[rowNum][patNum] = !rowPatterns[rowNum][patNum];
	}
	
	public int getEnabledPatternInRow(int rowNum)
	{
		for(int i=0;i<7;i++)
		{
			if(rowPatterns[rowNum][i])
				return i;
		}
		
		return 0;
	}
	
	public boolean isPatternEnabledAtRow(int patNum, int rowNum)
	{
		return rowPatterns[rowNum][patNum];
	}

	@SuppressWarnings("unchecked")
	public void loadXml(Element xmlSequenceBank) {
		
		//Clear current patterns
		rowPatterns = new boolean[8][7];

		List<Element> xmlRows;
		List<Element> xmlPatterns;
		Integer rowNum;
		Integer patternNum;
		
		xmlRows = xmlSequenceBank.getChildren();
		
		int outerindex = 0;
		for (Element xmlRow : xmlRows)
		{
			rowNum = xmlRow.getAttributeValue("row") == null ? outerindex : Integer.parseInt(xmlRow.getAttributeValue("row"));
			xmlPatterns = xmlRow.getChildren();
			
			int innerindex = 0;
			for (Element xmlPattern : xmlPatterns)
			{
				patternNum = xmlPattern.getAttributeValue("patternNum") == null ? innerindex : Integer.parseInt(xmlPattern.getAttributeValue("patternNum"));
				rowPatterns[rowNum][patternNum] = true;
				innerindex++;
			}
			outerindex++;
		}
	}
	
	public Element toXmlElement()
	{
		Element xmlSequenceBank;
		Element xmlSequenceRow;
		Element xmlPattern;
		
		xmlSequenceBank = new Element("sequenceBank");
		
		for(Integer i=0;i<rowPatterns.length;i++)
		{
			xmlSequenceRow = new Element("sequenceRow");
			xmlSequenceRow.setAttribute(new Attribute("row", i.toString()));
			for(Integer j=0;j<rowPatterns[i].length;j++)
			{
				if(rowPatterns[i][j])
				{
					xmlPattern = new Element("pattern");
					xmlPattern.setAttribute(new Attribute("patternNum", j.toString()));
					xmlSequenceRow.addContent(xmlPattern);
				}
			}
			xmlSequenceBank.addContent(xmlSequenceRow);
		}
		
		return xmlSequenceBank;
	}
}
