package com.springapp.mvc;

import Backend.*;
import Backend.DAO.LicenseDAO;
import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.PurchaseDAO;
import Backend.DAO.PurchaseDAOInterface;
import Backend.rowMapper.DeletedLicenseRowMapper;
import Backend.rowMapper.PurchaseRowMapper;
import com.sun.org.apache.xpath.internal.SourceTree;
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
 * Created by Isak on 2015-05-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class LicenseDAOTest {

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    DeletedLicenseRowMapper deletedLicenseRowMapper;

    @InjectMocks
    LicenseDAO ldao;

    License l1;
    DeletedLicense dl1;

    List<License> expectedLicense;
    List<DeletedLicense> expectedDeleted;


    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(this.wac).build();

        l1 = new License();
        l1.setLicenseId(1);
        l1.setPurchaseId(1);
        l1.setUser("Kalle");
        l1.setSerialKey("dsadf234");
        l1.setExpireDate(new Date(20150513));

        dl1 = new DeletedLicense(40, null, "345345", 38, new Date(20150513), 1, "Kalle", new Date(20150518));
        expectedDeleted = new ArrayList<DeletedLicense>();
        expectedDeleted.add(dl1);
    }


    @Test
    public void searchDeletedTest() throws Exception {
        when(jdbcTemplate.query(ldao.SQL_SEARCH_DELETED_LICENSE, deletedLicenseRowMapper)).thenReturn(expectedDeleted);
        assertEquals(expectedDeleted, ldao.searchDeletedLicenses());
    }

//    @Test
//    public void addViewTest()throws Exception{
//        mockMvc.perform(get("/addPurchase"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("add/add_inner"));
//
//    }


//    @Test
//    public void searchDAOTest()throws Exception{
//        System.out.println(expected.get(0).getProductName());
//        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_PRODUCT_NAME, purchaseRowMapper, expected.get(0).getProductName()+"%")).thenReturn(expected);
//        //when(db.query(anyString(),(RowMapper<Purchase>) anyObject(), anyString())).thenReturn(expected);
//        //when(jdbcTemplate.query(pdao.SQL_SEARCH_ALL_PRODUCT,purchaseRowMapper)).thenReturn(expected);
//        //assertEquals(expected, pdao.searchAllPurchases());
//        assertEquals(expected, pdao.searchPurchaseByName(p1.getProductName()));
//    }

//    @Test(expected = DataAccessException.class)
//    public void searchNameDAOAccessExceptionTest()throws Exception{
//        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_PRODUCT_NAME,purchaseRowMapper,p1.getProductName()+"%")).thenThrow(mock(DataAccessException.class));
//
//        pdao.searchPurchaseByName(p1.getProductName());
//    }
//
//    @Test(expected = Exception.class)
//    public void searchNameDAONameExceptionTest()throws Exception{
//        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_PRODUCT_NAME,purchaseRowMapper,null)).thenThrow(mock(Exception.class));
//
//        pdao.searchPurchaseByName(null);
//    }
//
//    @Test
//    public void searchByIdTest(){
//        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_ID, purchaseRowMapper, expected.get(0).getPurchaseId())).thenReturn(expected);
//
//        assertEquals(expected.get(0), pdao.searchPurchaseById(expected.get(0).getPurchaseId()));
//    }
//
//    @Test
//    public void searchByDistributor(){
//        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_DISTRIBUTOR,purchaseRowMapper,expected.get(0).getDistributorName()+"%")).thenReturn(expected);
//
//        assertEquals(expected, pdao.searchPurchaseByDistributorName(expected.get(0).getDistributorName()));
//    }
//
//    @Test
//    public void searchByManufacturer(){
//        when(jdbcTemplate.query(pdao.SQL_SEARCH_BY_MANUFACTURER,purchaseRowMapper,expected.get(0).getManufacturerName()+"%")).thenReturn(expected);
//
//        assertEquals(expected,pdao.searchPurchaseByManufacturerName(expected.get(0).getManufacturerName()));
//    }





}
