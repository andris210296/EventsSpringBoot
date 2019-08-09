package com.events.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String forms(@Valid Event event, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("message", "Check those two fields");
			return "redirect:/{id}";			
		}
		
		eventController.save(event);
		attributes.addFlashAttribute("message", "Event added successfully");
		return "redirect:/createEvent";
	}
	
	@RequestMapping({"/","/index", "/events"})
	public ModelAndView eventList() {
		ModelAndView view = new ModelAndView("index.html");
		
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
	
	@RequestMapping("/eventDelete")
	public String eventDelete(long id) {
		Event event = eventController.findById(id);
		eventController.delete(event);		
		return  "redirect:/events";		
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.POST)
	public String eventDetailsPost(@PathVariable("id") long id, @Valid Guest guest, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("message", "Check those two fields");
			return "redirect:/{id}";
		}
		
		Event event = eventController.findById(id);
		guest.setEvent(event);		
		guestRepository.save(guest);
		attributes.addFlashAttribute("message", "Guest added successfully");
		return "redirect:/{id}";
	}
	
	@RequestMapping("/guestDelete")
	public String guestDelete(String id) {

		Optional<Guest> guest = guestRepository.findById(id);
		guestRepository.delete(guest.get());
		
		Event event = guest.get().getEvent();
		long idEvent = event.getId();
		
		return  "redirect:/" + String.valueOf(idEvent);				
	}

}
