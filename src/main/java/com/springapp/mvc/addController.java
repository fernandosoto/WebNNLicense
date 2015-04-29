package com.springapp.mvc;

import Backend.*;
import Backend.DAO.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Thomas on 2015-04-24.
 */
@Controller
public class addController{
    private PurchaseDAOInterface db;
    private LicenseDAOInterface licenseDao;
    private ManufacturerDAOInterface manufacturerDAO;
    private DistributorDAOInterface distributorDAO;

    List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
    List<Distributor> distributors = new ArrayList<Distributor>();

    @RequestMapping(value = "/addPurchase",method = RequestMethod.POST)
    public String printWelcome(@ModelAttribute RegisterForm regForm,ModelMap model) {
        Long purchaseID;
        List<License> licenses = new ArrayList<License>();
        licenses = regForm.getSerialKeysWithSeparatedLicenses();
        Purchase p= regForm.getPurchases();
        System.out.println(p.getProductName());
        purchaseID = db.addPurchase(p,"kalle");
        for(License l: licenses){
            l.setPurchaseId(purchaseID);
            licenseDao.addLicense(l);
        }

        return "add/add_inner";
    }

    private void getManufacturersAndDistributors(){

        manufacturers =  manufacturerDAO.searchManufacturerByName("");
        distributors = distributorDAO.searchDistributorByName("");
    }


    @RequestMapping("/addPurchase")
    public String addInner(ModelMap model)
    {
        manufacturers.add(new Manufacturer(0,"Adobe","freeee"));
        distributors.add(new Distributor(0,"webhallen","ffreee"));

        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("distributors",distributors);
        model.addAttribute("registerForm",new RegisterForm());
        return "add/add_inner";
    }

    }

