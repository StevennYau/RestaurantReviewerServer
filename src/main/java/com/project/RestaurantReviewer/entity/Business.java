package com.project.RestaurantReviewer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Business {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String Address;
    private String city;
    private String state;
    private String postal_code;
    private int latitude;
    private int longitude;
    private int stars;
    private boolean is_open;

}