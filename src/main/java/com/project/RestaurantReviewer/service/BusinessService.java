package com.project.RestaurantReviewer.service;

import com.project.RestaurantReviewer.entity.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    //post
    public Business saveBusiness(Business business){
        return businessRepository.save(business);
    }

    //get
    public Business getBusinesses(){
        return businessRepository.findAll();
    }

    public Business getBusiness(int id){
        return businessRepository.findById(id).orElse(null);
    }

}
