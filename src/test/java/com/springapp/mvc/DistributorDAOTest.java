package com.springapp.mvc;

import Backend.DAO.LicenseDAO;
import Backend.Distributor;
import Backend.rowMapper.DistributorRowMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 2015-05-18.
 */
public class DistributorDAOTest {

    private static List<Distributor> allDistributor;
    private static List<Distributor> oneDistributor;

    private static Distributor d1;
    private static Distributor d2;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    DistributorRowMapper distributorRowMapper;

    @InjectMocks
    LicenseDAO lDao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Distributor d1 = new Distributor(23, "Inet", "Butik pa Hotorget");
        Distributor d2 = new Distributor(11, "Komplett", "Natbutik");

        allDistributor =  new ArrayList<Distributor>(2);
        allDistributor.add(d1);
        allDistributor.add(d2);

        oneDistributor = new ArrayList<Distributor>(1);
        oneDistributor.add(d2);
    }

    @Test
    public void testGetAllDistributor(){

    }
}
