package com.springapp.mvc;

import Backend.DAO.LicenseDAO;
import Backend.DAO.PurchaseDAO;
import Backend.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Fernando on 2015-04-24.
 */

@Controller
public class searchController
{
    @Autowired
    private PurchaseDAO pdao;
    private LicenseDAO ldao;

    @RequestMapping(value = "/searchPurchaseName")
    public String searchPurchaseByName(ModelMap model)
    {
        model.addAttribute("msg","Search by Product Name!");
        model.addAttribute("message","Product Name");
        model.addAttribute("var","productName");
        model.addAttribute("purchase", new Purchase());
        
        return "searchPurchase";
    }

    @RequestMapping(value = "/searchPurchaseName",method = RequestMethod.POST)
    public String showPurchaseByName(@ModelAttribute Purchase purchase, ModelMap model)
    {
        List<Purchase> p = pdao.searchPurchaseByName(purchase.getProductName());
        model.addAttribute("purchases",p);
        return "showPurchase";
    }

    @RequestMapping(value = "/searchPurchaseDistributor")
    public String searchPurchaseByDistributor(ModelMap model)
    {
        model.addAttribute("msg","Search by Distributor Name!");
        model.addAttribute("message","Distributor Name");
        model.addAttribute("var","distributorName");
        model.addAttribute("purchase",new Purchase());
        return "searchPurchase";
    }

    @RequestMapping(value = "/searchPurchaseDistributor",method = RequestMethod.POST)
    public String showPurchaseByDistributor(@ModelAttribute Purchase purchase, ModelMap model)
    {
        List<Purchase> p = pdao.searchPurchaseByDistributorName(purchase.getDistributorName());
        model.addAttribute("purchases",p);
        return "showPurchase";
    }

    @RequestMapping(value = "/searchPurchaseManufacturer")
    public String searchPurchaseByManufacturer(ModelMap model)
    {
        model.addAttribute("msg","Search by Manufacturer!");
        model.addAttribute("message","Manufacturer Name");
        model.addAttribute("var","manufacturerName");
        model.addAttribute("purchase",new Purchase());
        return "searchPurchase";
    }

    @RequestMapping(value = "/searchPurchaseManufacturer",method = RequestMethod.POST)
    public String showPurchaseByManufacturer(@ModelAttribute Purchase purchase, ModelMap model)
    {
        List<Purchase> p = pdao.searchPurchaseByManufacturerName(purchase.getManufacturerName());
        model.addAttribute("purchases",p);
        return "showPurchase";
    }

    @RequestMapping(value = "/searchPurchaseType")
    public String searchPurchaseByType(ModelMap model)
    {
        model.addAttribute("msg","Search by Type!");
        model.addAttribute("message","Type");
        model.addAttribute("var","type");
        model.addAttribute("purchase",new Purchase());
        return "searchPurchase";
    }

    @RequestMapping(value = "/searchPurchaseType",method = RequestMethod.POST)
    public String showPurchaseByType(@ModelAttribute Purchase purchase, ModelMap model)
    {
        List<Purchase> p = pdao.searchPurchaseByManufacturerName(purchase.getManufacturerName());
        model.addAttribute("purchases",p);

        return "showPurchase";
    }

}
