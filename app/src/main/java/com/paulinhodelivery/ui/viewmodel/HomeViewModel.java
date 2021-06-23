package com.paulinhodelivery.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paulinhodelivery.data.Restaurant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeViewModel extends ViewModel {

    public String mockRestaurant = "[" +
            "{id: 1, logo: 'tacobell.png', name: 'Taco Bell', type: 'Lanche', distance: '3', stats: '4.5'}," +
            "{id: 2, logo: 'mcdonalds.png', name: 'Mc Donalds', type: 'Lanche', distance: '4.2', stats: '4.6'}," +
            "{id: 3, logo: 'Burguer King.png', name: 'Burguer King', type: 'Lanche', distance: '2', stats: '4.1'}," +
            "{id: 4, logo: 'thefifties.png', name: 'The Fifties', type: 'Lanche', distance: '7.8', stats: '4.9'}," +
            "{id: 5, logo: 'galetos.png', name: 'Galetos', type: 'Brasileira', distance: '3', stats: '4.5'}," +
            "{id: 6, logo: 'liglig.png', name: 'Lig Lig', type: 'Chinesa', distance: '3', stats: '4.5'}," +
            "{id: 7, logo: 'chineinbox.png', name: 'Chine In Box', type: 'Chinesa', distance: '3', stats: '4.5'}," +
            "{id: 8, logo: 'pizzahut.png', name: 'Pizza Hut', type: 'Pizza', distance: '3', stats: '4.5'}," +
            "{id: 9, logo: 'outback.png', name: 'Outback', type: 'Lanche', distance: '3', stats: '4.5'}" +
            "]";

    private MutableLiveData<List<Restaurant>> list;
    private MutableLiveData<List<Restaurant>> listFiltered;

    public HomeViewModel() {
        list = new MutableLiveData<>();
        listFiltered = new MutableLiveData<>();

        Type listType = new TypeToken<ArrayList<Restaurant>>(){}.getType();
        List<Restaurant> restaurants = new Gson().fromJson(mockRestaurant, listType);
        list.setValue(restaurants);
        listFiltered.setValue(restaurants);
    }

    public void findRestaurant(String text) {
        Type listType = new TypeToken<ArrayList<Restaurant>>(){}.getType();
        List<Restaurant> restaurants = new Gson().fromJson(mockRestaurant, listType);
        listFiltered.setValue(restaurants.stream()
                .filter(restaurant -> restaurant.getName().toUpperCase().contains(text.toUpperCase()))
                .collect(Collectors.toList()));
    }

    public MutableLiveData<List<Restaurant>> getList() {
        return list;
    }

    public MutableLiveData<List<Restaurant>> getListFiltered() {
        return listFiltered;
    }
}