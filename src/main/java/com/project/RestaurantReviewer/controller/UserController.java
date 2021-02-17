package com.project.RestaurantReviewer.controller;

import com.project.RestaurantReviewer.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/getUserTest")
    public String getUserTest(){
        String test = myUserDetailsService.getLoggedInUser();
        System.out.println(test);
        log.info("this is log back");
        return test;
    }

}
