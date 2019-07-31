package com.events.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Guest {

	@Id
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String nameGuest;
	
	@ManyToOne
	private Event event;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameGuest() {
		return nameGuest;
	}

	public void setNameGuest(String nameGuest) {
		this.nameGuest = nameGuest;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
