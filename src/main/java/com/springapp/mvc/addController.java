package com.springapp.mvc;

import Backend.*;
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

    private RegisterForm rf = new RegisterForm();
    private List<License> licenses = new ArrayList<License>();

    @RequestMapping(value = "/addPurchase",method = RequestMethod.POST)
    public String printWelcome(@ModelAttribute RegisterForm regForm,ModelMap model) {
        fromSerialKeyStringToLicenseObj(regForm);



        try {
           // db.addPurchase();
        } catch (Exception e){}

        return "add/add_inner";
    }

    private void fromSerialKeyStringToLicenseObj(RegisterForm regForm){


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
    }


    @RequestMapping("/addPurchase")
    public String addInner(ModelMap model)
    {
        model.addAttribute("licenses",new License());

        model.addAttribute("registerForm",new RegisterForm());
        return "add/add_inner";
    }



    }

