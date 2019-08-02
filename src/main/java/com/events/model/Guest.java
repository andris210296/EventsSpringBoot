package com.events.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Guest implements Serializable{

	@Id
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String nameGuest;
	
	@ManyToOne
	@JoinColumn(name = "event")
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
