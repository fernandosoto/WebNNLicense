package com.springapp.mvc;

import Backend.DAO.PurchaseDAO;
import Backend.Manufacturer;
import Backend.Purchase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Thomas on 2015-04-24.
 */
@Controller
public class addController {
    private PurchaseDAO db;
    @RequestMapping(value = "/addPurchase",method = RequestMethod.POST)
    public String printWelcome(@ModelAttribute Purchase purchase,ModelMap model) {
        System.out.println(purchase.getProductName());
        try {
            db.addPurchase(purchase);
        } catch (Exception e){}

        return "add/add_inner";
    }


    @RequestMapping("/addPurchase")
    public String addInner(ModelMap model)
    {
        model.addAttribute("purchase", new Purchase());
        return "add/add_inner";
    }

}
