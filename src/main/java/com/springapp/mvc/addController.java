package com.springapp.mvc;

import Backend.*;
import Backend.DAO.*;
import Backend.form.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Thomas on 2015-04-24.
 */
@Controller
public class addController{
    @Autowired
    private PurchaseDAOInterface db;
    @Autowired
    private LicenseDAOInterface licenseDao;
    @Autowired
    private ManufacturerDAOInterface manufacturerDAO;
    @Autowired
    private DistributorDAOInterface distributorDAO;

    private List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
    private List<Distributor> distributors = new ArrayList<Distributor>();


    @RequestMapping(value = "/addPurchase",method = RequestMethod.GET)
    public String addInner(ModelMap model)
    {
        getManufacturersAndDistributors();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("distributors", distributors);
        model.addAttribute("registerForm",new RegisterForm());
        return "add/add_inner";
    }

    @Transactional
    @RequestMapping(value = "/addPurchase",method = RequestMethod.POST)
    public String printWelcome(@ModelAttribute RegisterForm regForm)throws Exception {
        Long purchaseID;
        List<License> licenses=regForm.getSerialKeysWithSeparatedLicenses();;

        Purchase p= regForm.getPurchases();
        purchaseID = db.addPurchase(p, User.getLoggedInUser(), regForm.getDistributor().getId(), regForm.getManufacturer().getId());

        for(License L: licenses){
            L.setPurchaseId(purchaseID);
            licenseDao.addLicense(L);
        }
        return "main/index";
    }

    @Transactional
    private void getManufacturersAndDistributors(){
        manufacturers =  manufacturerDAO.searchManufacturerByName("");
        distributors = distributorDAO.searchDistributorByName("");
    }

    }

