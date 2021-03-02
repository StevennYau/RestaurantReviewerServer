package com.project.RestaurantReviewer.repository;


import com.project.RestaurantReviewer.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business,Integer> {

    @Query(value = "SELECT * FROM business b WHERE b.name LIKE %:name%", nativeQuery = true)
    List<Business> findByName(@Param("name") String name);

}
