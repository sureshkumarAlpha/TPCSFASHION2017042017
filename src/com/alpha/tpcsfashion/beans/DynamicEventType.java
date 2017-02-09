package com.alpha.tpcsfashion.beans;

import java.io.Serializable;

public class DynamicEventType implements Serializable {

	
	private int eventId;
    private String eventName;
	
    
    public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
    
    
    
	
    
}
