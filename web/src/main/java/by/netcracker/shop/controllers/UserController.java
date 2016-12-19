package by.netcracker.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String loginUser() {
        return "user/login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationUser(){
        return "user/registration";
    }
}
