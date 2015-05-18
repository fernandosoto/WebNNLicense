package com.springapp.mvc.rowMapperTest;

import Backend.License;
import Backend.rowMapper.LicenseRowMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Fernando on 2015-05-18.
 */
public class LicenseRowMapperTest {
    public static final long TEST_LICENSE_ID = 1;
    public static final String TEST_LICENSE_USER = "Kalle";
    public static final String TEST_LICENSE_KEY = "435ASQ5587";
    public static final long TEST_L_PURCHASE_ID= 1;
    public static final Date TEST_EXPIRE_DATE = new Date(System.currentTimeMillis());
    private static License testLicense;

    @Mock
    ResultSet rs;

    @InjectMocks
    LicenseRowMapper licenseRowMapper;

    @Before
    public void setUp()throws SQLException {
        MockitoAnnotations.initMocks(this);
        testLicense = new License(TEST_LICENSE_ID,TEST_LICENSE_USER,TEST_LICENSE_KEY,TEST_L_PURCHASE_ID,TEST_EXPIRE_DATE);
        initExpectations();
    }

    @Test
    public void testMapRow() throws SQLException{
        assertEquals(testLicense,licenseRowMapper.mapRow(rs,1));
    }

    private void initExpectations() throws SQLException{
        when(rs.getLong(LicenseRowMapper.LICENSE_ID)).thenReturn(TEST_LICENSE_ID);
        when(rs.getString(LicenseRowMapper.LICENSE_USER)).thenReturn(TEST_LICENSE_USER);
        when(rs.getString(LicenseRowMapper.LICENSE_KEY)).thenReturn(TEST_LICENSE_KEY);
        when(rs.getLong(LicenseRowMapper.L_PURCHASE_ID)).thenReturn(TEST_L_PURCHASE_ID);
        when(rs.getDate(LicenseRowMapper.EXPIRE_DATE)).thenReturn(TEST_EXPIRE_DATE);
    }
}
