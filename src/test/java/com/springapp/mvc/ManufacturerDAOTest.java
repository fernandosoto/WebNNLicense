package com.springapp.mvc;

import Backend.Manufacturer;
import Backend.DAO.ManufacturerDAO;
import Backend.rowMapper.ManufacturerRowMapper;
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
 * Created by Isak on 2015-05-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class ManufacturerDAOTest {
    private Manufacturer m1,m2,m3,m4;
    private List<Manufacturer> mList;


    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;


    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    ManufacturerRowMapper manufacturerRowMapper;

    @InjectMocks
    ManufacturerDAO mDAO;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(this.wac).build();

        mList = new ArrayList<Manufacturer>();

        m1 = new Manufacturer(1, "Microsoft", "test test");
        m2 = new Manufacturer(2, "ahlbacks", "testar nytt");
        m3 = new Manufacturer(3, "automatron", "blabla");
        m4 = new Manufacturer(4, "123ijasd", "lsadas");

        mList.add(m1);
        mList.add(m2);
        mList.add(m3);
    }

    @Test
    public void shouldSearchManufacturerById(){
        when(jdbcTemplate.query(mDAO.SQL_SEARCH_BY_MANUFACTURER_ID,manufacturerRowMapper,(long) 1)).thenReturn(mList);
        assertEquals(mList.get(0), mDAO.searchManufacturerById((long) 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceptionManufacturerById(){
        mDAO.searchManufacturerById(0);
    }

    @Test
    public void shouldReturnManufacturerByNameQuery(){
        when(jdbcTemplate.query(mDAO.SQL_SEARCH_BY_MANUFACTURER_NAME,manufacturerRowMapper, mList.get(0).getName()+"%")).thenReturn(mList);
        assertEquals(mList, mDAO.searchManufacturerByName(mList.get(0).getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceptionManufacturerByName(){
        mDAO.searchManufacturerByName(null);
    }


}
