package com.springapp.mvc;

import Backend.DAO.DistributorDAOInterface;
import Backend.DAO.ManufacturerDAOInterface;
import Backend.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Fernando on 2015-05-07.
 */
@Controller
public class ManuDistoController
{
    @Autowired
    private ManufacturerDAOInterface manufacturerDAO;
    @Autowired
    private DistributorDAOInterface distributorDAO;

    @RequestMapping(value="/addManufacturer",method= RequestMethod.GET)
    public String addManufacturer(ModelMap model){
        model.addAttribute("manufacturer",new Manufacturer());
        return "add/manufacturer_inner";
    }

    @RequestMapping(value="/addManufacturer",method = RequestMethod.POST)
    public String addManufacturerDB(@ModelAttribute Manufacturer manufacturer){
        manufacturerDAO.addManufacturer(manufacturer);
        return "";
    }


}
