package com.project.RestaurantReviewer.controller;

import com.project.RestaurantReviewer.entity.Business;
import com.project.RestaurantReviewer.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    //post
    @PostMapping("/addBusiness")
    public Business addBusiness(@RequestBody Business business){
        return businessService.saveBusiness(business);
    }

    //get
    @GetMapping("/businesses")
    public List<Business> findAllBusinesses(){
        return businessService.getBusinesses();
    }

    @GetMapping("/businessById/{id}")
    public Business findBusinessById(@PathVariable int id){
        return businessService.getBusinessById(id);
    }

    @GetMapping("/businessByName/{name}")
    public Business findBusinessByName(@PathVariable String name){
        return businessService.getBusinessByName(name);
    }
}
