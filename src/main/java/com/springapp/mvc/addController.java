package com.springapp.mvc;

import Backend.*;
import Backend.DAO.DistributorDAO;
import Backend.DAO.ManufacturerDAO;
import Backend.DAO.PurchaseDAO;
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
    private PurchaseDAO db;
    private ManufacturerDAO manufacturerDAO;
    private DistributorDAO distributorDAO;

    private RegisterForm rf = new RegisterForm();

    List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
    List<Distributor> distributors = new ArrayList<Distributor>();

    @RequestMapping(value = "/addPurchase",method = RequestMethod.POST)
    public String printWelcome(@ModelAttribute RegisterForm regForm,ModelMap model) {
        Long puchaseID;
        List<License> licenses = new ArrayList<License>();

        licenses = fromSerialKeyStringToLicenseObj(regForm);
        try {
            db.addPurchase(regForm.getPurchases(),"kalle");
        } catch (Exception e){}

        return "add/add_inner";
    }


    private  List<License> fromSerialKeyStringToLicenseObj(RegisterForm regForm){
        List<License> licenses = new ArrayList<License>();

        int year = Integer.parseInt(regForm.getDate().substring(0, 4));
        int month = (Integer.parseInt(regForm.getDate().substring(5,7)) - 1); // months: 0(jan), 11(dec)
        int day = Integer.parseInt(regForm.getDate().substring(8, 10));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        Date date = new Date(calendar.getTimeInMillis());

        String[] splittedSerialKeys = regForm.getSerialKeys().split(regForm.getKeySeparator());
        for(int i=0;i<splittedSerialKeys.length;++i){
            licenses.add(new License(0,"Kalle", splittedSerialKeys[i],0, date));
        }

        return licenses;
    }


    private void getManufacturersAndDistributors(){
        manufacturers =  manufacturerDAO.searchManufacturerByName("");
        distributors = distributorDAO.searchDistributorByName("");
    }




    @RequestMapping("/addPurchase")
    public String addInner(ModelMap model)
    {
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("distributors",distributors);
        model.addAttribute("licenses",new License());
        model.addAttribute("registerForm",new RegisterForm());
        return "add/add_inner";
    }



    }

