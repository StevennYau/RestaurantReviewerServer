package com.project.RestaurantReviewer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.RestaurantReviewer.entity.*;
import com.project.RestaurantReviewer.repository.AddressRepository;
import com.project.RestaurantReviewer.repository.BusinessRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {

    static final Logger LOG = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private WebClient webClient;

    //post
    public Business saveBusiness(Business business){
        return businessRepository.save(business);
    }

    public Business addBusinessWithAddress(BusinessInfo businessInfo) {
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

    public Business addBusinessFromYelp(String location, String keyword, int offset) {

        /*Mono<YelpBusiness[]> response = webClient.get()
                .uri("/businesses/search?location=" + location + "&term=" + keyword)
                .header("Authorization", "Bearer" +
                        " EVdKo6SSXwu6FezJr4Zh40LuOupqk95vokRdG6BRdBHJhAY6DcyIQ6WzJkx27g1PKb-CWZ8xZSwhqasnaBbt0QXj0ZN1OvssptC05Z93UZ9a4tBLh1PFix0lC78tYHYx")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(YelpBusiness[].class);
       // JSONObject myObject = new JSONObject(result);*/

        //YelpBusiness[] yelpBusinesses = response.block();

       /* Mono<BusinessWrapper> response = webClient.get()
                .uri("/businesses/search?location=" + location + "&term=" + keyword)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BusinessWrapper.class);
                //.collectList();*/

        Mono<BusinessWrapper> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                .path("/businesses/search")
                .queryParam("location", location)
                .queryParam("term", keyword)
                .queryParam("offset", offset)
                .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BusinessWrapper.class);
        //.collectList();

        BusinessWrapper yelpBusinesses =  response.block();

        //manipulate info for use
        List<YelpBusiness> yelpBusinessList;


        assert yelpBusinesses != null;
        yelpBusinessList = yelpBusinesses.getBusinesses();

        /*String alias;
        String name;
        String image_url;
        boolean is_closed;
        String url;
        int review_count;
        int rating;
        String phone;*/
        //address_id
        Business businessReturned = null;
        for (YelpBusiness yelpBusiness: yelpBusinessList) {
            /*alias = yelpBusiness.getAlias();
            name = yelpBusiness.getName();
            image_url = yelpBusiness.getImage_url();
            is_closed = yelpBusiness.isIs_closed();
            url = yelpBusiness.getUrl();
            review_count = yelpBusiness.getReview_count();
            rating = yelpBusiness.getRating();
            phone = yelpBusiness.getPhone();*/

            Address addressInfo2 = new Address();
            addressInfo2.setLatitude(yelpBusiness.getCoordinates().getLatitude());
            addressInfo2.setLongitude(yelpBusiness.getCoordinates().getLongitude());
            addressInfo2.setAddress1(yelpBusiness.getLocation().getAddress1());
            addressInfo2.setAddress2(yelpBusiness.getLocation().getAddress2());
            addressInfo2.setAddress3(yelpBusiness.getLocation().getAddress3());
            addressInfo2.setCity(yelpBusiness.getLocation().getCity());
            addressInfo2.setZip_code(yelpBusiness.getLocation().getZip_code());
            addressInfo2.setCountry(yelpBusiness.getLocation().getCountry());
            addressInfo2.setState(yelpBusiness.getLocation().getState());
            Address newAddress2 = addressRepository.save(addressInfo2);

            //  addres_id (newAddress.getid) and other info, insert a new Business record
            Business newBusiness2 = new Business();
            newBusiness2.setAlias(yelpBusiness.getAlias());
            newBusiness2.setName(yelpBusiness.getName());
            newBusiness2.setImage_url(yelpBusiness.getImage_url());
            newBusiness2.setIs_closed(yelpBusiness.isIs_closed());
            newBusiness2.setUrl(yelpBusiness.getUrl());
            newBusiness2.setReview_count(yelpBusiness.getReview_count());
            newBusiness2.setRating(yelpBusiness.getRating());
            newBusiness2.setPhone(yelpBusiness.getPhone());
            newBusiness2.setAddress_id(newAddress2.getAddress_id());
            businessReturned = businessRepository.save(newBusiness2);
        }


        LOG.debug(" size =" + yelpBusinesses.getBusinesses().size()+"");
        LOG.debug("erioghoerigh");


        return businessReturned; // whole
        //return yelpBusinessList; //all businesses

    }

    public Mono<YelpBusiness> getBusinessFromYelpById(String id) {

        Mono<YelpBusiness> response = webClient.get()
                .uri("/businesses/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(YelpBusiness.class);


        return response;

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

    public List<Business> getBusinessByName(String name){
        return businessRepository.findByName(name);
    }

    //delete
    public String deleteBusiness(int id){
        businessRepository.deleteById(id);
        return "business " + id + " has been removed";
    }

    public void deleteBusinessWithAddress(int id){
        Business oldBusiness = businessRepository.findById(id).orElse(null);
        if (oldBusiness == null){
            return;
        }
        int addressId = oldBusiness.getAddress_id();
        addressRepository.deleteById(addressId); // cascading delete
        businessRepository.deleteById(id);

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
