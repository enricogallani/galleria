package com.paulinhodelivery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.paulinhodelivery.R;
import com.paulinhodelivery.data.Food;
import com.paulinhodelivery.data.Restaurant;
import com.paulinhodelivery.databinding.ActivityHomeBinding;
import com.paulinhodelivery.databinding.ActivityRestaurantBinding;
import com.paulinhodelivery.ui.adapter.FoodAdapter;
import com.paulinhodelivery.ui.adapter.RestaurantAdapter;
import com.paulinhodelivery.ui.viewmodel.FoodViewModel;
import com.paulinhodelivery.ui.viewmodel.HomeViewModel;

import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    private ActivityRestaurantBinding binding;
    private FoodViewModel foodViewModel;
    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        getSupportActionBar().setTitle(getString(R.string.selection));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");

        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        foodViewModel.filterRestaurant(restaurant.getId());

        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.name.setText(restaurant.getName());
        binding.detail.setText(getString(R.string.restaurant_activity_detail, restaurant.getType(), restaurant.getDistance()));
        binding.stats.setText(getString(R.string.avaliation, restaurant.getStats()));

        recyclerView = binding.list;

        foodViewModel.getList().observe(this, this::showFoods);
    }

    public void showFoods(List<Food> foods) {

        adapter = new FoodAdapter(foods);
        adapter.setOnItemClickListener((view, position) -> {
            final Food food = foods.get(position);

            Intent intent = new Intent(RestaurantActivity.this, OrderActivity.class);
            intent.putExtra("restaurant", restaurant);
            intent.putExtra("food", food);

            startActivity(intent);
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
}