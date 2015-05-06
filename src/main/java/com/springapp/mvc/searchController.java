package com.springapp.mvc;

import Backend.DAO.LicenseDAO;
import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.PurchaseDAO;
import Backend.DAO.PurchaseDAOInterface;
import Backend.Purchase;
import Backend.SearchForm;
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
    private PurchaseDAOInterface pdao;
    @Autowired
    private LicenseDAOInterface ldao;

    @RequestMapping(value = "/search_inner",method = RequestMethod.GET)
    public String searchPurchaseByName(ModelMap model)
    {
        model.addAttribute("searchForm", new SearchForm());
        return "search/search_inner";
    }

    @RequestMapping(value = "/search_inner",method = RequestMethod.POST)
    public String showPurchase(@ModelAttribute SearchForm searchForm, ModelMap model)
    {
        List<Purchase> p = pdao.searchPurchaseByName(searchForm.getPurchase().getProductName());
        model.addAttribute("purchases",p);
        return "search/search_results";
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
