package com.springapp.mvc;

import Backend.*;
import Backend.DAO.LicenseDAO;
import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.PurchaseDAO;
import Backend.DAO.PurchaseDAOInterface;
import Backend.rowMapper.DeletedPurchaseRowMapper;
import Backend.rowMapper.PurchaseRowMapper;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import java.sql.*;
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
import static org.mockito.Matchers.*;

/**
 * Created by Fernando on 2015-05-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class PurchaseDAOTest {

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    PurchaseRowMapper purchaseRowMapper;

    @Mock
    DeletedPurchaseRowMapper deletedPurchaseRowMapper;

    @InjectMocks
    PurchaseDAO pdao;

    Purchase p1,p2;

    DeletedPurchase dp;

    List<Purchase> expected;
    List<DeletedPurchase> dpExpected;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(this.wac).build();
        p1 = new Purchase();
        p1.setPurchaseId(1);
        p1.setProductName("XP");
        p1.setCreatedDate(new Date(System.currentTimeMillis()));
        p1.setDistributorName("Komplett");
        p1.setManufacturerName("Microsoft");
        p1.setType("os");
        p1.setFreeText("Bra grejer");

        p2 = new Purchase();
        p2.setProductName("redfox");
        p2.setCreatedDate(new Date(System.currentTimeMillis()));
        p2.setDistributorName("Komplett");
        p2.setManufacturerName("Linux");
        p2.setType("server");
        p2.setFreeText("Bra server");
        //long purchaseId, String manufacturerName, String productName, String type, String distributorName,
        //String freeText, long upgradeFrom, String createdBy, Date createdDate, long id, String deletedBy,
        //        Date deletedDate
        dp = new DeletedPurchase(1,"Microsoft","Windows 7","OS","komplett","Bra grejer",0,"Kalle",new Date(System.currentTimeMillis()),
                0,"Kalle",new Date(System.currentTimeMillis()));
        dpExpected = new ArrayList<DeletedPurchase>();
        dpExpected.add(dp);

        expected = new ArrayList<Purchase>();
        expected.add(p1);

    }

    @Test
    public void addViewTest()throws Exception{
        mockMvc.perform(get("/addPurchase"))
                .andExpect(status().isOk())
                .andExpect(view().name("add/add_inner"));

    }


    @Test
    public void searchByNameDAOTest(){
        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_PRODUCT_NAME, purchaseRowMapper, expected.get(0).getProductName()+"%")).thenReturn(expected);

        assertEquals(expected, pdao.searchPurchaseByName(p1.getProductName()));
    }

    @Test(expected = DataAccessException.class)
    public void searchByNameDAOAccessExceptionTest()throws Exception{
        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_PRODUCT_NAME,purchaseRowMapper,p1.getProductName()+"%")).thenThrow(mock(DataAccessException.class));

        pdao.searchPurchaseByName(p1.getProductName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByNameDAONameExceptionTest(){

        pdao.searchPurchaseByName(null);
    }

    @Test
    public void searchByIdDAOTest(){
        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_ID, purchaseRowMapper, expected.get(0).getPurchaseId())).thenReturn(expected);

        assertEquals(expected.get(0), pdao.searchPurchaseById(expected.get(0).getPurchaseId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByIdExceptionTest(){

        pdao.searchPurchaseById(0);
    }

    @Test
    public void searchByDistributorDAOTest(){
        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_DISTRIBUTOR,purchaseRowMapper,expected.get(0).getDistributorName()+"%")).thenReturn(expected);

        assertEquals(expected, pdao.searchPurchaseByDistributorName(expected.get(0).getDistributorName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchDistributorDAOExceptionTest(){

        pdao.searchPurchaseByDistributorName(null);
    }

    @Test
    public void searchByManufacturerDAOTest(){
        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_MANUFACTURER,purchaseRowMapper,expected.get(0).getManufacturerName()+"%")).thenReturn(expected);

        assertEquals(expected, pdao.searchPurchaseByManufacturerName(expected.get(0).getManufacturerName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByManufacturerDAOExceptionTest(){

        pdao.searchPurchaseByManufacturerName(null);
    }

    @Test
    public void searchByType(){
        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_TYPE,purchaseRowMapper,expected.get(0).getType()+"%")).thenReturn(expected);

        assertEquals(expected, pdao.searchPurchaseByType(expected.get(0).getType()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByTypeDAOExceptionTest(){

        pdao.searchPurchaseByType(null);
    }

    @Test
    public void searchAllDeleted(){
        when(jdbcTemplate.query(pdao.SQL_SEARCH_ALL_DELETED,deletedPurchaseRowMapper)).thenReturn(dpExpected);

        assertEquals(dpExpected,pdao.searchDeletedPurchases());
    }

    @Test
    public void searchDeletedByNameDAOTest(){

    }





}
