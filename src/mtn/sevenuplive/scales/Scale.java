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

package mtn.sevenuplive.scales;

public class Scale {
	
	public int Degrees[];
	public ScaleName Name;
	public String label;
	
	/**
	 * The Commandments of Scales in 7up
	 * 
	 * You can have less than 7 degrees, but never more
	 * A degree cannot be more than 11
	 * 
	 * @param name
	 */
	public Scale(ScaleName name){
		Degrees = new int[7];
		switch(name){
        case Minor:
            Name = ScaleName.Minor;
            label = "Minor";
            Degrees[0] = 0;
            Degrees[1] = 2;
            Degrees[2] = 3;
            Degrees[3] = 5;
            Degrees[4] = 7;
            Degrees[5] = 8;
            Degrees[6] = 10;
            break;     
       case HarmonicMinor:
            Name = ScaleName.HarmonicMinor;
            label = "Harmonic Minor";
            Degrees[0] = 0;
            Degrees[1] = 2;
            Degrees[2] = 3;
            Degrees[3] = 5;
            Degrees[4] = 7;
            Degrees[5] = 8;
            Degrees[6] = 11;
            break;
       case Phrygian:
            Name = ScaleName.Phrygian;
            label = "Phrygian";
            Degrees[0] = 0;
            Degrees[1] = 1;
            Degrees[2] = 3;
            Degrees[3] = 5;
            Degrees[4] = 7;
            Degrees[5] = 8;
            Degrees[6] = 10;
            break;
      case Mixolydian:
            Name = ScaleName.Mixolydian;
            label = "Mixolydian";
            Degrees[0] = 0;
            Degrees[1] = 2;
            Degrees[2] = 4;
            Degrees[3] = 5;
            Degrees[4] = 7;
            Degrees[5] = 9;
            Degrees[6] = 10;
            break;
      case Diminished:
            Name = ScaleName.Diminished;
            label = "Diminished";
            Degrees[0] = 0;
            Degrees[1] = 2;
            Degrees[2] = 3;
            Degrees[3] = 5;
            Degrees[4] = 6;
            Degrees[5] = 8;
            Degrees[6] = 9;
            break;
      case WholeTone:
            Name = ScaleName.WholeTone;
            label = "Whole Tone";
            Degrees = new int[6];
            Degrees[0] = 0;
            Degrees[1] = 2;
            Degrees[2] = 4;
            Degrees[3] = 6;
            Degrees[4] = 8;
            Degrees[5] = 10;
            break;
       case Augmented:
            Name = ScaleName.Augmented;
            label = "Augmented";
            Degrees = new int[6];
            Degrees[0] = 0;
            Degrees[1] = 3;
            Degrees[2] = 4;
            Degrees[3] = 7;
            Degrees[4] = 8;
            Degrees[5] = 11;
            break;
		case MinorPentatonic:
			Name = ScaleName.MinorPentatonic;
			label = "Pentatonic (Minor)";
			Degrees = new int[5];
			Degrees[0] = 0;
			Degrees[1] = 3;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 7;
			break;
		case Pentatonic:
			Name = ScaleName.Pentatonic;
			label = "Pentatonic";
			Degrees = new int[5];
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 7;
			Degrees[4] = 9;
			break;
		case Blues:
			Name = ScaleName.Blues;
			label = "Blues";
			Degrees = new int[6];
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 4;
			Degrees[4] = 7;
			Degrees[5] = 9;
			break;
		case MinorBlues:
			Degrees = new int[6];
			Name = ScaleName.MinorBlues;
			label = "Blues (Minor)";
			Degrees[0] = 0;
			Degrees[1] = 3;
			Degrees[2] = 5;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 10;
			break;
		case Dorian:
			Name = ScaleName.Dorian;
			label = "Dorian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 10;
			break;
		case UltraLocrian:
			Name = ScaleName.UltraLocrian;
			label = "Ultra Locrian";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 3;
			Degrees[3] = 4;
			Degrees[4] = 6;
			Degrees[5] = 8;
			Degrees[6] = 9;
			break;
		case Major:
			Name = ScaleName.Major;
			label = "Major";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case Yorkian:
			Name = ScaleName.Yorkian;
			label = "Yorkian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 10;
			break;
		case MinorSeven:
			Name = ScaleName.MinorSeven;
			label = "Minor Seven";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 10;
			break;
		case Billian:
			Name = ScaleName.Billian;
			label = "Billian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case MelodicMinor:
			Name = ScaleName.MelodicMinor;
			label = "Melodic Minor";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case Mullnixian:
			Name = ScaleName.Mullnixian;
			label = "Mullnixian";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case Telerium:
			Name = ScaleName.Telerium;
			label = "Telerium";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 6;
			Degrees[5] = 8;
			Degrees[6] = 11;
			break;
		case Chromatic:
			Name = ScaleName.Chromatic;
			label = "Chromatic";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 2;
			Degrees[3] = 3;
			Degrees[4] = 4;
			Degrees[5] = 5;
			Degrees[6] = 6;
			break;
		
		
		
		/* Added Beta 1.4 */
		case ChromaticHypolydian:
			Name = ScaleName.ChromaticHypolydian;
			label = "ChromaticHypolydian";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 4;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 11;
			break;
		case DoubleHarmonic:
			Name = ScaleName.DoubleHarmonic;
			label = "DoubleHarmonic";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 11;
			break;
		case InstantJazz:
			Name = ScaleName.InstantJazz;
			label = "InstantJazz";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 3;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case Ionian5:
			Name = ScaleName.Ionian5;
			label = "Ionian5";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 8;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case JazzMinorInverse:
			Name = ScaleName.JazzMinorInverse;
			label = "JazzMinorInverse";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 10;
			break;
		case JethsMode:
			Name = ScaleName.JethsMode;
			label = "JethsMode";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 6;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case LeadingWholeTone:
			Name = ScaleName.LeadingWholeTone;
			label = "LeadingWholeTone";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 6;
			Degrees[4] = 8;
			Degrees[5] = 10;
			Degrees[6] = 11;
			break;
		case Locrian:
			Name = ScaleName.Locrian;
			label = "Locrian";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 6;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case LocrianSharp2:
			Name = ScaleName.LocrianSharp2;
			label = "LocrianSharp2";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 6;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case LydianAugmented:
			Name = ScaleName.LydianAugmented;
			label = "LydianAugmented";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 6;
			Degrees[4] = 8;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case LydianDiminished:
			Name = ScaleName.LydianDiminished;
			label = "LydianDiminished";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case LydianFlat7:
			Name = ScaleName.LydianFlat7;
			label = "LydianFlat7";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 10;
			break;
		case LydianMinor:
			Name = ScaleName.LydianMinor;
			label = "LydianMinor";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case Lydian:
			Name = ScaleName.Lydian;
			label = "Lydian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case LydianSharp2:
			Name = ScaleName.LydianSharp2;
			label = "LydianSharp2";
			Degrees[0] = 0;
			Degrees[1] = 3;
			Degrees[2] = 4;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;	
		case LocrianMajor:
			Name = ScaleName.LocrianMajor;
			label = "LocrianMajor";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 6;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case Miles:
			Degrees = new int[6];
			Name = ScaleName.Miles;
			label = "Miles";
			Degrees[0] = 0;
			Degrees[1] = 3;
			Degrees[2] = 4;
			Degrees[3] = 7;
			Degrees[4] = 8;
			Degrees[5] = 11;
			break;
		case PhrygianFlat4:
			Name = ScaleName.PhrygianFlat4;
			label = "PhrygianFlat4";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 3;
			Degrees[3] = 4;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case SuperLocrian:
			Name = ScaleName.SuperLocrian;
			label = "SuperLocrian";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 3;
			Degrees[3] = 4;
			Degrees[4] = 6;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		
		/* Ethnic Scales */	
		case Chinese:
			Degrees = new int[5];
			Name = ScaleName.Chinese;
			label = "Chinese";
			Degrees[0] = 0;
			Degrees[1] = 4;
			Degrees[2] = 6;
			Degrees[3] = 7;
			Degrees[4] = 11;
			break;
		case Egyptian:
			Degrees = new int[5];
			Name = ScaleName.Egyptian;
			label = "Egyptian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 5;
			Degrees[3] = 7;
			Degrees[4] = 10;
			break;
		case Hindu:
			Name = ScaleName.Hindu;
			label = "Hindu";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case Hirajoshi:
			Degrees = new int[5];
			Name = ScaleName.Hirajoshi;
			label = "Hirajoshi";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 7;
			Degrees[4] = 8;
			break;
		case HungarianGypsy:
			Name = ScaleName.HungarianGypsy;
			label = "HungarianGypsy";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case Hungarian:
			Name = ScaleName.Hungarian;
			label = "Hungarian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 11;
			break;
		case HungarianMajor:
			Name = ScaleName.HungarianMajor;
			label = "HungarianMajor";
			Degrees[0] = 0;
			Degrees[1] = 3;
			Degrees[2] = 4;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 10;
			break;
		case InSen:
			Degrees = new int[5];
			Name = ScaleName.InSen;
			label = "InSen";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 5;
			Degrees[3] = 7;
			Degrees[4] = 11;
			break;
		case IWato:
			Degrees = new int[5];
			Name = ScaleName.IWato;
			label = "IWato";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 5;
			Degrees[3] = 6;
			Degrees[4] = 10;
			break;
		case Japanese:
			Degrees = new int[6];
			Name = ScaleName.Japanese;
			label = "Japanese";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 5;
			Degrees[3] = 7;
			Degrees[4] = 8;
			Degrees[5] = 10;
			break;
		case JewishSpanish:
			Name = ScaleName.JewishSpanish;
			label = "JewishSpanish";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case Philgarian:
			Name = ScaleName.Philgarian;
			label = "Philgarian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case Romanian:
			Name = ScaleName.Romanian;
			label = "Romanian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 10;
			break;
		case Sambah:
			Name = ScaleName.Sambah;
			label = "Sambah";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 4;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;	
		default: // Major is the default
			Name = ScaleName.Major;
			label = "Major";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		}
	}

}
