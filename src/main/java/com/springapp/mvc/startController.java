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

    private boolean isLoggedIn=false;
    private User user = new User();
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(ModelMap model){

        if(!isLoggedIn){
            System.out.println(isLoggedIn);
            isLoggedIn=true;
            return "redirect:/login";
        }else {
            System.out.println(isLoggedIn + " utanför");
            return "main/index";
        }
    }

}
