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

import mtn.sevenuplive.m4l.Note;

public interface PlayContext {
	
	/**
	 * Performs any tranposition of pitch on notes.
	 * Note that if notes are modified, a new Array with new notes should be returned
	 * @param notes
	 */
	public ArrayList<Note> transpose(ArrayList<Note> notes, int transpositionIndex);
	
	/**
	 * Are we transposing
	 * @return
	 */
	public boolean getTranspose();
	
	/**
	 * Get Current Transposition Context for a sequence
	 */
	public TranspositionContext getTranspositionContext(int sequence);
	
	/**
	 * Transpose with a specific context
	 */
	public Note transposeWithContext(Note note, TranspositionContext tc);
	
}
