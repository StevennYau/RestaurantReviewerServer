package com.project.RestaurantReviewer.service;

import com.project.RestaurantReviewer.entity.Address;
import com.project.RestaurantReviewer.entity.Business;
import com.project.RestaurantReviewer.entity.BusinessInfo;
import com.project.RestaurantReviewer.repository.AddressRepository;
import com.project.RestaurantReviewer.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private AddressRepository addressRepository;

    //post
    public Business saveBusiness(Business business){
        return businessRepository.save(business);
    }

    public Business addBusinessWithAddress(BusinessInfo businessInfo){
        // save address first
        Address addressInfo = new Address();
        addressInfo.setLatitude(businessInfo.getLatitude());
        addressInfo.setLongitude(businessInfo.getLongitude());
        addressInfo.setAddress1(businessInfo.getAddress1());
        addressInfo.setAddress2(businessInfo.getAddress2());
        addressInfo.setAddress3(businessInfo.getAddress3());
        addressInfo.setCity(businessInfo.getCity());
        addressInfo.setZip_code(businessInfo.getZip_code());
        addressInfo.setCountry(businessInfo.getCountry());
        addressInfo.setState(businessInfo.getState());
        Address newAddress = addressRepository.save(addressInfo);

        //  addres_id (newAddress.getid) and other info, insert a new Business record
        Business newBusiness = new Business();
        newBusiness.setAlias(businessInfo.getAlias());
        newBusiness.setName(businessInfo.getName());
        newBusiness.setImage_url(businessInfo.getImage_url());
        newBusiness.setIs_closed(businessInfo.isIs_closed());
        newBusiness.setUrl(businessInfo.getUrl());
        newBusiness.setReview_count(businessInfo.getReview_count());
        newBusiness.setRating(businessInfo.getRating());
        newBusiness.setPhone(businessInfo.getPhone());
        newBusiness.setAddress_id(newAddress.getAddress_id());

        return businessRepository.save(newBusiness);
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

    public String deleteBusinessWithAddress(int id){
        Business oldBusiness = businessRepository.findById(id).orElse(null);
        if (oldBusiness == null){
            return "business " + id + " does not exist";
        }
        int addressId = oldBusiness.getAddress_id();
        addressRepository.deleteById(addressId); // cascading delete
        businessRepository.deleteById(id);

        return "business " + id + " has been removed and address " + addressId + " has been removed";
    }

    //update
    public Business updateBusiness(Business business){

        Business existingBusiness = businessRepository.findById(business.getId()).orElseThrow(() -> new IllegalStateException(
                "business with id " + business.getId() + " does not exist"
        ));

        existingBusiness.setName(business.getName());
        existingBusiness.setAlias(business.getAlias());
        existingBusiness.setImage_url(business.getImage_url());
        existingBusiness.setIs_closed(business.isIs_closed());
        existingBusiness.setUrl(business.getUrl());
        existingBusiness.setReview_count(business.getReview_count());
        existingBusiness.setRating(business.getRating());
        existingBusiness.setPhone(business.getPhone());
        existingBusiness.setAddress_id(business.getAddress_id());

        return businessRepository.save(existingBusiness); // save new business
    }

}
