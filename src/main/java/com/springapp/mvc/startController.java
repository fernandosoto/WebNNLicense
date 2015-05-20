package com.springapp.mvc;

import Backend.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Thomas on 2015-04-24.
 */
@Controller
public class startController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(ModelMap model){

        return "main/index";
    }
}
