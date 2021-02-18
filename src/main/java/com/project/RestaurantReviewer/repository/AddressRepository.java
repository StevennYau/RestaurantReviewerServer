package com.project.RestaurantReviewer.repository;

import com.project.RestaurantReviewer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
