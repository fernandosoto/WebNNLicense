package com.springapp.mvc;

import Backend.DAO.DistributorDAOInterface;
import Backend.DAO.ManufacturerDAOInterface;
import Backend.DAO.PurchaseDAOInterface;
import Backend.DeleteForm;
import Backend.License;
import Backend.Purchase;
import Backend.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class deleteController {


    private PurchaseDAOInterface pDAO;
    private ManufacturerDAOInterface mDAO;
    private DistributorDAOInterface dDAO;
    private DeleteForm delForm = new DeleteForm();


    // ------------------------ Delete_index -------------------------


    @RequestMapping(value = "/delete_index",method = RequestMethod.POST)
    public String deleteIndex(@ModelAttribute DeleteForm delForm, ModelMap model) {


        //pDAO.deletePurchase(p, "Kalle");
        return "delete/delete_inner";
    }


    @RequestMapping(value = "/delete_index", method = RequestMethod.GET)
    public String deleteIndex(ModelMap model)
    {
        delForm.setRadioButtonAlternatives("Purchase");
        delForm.setRadioButtonAlternatives("License");

        model.addAttribute("labels",delForm.getRadioButtonAlternatives());
        model.addAttribute("deleteForm", delForm);
        return "delete/delete_index";
    }





    // -------------------------------- Delete_inner -----------------------------


    @RequestMapping(value = "/delete_inner",method = RequestMethod.POST)
    public String deleteInner(@ModelAttribute DeleteForm delForm, ModelMap model) {

        return "delete/delete_inner";
    }




    @RequestMapping(value = "/delete_inner", method = RequestMethod.GET)
    public String deleteInner(ModelMap model)
    {

        model.addAttribute("deleteForm", delForm);
        return "delete/delete_inner";
    }
}
