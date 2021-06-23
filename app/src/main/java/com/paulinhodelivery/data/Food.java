package com.paulinhodelivery.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Food implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("restaurantId")
    private Integer restaurantId;
    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private Double value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
