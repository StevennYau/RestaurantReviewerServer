package com.project.RestaurantReviewer.controller;

import com.project.RestaurantReviewer.entity.Address;
import com.project.RestaurantReviewer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    //Post
    @PostMapping("addAddress")
    public Address addAddress(@RequestBody Address address){
        return addressService.saveAddress(address);
    }

    //get
    @GetMapping("/getAddressById/{id}")
    public Address getAddressById(@PathVariable int id){
        return addressService.getAddressById(id);
    }

    @GetMapping("/getAllAddress")
    public List<Address> getAllAddress(){
        return addressService.getAllAddress();
    }

    //delete
    @DeleteMapping("/deleteAddressById/{id}")
    public String deleteAddressById(@PathVariable int id){
        return addressService.deleteAddressById(id);
    }

    //update
    @PutMapping("/updateAddress")
    public Address updateAddress(@RequestBody Address address){
        return addressService.updateAddress(address);
    }
}
