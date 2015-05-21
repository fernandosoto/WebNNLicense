package com.springapp.mvc;

import Backend.Manufacturer;
import Backend.Purchase;
import Backend.rowMapper.ManufacturerRowMapper;
import Backend.rowMapper.PurchaseRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import static org.mockito.Mockito.when;

/**
 * Created by Isak on 2015-05-15.
 */
public class ManufacturerRowMapperTest {
/*    public final static long date = System.currentTimeMillis();
    public final static long TEST_PURCHASE_ID = 1;
    public final static String TEST_MANUFACTURER_NAME = "Microsoft";
    public final static String TEST_PRODUCT_NAME = "XP";
    public final static String TEST_LICENSE_TYPE = "OS";
    public final static String TEST_DISTRIBUTOR_NAME ="Komplett";
    public final static String TEST_FREE_TEXT = "bra grejer";
    public final static String TEST_CREATED_BY = "Duncan the tall the not knight";
    public final static Date TEST_CREATED_DATE = new Date(date);

    private static final Integer DUMMY_ROWNUM = 1;

    private static Purchase testPurchase;*/

    public final static long TEST_MANUFACTURER_ID = 1;
    public final static String TEST_MANUFACTURER_NAME = "Microsoft";
    public final static String TEST_FREE_TEXT = "Niiiiice";

    private static Manufacturer testManufacturer;

    @Mock
    ResultSet resultSet;

    @InjectMocks
    ManufacturerRowMapper manufacturerRowMapper;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        testManufacturer = new Manufacturer(TEST_MANUFACTURER_ID, TEST_MANUFACTURER_NAME, TEST_FREE_TEXT);
        initExpectations();
    }

    @Test
    public void testMapRow() throws SQLException {
        Assert.assertEquals(testManufacturer, manufacturerRowMapper.mapRow(resultSet, 1));
    }


    private void initExpectations() throws SQLException {
        when(resultSet.getLong(ManufacturerRowMapper.MANUFACTURER_ID)).thenReturn(TEST_MANUFACTURER_ID);
        when(resultSet.getString(ManufacturerRowMapper.MANUFACTURER_NAME)).thenReturn(TEST_MANUFACTURER_NAME);
        when(resultSet.getString(ManufacturerRowMapper.FREE_TEXT)).thenReturn(TEST_FREE_TEXT);
    }

}
