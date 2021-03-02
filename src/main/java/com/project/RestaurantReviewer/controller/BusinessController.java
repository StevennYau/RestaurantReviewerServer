package com.project.RestaurantReviewer.controller;

import com.project.RestaurantReviewer.entity.*;
import com.project.RestaurantReviewer.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @PostMapping("/addBusinesses")
    public List<Business> addBusinesses(@RequestBody List<Business> businesses){
        return businessService.saveBusinesses(businesses);
    }

    @PostMapping("/addBusinessWithAddress")
    public Business addBusinessWithAddress(@RequestBody BusinessInfo businessInfo){
        return businessService.addBusinessWithAddress(businessInfo);
    }

    //change to post
    @GetMapping("/addBusinessFromYelp/{location}/{keyword}/{offset}")
    public Business addBusinessFromYelp(@PathVariable String location, @PathVariable String keyword, @PathVariable int offset){
        return businessService.addBusinessFromYelp(location, keyword, offset);
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
    public List<Business> findBusinessByName(@PathVariable String name){
        return businessService.getBusinessByName(name);
    }

    @GetMapping("/getBusinessFromYelpById/{id}")
    public Mono<YelpBusiness> getBusinessFromYelpById(@PathVariable String id){
        return businessService.getBusinessFromYelpById(id);
    }

    //delete
    @DeleteMapping("/deleteBusiness/{id}")
    public String deleteBusiness(@PathVariable int id){
        return businessService.deleteBusiness(id);
    }

    @DeleteMapping("/deleteBusinessWithAddress/{id}") //using business id
    public ResponseEntity<?> deleteBusinessWithAddress(@PathVariable int id){
        businessService.deleteBusinessWithAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //put
    @PutMapping("/updateBusiness")
    public Business updateBusiness(@RequestBody Business business){
        return businessService.updateBusiness(business);
    }


}
