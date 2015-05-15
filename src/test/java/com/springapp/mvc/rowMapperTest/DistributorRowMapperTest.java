package com.springapp.mvc.rowMapperTest;

import Backend.Distributor;
import Backend.rowMapper.DistributorRowMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Fernando on 2015-05-15.
 */
public class DistributorRowMapperTest {

    public final static long TEST_DISTRIBUTOR_ID = 1;
    public final static String TEST_DISTRIBUTOR_NAME = "Komplett";
    public final static String TEST_FREE_TEXT = "Bra leveranstid";
    private static Distributor testDistributor;

    @Mock
    ResultSet rs;

    @InjectMocks
    DistributorRowMapper distributorRowMapper;

    @Before
    public void setUp()throws SQLException{
        MockitoAnnotations.initMocks(this);
        testDistributor = new Distributor(TEST_DISTRIBUTOR_ID,TEST_DISTRIBUTOR_NAME,TEST_FREE_TEXT);
        initExpectations();
    }

    @Test
    public void testMapRow() throws SQLException{
        assertEquals(testDistributor,distributorRowMapper.mapRow(rs,1));
    }

    private void initExpectations() throws SQLException{
        when(rs.getLong(DistributorRowMapper.DISTRIBUTOR_ID)).thenReturn(TEST_DISTRIBUTOR_ID);
        when(rs.getString(DistributorRowMapper.DISTRIBUTOR_NAME)).thenReturn(TEST_DISTRIBUTOR_NAME);
        when(rs.getString(DistributorRowMapper.FREE_TEXT)).thenReturn(TEST_FREE_TEXT);
    }
}
