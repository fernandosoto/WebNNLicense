package com.springapp.mvc;

import Backend.DAO.ManufacturerDAO;
import Backend.DAO.ManufacturerDAOInterface;
import Backend.Distributor;
import Backend.Manufacturer;
import Backend.RegisterForm;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class AppTests {
    private MockMvc mockMvc;

    @Mock
    private ManufacturerDAO manufacturerDAO;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;
    private addController addMock;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void simpleAddWebTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main/index"));

        mockMvc.perform(get("/addPurchase"))
                .andExpect(status().isOk())
                .andExpect(view().name("add/add_inner"));
    }


    @Test
    public void manufacturerTest()throws Exception{
        mockMvc.perform(get("/addManufacturer"))
                .andExpect(status().isOk())
                .andExpect(view().name("add/manufacturer_inner"));
    }

    @Test
    public void manufacturerPostTest()throws Exception{
        Manufacturer m = new Manufacturer(1,"Microsoft","Niklas s�ljer bra grejer!");
        mockMvc.perform(post("/addManufacturer")
                .param("id",Long.toString(m.getId()))
                .param("name",m.getName())
                .param("freeText",m.getFreeText()))
                .andExpect(status().isOk())
                .andExpect(view().name(""));
    }

    @Test
    public void manufacturerDAOTest()throws Exception{
        manufacturerDAO = new ManufacturerDAO();
        List<Manufacturer> mList = new ArrayList<Manufacturer>();
        mList.add(new Manufacturer(1,"Microsoft","Niklas s�ljer bra grejer!"));
        Mockito.when(manufacturerDAO.searchManufacturerByName("")).thenReturn(mList);

        List<Manufacturer> test = manufacturerDAO.searchManufacturerByName("Microsoft");
        assert(test.get(0).getName().equals(mList.get(0).getName()));

    }
}
