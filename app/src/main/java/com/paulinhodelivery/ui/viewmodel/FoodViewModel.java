package com.paulinhodelivery.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paulinhodelivery.data.Food;
import com.paulinhodelivery.data.Restaurant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoodViewModel extends ViewModel {
    private String mockFood = "[" +
            "{id: 1, restaurantId: 1, image: 'taco.png', name: 'Taco carne de porco', value: 23.5}," +
            "{id: 2, restaurantId: 1, image: 'burrito.png', name: 'Burrito vegetariano', value: 15.5}," +
            "{id: 3, restaurantId: 1, image: 'nachos.png', name: 'Nacho com queijo', value: 32.5}," +
            "{id: 4, restaurantId: 2, image: 'bigmac.png', name: 'BigMac', value: 8.5}," +
            "{id: 5, restaurantId: 2, image: 'cheedar.png', name: 'Cheedar', value: 8.5}," +
            "{id: 6, restaurantId: 2, image: 'batata.png', name: 'Fritas Média', value: 3.5}," +
            "{id: 7, restaurantId: 8, image: 'mussarela.png', name: 'Pizza Mussarela', value: 35.5}," +
            "{id: 8, restaurantId: 8, image: 'brasileira.png', name: 'Pizza Brasileira', value: 40.1}," +
            "{id: 9, restaurantId: 8, image: 'supreme.png', name: 'Pizza Supreme', value: 42.5}" +
            "]";

    private MutableLiveData<List<Food>> list;

    public FoodViewModel() {
        list = new MutableLiveData<>();
    }

    public void filterRestaurant(Integer restaurantId) {
        Type listType = new TypeToken<ArrayList<Food>>(){}.getType();
        List<Food> foods = new Gson().fromJson(mockFood, listType);
        list.setValue(foods.stream().filter(food -> food.getRestaurantId().equals(restaurantId)).collect(Collectors.toList()));
    }

    public MutableLiveData<List<Food>> getList() {
        return list;
    }
}
