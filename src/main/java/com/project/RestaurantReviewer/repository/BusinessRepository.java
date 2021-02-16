package com.project.RestaurantReviewer.repository;


import com.project.RestaurantReviewer.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business,Integer> {
    Business findByName(String name);
}
