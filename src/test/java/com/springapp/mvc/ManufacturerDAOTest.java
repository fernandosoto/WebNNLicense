package com.springapp.mvc;

import Backend.DAO.ManufacturerDAO;
import Backend.DAO.ManufacturerDAOInterface;
import Backend.Manufacturer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Isak on 2015-05-15.
 */
public class ManufacturerDAOTest {

    @Mock
    private ManufacturerDAOInterface mDAO;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mDAO = mock(ManufacturerDAO.class);
    }

    @Test
    public void shouldSearchManufacturerById(){
        Manufacturer m = new Manufacturer(1, "AdobeOf2015NewEdition", "noScopeSerialKeys.com");
        //mDAO.addManufacturer(m);
        when( mDAO.searchManufacturerById(1)).thenReturn(m);
        verify(mDAO).searchManufacturerById(1);
    }
}
