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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDispatcherImpl implements EventDispatcher {

	/** HashMap key is event type, value is List of Event Listener targets */
	private Map<String, List<EventListener>> subscriberMap = new HashMap<String, List<EventListener>>();
	
	public void sendEvent(Event event) {
		List<EventListener> listeners = subscriberMap.get(event.getType());
		if (listeners != null) {
			for (EventListener listener : listeners) {
				listener.onEvent(event);
			}
		}
	}

	public void subscribe(Event event, EventListener target) {
		List<EventListener> listeners = subscriberMap.get(event.getType());
		if (listeners == null) {
			listeners = new ArrayList<EventListener>();
			listeners.add(target);
		} else {
			boolean exists = false;
			for (EventListener listener : listeners) {
				if (listener == target) {
					exists = true;
					break;
				}
			}	
			if (!exists) {
				listeners.add(target);
			}
		}
		subscriberMap.put(event.getType(), listeners);
	}

	public void unsubscribe(Event event, EventListener target) {
		List<EventListener> listeners = subscriberMap.get(event.getType());
		if (listeners == null) {
			return; // nothing to do
		} else {
			boolean exists = false;
			for (EventListener listener : listeners) {
				if (listener == target) {
					exists = true;
					break;
				}
			}	
			if (exists) {
				listeners.remove(target);
			}
		}
	}

}
