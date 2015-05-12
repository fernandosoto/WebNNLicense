package com.springapp.mvc;

import Backend.DAO.PurchaseDAO;
import Backend.ModifyForm;
import Backend.Purchase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Thomas on 2015-04-24.
 */
@Controller
public class modifyController {

    private ModifyForm modifyForm = new ModifyForm();



    @RequestMapping(value = "/modify_inner",method = RequestMethod.GET)
    public String modifyIndex(ModelMap model)
    {
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_inner";
    }

    @RequestMapping(value = "/modify_inner",method = RequestMethod.POST)
    public String modifyIndex(@ModelAttribute ModifyForm modifyForm,ModelMap model)
    {
        this.modifyForm = modifyForm;
        return "redirect:/"+modifyForm.getRadioButtonSelect();
    }




    @RequestMapping(value = "/modify_licenseKeys",method = RequestMethod.GET)
    public String modifyLicenseKeys(ModelMap model)
    {
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_licenseKeys";
    }

    @RequestMapping(value = "/modify_licenseKeys",method = RequestMethod.POST)
    public String modifyLicenseKeys(@ModelAttribute ModifyForm modifyForm,ModelMap model)
    {
        this.modifyForm = modifyForm;
        return "redirect:/modify_";         // vart ska vi?
    }




    @RequestMapping(value = "/modify_assign_remove",method = RequestMethod.GET)
    public String modifyAssignRemove(ModelMap model)
    {
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_assign_remove";
    }

    @RequestMapping(value = "/modify_assign_remove",method = RequestMethod.POST)
    public String modifyAssignRemove(@ModelAttribute ModifyForm modifyForm,ModelMap model)
    {
        this.modifyForm = modifyForm;
        return "redirect:/modify_";         // vart ska vi?
    }




    @RequestMapping(value = "/modify_purchase",method = RequestMethod.GET)
    public String modifyPurchase(ModelMap model)
    {
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_purchase";
    }

    @RequestMapping(value = "/modify_purchase",method = RequestMethod.POST)
    public String modifyPurchase(@ModelAttribute ModifyForm modifyForm,ModelMap model)
    {
        this.modifyForm = modifyForm;
        return "redirect:/modify_";         // vart ska vi?
    }





}
