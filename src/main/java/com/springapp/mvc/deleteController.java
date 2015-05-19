package com.springapp.mvc;

import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.PurchaseDAOInterface;
import Backend.DeleteForm;
import Backend.License;
import Backend.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Thomas on 2015-05-18.
 */
@Controller
public class deleteController {

    @Autowired
    private PurchaseDAOInterface pdao;
    @Autowired
    private LicenseDAOInterface ldao;
    private DeleteForm deleteForm = new DeleteForm();
    private List<License> dbLicenses;


    @RequestMapping(value = "/delete_inner",method = RequestMethod.GET)
    public String deleteInner(ModelMap model)
    {
        model.addAttribute("deleteForm",deleteForm);
        return "delete/delete_inner";
    }

    @RequestMapping(value = "/delete_inner",method = RequestMethod.POST)
    public String deleteInner(@ModelAttribute DeleteForm deleteForm,ModelMap model)
    {
        this.deleteForm.setRadioButtonSelect(deleteForm.getRadioButtonSelect());
        return "redirect:/delete_search";
    }



    @RequestMapping(value = "/delete_search",method = RequestMethod.GET)
    public String deleteSearch(ModelMap model)
    {
        model.addAttribute("deleteForm",deleteForm);
        return "delete/delete_search";
    }

    @RequestMapping(value = "/delete_search",method = RequestMethod.POST)
    public String deleteSearch(@ModelAttribute DeleteForm deleteForm,ModelMap model)
    {

        this.deleteForm.getPurchase().setProductName(deleteForm.getPurchase().getProductName());
        return "redirect:/delete_results";
    }


    @RequestMapping(value = "/delete_results",method = RequestMethod.GET)
    public String deleteResults(ModelMap model)
    {
        List<Purchase> p = null;
        try {
            p = pdao.searchPurchaseByName(deleteForm.getPurchase().getProductName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("purchases", p);
        model.addAttribute("deleteForm",deleteForm);
        return "delete/delete_results";
    }

    @RequestMapping(value = "/delete_results",method = RequestMethod.POST)
    public String deleteResults(@ModelAttribute DeleteForm deleteForm,ModelMap model)
    {
        Purchase purchase = pdao.searchPurchaseById(deleteForm.getPurchase().getPurchaseId());
        this.deleteForm.setPurchase(purchase);
        this.deleteForm.setDate(this.deleteForm.getPurchase().getCreatedDate().toString());
        return "redirect:/"+this.deleteForm.getRadioButtonSelect();
    }


    @RequestMapping(value = "/delete_purchase_details",method = RequestMethod.GET)
    public String deletePurchaseDetails(ModelMap model)
    {
        dbLicenses = ldao.searchLicenseByPurchase(deleteForm.getPurchase());
        List<String> licenses = deleteForm.LicenseDetailToString(dbLicenses);

        model.addAttribute("licenses", licenses);
        model.addAttribute("deleteForm",deleteForm);
        return "delete/delete_purchase_details";
    }

    @Transactional
    @RequestMapping(value = "/delete_purchase_details",method = RequestMethod.POST)
    public String deletePurchaseDetails(@ModelAttribute DeleteForm deleteForm,ModelMap model)
    {
        pdao.deletePurchase(this.deleteForm.getPurchase(), "Anwar al Sham");
        for(License l: dbLicenses) {
            ldao.deleteLicense(l, "Anwar al Halabi");
        }
        return "redirect:/delete_search";
    }



    @RequestMapping(value = "/delete_licenseKey_details",method = RequestMethod.GET)
    public String deleteLicenseKeyDetails(ModelMap model)
    {
        List<License> dbLicenses = ldao.searchLicenseByPurchase(deleteForm.getPurchase());
        List<String> licenses = deleteForm.LicenseDetailToString(dbLicenses);

        model.addAttribute("licenses", deleteForm.getLicenseKeyList(dbLicenses));
        model.addAttribute("deleteForm",deleteForm);

        return "delete/delete_licenseKey_details";
    }

    @RequestMapping(value = "/delete_licenseKey_details",method = RequestMethod.POST)
    public String deleteLicenseKeyDetails(@ModelAttribute DeleteForm deleteForm,ModelMap model)
    {
        this.deleteForm.setLicense(this.deleteForm.getLicenseFromId(deleteForm.getLicense().getLicenseId()));
        ldao.deleteLicense(this.deleteForm.getLicense(), "Anwar al Sham");
        this.deleteForm.clearLicenseKeys();
        return "redirect:/delete_licenseKey_details";
    }

}
