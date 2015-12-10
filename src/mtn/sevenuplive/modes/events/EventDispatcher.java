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

package mtn.sevenuplive.modes.events;

public interface EventDispatcher {

	/** 
	 * Subscribe to events 
	 * @param event Prototype of the event we are subscribing to
	 * @param target that is listening for the event 
	 */
	public void subscribe(Event event, EventListener target);
	
	/** 
	 * Unsubscribe to events 
	 * @param event Prototype of the event we are unsubscribing from
	 * @param target that is listening for the event 
	 */
	public void unsubscribe(Event event, EventListener target);

	/**
	 * Send the event to the all listeners
	 * @param event
	 */
	public void sendEvent(Event event);
}
