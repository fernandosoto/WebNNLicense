package com.springapp.mvc;

import Backend.SearchForm;
import Backend.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 2015-05-07.
 */
@Controller
public class loginController {

    private List<User> userList = new ArrayList<User>();
    private User user = new User();


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(ModelMap model)
    {
        preDefinedUsers();
        model.addAttribute("user", user);
        return "login/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String showPurchase(@ModelAttribute User user, ModelMap model)
    {
        for(User u:userList){
            if(u.equals(user)){
                return "redirect:/index";
            }
        }

        return "redirect:/login";
    }

    private void preDefinedUsers() {
        userList.add(new User("Karl","123"));
        userList.add(new User("Finn","123"));
        userList.add(new User("Jenny","123"));
        userList.add(new User("Cecilia","123"));
    }
}
