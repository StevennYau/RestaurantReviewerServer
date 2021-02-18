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
    private int id;
    private String alias;
    private String name;
    private String image_url;
    private boolean is_closed;
    private String url;
    private int review_count;
    private int rating;
    private String phone;
    private int address_id;

    public Business() {
    }

    public Business(int id, String alias, String name, String image_url, boolean is_closed, String url, int review_count, int rating, String phone, int address_id) {
        this.id = id;
        this.alias = alias;
        this.name = name;
        this.image_url = image_url;
        this.is_closed = is_closed;
        this.url = url;
        this.review_count = review_count;
        this.rating = rating;
        this.phone = phone;
        this.address_id = address_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}