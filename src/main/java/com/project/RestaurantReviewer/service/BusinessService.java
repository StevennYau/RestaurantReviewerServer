package com.project.RestaurantReviewer.service;

import com.project.RestaurantReviewer.entity.Business;
import com.project.RestaurantReviewer.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    //post
    public Business saveBusiness(Business business){
        return businessRepository.save(business);
    }

    //get
    public List<Business> getBusinesses(){
        return businessRepository.findAll();
    }

    public Business getBusiness(int id){
        return businessRepository.findById(id).orElse(null);
    }

    public Business getBusinessByName(String name){
        return businessRepository.findByName(name);
    }

}
