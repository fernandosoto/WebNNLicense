package com.springapp.mvc;

import Backend.*;
import Backend.DAO.LicenseDAO;
import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.PurchaseDAO;
import Backend.DAO.PurchaseDAOInterface;
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

    @InjectMocks
    PurchaseDAO pdao;

    Purchase p1,p2;


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

    }

    @Test
    public void addViewTest()throws Exception{
        mockMvc.perform(get("/addPurchase"))
                .andExpect(status().isOk())
                .andExpect(view().name("add/add_inner"));

    }


    @Test
    public void searchDAOTest()throws Exception{

        List<Purchase> expected = new ArrayList<Purchase>();
        expected.add(p1);
        System.out.println(expected.get(0).getProductName());
        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_PRODUCT_NAME, purchaseRowMapper, expected.get(0).getProductName())).thenReturn(expected);
        //when(db.query(anyString(),(RowMapper<Purchase>) anyObject(), anyString())).thenReturn(expected);
        //when(jdbcTemplate.query(pdao.SQL_SEARCH_ALL_PRODUCT,purchaseRowMapper)).thenReturn(expected);
        //assertEquals(expected, pdao.searchAllPurchases());
        assertEquals(expected, pdao.searchPurchaseByName(p1.getProductName()));
    }

    @Test(expected = DataAccessException.class)
    public void searchNameDAOAccessExceptionTest()throws Exception{
        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_PRODUCT_NAME,purchaseRowMapper,p1.getProductName())).thenThrow(mock(DataAccessException.class));

        pdao.searchPurchaseByName(p1.getProductName());
    }

    @Test(expected = Exception.class)
    public void searchNameDAONameExceptionTest()throws Exception{
        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_PRODUCT_NAME,purchaseRowMapper,null)).thenThrow(mock(Exception.class));

        pdao.searchPurchaseByName(null);
    }

   /* @Test
    public void searchByIdTest(){
        when(jdbcTemplate.queryForObject(pdao.SQL_SEARCH_BY_ID,purchaseRowMapper,1)).thenReturn(p1);

        assertEquals(p1,pdao.searchPurchaseById(1));

    }*/



}
