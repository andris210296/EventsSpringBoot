package com.events.repository;

import org.springframework.data.repository.CrudRepository;

import com.events.model.Event;
import com.events.model.Guest;

public interface GuestRepository extends CrudRepository<Guest, String>{
	
	Iterable<Guest> findByEvent(Event event);
}
