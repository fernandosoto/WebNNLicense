package com.springapp.mvc;

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


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String login(ModelMap model)
    {
        preDefinedUsers();
        model.addAttribute("user", user);
        return "login/login";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String showPurchase(@ModelAttribute User user)
    {
        this.user=user;
        for(User u:userList){
            if(u.equals(user)){
                User.setLoggedInUser(user.getUserName());
                return "redirect:/index";
            }
        }

        return "redirect:/";
    }

    private void preDefinedUsers() {
        userList.add(new User("Karl","123"));
        userList.add(new User("Finn","123"));
    }
}
