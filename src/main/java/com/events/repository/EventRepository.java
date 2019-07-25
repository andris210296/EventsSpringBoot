package com.events.repository;

import org.springframework.data.repository.CrudRepository;

import com.events.model.Event;

public interface EventRepository extends CrudRepository<Event, String>{

}
