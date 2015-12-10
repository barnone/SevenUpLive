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

public interface MonomeListener {

	/**
	 * Implement in your listener class 
	 * @param x
	 * @param y
	 */
	public void monomePressed(int x, int y);
	
	/**
	 * Implement in your listener class
	 * @param x
	 * @param y
	 */
	public void monomeReleased(int x, int y);
	
	/**
	 * Implement in your listener class
	 * @param x
	 * @param value
	 */
	public void monomeAdc(int x, float value);

	/**
	 * Implement in your listener class
	 * NOTE: This is a monome protocol unsupported extension 
	 * @param x
	 * @param y
	 * @param velocity
	 */
	public void monomeXPressed(int x, int y, int velocity);
	
	/**
	 * Implement in your listener class
	 * NOTE: This is a monome protocol unsupported extension 
	 * @param x
	 * @param y
	 * @param value
	 */
	public void monomeAfterTouch(int x, int y, float value);
	
	/**
	 * Implement in your listener class
	 * NOTE: This is a monome protocol unsupported extension
	 * Tells app to redraw the entire monome surface  
	 */
	public void monomeRefresh();
}
