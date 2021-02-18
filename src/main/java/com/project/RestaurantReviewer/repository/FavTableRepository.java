package com.project.RestaurantReviewer.repository;

import com.project.RestaurantReviewer.entity.FavTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavTableRepository extends JpaRepository<FavTable, Integer> {
    //FavTable findByName(String name);

    /*@Query(value = "")
    FavTable findByName(String name);*/

    /*@Query(value = "DELETE FROM ")
    void deleteByName(String name);*/
}
