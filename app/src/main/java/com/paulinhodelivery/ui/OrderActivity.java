package com.paulinhodelivery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.paulinhodelivery.R;
import com.paulinhodelivery.data.Food;
import com.paulinhodelivery.data.Restaurant;
import com.paulinhodelivery.databinding.ActivityOrderBinding;
import com.paulinhodelivery.databinding.ActivityRestaurantBinding;
import com.paulinhodelivery.ui.adapter.FoodAdapter;
import com.paulinhodelivery.ui.viewmodel.FoodViewModel;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding binding;
    private FoodViewModel foodViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getSupportActionBar().setTitle(getString(R.string.order, "251234"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Food food = (Food) getIntent().getSerializableExtra("food");
        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");

        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.name.setText(restaurant.getName());
        binding.detail.setText(food.getName());
        binding.value.setText(getString(R.string.value_total, food.getValue()));
        binding.btOrder.setOnClickListener(v -> {
            Intent intent = new Intent(OrderActivity.this, FinishedOrderActivity.class);
            intent.putExtra("restaurant", restaurant);
            intent.putExtra("food", food);

            startActivity(intent);
            finish();
        });

    }
}