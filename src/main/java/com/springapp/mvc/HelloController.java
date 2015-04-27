package com.springapp.mvc;

import Backend.Purchase;
import Backend.DAO.PurchaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
public class HelloController {

	@Autowired
	private PurchaseDAO db;

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Add something");
		model.addAttribute("purchase", new Purchase());
		return "main/index";
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
		//DBCommunication db =
		try {
			db.addPurchase(purchase);
		} catch (Exception e){}
		//db.searchPurchaseById((int) purchase.getPurchaseId());

		return "hello";
	}

	@RequestMapping(value = "/searchPurchase",method = RequestMethod.POST)
	public String showPurchase(@ModelAttribute Purchase purchase, ModelMap model)
	{
		List<Purchase> p = db.searchPurchaseByName(purchase.getProductName());
		model.addAttribute("purchases",p);

		return "showPurchase";
	}

	@RequestMapping(value = "/searchPurchase")
	public String searchPurchase(ModelMap model)
	{
		model.addAttribute("purchase", new Purchase());
		return "searchPurchase";
	}


}