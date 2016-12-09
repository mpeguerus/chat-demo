package com.demo.chat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home Controller.
 * 
 * @author mpeguero
 *
 */
@Controller
public class HomeController {
	/**
	 * Match everything without a suffix (so not a static resource)
	 * @return
	 */
	@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
		// Forward to home page so that route is preserved.
		return "forward:/";
	}
}
