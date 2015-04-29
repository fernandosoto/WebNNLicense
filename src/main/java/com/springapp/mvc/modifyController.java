package com.springapp.mvc;

import Backend.DAO.PurchaseDAO;
import Backend.Purchase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Thomas on 2015-04-24.
 */
@Controller
public class modifyController {
    private PurchaseDAO db;
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public String printWelcome(@ModelAttribute Purchase purchase,ModelMap model) {
        System.out.println(purchase.getProductName());
        try {
          //  db.addPurchase(purchase);
        } catch (Exception e){}

        return "modify/modify_inner";
    }


    @RequestMapping("/modify")
    public String addInner(ModelMap model)
    {
       // model.addAttribute("purchase", new Purchase());
        return "modify/modify_inner";
    }

}
