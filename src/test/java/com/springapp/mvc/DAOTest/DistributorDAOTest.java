package com.springapp.mvc.DAOTest;

import Backend.DAO.DistributorDAO;
import Backend.DAO.LicenseDAO;
import Backend.Distributor;
import Backend.rowMapper.DistributorRowMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.Mockito.*;
import static junit.framework.Assert.assertEquals;

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
    DistributorDAO dDao;

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
    public void searchByIdDAOTest(){
        when(jdbcTemplate.query(dDao.SQL_SEARCH_DISTRIBUTOR_BY_ID,distributorRowMapper,oneDistributor.get(0).getId()))
                .thenReturn(oneDistributor);

        assertEquals(oneDistributor.get(0), dDao.searchDistributorById(oneDistributor.get(0).getId()));

        verify(jdbcTemplate,times(1)).query(dDao.SQL_SEARCH_DISTRIBUTOR_BY_ID, distributorRowMapper, oneDistributor.get(0).getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByIdIllegalArgumentExceptionTest(){
        dDao.searchDistributorById(0);
    }

    @Test(expected = DataAccessException.class)
    public void searchByIdDataAccessExceptionTest(){
        when(jdbcTemplate.query(dDao.SQL_SEARCH_DISTRIBUTOR_BY_ID,distributorRowMapper,(long)1)).thenThrow(mock(DataAccessException.class));

        dDao.searchDistributorById((long)1);
    }

    @Test(expected = NullPointerException.class)
    public void searchByIdNullpointerExceptionTest(){
        when(jdbcTemplate.query(dDao.SQL_SEARCH_DISTRIBUTOR_BY_ID,distributorRowMapper,(long)1)).thenThrow(mock(NullPointerException.class));

        dDao.searchDistributorById((long)1);
    }

    @Test
    public void searchByNameDAOTest(){
        when(jdbcTemplate.query(dDao.SQL_SEARCH_DISTRIBUTOR_BY_NAME,distributorRowMapper,allDistributor.get(0).getName()+"%"))
                .thenReturn(allDistributor);

        assertEquals(allDistributor, dDao.searchDistributorByName(allDistributor.get(0).getName()));

        verify(jdbcTemplate,times(1)).query(dDao.SQL_SEARCH_DISTRIBUTOR_BY_NAME, distributorRowMapper, allDistributor.get(0).getName() + "%");
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchByNameIllegalArgumentExceptionTest(){
        dDao.searchDistributorByName(null);
    }


    @Test(expected = DataAccessException.class)
    public void searchByNameDataAccessExceptionTest(){
        when(jdbcTemplate.query(dDao.SQL_SEARCH_DISTRIBUTOR_BY_NAME,distributorRowMapper, oneDistributor.get(0).getName()+"%"))
                .thenThrow(mock(DataAccessException.class));

        dDao.searchDistributorByName(oneDistributor.get(0).getName());

    }


}
