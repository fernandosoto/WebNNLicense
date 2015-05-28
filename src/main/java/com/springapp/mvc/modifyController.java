package com.springapp.mvc;

import Backend.*;
import Backend.DAO.*;
import Backend.form.ModifyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Thomas on 2015-05-12.
 */
@Controller
public class modifyController {

    @Autowired
    private ManufacturerDAOInterface manufacturerDAO;
    @Autowired
    private DistributorDAOInterface distributorDAO;
    @Autowired
    private PurchaseDAOInterface pdao;
    @Autowired
    private LicenseDAOInterface ldao;

    private ModifyForm modifyForm = new ModifyForm();
    private List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
    private List<Distributor> distributors = new ArrayList<Distributor>();

    @RequestMapping(value = "/modify_inner",method = RequestMethod.GET)
    public String modifyIndex(ModelMap model)
    {
        this.modifyForm.clearLicenseKeys();
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_inner";
    }

    @RequestMapping(value = "/modify_inner",method = RequestMethod.POST)
    public String modifyIndex(@ModelAttribute ModifyForm modifyForm)
    {
        this.modifyForm.setRadioButtonSelect(modifyForm.getRadioButtonSelect());
        return "redirect:/modify_search";
    }


    @RequestMapping(value = "/modify_search",method = RequestMethod.GET)
    public String modifySearch(ModelMap model)
    {
        this.modifyForm.clearLicenseKeys();
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_search";
    }

    @RequestMapping(value = "/modify_search",method = RequestMethod.POST)
    public String modifySearch(@ModelAttribute ModifyForm modifyForm)
    {
        this.modifyForm.getPurchase().setProductName(modifyForm.getPurchase().getProductName());
        return "redirect:/modify_results";
    }


    @RequestMapping(value = "/modify_results",method = RequestMethod.GET)
    public String modifyResults(ModelMap model)throws Exception
    {
        this.modifyForm.clearLicenseKeys();
        List<Purchase> p = pdao.searchPurchaseByName(modifyForm.getPurchase().getProductName());
        model.addAttribute("purchases", p);
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_results";
    }

    @RequestMapping(value = "/modify_results",method = RequestMethod.POST)
    public String modifyResults(@ModelAttribute ModifyForm modifyForm)
    {
        Purchase purchase = pdao.searchPurchaseById(modifyForm.getPurchase().getPurchaseId());
        this.modifyForm.setPurchase(purchase);
        this.modifyForm.setDate(purchase.getCreatedDate().toString());
        return "redirect:/"+this.modifyForm.getRadioButtonSelect();
    }


    @RequestMapping(value = "/modify_assign_remove",method = RequestMethod.GET)
    public String modifyAssignRemove(ModelMap model)
    {
        this.modifyForm.clearLicenseKeys();
        List<License> dbLicenses = ldao.searchLicenseByPurchase(modifyForm.getPurchase());
        model.addAttribute("licenses", modifyForm.getLicenseKeyList(dbLicenses));
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_assign_remove";
    }

    @Transactional
    @RequestMapping(value = "/modify_assign_remove",method = RequestMethod.POST)
    public String modifyAssignRemove(@ModelAttribute ModifyForm modifyForm)
    {
        this.modifyForm.setLicense(ldao.searchLicenseById(modifyForm.getLicense().getLicenseId()));
        this.modifyForm.getLicense().setUser(modifyForm.getLicense().getUser());
        ldao.editLicense(this.modifyForm.getLicense(), User.getLoggedInUser());
        this.modifyForm.clearLicenseKeys();
        return "redirect:/modify_assign_remove";
    }



    @RequestMapping(value = "/modify_licenseKeys",method = RequestMethod.GET)
    public String modifyDetails(ModelMap model)
    {
        List<License> dbLicenses = ldao.searchLicenseByPurchase(modifyForm.getPurchase());
        model.addAttribute("licenses", modifyForm.getLicenseKeyList(dbLicenses));
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_licenseKeys";
    }

    @RequestMapping(value = "/modify_licenseKeys",method = RequestMethod.POST)
    public String modifyLicenseKeys(@ModelAttribute ModifyForm modifyForm)
    {
        this.modifyForm.setLicense(ldao.searchLicenseById(modifyForm.getLicense().getLicenseId()));
        return "redirect:/modify_licenseKeyDetails";
    }


    @RequestMapping(value = "/modify_licenseKeyDetails",method = RequestMethod.GET)
    public String modifyLicenseDetails(ModelMap model)
    {

        this.modifyForm.setExpireDate(this.modifyForm.getLicense().getExpireDate().toString());
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_licenseKeyDetails";
    }


    @RequestMapping(value = "/modify_licenseKeyDetails",method = RequestMethod.POST)
    public String modifyLicenseDetails(@ModelAttribute ModifyForm modifyForm)
    {

        Date date = this.modifyForm.getDateFromString(modifyForm.getExpireDate());
        this.modifyForm.getLicense().setExpireDate(date);

        this.modifyForm.getLicense().setUser(modifyForm.getLicense().getUser());
        this.modifyForm.getLicense().setSerialKey(modifyForm.getLicense().getSerialKey());

        ldao.editLicense(this.modifyForm.getLicense(), User.getLoggedInUser());
        this.modifyForm.clearLicenseKeys();
        return "redirect:/modify_licenseKeys";
    }



    @RequestMapping(value = "/modify_purchase",method = RequestMethod.GET)
    public String modifyPurchase(ModelMap model)
    {
        getManufacturersAndDistributors();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("distributors", distributors);
        model.addAttribute("modifyForm", modifyForm);
        return "modify/modify_purchase";
    }

    @RequestMapping(value = "/modify_purchase",method = RequestMethod.POST)
    public String modifyPurchase(@ModelAttribute ModifyForm modifyForm) {
        this.modifyForm.getPurchase().setProductName(modifyForm.getPurchase().getProductName());
        this.modifyForm.getPurchase().setFreeText(modifyForm.getPurchase().getFreeText());
        this.modifyForm.getPurchase().setType(modifyForm.getPurchase().getType());

        Purchase newPurchase = new Purchase(this.modifyForm.getPurchase().getPurchaseId(),
                manufacturerDAO.searchManufacturerById(modifyForm.getManufacturer().getId()).getName(),
                this.modifyForm.getPurchase().getProductName(),
                this.modifyForm.getPurchase().getType(),
                distributorDAO.searchDistributorById(modifyForm.getDistributor().getId()).getName(),
                this.modifyForm.getPurchase().getFreeText(),
                this.modifyForm.getPurchase().getUpgradeFrom(),
                this.modifyForm.getPurchase().getCreatedBy(),
                this.modifyForm.getPurchase().getCreatedDate());

        pdao.editPurchase(newPurchase, User.getLoggedInUser(), modifyForm.getManufacturer().getId(), modifyForm.getDistributor().getId());

        if(modifyForm.getNewSerialKeys().length()!=0) {
            List<License> licenses = new ArrayList<License>();
            licenses.clear();
            licenses = modifyForm.getSerialKeysWithSeparatedLicenses();
            for (License L : licenses) {
                L.setPurchaseId(this.modifyForm.getPurchase().getPurchaseId());
                ldao.addLicense(L);
            }
        }

        return "redirect:/modify_search";
    }

    private void getManufacturersAndDistributors(){
        manufacturers =  manufacturerDAO.searchManufacturerByName("");
        distributors = distributorDAO.searchDistributorByName("");
    }





}
