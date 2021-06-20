package com.paulinhodelivery.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    public String mockRestaurant = "[" +
            "{id: 1, logo: 'tacobell.png', name: 'Taco Bell', type: 'Lanche', distance: '3', stats: '4.5'}," +
            "{id: 2, logo: 'mcdonalds.png', name: 'Mc Donalds', type: 'Lanche', distance: '4.2', stats: '4.6'}," +
            "{id: 3, logo: 'Burguer King.png', name: 'Burguer King', type: 'Lanche', distance: '2', stats: '4.1'}," +
            "{id: 4, logo: 'thefifties.png', name: 'The Fifties', type: 'Lanche', distance: '7.8', stats: '4.9'}" +
            "{id: 5, logo: 'galetos.png', name: 'Galetos', type: 'Brasileira', distance: '3', stats: '4.5'}" +
            "{id: 6, logo: 'liglig.png', name: 'Lig Lig', type: 'Chinesa', distance: '3', stats: '4.5'}" +
            "{id: 7, logo: 'chineinbox.png', name: 'Chine In Box', type: 'Chinesa', distance: '3', stats: '4.5'}" +
            "{id: 8, logo: 'pizzahut.png', name: 'Pizza Hut', type: 'Pizza', distance: '3', stats: '4.5'}" +
            "{id: 9, logo: 'outback.png', name: 'Outback', type: 'Lanche', distance: '3', stats: '4.5'}" +
            "]";

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}