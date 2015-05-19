package com.springapp.mvc;


import Backend.DAO.ManufacturerDAOInterface;
import Backend.Manufacturer;
import Backend.ManufacturerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2015-05-15.
 */
@Controller
public class manufacturerController {

    private List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
    ManufacturerForm manufacturerForm = new ManufacturerForm();
    @Autowired
    private ManufacturerDAOInterface manufacturerDAO;

    @RequestMapping(value = "/manufacturer_inner",method = RequestMethod.GET)
    public String modifyIndex(ModelMap model)
    {
        model.addAttribute("manufacturerForm",manufacturerForm);
        return "manufacturer/manufacturer_inner";
    }

    @RequestMapping(value = "/manufacturer_inner",method = RequestMethod.POST)
    public String modifyIndex(@ModelAttribute ManufacturerForm manufacturerForm,ModelMap model)
    {
        this.manufacturerForm.setRadioButtonSelect(manufacturerForm.getRadioButtonSelect());
        return "redirect:/"+manufacturerForm.getRadioButtonSelect();
    }


    @RequestMapping(value = "/manufacturer_add",method = RequestMethod.GET)
    public String manufacturerAdd(ModelMap model)
    {

        model.addAttribute("manufacturerForm",manufacturerForm);
        return "manufacturer/manufacturer_add";
    }

    @RequestMapping(value = "/manufacturer_add",method = RequestMethod.POST)
    public String manufacturerAdd(@ModelAttribute ManufacturerForm manufacturerForm,ModelMap model)
    {
        this.manufacturerForm.setManufacturer(manufacturerForm.getManufacturer());
        manufacturerDAO.addManufacturer(this.manufacturerForm.getManufacturer());
        return "redirect:/manufacturer_inner";
    }


    @RequestMapping(value = "/manufacturer_modify",method = RequestMethod.GET)
    public String manufacturerModify(ModelMap model)
    {
        manufacturers =  manufacturerDAO.searchManufacturerByName("");
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("manufacturerForm",manufacturerForm);
        return "manufacturer/manufacturer_modify";
    }


    @RequestMapping(value = "/manufacturer_modify",method = RequestMethod.POST)
    public String manufacturerModify(@ModelAttribute ManufacturerForm manufacturerForm,ModelMap model)
    {
        this.manufacturerForm.setManufacturer(manufacturerForm.getManufacturerById(manufacturers, manufacturerForm.getManufacturer().getId()));
        return "redirect:/manufacturer_details";
    }


    @RequestMapping(value = "/manufacturer_details",method = RequestMethod.GET)
    public String manufacturerDetails(ModelMap model)
    {

        model.addAttribute("manufacturerForm",manufacturerForm);
        return "manufacturer/manufacturer_details";
    }


    @RequestMapping(value = "/manufacturer_details",method = RequestMethod.POST)
    public String manufacturerDetails(@ModelAttribute ManufacturerForm manufacturerForm,ModelMap model)
    {
        this.manufacturerForm.getManufacturer().setName(manufacturerForm.getManufacturer().getName());
        this.manufacturerForm.getManufacturer().setFreeText(manufacturerForm.getManufacturer().getFreeText());
        System.out.println(this.manufacturerForm.getManufacturer().getName());
        //Backen func!
        return "redirect:/manufacturer_modify";
    }


}
