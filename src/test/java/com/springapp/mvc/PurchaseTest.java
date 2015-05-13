package com.springapp.mvc;

import Backend.*;
import Backend.DAO.LicenseDAO;
import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.PurchaseDAO;
import Backend.DAO.PurchaseDAOInterface;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Fernando on 2015-05-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class PurchaseTest {

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @InjectMocks
    @Autowired
    private addController addController;

    @Mock
    private PurchaseDAO pdao;

    @Mock
    private LicenseDAO ldao;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        pdao = mock(PurchaseDAO.class);
        ldao = mock(LicenseDAO.class);
    }

    @Test
    public void addViewTest()throws Exception{
        mockMvc.perform(get("/addPurchase"))
                .andExpect(status().isOk())
                .andExpect(view().name("add/add_inner"));

    }

    private RegisterForm regFormBuilder(){
        RegisterForm regForm = new RegisterForm();
        Manufacturer m = new Manufacturer();
        m.setId(1);
        m.setName("Microsoft");
        m.setFreeText("Bajs");
        Distributor d = new Distributor();
        d.setId(1);
        d.setName("Komplett");
        d.setFreeText("Ofta");
        Purchase p = new Purchase();
        p.setProductName("Excel");
        p.setCreatedBy("Kalle");
        p.setCreatedDate(new Date(System.currentTimeMillis()));
        p.setDistributorName("Komplett");
        p.setManufacturerName("Microsoft");
        p.setType("Office");
        p.setFreeText("Bra grejer");
        regForm.setPurchases(p);
        regForm.setDate("2015-12-31");
        regForm.setManufacturer(m);
        regForm.setDistributor(d);
        regForm.setKeySeparator(",");
        regForm.setSerialKeys("1231,1231231,135123");
        List<License> l = new ArrayList<License>();
        License a = new License();
        a.setUser("kalle");
        a.setSerialKey("12321asd12");
        a.setExpireDate(new Date(System.currentTimeMillis()));
        l.add(a);
        regForm.setLicenses(l);

        return regForm;
    }

    @Test
    public void addDBTest()throws Exception{
        RegisterForm regForm = regFormBuilder();
        System.out.println(regForm.getManufacturer().getName());

        when(pdao.addPurchase(regForm.getPurchases(),"Kalle",1,1)).thenReturn(Long.valueOf(0));
        Long id = pdao.addPurchase(regForm.getPurchases(), null, 0, 1);
        assertEquals(Long.valueOf(0), id);

       // List<Purchase> p = pdao.searchPurchaseByName("");
        //assertEquals(1, p.size());
    }

}
