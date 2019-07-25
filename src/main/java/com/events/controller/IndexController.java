package com.events.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author aandermann
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
