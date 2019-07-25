package com.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.events.model.Event;
import com.events.repository.EventRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository eventController;
	
	
	@RequestMapping(value="/createEvent", method=RequestMethod.GET)
	public String form() {
		return "event/formEvent";
	}
	
	@RequestMapping(value="/createEvent", method=RequestMethod.POST)
	public String forms(Event event) {
		
		eventController.save(event);
		
		return "redirect:/createEvent";
	}

}
