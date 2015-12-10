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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import mtn.sevenuplive.main.MonomeUp;
import mtn.sevenuplive.modes.AllModes;
import mtn.sevenuplive.modes.events.UpdateDisplayEvent;
import mtn.sevenuplive.modes.events.UpdateNavEvent;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.cycling74.max.Atom;

public class SevenUp4LivePatchManager {
	
	private SevenUp4Live app;
	
	public SevenUp4LivePatchManager(SevenUp4Live app) {
		this.app = app;
	}
	
	public void savePatch(String filepath) {

		FileWriter fileWriter = null;
		
		try {
			if (filepath != null) {
				java.io.File file = new File(filepath);
				org.jdom.Document doc = app.getEnvironment().toJDOMXMLDocument(file.getName());
				org.jdom.output.XMLOutputter fmt = new XMLOutputter();

				fileWriter = new FileWriter(file);
				fmt.output(doc, fileWriter);
			}
		} catch (Exception e) {
			SevenUp4Live.post("Could not save patch: " + e);
		} finally { // standard java closing resources in finally
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e1) {
					// drop it
				}
			}	
		}
	}
	
	public void loadPatch(String filepath) {
		
		try {
			if (filepath != null) {
				java.io.File file = new File(filepath);
				SAXBuilder builder = new SAXBuilder();
				try
				{
					Document doc = builder.build(file);
					app.getEnvironment().loadJDOMXMLDocument(doc);
					
					// OK Push everything to the UI
					pushAllPatchSettings();
					
					// Make sure all views are updated
					AllModes.getInstance().getMelodizer1Model().sendEvent(new UpdateDisplayEvent());
					AllModes.getInstance().getMelodizer2Model().sendEvent(new UpdateDisplayEvent());
					AllModes.getInstance().getPatternizerModel().sendEvent(new UpdateDisplayEvent());
					AllModes.getInstance().getControllerModel().sendEvent(new UpdateDisplayEvent());
					AllModes.getInstance().getSequencer().sendEvent(new UpdateDisplayEvent());
					AllModes.getInstance().getMasterizer().sendEvent(new UpdateDisplayEvent());
					AllModes.getInstance().getLooper().sendEvent(new UpdateDisplayEvent());
					AllModes.getInstance().getLoopRecorder().sendEvent(new UpdateDisplayEvent());
					
					AllModes.getInstance().getMelodizer1Model().sendEvent(new UpdateNavEvent());
					AllModes.getInstance().getMelodizer2Model().sendEvent(new UpdateNavEvent());
					AllModes.getInstance().getPatternizerModel().sendEvent(new UpdateNavEvent());
					AllModes.getInstance().getControllerModel().sendEvent(new UpdateNavEvent());
					AllModes.getInstance().getSequencer().sendEvent(new UpdateNavEvent());
					AllModes.getInstance().getMasterizer().sendEvent(new UpdateNavEvent());
					AllModes.getInstance().getLooper().sendEvent(new UpdateNavEvent());
					AllModes.getInstance().getLoopRecorder().sendEvent(new UpdateNavEvent());
				}
				catch(Exception ex)
				{
					SevenUp4Live.post(ex.getMessage());   
				}
			}
		} catch (Exception e) {
			SevenUp4Live.post("Could not save patch: " + e);
		} 	

	}


	/**
	 * Push all Patch Settings to the UI
	 */
	private void pushAllPatchSettings() {
		MonomeUp m = app.getEnvironment().getMonome();
		
		pushLooperSettings(m);
		pushMelodizer1Settings(m);
		pushMelodizer2Settings(m);
		pushControllerSettings(m);
		pushTiltSettings(m);
	}
	
	private void pushLooperSettings(MonomeUp m) {
		
		// Send gate choked loops
		app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
			Atom.newAtom(SevenUp4Live.eOutletCategories.looper.toString()),
			Atom.newAtom("gatechokedloops"),
			Atom.newAtom(AllModes.getInstance().getLooper().getGateLoopChokes() ? 1 : 0)
			});
		
		// Send choke groups
		for (int i = 0; i < 7; i++) {
			app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
				Atom.newAtom(SevenUp4Live.eOutletCategories.looper.toString()),
				Atom.newAtom("chokegroup"),
				Atom.newAtom(i),
				Atom.newAtom(m.getLoopChokeGroup(i) + 1) // -1 means no group so to get to 0 index we add 1
				});
		}
		
		// Send loop mode
		for (int i = 0; i < 7; i++) {
			app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
				Atom.newAtom(SevenUp4Live.eOutletCategories.looper.toString()),
				Atom.newAtom("loopmode"),
				Atom.newAtom(i),
				Atom.newAtom(m.getLoopType(i)) 
				});
		}
		
		// Send loop length
		for (int i = 0; i < 7; i++) {
			int index = 0;
			float looplength = m.getLoopLength(i);
			
			if (looplength == 0.5) {
				index = 0;
			} else if (looplength == 1) {
				index = 1;
			} else if (looplength == 2) {
				index = 2;
			} else if (looplength == 4) {
				index = 3;
			} else if (looplength == 8) {
				index = 4;
			} else if (looplength == 16) {
				index = 5;
			} else if (looplength == 32) {
				index = 6;
			}
			
			app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
				Atom.newAtom(SevenUp4Live.eOutletCategories.looper.toString()),
				Atom.newAtom("looplength"),
				Atom.newAtom(i),
				Atom.newAtom(index) 
				});
		}
	}
	
	private void pushMelodizer1Settings(MonomeUp m) {
		
		// Send the Scalename
		app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
			Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer1.toString()),
			Atom.newAtom("scalename"),
			Atom.newAtom(m.getMelody1Scale().Name.toString())
			});
		
		// Send the record mode
		app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
			Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer1.toString()),
			Atom.newAtom("recmode"),
			Atom.newAtom(m.getMel1RecMode())
			});
		
		// Send the tool mode
		String modeString = null;
		if (m.getMelody1Mode() == m.getMelody1AltMode()) {
			modeString = m.getMelody1Mode().toString(); 
		} else {
			modeString = m.getMelody1Mode().toString() + "/" + m.getMelody1AltMode().toString(); 
		}
		app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
			Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer1.toString()),
			Atom.newAtom("toolmode"),
			Atom.newAtom(modeString)
			});
		
		// Send transpose
		app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
			Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer1.toString()),
			Atom.newAtom("transpose"),
			Atom.newAtom(m.getMelody1Transpose() ? 1 : 0)
			});
		
		// Send transpose groups
		for (int i = 0; i < 7; i++) {
			app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
				Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer1.toString()),
				Atom.newAtom("transposegroup"),
				Atom.newAtom(i),
				Atom.newAtom(m.getMel1TransposeGroup(i) + 1) // -1 means no group so to get to 0 index we add 1
				});
		}
		
		// Send sustain modes
		for (int i = 0; i < 7; i++) {
			app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
				Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer1.toString()),
				Atom.newAtom("sustainmode"),
				Atom.newAtom(i),
				Atom.newAtom(m.getMel1TransposeSustain(i) ? 1 : 0) 
				});
		}
	}
	
	private void pushMelodizer2Settings(MonomeUp m) {
		
		// Send the Scalename
		app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
			Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer2.toString()),
			Atom.newAtom("scalename"),
			Atom.newAtom(m.getMelody2Scale().Name.toString())
			});
		
		// Send the record mode
		app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
			Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer2.toString()),
			Atom.newAtom("recmode"),
			Atom.newAtom(m.getMel2RecMode())
			});
		
		// Send the tool mode
		String modeString = null;
		if (m.getMelody2Mode() == m.getMelody2AltMode()) {
			modeString = m.getMelody2Mode().toString(); 
		} else {
			modeString = m.getMelody2Mode().toString() + "/" + m.getMelody2AltMode().toString(); 
		}
		app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
			Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer2.toString()),
			Atom.newAtom("toolmode"),
			Atom.newAtom(modeString)
			});
		
		// Send transpose
		app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
			Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer2.toString()),
			Atom.newAtom("transpose"),
			Atom.newAtom(m.getMelody2Transpose() ? 1 : 0)
			});
		
		// Send transpose groups
		for (int i = 0; i < 7; i++) {
			app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
				Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer2.toString()),
				Atom.newAtom("transposegroup"),
				Atom.newAtom(i),
				Atom.newAtom(m.getMel2TransposeGroup(i) + 1) // -1 means no group so to get to 0 index we add 1
				});
		}
		
		// Send sustain modes
		for (int i = 0; i < 7; i++) {
			app.outlet(SevenUp4Live.eOutlets.PatchDataOutlet.ordinal(), new Atom[] {
				Atom.newAtom(SevenUp4Live.eOutletCategories.melodizer2.toString()),
				Atom.newAtom("sustainmode"),
				Atom.newAtom(i),
				Atom.newAtom(m.getMel2TransposeSustain(i) ? 1 : 0) 
				});
		}
	}
	
	private void pushControllerSettings(MonomeUp m) {
		
	}
	
	private void pushTiltSettings(MonomeUp m) {
		
	}
}
