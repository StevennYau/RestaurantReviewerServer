package com.project.RestaurantReviewer.repository;

import com.project.RestaurantReviewer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);

    User findUserById(int id);

    @Query(value = "SELECT id FROM User u WHERE u.user_name = :userName",nativeQuery = true)
    int findIdByUser(@Param("userName") String userName);

}
