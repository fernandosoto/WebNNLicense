package com.springapp.mvc;

import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.PurchaseDAOInterface;
import Backend.DeletedPurchase;
import Backend.License;
import Backend.Purchase;
import Backend.form.SearchForm;
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
    private List<License> dbLicenses;

    @RequestMapping(value = "/search_inner",method = RequestMethod.GET)
    public String searchPurchaseByName(ModelMap model)
    {
        model.addAttribute("searchForm", searchForm);
        return "search/search_inner";
    }

    @RequestMapping(value = "/search_inner",method = RequestMethod.POST)
    public String showPurchase(@ModelAttribute SearchForm searchForm)
    {
        this.searchForm.setRadioButtonSelect(searchForm.getRadioButtonSelect());
        this.searchForm.getPurchase().setProductName(searchForm.getPurchase().getProductName());

        if(this.searchForm.getRadioButtonSelect().equals("user")){
            this.searchForm.setSearchUserName(searchForm.getSearchUserName());
            if(this.searchForm.getSearchUserName().length()==0){
                return "redirect:/search_inner";
            }
            return "redirect:/user_results";
        }

        return "redirect:/search_results";
    }


    @RequestMapping(value = "/search_results",method = RequestMethod.GET)
    public String searchResults(ModelMap model)throws Exception
    {

        List<Purchase> purchases = new ArrayList<Purchase>();
        if(this.searchForm.getRadioButtonSelect().equals("active")) {
            purchases = pdao.searchPurchaseByName(searchForm.getPurchase().getProductName());
        } else {
            List <DeletedPurchase> deletedPurchases =  pdao.searchDeletedPurchaseByName(this.searchForm.getPurchase().getProductName());
            for(DeletedPurchase dL: deletedPurchases) {
                purchases.add(dL);
            }
        }
        model.addAttribute("purchases", purchases);
        model.addAttribute("searchForm", searchForm);
        return "search/search_results";
    }


    @RequestMapping(value = "/search_results",method = RequestMethod.POST)
    public String searchResults(@ModelAttribute SearchForm searchForm)
    {
        Purchase purchase;
        if(this.searchForm.getRadioButtonSelect().equals("active")) {
            purchase = pdao.searchPurchaseById(searchForm.getPurchase().getPurchaseId());
            dbLicenses = ldao.searchLicenseByPurchase(searchForm.getPurchase());
            this.searchForm.setDeletedBy("  -");
            this.searchForm.setDeletedDate("  -");
        }else{
            purchase = pdao.searchDeletedPurchaseById(searchForm.getPurchase().getPurchaseId());
            dbLicenses = ldao.searchDeletedLicensesByPurchaseId(searchForm.getPurchase().getPurchaseId());
            this.searchForm.setDeletedBy(((DeletedPurchase) purchase).getDeletedBy());
            this.searchForm.setDeletedDate(((DeletedPurchase) purchase).getDeletedDate().toString());
        }

        this.searchForm.setPurchase(purchase);
        return "redirect:/search_details";
    }


    @RequestMapping(value = "/search_details",method = RequestMethod.GET)
    public String searchDetails(ModelMap model)
    {
        List<String> licenses = searchForm.LicenseDetailToString(dbLicenses);
        model.addAttribute("licenses", licenses);
        model.addAttribute("searchForm", searchForm);
        return "search/search_details";
    }


    @RequestMapping(value = "/user_results",method = RequestMethod.GET)
    public String userResults(ModelMap model)
    {
        this.searchForm.clearLicenseKeyToList();
        List<License> dbLicenses = ldao.searchLicenseByUser(this.searchForm.getSearchUserName());

        Purchase purchase;
        for(License l: dbLicenses){
            purchase = pdao.searchPurchaseById(l.getPurchaseId());
            this.searchForm.setLicenseKeyToList(purchase, l);
        }
        model.addAttribute("userLicenseList",this.searchForm.getLicenseToUser());
        model.addAttribute("searchForm", searchForm);
        return "search/user_results";
    }



}
