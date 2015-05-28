package com.springapp.mvc.DAOTest;

import Backend.*;
import Backend.DAO.LicenseDAO;
import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.PurchaseDAO;
import Backend.DAO.PurchaseDAOInterface;
import Backend.rowMapper.DeletedLicenseRowMapper;
import Backend.rowMapper.LicenseRowMapper;
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
import static org.mockito.Mockito.*;
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

    @Mock
    LicenseRowMapper licenseRowMapper;

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

        expectedLicense = new ArrayList<License>();
        expectedLicense.add(l1);

        dl1 = new DeletedLicense(40, null, "345345", 38, new Date(20150513), 1, "Kalle", new Date(20150518));
        expectedDeleted = new ArrayList<DeletedLicense>();
        expectedDeleted.add(dl1);
    }


    @Test
    public void searchDeletedTest(){
        when(jdbcTemplate.query(ldao.SQL_SEARCH_DELETED_LICENSE, deletedLicenseRowMapper))
                .thenReturn(expectedDeleted);
        assertEquals(expectedDeleted, ldao.searchDeletedLicenses());

        verify(jdbcTemplate,times(1)).query(ldao.SQL_SEARCH_DELETED_LICENSE,deletedLicenseRowMapper);
    }

    @Test
    public void searchByUserDAOTest(){
        when(jdbcTemplate.query(ldao.SQL_SEARCH_LICENSE_BY_USER,licenseRowMapper,expectedLicense.get(0).getUser()))
                .thenReturn(expectedLicense);

        assertEquals(expectedLicense, ldao.searchLicenseByUser(expectedLicense.get(0).getUser()));

        verify(jdbcTemplate,times(1)).query(ldao.SQL_SEARCH_LICENSE_BY_USER,licenseRowMapper,expectedLicense.get(0).getUser());
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByUserDAOExceptionTest(){
        ldao.searchLicenseByUser(null);
    }

    @Test
    public void searchByPurchaseDAOTest(){
        Purchase p  = new Purchase();
        p.setPurchaseId(1);
        p.setProductName("XP");
        p.setCreatedDate(new Date(System.currentTimeMillis()));
        p.setDistributorName("Komplett");
        p.setManufacturerName("Microsoft");
        p.setType("os");
        p.setFreeText("Bra grejer");

        when(jdbcTemplate.query(ldao.SQL_SEARCH_LICENSE_BY_PURCHASE,licenseRowMapper,p.getPurchaseId())).thenReturn(expectedLicense);

        assertEquals(expectedLicense, ldao.searchLicenseByPurchase(p));

        verify(jdbcTemplate,times(1)).query(ldao.SQL_SEARCH_LICENSE_BY_PURCHASE,licenseRowMapper,p.getPurchaseId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByPurchaseDAOExceptionTest(){
        ldao.searchLicenseByPurchase(null);
    }


    @Test
    public void searchByIdDAOTest(){
        when(jdbcTemplate.query(ldao.SQL_SEARCH_LICENSE_BY_ID,licenseRowMapper,expectedLicense.get(0).getLicenseId())).thenReturn(expectedLicense);

        assertEquals(expectedLicense.get(0), ldao.searchLicenseById(expectedLicense.get(0).getLicenseId()));

        verify(jdbcTemplate,times(1)).query(ldao.SQL_SEARCH_LICENSE_BY_ID,licenseRowMapper,expectedLicense.get(0).getLicenseId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByIdDAOExceptionTest(){
        ldao.searchLicenseById(0);
    }


    @Test
    public void searchByPurchaseIdForDeletedLicenses(){
        when(jdbcTemplate.query(ldao.SQL_SEARCH_DELETED_LICENSE_BY_PURCHASE_ID,licenseRowMapper,expectedLicense.get(0).getPurchaseId()))
                .thenReturn(expectedLicense);

        ldao.searchDeletedLicensesByPurchaseId(expectedLicense.get(0).getPurchaseId());

        verify(jdbcTemplate,times(1)).query(ldao.SQL_SEARCH_DELETED_LICENSE_BY_PURCHASE_ID,licenseRowMapper,expectedLicense.get(0).getPurchaseId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByPurchaseIdForDeletedLicensesIllegalArgumentException(){
        ldao.searchDeletedLicensesByPurchaseId(0);
    }
}
