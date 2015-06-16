package com.springapp.mvc;


import Backend.DAO.DistributorDAOInterface;
import Backend.Distributor;
import Backend.form.DistributorForm;
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
public class distributorController {

    private List<Distributor> distributors = new ArrayList<Distributor>();
    DistributorForm distributorForm = new DistributorForm();
    @Autowired
    private DistributorDAOInterface distributorDAO;

    @RequestMapping(value = "/distributor_inner",method = RequestMethod.GET)
    public String modifyIndex(ModelMap model)
    {
        model.addAttribute("distributorForm",distributorForm);
        return "distributor/distributor_inner";
    }

    @RequestMapping(value = "/distributor_inner",method = RequestMethod.POST)
    public String modifyIndex(@ModelAttribute DistributorForm distributorForm)
    {
        this.distributorForm.setRadioButtonSelect(distributorForm.getRadioButtonSelect());
        return "redirect:/"+distributorForm.getRadioButtonSelect();
    }



    @RequestMapping(value = "/distributor_add",method = RequestMethod.GET)
    public String distributorAdd(ModelMap model)
    {
        model.addAttribute("distributorForm",distributorForm);
        return "distributor/distributor_add";
    }

    @RequestMapping(value = "/distributor_add",method = RequestMethod.POST)
    public String distributorAdd(@ModelAttribute DistributorForm distributorForm)
    {
        if(distributorForm.getDistributor().getName().length()==0){
            return "redirect:/distributor_inner";
        }
        this.distributorForm.setDistributor(distributorForm.getDistributor());
        distributorDAO.addDistributor(this.distributorForm.getDistributor());
        return "redirect:/distributor_inner";
    }



    @RequestMapping(value = "/distributor_modify",method = RequestMethod.GET)
    public String distributorModify(ModelMap model)
    {
        distributors.clear();
        distributors =  distributorDAO.searchDistributorByName("");
        model.addAttribute("distributors", distributors);
        model.addAttribute("distributorForm",distributorForm);
        return "distributor/distributor_modify";
    }




    @RequestMapping(value = "/distributor_modify",method = RequestMethod.POST)
    public String distributorModify(@ModelAttribute DistributorForm distributorForm)
    {
        this.distributorForm.setDistributor(distributorForm.getDistributorById(distributors, distributorForm.getDistributor().getId()));
        return "redirect:/distributor_details";
    }


    @RequestMapping(value = "/distributor_details",method = RequestMethod.GET)
    public String distributorDetails(ModelMap model)
    {
        model.addAttribute("distributorForm",distributorForm);
        return "distributor/distributor_details";
    }


    @RequestMapping(value = "/distributor_details",method = RequestMethod.POST)
    public String distributorDetails(@ModelAttribute DistributorForm distributorForm)
    {
        this.distributorForm.getDistributor().setName(distributorForm.getDistributor().getName());
        this.distributorForm.getDistributor().setFreeText(distributorForm.getDistributor().getFreeText());
        System.out.println(this.distributorForm.getDistributor().getName());
        distributorDAO.editDistributor(this.distributorForm.getDistributor());
        return "redirect:/distributor_modify";
    }


}
