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

    public List<Business> saveBusinesses(List<Business> businesses){
        return businessRepository.saveAll(businesses);
    }

    //get
    public List<Business> getBusinesses(){
        return businessRepository.findAll();
    }

    public Business getBusinessById(int id){
        return businessRepository.findById(id).orElse(null);
    }

    public Business getBusinessByName(String name){
        return businessRepository.findByName(name);
    }

    //delete
    public String deleteBusiness(int id){
        businessRepository.deleteById(id);
        return "business " + id + " has been removed";
    }

    //update
    public Business updateBusiness(Business business){

        Business existingBusiness = businessRepository.findById((int) business.getId()).orElseThrow(() -> new IllegalStateException(
                "business with id " + business.getId() + " does not exist"
        ));

        existingBusiness.setName(business.getName());
        existingBusiness.setAddress(business.getAddress());
        existingBusiness.setCity(business.getCity());
        existingBusiness.setState(business.getState());
        existingBusiness.setPostal_code(business.getPostal_code());
        existingBusiness.setLatitude(business.getLatitude());
        existingBusiness.setLongitude(business.getLongitude());
        existingBusiness.setStars(business.getStars());
        existingBusiness.setIs_open(business.isIs_open());
        return businessRepository.save(existingBusiness); // save new business
    }

}
