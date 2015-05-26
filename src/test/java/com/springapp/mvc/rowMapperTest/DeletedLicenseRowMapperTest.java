package com.springapp.mvc.rowMapperTest;

import Backend.DeletedLicense;
import Backend.rowMapper.DeletedLicenseRowMapper;
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
public class DeletedLicenseRowMapperTest {

    public final static long date = System.currentTimeMillis();
    public final static long TEST_LICENSE_ID = 1;
    public final static String TEST_USER = "Jacobe";
    public final static String TEST_SERIAL_KEY = "123A-DASD4-GITM";
    public final static long TEST_PURCHASE_ID = 1;
    public final static Date TEST_EXPIRE_DATE = new Date(date);
    public final static long TEST_DELETED_ID = 1;
    public final static String TEST_DELETED_BY = "Greenback";
    public final static Date TEST_DELETED_DATE = new Date(date);

    private static DeletedLicense testDeletedLicense;

    @Mock
    ResultSet resultSet;

    @InjectMocks
    DeletedLicenseRowMapper deletedLicenseRowMapper;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        testDeletedLicense = new DeletedLicense(TEST_LICENSE_ID, TEST_USER, TEST_SERIAL_KEY, TEST_PURCHASE_ID, TEST_EXPIRE_DATE,
                TEST_DELETED_ID, TEST_DELETED_BY, TEST_DELETED_DATE);
        initExpectations();
    }

    @Test
    public void testMapRow() throws SQLException {
        Assert.assertEquals(testDeletedLicense, deletedLicenseRowMapper.mapRow(resultSet, 1));
    }

    private void initExpectations() throws SQLException {
        when(resultSet.getLong(DeletedLicenseRowMapper.LICENSE_ID)).thenReturn(TEST_LICENSE_ID);
        when(resultSet.getString(DeletedLicenseRowMapper.LICENSE_USER)).thenReturn(TEST_USER);
        when(resultSet.getString(DeletedLicenseRowMapper.LICENSE_KEY)).thenReturn(TEST_SERIAL_KEY);
        when(resultSet.getLong(DeletedLicenseRowMapper.L_PURCHASE_ID)).thenReturn(TEST_PURCHASE_ID);
        when(resultSet.getDate(DeletedLicenseRowMapper.EXPIRE_DATE)).thenReturn(TEST_EXPIRE_DATE);
        when(resultSet.getLong(DeletedLicenseRowMapper.DELETED_ID)).thenReturn(TEST_DELETED_ID);
        when(resultSet.getString(DeletedLicenseRowMapper.DELETED_BY)).thenReturn(TEST_DELETED_BY);
        when(resultSet.getDate(DeletedLicenseRowMapper.DELETED_DATE)).thenReturn(TEST_DELETED_DATE);
    }
}