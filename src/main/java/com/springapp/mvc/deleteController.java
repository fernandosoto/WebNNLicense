package com.springapp.mvc;

import Backend.DAO.DistributorDAOInterface;
import Backend.DAO.ManufacturerDAOInterface;
import Backend.DAO.PurchaseDAOInterface;
import Backend.Purchase;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anna on 2015-04-29.
 */
public class deleteController {

    private PurchaseDAOInterface pDAO;
    private ManufacturerDAOInterface mDAO;
    private DistributorDAOInterface dDAO;

    @RequestMapping(value = "/deletePurchase", method = RequestMethod.POST)
    public String deletePurchase(@ModelAttribute Purchase p, ModelMap model){

        pDAO.deletePurchase(p, "Kalle");

        return "delete/delete_inner";
    }
}
