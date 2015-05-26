package com.springapp.mvc.rowMapperTest;

import Backend.Purchase;
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
 * Created by Fernando on 2015-05-13.
 */
public class PurchaseRowMapperTest {
    public final static long date = System.currentTimeMillis();
    public final static long TEST_PURCHASE_ID = 1;
    public final static String TEST_MANUFACTURER_NAME = "Microsoft";
    public final static String TEST_PRODUCT_NAME = "XP";
    public final static String TEST_LICENSE_TYPE = "OS";
    public final static String TEST_DISTRIBUTOR_NAME ="Komplett";
    public final static String TEST_FREE_TEXT = "bra grejer";
    public final static String TEST_CREATED_BY = "Duncan the tall the not knight";
    public final static Date TEST_CREATED_DATE = new Date(date);

    private static final Integer DUMMY_ROWNUM = 1;

    private static Purchase testPurchase;


    @Mock
    ResultSet resultSet;

    @InjectMocks
    PurchaseRowMapper purchaseRowMapper;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        testPurchase = new Purchase(TEST_PURCHASE_ID,TEST_MANUFACTURER_NAME, TEST_PRODUCT_NAME,TEST_LICENSE_TYPE,TEST_DISTRIBUTOR_NAME
        ,TEST_FREE_TEXT,TEST_CREATED_BY,TEST_CREATED_DATE);
        initExpectations();
    }

    @Test
    public void testMapRow() throws SQLException {
        Assert.assertEquals(testPurchase, purchaseRowMapper.mapRow(resultSet, 1));
    }


    private void initExpectations() throws SQLException {
        when(resultSet.getLong(PurchaseRowMapper.PURCHASE_ID)).thenReturn(TEST_PURCHASE_ID);
        when(resultSet.getString(PurchaseRowMapper.MANUFACTURER_NAME)).thenReturn(TEST_MANUFACTURER_NAME);
        when(resultSet.getString(PurchaseRowMapper.PRODUCT_NAME)).thenReturn(TEST_PRODUCT_NAME);
        when(resultSet.getString(PurchaseRowMapper.LICENSE_TYPE)).thenReturn(TEST_LICENSE_TYPE);
        when(resultSet.getString(PurchaseRowMapper.DISTRIBUTOR_NAME)).thenReturn(TEST_DISTRIBUTOR_NAME);
        when(resultSet.getString(PurchaseRowMapper.FREE_TEXT)).thenReturn(TEST_FREE_TEXT);
        when(resultSet.getString(PurchaseRowMapper.CREATED_BY)).thenReturn(TEST_CREATED_BY);
        when(resultSet.getDate(PurchaseRowMapper.CREATED_DATE)).thenReturn(TEST_CREATED_DATE);
    }

}
