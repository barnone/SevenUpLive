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
import mtn.sevenuplive.max.mxj.SevenUpClock;
import mtn.sevenuplive.scales.Scale;
import mtn.sevenuplive.scales.ScaleName;

import org.jdom.Document;

import proxml.XMLInOut;

public class SevenUpEnvironment {
	
	private MonomeUp m;

	private SevenUpApplet applet;

	private Scale monomeScale;

	private ConnectionSettings sevenUpConnections;

	private int monomeType = MonomeUp.MONOME_64;
	
	private M4LMidi midiIO;
	
	private XMLInOut xmlIO;
	
	//M4LMidiSystem.init(this);
	public SevenUpEnvironment(M4LMidi midiIO, ConnectionSettings sevenUpConnections) {
		this.sevenUpConnections = sevenUpConnections;
		this.midiIO = midiIO;
	}
	
	public SevenUpClock getClock() {
		if (applet != null) {
			return applet;
		}
		return null;
	}
	
	public MonomeUp getMonome() {
		return m;
	}
	
	public boolean isStarted() {
		if (applet != null) {
			if (applet.isRunning()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean startSevenUp() {
		if (isStarted()) 
			return false;
	
		newMonome();
		applet = new SevenUpApplet(m, sevenUpConnections, midiIO);
		applet.setVisible(false);
		xmlIO = new XMLInOut(applet);
	
		return true;
	}

	public boolean stopSevenUp() {
		if (applet == null) {
			return false;
		} else {
			try {
				if (m != null) {
					m.forceShutdown();
				}
				applet.stop();
			} catch (Throwable e) {
				applet = null;
				e.printStackTrace();
			}
			applet = null;
		}
		return true;
	}
	
	private void newMonome() {
		monomeScale = new Scale(ScaleName.Major);

		// Get the type of monome selected
		monomeType = sevenUpConnections.monomeType;

		MonomeUp.eDeviceType deviceType = MonomeUp.eDeviceType.Monome;
		
		// Figure out dimensions of monome grid
		int x_grids = 1;
		int y_grids = 1;
		
		if (sevenUpConnections.gridx > 0 && sevenUpConnections.gridy > 0) {
			x_grids = sevenUpConnections.gridx;
			y_grids = sevenUpConnections.gridy;
		} else {
			switch (monomeType) {
			case 0: //1 x 64
				x_grids=1; y_grids=1;
				break;
			case 1: //128H
				x_grids=2; y_grids=1;
				break;
			case 2: //128V
				x_grids=1; y_grids=2;
				break;
			case 3: //3 x 64's
				x_grids=1; y_grids=3;
				break;
			case 4: //256
				x_grids=2; y_grids=2;
				break;
			case 5: //2x256
				x_grids=4; y_grids=2;
				break;
			case 6: //3x256
				x_grids=6; y_grids=2;
				break;	   
			case 7:
				x_grids = 1; y_grids = 5;
				break;
			case 8:
				x_grids = 1; y_grids = 6;
				break;
			case 9:
				x_grids = 1; y_grids = 7;
				break;
			case 10:
				x_grids = 1; y_grids = 8;
				break;
			case 11:
				x_grids = 1; y_grids = 9;
				break;
			case 12: //10 x 64's
				x_grids = 1; y_grids = 10;
				break;
			case 13: //MultiColorDevice
				x_grids = 1; y_grids = 1;
				deviceType = MonomeUp.eDeviceType.MultiColorDevice;
				break;
			case 14: //MultiColor256
				x_grids = 2; y_grids = 2;
				deviceType = MonomeUp.eDeviceType.MultiColorDevice;
				break;
			case 15: //MantaV2
				x_grids = 1; y_grids = 1;
				deviceType = MonomeUp.eDeviceType.Manta;
				break;	
			};
		}
		m = new MonomeUp(deviceType, x_grids, y_grids, sevenUpConnections, monomeScale, midiIO);
		
	}

	public ConnectionSettings getSevenUpConnections() {
		return sevenUpConnections;
	}

	public void setSevenUpConnections(ConnectionSettings sevenUpConnections) {
		this.sevenUpConnections = sevenUpConnections;
	}

	public void setMonomeType(int monomeType) {
		this.monomeType = monomeType;
	}

	public int getMonomeType() {
		return monomeType;
	}

	public int getLoopType(int loopNum) {
		return m.getLoopType(loopNum);
	}

	public void finalize()
	{
		if (m != null) {
			m.lightsOff();
		}
		m = null;
	}

	public boolean openSevenUpPatch(String patchPath)
	{
		if (xmlIO == null)
			return false;

		try
		{
			xmlIO.loadElement(patchPath);
			m.setDirty(false);
			return true;
		}catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public Document toJDOMXMLDocument(String fileName)
	{
		return m.toJDOMXMLDocument(fileName);
	}

	public boolean loadJDOMXMLDocument(Document XMLDoc)
	{
		return m.loadJDOMXMLDocument(XMLDoc);
	}

}
