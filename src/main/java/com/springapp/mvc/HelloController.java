package com.springapp.mvc;

import Backend.Purchase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class HelloController {

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String printWelcome(ModelMap model){
		model.addAttribute("message", "Add something");
		model.addAttribute("purchase", new Purchase());
		return "hello";
	}
	@RequestMapping("/time")
	public String printTime(ModelMap model)
	{
		Date now = new Date();
		model.addAttribute("message","Hello world the time is: " + now.toString());
		return "time";
	}

	@RequestMapping(value = "/",method = RequestMethod.POST)
	public String printWelcome(@ModelAttribute Purchase purchase,ModelMap model) {
		System.out.println(purchase.getProductName());
		model.addAttribute("purchase",purchase);
		return "result";
	}

}