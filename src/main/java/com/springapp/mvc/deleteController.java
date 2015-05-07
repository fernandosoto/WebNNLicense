package com.springapp.mvc;

import Backend.DAO.DistributorDAOInterface;
import Backend.DAO.ManufacturerDAOInterface;
import Backend.DAO.PurchaseDAOInterface;
import Backend.DeletedForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class deleteController {


    private PurchaseDAOInterface pDAO;
    private ManufacturerDAOInterface mDAO;
    private DistributorDAOInterface dDAO;
    private DeletedForm delForm = new DeletedForm();


    // ------------------------ Delete_index -------------------------


    @RequestMapping(value = "/delete_index",method = RequestMethod.POST)
    public String deleteIndex(@ModelAttribute DeletedForm delForm, ModelMap model) {


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
    public String deleteInner(@ModelAttribute DeletedForm delForm, ModelMap model) {

        return "delete/delete_inner";
    }




    @RequestMapping(value = "/delete_inner", method = RequestMethod.GET)
    public String deleteInner(ModelMap model)
    {

        model.addAttribute("deleteForm", delForm);
        return "delete/delete_inner";
    }
}
