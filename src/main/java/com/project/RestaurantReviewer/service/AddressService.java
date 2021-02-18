package com.project.RestaurantReviewer.service;

import com.project.RestaurantReviewer.entity.Address;
import com.project.RestaurantReviewer.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    //post
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    //get
    public Address getAddressById(int id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    //delete
    public String deleteAddressById(int id) {
        addressRepository.deleteById(id);
        return "address " + id + " has been removed";
    }

    //update
    public Address updateAddress(Address address) {
        Address existingAddress = addressRepository.findById(address.getAddress_id()).orElseThrow(() -> new IllegalStateException(
                "address with id " + address.getAddress_id() + " does not exist"
        ));

        existingAddress.setLatitude(address.getLatitude());
        existingAddress.setLongitude(address.getLongitude());
        existingAddress.setAddress1(address.getAddress1());
        existingAddress.setAddress2(address.getAddress2());
        existingAddress.setAddress3(address.getAddress3());
        existingAddress.setCity(address.getCity());
        existingAddress.setZip_code(address.getZip_code());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setState(address.getState());

        return addressRepository.save(existingAddress);

    }
}
