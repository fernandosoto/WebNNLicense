package com.springapp.mvc;

import Backend.DAO.LicenseDAO;
import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.PurchaseDAO;
import Backend.DAO.PurchaseDAOInterface;
import Backend.DeletedPurchase;
import Backend.License;
import Backend.Purchase;
import Backend.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
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
    private SearchForm searchForm = new SearchForm();


    @RequestMapping(value = "/search_inner",method = RequestMethod.GET)
    public String searchPurchaseByName(ModelMap model)
    {
        model.addAttribute("searchForm", searchForm);
        return "search/search_inner";
    }

    @RequestMapping(value = "/search_inner",method = RequestMethod.POST)
    public String showPurchase(@ModelAttribute SearchForm searchForm, ModelMap model)
    {
        this.searchForm.setRadioButtonSelect(searchForm.getRadioButtonSelect());
        this.searchForm.getPurchase().setProductName(searchForm.getPurchase().getProductName()); // S�tter namnet fr�n webben
        return "redirect:/search_results";
    }




    @RequestMapping(value = "/search_results",method = RequestMethod.GET)
    public String searchResults(ModelMap model)throws Exception
    {
        List<Purchase> purchases = new ArrayList<Purchase>();
        if(this.searchForm.getRadioButtonSelect().equals("active")) {
            purchases = pdao.searchPurchaseByName(searchForm.getPurchase().getProductName());
        } else {
            List <DeletedPurchase> deletedPurchases =  pdao.searchDeletedPurchases();
            for(DeletedPurchase dL: deletedPurchases) {
                purchases.add(dL);
            }
            // if purchases.size()==0
        }
        model.addAttribute("purchases", purchases);
        model.addAttribute("searchForm", searchForm);
        return "search/search_results";
    }


    @RequestMapping(value = "/search_results",method = RequestMethod.POST)
    public String searchResults(@ModelAttribute SearchForm searchForm, ModelMap model)
    {
        Purchase purchase = pdao.searchPurchaseById(searchForm.getPurchase().getPurchaseId());
        this.searchForm.setPurchase(purchase);
        return "redirect:/search_details";
    }


    @RequestMapping(value = "/search_details",method = RequestMethod.GET)
    public String searchDetails(ModelMap model)
    {
        List<License> dbLicenses = ldao.searchLicenseByPurchase(searchForm.getPurchase());
        List<String> licenses = searchForm.LicenseDetailToString(dbLicenses);
        model.addAttribute("licenses", licenses);
        model.addAttribute("searchForm", searchForm);
        return "search/search_details";
    }
}
