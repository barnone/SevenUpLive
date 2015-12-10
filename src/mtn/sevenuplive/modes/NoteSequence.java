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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.main.MonomeUp;

import org.jdom.Attribute;
import org.jdom.Element;

/***
 * A sequence of notes that may be recorded live and played back.  
 * This object keeps track of the current record/play position and which notes are attached to each position.  
 * Note information is stored in a hashtable which maintains the event position and an array of notes that were recorded.
 * @author Adam Ribaudo
 *
 */
public class NoteSequence {
	
	private Integer counter;  //Event position
	private Integer length;  //Length of the entire sequence
	private Integer index;	//Sequence index out of all possible NoteSequences for printout in XML
	private int status;  //Current status of the sequence
	private Hashtable <Integer, Hashtable<Integer, TranspositionContext>> heldNotesTranspositionContext;  //First Hashtable keys sequence number to internal Hashtable keeping track of the transposition context of held notes
	private Hashtable< Integer, Hashtable <Integer, Note>> originalHeldNotesPlaying;  //First Hashtable keys sequence number to internal Hashtable keeping track of currently held notes at original pitchs
	
	//Create hashtable of keys (metronome count) and ArrayList<Note>(notes played at that event position)
	private Hashtable<Integer, ArrayList<Note>> events;
	private ArrayList<Integer> notesOn;
	private int recMode = ModeConstants.MEL_ON_BUTTON_PRESS;
	
	private PlayContext context;
	
	
	NoteSequence(int _index, PlayContext context){
		initialize();
		index = _index;
		counter = 0;
		originalHeldNotesPlaying = new Hashtable<Integer, Hashtable<Integer, Note>>();
		heldNotesTranspositionContext =  new Hashtable<Integer, Hashtable<Integer, TranspositionContext>>();
		this.context = context;
	}
	
	private void initialize()
	{
		status = MonomeUp.EMPTY;
		counter = 0;
		events = new Hashtable<Integer, ArrayList<Note>>();	
		notesOn = new ArrayList<Integer>();
	}
	
	public void beginCue()
	{
		initialize();
		status = MonomeUp.CUED;
	}
	
	/***
	 * Called when the noteSequence is told to stop recording.  Depending on the recMode, recording may not actually stop until the next quantize point
	 */
	public void endRecording()
	{
		if(recMode == ModeConstants.MEL_QUANTIZED)
			//Wait for a locatorEvent before actually ending the recording
			status = MonomeUp.CUEDSTOP;
		else
			endAllRecording();
	}
	
	/***
	 * Called when recording is to stop after the quantize point as been reached.
	 */
	private void endAllRecording() {
		//Quantizing to 1/8th note
		int modCount = (counter - 1) % 4;
		int quantizedCount;
		
		//If the modCount is 0, 1, or 2 then the event was late and should be pushed back to the previous count % 4
		if (modCount <= 2)
		{
			quantizedCount = counter - modCount;
		}
		//Else if the modCount is 3 then the hit was early and should be positioned to the next counter
		else
		{
			quantizedCount = counter + 1;
		}
		
		//Remove one because it ends at the end of the last bar, not the beginning of the next
		quantizedCount--;
		
		length = quantizedCount;

		//Turn held notes off at the end of the recording
		ArrayList<Note> notesOnSend = new ArrayList<Note>();
		
		for(int i=0;i<notesOn.size();i++)
		{
			notesOnSend.add(new Note(notesOn.get(i), 0, 0));
		}
		
		//Check to see if there are any events AFTER the length and add them to the final event
		int eventIndex;
		for(Enumeration<Integer> els = events.keys();els.hasMoreElements();)
		{
			eventIndex = Integer.class.cast(els.nextElement());
			if(eventIndex > length)
				for(int i=0;i<events.get(eventIndex).size();i++)
					notesOnSend.add(events.get(eventIndex).get(i));
		}
		
		if(!isEmpty())events.put(length, notesOnSend);
		status = MonomeUp.STOPPED;
	}

	/**
	 * Called by the intiating class in order to cycle through the sequence events and return any notes to be played
	 * @return An ArrayList to be played at the current count.  If no events, returns null.
	 * 
	 */
	public ArrayList<Note> heartbeat()
	{
		//account for recording never stopping
		if(counter == Integer.MAX_VALUE - 1)
		{
			endRecording();
			return null;
		}
		
		//Advance counter if sequence is playing or recording
		if(status == MonomeUp.PLAYING || status == MonomeUp.RECORDING || status == MonomeUp.CUEDSTOP)
			counter ++;
		
		//Send note if isPlaying and there is an event at the current count
		if(status == MonomeUp.PLAYING)
		{
			//Reset counter to beginning if it reaches the length
			if(counter > length)
			{
				counter = 1;
			}

			if(events.containsKey(counter))
			{
				//Keep track of which notes are playing
				ArrayList<Note> noteList;
				noteList  = events.get(counter);
				
				for(int i=0;i<noteList.size();i++) {
					if(noteList.get(i).getVelocity() > 0)
					{
						//Add a noteOn event to heldNotes
						Hashtable <Integer, Note> internal = originalHeldNotesPlaying.get(index);
						if (internal == null) {
							internal = new Hashtable <Integer, Note>();
							originalHeldNotesPlaying.put(index, internal);
						}	
						internal.put(noteList.get(i).getPitch(), noteList.get(i));
						
						// Mark this note as held at current transposition context
						markHeldNoteAtCurrentTranspositionContext(noteList.get(i));	
					}
					else
					{
						//Remove a note from heldNotes
						if(originalHeldNotesPlaying.containsKey(noteList.get(i).getPitch()))
						{
							originalHeldNotesPlaying.remove(noteList.get(i).getPitch()); // they are the same so pitch here should be correct
							heldNotesTranspositionContext.remove(noteList.get(i).getPitch());
						}
					}
				}
				
				// If transposing 
				if (context.getTranspose()) {
					
					// Transpose if necessary. If transposing then NoteList will actually be a clone of the original
					TranspositionContext tc = context.getTranspositionContext(index);
					ArrayList<Note> noteListAfterTranspose = new ArrayList<Note>();
					Note newNote;
					for(int i=0;i<noteList.size();i++) {
						newNote = context.transposeWithContext(noteList.get(i), tc);
						if (newNote != null) {
							noteListAfterTranspose.add(newNote);
						} else {
							//System.out.println("Note transposed off the grid");
							noteListAfterTranspose.add(new Note(noteList.get(i).getPitch(), 0, 0)); // If off grid then send a note off
						}	
					}
					return noteListAfterTranspose;
				}
				
				return noteList;
			}
				
			else return null;
		}
		
		return null;
	}
	
	/**
	 * Mark a held note as being played at the current transposition context
	 * @param note Must be at the original UNTRANSPOSED pitch
	 */
	public void markHeldNoteAtCurrentTranspositionContext(Note note) {
		// Only record if transposing
		if (context.getTranspose()) {
			Hashtable <Integer, TranspositionContext> internal2 = heldNotesTranspositionContext.get(index);
			if (internal2 == null) {
				internal2 = new Hashtable <Integer, TranspositionContext>();
				heldNotesTranspositionContext.put(index, internal2);
			}	
			internal2.put(note.getPitch(), context.getTranspositionContext(index));
		}	
	}
	
	/***
	 * Called by the initiating class to add a note event to the sequence
	 * @param note The note to be added at the current event position
	 */
	public void addEvent(Note note)
	{
		ArrayList<Note> noteList;
		
		//Ensures that the user can add events to the first beat of a quantized recording
		if(status == MonomeUp.CUED && recMode == ModeConstants.MEL_QUANTIZED)
		{
			
			noteList = new ArrayList<Note>();
			noteList.add(note);
			counter = 1;
			events.put(counter, noteList);
			notesOn.add(note.getPitch());
		}
		//Else if the recMode is on button press, begin recording immediately
		else if(status == MonomeUp.CUED && recMode == ModeConstants.MEL_ON_BUTTON_PRESS)
		{
			
			noteList = new ArrayList<Note>();
			noteList.add(note);
			
			status = MonomeUp.RECORDING;
			counter = 1;
			events.put(counter, noteList);
			notesOn.add(note.getPitch());
			//System.out.println("Sequencer " + _index + " - Adding note " + note.getPitch() + " to " + counter);
		}
		//If currently recording, just add a note to the sequence
		else if(status == MonomeUp.RECORDING)
		{
			//Quantizing!
			int modCount = (counter - 1) % 4;
			int quantizedCount;
			
			//If the modCount is 0, 1, or 2 then the event was late and should be pushed back to the previous count % 4
			if (modCount <= 2)
			{
				quantizedCount = counter - modCount;
			}
			//Else if the modCount is 3 then the hit was early and should be positioned to the next counter
			else
			{
				quantizedCount = counter + 1;
			}

			boolean found = false;
			int removeIndex = 0;
			
			if (note.getVelocity() > 0)
			{
				//Add note to list of notes that are on if it is not already in the list
				for(int i=0;i<notesOn.size();i++)
				{
					if(notesOn.get(i) == note.getPitch())
					{
						found = true;
					}
				}
				if(!found)
				{
					notesOn.add(note.getPitch());
				}
				
				//System.out.println("Quantized count is: " + quantizedCount);
				//If the event list already contains an event for this count, add the note event to the existing arraylist
				if(events.containsKey(quantizedCount))
				{
					events.get(quantizedCount).add(note);
				}
				//Otherwise add a new event and arraylist
				else
				{
					noteList = new ArrayList<Note>();
					noteList.add(note);
					events.put(quantizedCount, noteList);
				}
				//System.out.println("Sequencer " + index + " - Adding note " + note.getPitch() + " to " + quantizedCount);
			}
			else
			{
				//remove note from list of notes that are on if it is in the list
				for(int i=0;i<notesOn.size();i++)
				{
					if(notesOn.get(i) == note.getPitch())
					{
						found = true;
						removeIndex = i;
					}
				}
				
				if(found)
				{
					notesOn.remove(removeIndex);
				}
				
				
				if(notesOn.contains(note.getPitch()))
				{
					notesOn.remove(new Integer(note.getPitch()));
				}
				
				//If the event list already contains an event for this count, add the note event to the existing arraylist
				if(events.containsKey(counter))
				{
					events.get(counter).add(note);
				}
				//Otherwise add a new event and arraylist
				else
				{
					noteList = new ArrayList<Note>();
					noteList.add(note);
					events.put(counter, noteList);
				}
				//System.out.println("Sequencer " + index + " - Adding release note " + note.getPitch() + " to " + counter);
			}
		}
	}
	
	public void play()
	{
		if(!(status == MonomeUp.CUEDSTOP))
		{
			status = MonomeUp.PLAYING;
			counter = 0;
		}
	}
	
	public void stop()
	{
		status = MonomeUp.STOPPED;
	}
	
	public int getStatus()
	{
		if(isEmpty()) return MonomeUp.EMPTY;
		
		return status;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	
	public Element toJDOMXMLElement()
	{
		Element xmlSequence = new Element("sequence");
		Element xmlEvent;
		Element xmlNote;
		
		Integer eventIndex;
		ArrayList<Note> noteList;

		xmlSequence.setAttribute(new Attribute("length", length.toString()));
		xmlSequence.setAttribute(new Attribute("index", index.toString()));

		for(Enumeration<Integer> els = events.keys();els.hasMoreElements();)
		{
			xmlEvent = new Element("event");
			eventIndex = Integer.class.cast(els.nextElement());
			xmlEvent.setAttribute(new Attribute("index", eventIndex.toString()));
			
			noteList = events.get(eventIndex);
			
			for(int i=0;i<noteList.size();i++)
			{
				xmlNote = new Element("note");
				xmlNote.setAttribute(new Attribute("pitch", ((Integer)noteList.get(i).getPitch()).toString()));
				xmlNote.setAttribute(new Attribute("velocity", ((Integer)noteList.get(i).getVelocity()).toString()));
				xmlEvent.addContent(xmlNote);
			}
			xmlSequence.addContent(xmlEvent);
		}
		
		return xmlSequence;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlSequence)
	{
		initialize();
		
		//Load XML
		length = xmlSequence.getAttributeValue("length") == null ? length : Integer.parseInt(xmlSequence.getAttributeValue("length"));
		
		Integer eventIndex;
		List<Element> xmlEvents;
		Integer velocity;
		Integer pitch;
		List<Element> xmlNotes;
		ArrayList<Note> notes;
		Note note;
		
		xmlEvents = xmlSequence.getChildren();
		
		int outerindex = 0;
		for (Element xmlEvent: xmlEvents)
		{
			eventIndex = xmlEvent.getAttributeValue("index") == null ? outerindex : Integer.parseInt(xmlEvent.getAttributeValue("index"));
			notes = new ArrayList<Note>();
			
			xmlNotes = xmlEvent.getChildren();
			
			for(Element xmlNote : xmlNotes)
			{
				velocity = xmlNote.getAttributeValue("velocity") == null ? new Integer(Note.DEFAULT_VELOCITY) : Integer.parseInt(xmlNote.getAttributeValue("velocity"));
				pitch = xmlNote.getAttributeValue("pitch") == null ? new Integer(ModeConstants.NOT_SET) : Integer.parseInt(xmlNote.getAttributeValue("pitch"));
				note = new Note(pitch,velocity, 0);
				notes.add(note);
			}
			
			events.put(eventIndex, notes);
			outerindex++;
		}	
	}

	/**
	 * Get held notes at the pitch they were played at.
	 * This takes into account what transpositions were 
	 * applied and reapplies them.
	 * @param sequence number 0-7
	 * @return
	 */
	public ArrayList<Note> getHeldNotesAtPlayedPitch(int sequence) {
		ArrayList<Note> heldNotesArray = new ArrayList<Note>();
		Integer index;
		Hashtable <Integer, Note> internal = originalHeldNotesPlaying.get(sequence);
		if (internal == null) 
			return new ArrayList<Note>(); // empty list
			
		for(Enumeration<Integer> els = internal.keys();els.hasMoreElements();)
		{
			index = Integer.class.cast(els.nextElement());
			
			Note originalNote = internal.get(index);
			
			Hashtable <Integer, TranspositionContext> internal2 = heldNotesTranspositionContext.get(sequence);
			if (internal2 == null)
				internal2 = new Hashtable <Integer, TranspositionContext>();
			
			TranspositionContext tc = internal2.get(originalNote.getPitch());
			Note newNote;
			if (tc != null) {
				newNote = context.transposeWithContext(originalNote, tc);
			} else {
				newNote = originalNote;
			}
			if (newNote != null)
				heldNotesArray.add(newNote);
			
		}
		
		return heldNotesArray;
	}
	
	/**
	 * Get held notes at the latest transpose level.
	 * Note that they may never have been played at this pitch
	 * @param sequence number 0-7
	 * @return
	 */
	public ArrayList<Note> getHeldNotesAtNewPitch(int sequence) {
		ArrayList<Note> heldNotesArray = new ArrayList<Note>();
		Integer index;
		Hashtable <Integer, Note> internal = originalHeldNotesPlaying.get(sequence);
		if (internal == null) 
			return new ArrayList<Note>(); // empty list
			
		
		for(Enumeration<Integer> els = internal.keys();els.hasMoreElements();)
		{
			index = Integer.class.cast(els.nextElement());
			
			Note originalNote = internal.get(index);
			
			TranspositionContext tc = context.getTranspositionContext(sequence);
			Note newNote;
			if (tc != null) {
				newNote = context.transposeWithContext(originalNote, tc);
			} else {
				newNote = originalNote;
			}
			if (newNote != null)
				heldNotesArray.add(newNote);
			
		}
		
		return heldNotesArray;
	}
	
	/**
	 * Get held notes at original untransposed pitch.
	 * Note that they may never have been played at this pitch
	 * @param sequence sequence to get held notes for
	 * @return
	 */
	public ArrayList<Note> getHeldNotesOriginalPitch(int sequence) {
		ArrayList<Note> heldNotesArray = new ArrayList<Note>();
		Integer index;
		Hashtable <Integer, Note> internal = originalHeldNotesPlaying.get(sequence);
		if (internal == null) 
			return new ArrayList<Note>(); // empty list
		
		for(Enumeration<Integer> els = internal.keys();els.hasMoreElements();)
		{
			index = Integer.class.cast(els.nextElement());
			Note originalNote = internal.get(index);
			heldNotesArray.add(originalNote);
		}
		
		return heldNotesArray;
	}

	/***
	 * When the melodizer is in quantized recording mode, locator events tell it 
	 * when to actually start or stop recording after being cued to do so
	 */
	public void locatorEvent() {
		if(recMode == ModeConstants.MEL_QUANTIZED)
		{
			if(status == MonomeUp.CUEDSTOP)
			{
				//System.out.println("Quantized Sequence " + index + " is STOPPED");
				endAllRecording();
				status = MonomeUp.STOPPED;
				//Immediately begin playback
				play();
			}
			if(status == MonomeUp.CUED)
			{	
				status = MonomeUp.RECORDING;
				counter = 1;
				if(!events.containsKey(counter))
					events.put(counter, new ArrayList<Note>());
				//System.out.println("Quantized Sequence " + index + "is STARTED");
			}
		}
	}

	public void setMelRecMode(int _recMode) {
		recMode = _recMode;
	}
	
	public boolean isEmpty(){
		return events.size() == 0;
	}
	
}
