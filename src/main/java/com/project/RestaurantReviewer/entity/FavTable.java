package com.project.RestaurantReviewer.entity;

import javax.persistence.*;

@Entity
@Table(name = "FavTable")
public class FavTable {

    @Id
    @Column(name="fav_id")
    @GeneratedValue
    private int fav_id;
    @Column(name="user_id")
    private int user_id;
    @Column(name="business_id")
    private int business_id;
    @Column(name="user_rating")
    private int user_rating;

}
