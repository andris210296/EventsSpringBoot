package com.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.events.model.Event;
import com.events.model.Guest;
import com.events.repository.EventRepository;
import com.events.repository.GuestRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository eventController;
	
	@Autowired
	private GuestRepository guestRepository;
	
	
	@RequestMapping(value="/createEvent", method=RequestMethod.GET)
	public String form() {
		return "event/formEvent";
	}
	
	@RequestMapping(value="/createEvent", method=RequestMethod.POST)
	public String forms(Event event) {
		
		eventController.save(event);
		
		return "redirect:/createEvent";
	}
	
	@RequestMapping("/events")
	public ModelAndView eventList() {
		ModelAndView view = new ModelAndView("/index.html");
		
		Iterable<Event> events = eventController.findAll();
		view.addObject("events", events);
		return view;
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ModelAndView eventDetails(@PathVariable("id") long id) {
		Event event = eventController.findById(id);
		
		ModelAndView view = new ModelAndView("event/eventDetails.html");
		view.addObject("event", event);
		
		Iterable<Guest> guests = guestRepository.findByEvent(event);		
		view.addObject("guests", guests);
		
		return view;
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.POST)
	public String eventDetailsPost(@PathVariable("id") long id, Guest guest) {
		Event event = eventController.findById(id);
		guest.setEvent(event);		
		guestRepository.save(guest);		
		return "redirect:/{id}";
	}


}
