package com.springapp.mvc.rowMapperTest;

import Backend.DeletedPurchase;
import Backend.rowMapper.DeletedPurchaseRowMapper;
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
import static junit.framework.Assert.assertEquals;

/**
 * Created by Isak on 2015-05-25.
 */
public class DeletedPurchaseRowMapperTest {
    public final static long date = System.currentTimeMillis();
    public final static long TEST_PURCHASE_ID = 1;
    public final static String TEST_MANUFACTURER_NAME = "Microsoft";
    public final static String TEST_PRODUCT_NAME = "XP";
    public final static String TEST_LICENSE_TYPE = "OS";
    public final static String TEST_DISTRIBUTOR_NAME = "Komplett";
    public final static String TEST_FREE_TEXT = "good stuff";
    public final static long TEST_UPGRADE_FROM = 0;
    public final static String TEST_CREATED_BY = "BILL GABEN";
    public final static Date TEST_CREATED_DATE = new Date(date);
    public final static long TEST_DELETED_ID = 1;
    public final static String TEST_DELETED_BY = "BILL GATES";
    public final static Date TEST_DELETED_DATE = new Date(date);

    private static DeletedPurchase testDeletedPurchase;

    @Mock
    ResultSet resultSet;

    @InjectMocks
    DeletedPurchaseRowMapper deletedPurchaseRowMapper;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        testDeletedPurchase = new DeletedPurchase(TEST_PURCHASE_ID, TEST_MANUFACTURER_NAME, TEST_PRODUCT_NAME, TEST_LICENSE_TYPE, TEST_DISTRIBUTOR_NAME,
                TEST_FREE_TEXT, TEST_UPGRADE_FROM, TEST_CREATED_BY, TEST_CREATED_DATE, TEST_DELETED_ID, TEST_DELETED_BY, TEST_DELETED_DATE);
        initExpectations();
    }

    @Test
    public void testMapRow() throws SQLException {
        Assert.assertEquals(testDeletedPurchase, deletedPurchaseRowMapper.mapRow(resultSet, 1));
    }

    private void initExpectations() throws SQLException {
        when(resultSet.getLong(DeletedPurchaseRowMapper.PURCHASE_ID)).thenReturn(TEST_PURCHASE_ID);
        when(resultSet.getString(DeletedPurchaseRowMapper.MANUFACTURER_NAME)).thenReturn(TEST_MANUFACTURER_NAME);
        when(resultSet.getString(DeletedPurchaseRowMapper.PRODUCT_NAME)).thenReturn(TEST_PRODUCT_NAME);
        when(resultSet.getString(DeletedPurchaseRowMapper.LICENSE_TYPE)).thenReturn(TEST_LICENSE_TYPE);
        when(resultSet.getString(DeletedPurchaseRowMapper.DISTRIBUTOR_NAME)).thenReturn(TEST_DISTRIBUTOR_NAME);
        when(resultSet.getString(DeletedPurchaseRowMapper.FREE_TEXT)).thenReturn(TEST_FREE_TEXT);
        when(resultSet.getLong(DeletedPurchaseRowMapper.UPGRADED_FROM)).thenReturn(TEST_UPGRADE_FROM);
        when(resultSet.getString(DeletedPurchaseRowMapper.CREATED_BY)).thenReturn(TEST_CREATED_BY);
        when(resultSet.getDate(DeletedPurchaseRowMapper.CREATED_DATE)).thenReturn(TEST_CREATED_DATE);
        when(resultSet.getLong(DeletedPurchaseRowMapper.DELETED_ID)).thenReturn(TEST_DELETED_ID);
        when(resultSet.getString(DeletedPurchaseRowMapper.DELETED_BY)).thenReturn(TEST_DELETED_BY);
        when(resultSet.getDate(DeletedPurchaseRowMapper.DELETED_DATE)).thenReturn(TEST_DELETED_DATE);
    }
}
