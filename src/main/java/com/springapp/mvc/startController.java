package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Thomas on 2015-04-24.
 */
@Controller
public class startController {
    @RequestMapping(value = "/")
    public String index(){

        return "main/index";
    }

}
