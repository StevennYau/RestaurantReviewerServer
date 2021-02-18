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

    public int getFav_id() {
        return fav_id;
    }

    public void setFav_id(int fav_id) {
        this.fav_id = fav_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public int getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(int user_rating) {
        this.user_rating = user_rating;
    }
}
