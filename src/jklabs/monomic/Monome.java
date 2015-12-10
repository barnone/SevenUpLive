/*
	Copyright (c) 2009 Jesse Kriss
	
	This file and all files in the jklabs java package have been modified for 
	SevenUpLive based on the Monomic Library written originally by Jesse Kriss 
	under the MIT license shown below.
	
	SevenUpLive as a whole is licensed under the GNU Lesser General Public License
    provided here <http://www.gnu.org/licenses/>.
    
    Original MIT License Notice for this file follows:  
	
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.

*/

package jklabs.monomic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Monome {

	private static int x_dim;
	private static int y_dim;
	private static int[][] ALL_ON;
	private static int[][] ALL_OFF;
	
	public static int NONE = 99;
	public static int FINE = 1;
	public static int WARNING = 2;
	protected int debug = NONE;
	
	private byte[][] buttonVals;
	
	private Method buttonPressedMethod;
	private Method buttonReleasedMethod;
	private Method xafterMethod;
	private Method xbuttonPressedMethod;
	private Method adcInputMethod;
	private Method refreshMethod;

	int[][] ledValues;
	boolean[][] buttonValues;
	
	int tempRow[];
	
	protected Monome() {
		this(1, 1); // default to 40h monome
	}
	
	protected Monome(MonomeListener callback) {
		this(callback, 1, 1); // default to 40h monome
	}
	
	protected Monome(int y_bytes, int x_bytes) {
		Monome.x_dim = x_bytes * 8;
		Monome.y_dim = y_bytes * 8;
		
		if (y_dim > 8) {
			//this.ledVals = new byte[y_dim][2];
			this.buttonVals = new byte[y_dim][2];
		} else {
			//this.ledVals = new byte[y_dim][1];
			this.buttonVals = new byte[y_dim][1];
		}
		
		
		this.ledValues = new int[y_dim][x_dim];
		this.buttonValues = new boolean[y_dim][x_dim];
		this.tempRow = new int[y_dim];
		
		initMatrices();
		
		getMethods(this);
	}
	
	protected Monome(MonomeListener callback, int y_bytes, int x_bytes) {
		Monome.x_dim = x_bytes * 8;
		Monome.y_dim = y_bytes * 8;
		
		if (y_dim > 8) {
			//this.ledVals = new byte[y_dim][2];
			this.buttonVals = new byte[y_dim][2];
		} else {
			//this.ledVals = new byte[y_dim][1];
			this.buttonVals = new byte[y_dim][1];
		}
		
		
		this.ledValues = new int[y_dim][x_dim];
		this.buttonValues = new boolean[y_dim][x_dim];
		this.tempRow = new int[y_dim];
		
		initMatrices();
		
		getMethods(callback);
	}

	
	/**
	 * create ALL_ON and ALL_OFF matrices
	 */
	private void initMatrices() {
		// create ALL_ON and ALL_OFF matrices
		ALL_ON = new int[x_dim][y_dim];
		ALL_OFF = new int[x_dim][y_dim];
		for (int i = 0; i < x_dim; i++) {
			for (int j = 0; j < y_dim; j++) {
				ALL_ON[i][j] = 1;
				ALL_OFF[i][j] = 0;
			}
		}
	}
	
	protected void init() {
		testPattern(false);
		lightsOff();
	}

	protected void getMethods(Object parent) {
		Class[] noargs = new Class[] {};
		Class[] args = new Class[] { int.class, int.class };
		Class[] adcArgs = new Class[] {int.class, float.class};
		Class[] xVel = new Class[] {int.class, int.class, int.class};
		Class[] xArgs = new Class[] {int.class, int.class, float.class};
		try {
			buttonPressedMethod = parent.getClass().getDeclaredMethod(
					"monomePressed", args);
		} catch (NoSuchMethodException e) {
			// not a big deal if they aren't implemented
		}
		try {
			buttonReleasedMethod = parent.getClass().getDeclaredMethod(
					"monomeReleased", args);
		} catch (NoSuchMethodException e) {
			// not a big deal if they aren't implemented
		}
		try {
			adcInputMethod = parent.getClass().getDeclaredMethod(
					"monomeAdc", adcArgs);
		} catch (NoSuchMethodException e) {
			// not a big deal if they aren't implemented
		}
		
		// Extended Monome Protocol Message for Aftertouch
		try {
			xafterMethod = parent.getClass().getDeclaredMethod(
					"monomeAfterTouch", xArgs);
		} catch (NoSuchMethodException e) {
			// not a big deal if they aren't implemented
		}
		
		// Extended Monome Protocol Message for Press with Velocity
		try {
			xbuttonPressedMethod = parent.getClass().getDeclaredMethod(
					"monomeXPressed", xVel);
		} catch (NoSuchMethodException e) {
			// not a big deal if they aren't implemented
		}
		
		// Extended Monome Protocol Message for triggering full Refresh
		try {
			refreshMethod = parent.getClass().getDeclaredMethod(
					"monomeRefresh", noargs);
		} catch (NoSuchMethodException e) {
			// not a big deal if they aren't implemented
		}
	}

	////////////////////////////////////////////////// handy stuff

	public void setDebug(boolean b) {
		if (b)
			setDebug(FINE);
		else
			setDebug(NONE);
	}

	public void setDebug(int debug) {
		this.debug = debug;
	}

	public void testInput() {
		handleInputEvent(0, 0, 1);
		handleInputEvent(3, 4, 0);
	}

	////////////////////////////////////////////////// button state
	
	public boolean isPressed(int x, int y) {
		return buttonValues[x][y];
	}
	
	public boolean isLit(int x, int y) {
		return ledValues[x][y] > 0;
	}
	
	public int getValue(int x, int y) {
		return ledValues[x][y];
	}
	
	// NOTE: Do we need this? Needs to be made compatible with changing from boolean[][] to int[][]
	/*public byte[][] getLedValues() {
		return pack(ledValues, ledVals);
	}*/
	
	public byte[][] getButtonValues() {
		return pack(buttonValues, buttonVals);
	}
		
	public byte[] getRowValues(int i) {
		for (int y=0; y<y_dim; y++)
			tempRow[y] = ledValues[y][i];
		return pack(tempRow);	
	}
	
	public byte[] getColValues(int i) {
		return pack(ledValues[i]);	
	}
	
	
	////////////////////////////////////////////////// monome functions
	
	public void pressButton(int x, int y) {
		handleInputEvent(x, y, 1);
	}

	public void releaseButton(int x, int y) {
		handleInputEvent(x, y, 0);
	}

	public void testPattern(boolean b) {
		if (debug == FINE)
			System.out.println("setting led test pattern " + (b ? "on" : "off"));
	}

	public void lightsOn() {
		setValues(ALL_ON);
	}

	public void lightsOff() {
		setValues(ALL_OFF);
	}

	public void lightOn(int x, int y) {
		setValue(x, y, 1);
	}

	public void lightOff(int x, int y) {
		setValue(x, y, 0);
	}

	public void setValue(int x, int y, boolean value) {
		setValue(x, y, value?1:0);
	}
	
	public void setValue(int x, int y, int value) {
		if (debug == FINE)
			System.out.println("setting light " + x + "," + y + " to " + value);
		setInternalLedValue(x, y, value);
	}

	public void invertRow(int i) {
		byte[] bytes = getRowValues(i);
		
		int index = 0;
		for (byte b : bytes) { 
			bytes[index] = (byte)(0xff-b); index++;
		}
		setRow(i, bytes);	
	}
	
	public void invertRowByte(int i, int byteIndex) {
		byte[] bytes = getRowValues(i);
		
		bytes[byteIndex] = (byte)(0xff-bytes[byteIndex]); 
		
		setRow(i, bytes);	
	}
	
	public void setRow(int i, int[] vals) {
		setRow(i, pack(vals));
	}

	public void setRow(int i, boolean[] vals) {
		setRow(i, pack(vals));
	}

	public void setRow(int i, byte[] bitVals) {
		if (debug == FINE) {
			StringBuffer buf = new StringBuffer();
			buf.append("setting row " + i + " to ");
			for (byte b : bitVals)
				buf.append(bitString(b));
			System.out.println(buf.toString());
		}
		int bytepos = 0 ;
		for (int j=0; j<y_dim; j++) {
			bytepos = Math.abs(j / 8);
			setInternalLedValue(j, i, (bitVals[bytepos] >> (j % 8))&0x01);
		}	
	}

	public void invertCol(int i) {
		byte[] bytes = getColValues(i);
		
		int index = 0;
		for (byte b : bytes) { 
			bytes[index] = (byte)(0xff-b); index++;
		}
		setCol(i, bytes); 
	}
	
	public void invertColByte(int i, int byteIndex) {
		byte[] bytes = getColValues(i);
		
		bytes[byteIndex] = (byte)(0xff-bytes[byteIndex]); 
		
		setCol(i, bytes);	
	}	
	
	public void setCol(int i, int[] vals) {
		setCol(i, pack(vals));
	}

	public void setCol(int i, boolean[] vals) {
		setCol(i, pack(vals));
	}

	public void setCol(int i, byte[] bitVals) {
		if (debug == FINE) {
			StringBuffer buf = new StringBuffer();
			buf.append("setting col " + i + " to ");
			for (byte b : bitVals)
				buf.append(bitString(b));
			System.out.println(buf.toString());
		}
		int bytepos = 0 ;
		for (int j=0; j<x_dim; j++) {
			bytepos = Math.abs(j / 8);
			setInternalLedValue(i, j, (bitVals[bytepos] >> (j % 8))&0x01);
		}	
	}

	public void setLowPower(boolean b) {
		if (debug == FINE)
			System.out.println("setting low power to " + b);
	}

	public void setLedIntensity(float f) {
		if (debug == FINE)
			System.out.println("setting led intensity to " + f);
	}

	public void invert() {
		for (int x=0; x<x_dim; x++)
			invertCol(x);
	}
	
	public void setValues(int[][] vals) {
		for (int i = 0; i < vals.length; i++)
			setRow(i, vals[i]);
	}

	public void setValues(boolean[][] vals) {
		for (int i = 0; i < vals.length; i++)
			setRow(i, vals[i]);
	}

	public void setValues(byte[][] vals) {
		for (int i = 0; i < vals.length; i++)
			setRow(i, vals[i]);
	}

	public void enableADC(int i) {
		if (debug == FINE) System.out.println("enabling adc " + i);
		setADC(i, true);
	}
	
	public void disableADC(int i) {
		if (debug == FINE) System.out.println("disabling adc " + i);
		setADC(i, false);
	}
	
	protected void setADC(int i, boolean b) {
		
	}

	protected synchronized void handleAdcInput(int port, float value) {
		if (debug == FINE) System.out.println("adc input: port " + port + ": " + value);
		if (adcInputMethod == null) return;
		
		try {
			adcInputMethod.invoke(this, Integer.valueOf(port), Float.valueOf(value));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// do nothing
		}
	}
	
	protected synchronized void handleInputEvent(int x, int y, int value) {
		if (x<0 || y<0) return;
		setInternalButtonValue(x, y, value);
		Method m = (value == 1) ? buttonPressedMethod : buttonReleasedMethod;
		if (m == null) // || x>x_dim-1 || y>y_dim-1 || x<0 || y<0)
			return;
		try {
			m.invoke(this, Integer.valueOf(x), Integer.valueOf(y));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// do nothing
		}		
	}

	protected synchronized void handleExtendedInputEvent(int x, int y, int value) {
		if (x<0 || y<0) return;
		
		Method m = null;
		
		if (value > 0) { // Press ON or OFF 
			setInternalButtonValue(x, y, 1);
			m = xbuttonPressedMethod;
			
			// call the method passing velocity
			try {
				m.invoke(this, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(value));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// do nothing
			}
		} else {
			setInternalButtonValue(x, y, 0);
			m = buttonReleasedMethod;
			
			try {
				m.invoke(this, Integer.valueOf(x), Integer.valueOf(y));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// do nothing
			}
		}
				
	}
	
	protected synchronized void handleAfterTouchEvent(int x, int y, float value) {
		if (x<0 || y<0) return;
		
		Method m = xafterMethod;
			
		try {
			m.invoke(this, Integer.valueOf(x), Integer.valueOf(y), Float.valueOf(value));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// do nothing
		}
	
	}
	
	protected synchronized void handleRefresh() {
		Method m = refreshMethod;
		
		try {
			m.invoke(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// Do nothing
		}
	}
	
	
	////////////////////////////////////////////////// helper methods

	private void setInternalLedValue(int x, int y, int value) {
		if (debug == FINE)
			System.out.println("setting internal state of " + x + "," + y + " to " + value);
		
		ledValues[x][y] = value;
	}

	private void setInternalButtonValue(int x, int y, int value) {
		buttonValues[x][y] = (value == 1);
	}
	
	private byte[] pack(int[] values) {
		byte b[] = new byte[Math.abs(values.length / 8)];
		
		int bindex = b.length;
		for (int i = 0; i < values.length; i++) {
			if (i % 8 == 0)
				bindex--;
			
			b[bindex] += values[values.length - 1 - i] << (7 - (i % 8));
		}	
		return b;
	}
	
	private byte[] pack(boolean[] values) {
		byte b[] = new byte[Math.abs(values.length / 8)];
		
		int bindex = b.length;
		for (int i = 0; i < values.length; i++) {
			if (i % 8 == 0)
				bindex--;
				
			b[bindex] += (values[values.length - 1 - i]?1:0) << (7 - (i % 8));
			
		}
		return b;
	}
	
	private byte[][] pack(boolean[][] values, byte[][] dest) {
		for (int i=0; i<values.length; i++)
			dest[i] = pack(values[i]);
		return dest;
	}

	private StringBuffer s = new StringBuffer();

	public String bitString(byte b) {
		s.setLength(0);
		for (int i = 0; i < 8; i++) {
			s.insert(0, (char) b & 0x1);
			b >>= 1;
		}
		return s.toString();
	}
	
	// NOTE: Do we need this? Needs to be made compatible with changing from boolean[][] to int[][]
	/*public String getMatrixString() {
		String s = "";
		for (int y=0; y<y_dim; y++) {
			for (int x=0; x<x_dim; x++)
				s += (ledValues[x][y] ? 1 : 0) + " ";
			s +="\n";
		}
		return s;
	}*/
	
}
