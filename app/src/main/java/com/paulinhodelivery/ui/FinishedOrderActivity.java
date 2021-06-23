package com.paulinhodelivery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.paulinhodelivery.R;
import com.paulinhodelivery.data.Food;
import com.paulinhodelivery.data.Restaurant;
import com.paulinhodelivery.databinding.ActivityFinishedOrderBinding;
import com.paulinhodelivery.databinding.ActivityOrderBinding;
import com.paulinhodelivery.ui.viewmodel.FoodViewModel;

public class FinishedOrderActivity extends AppCompatActivity {

    private ActivityFinishedOrderBinding binding;
    private FoodViewModel foodViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_order);

        getSupportActionBar().setTitle("Pedido Finalizado - #251234");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Food food = (Food) getIntent().getSerializableExtra("food");
        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");

        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        binding = ActivityFinishedOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.name.setText(restaurant.getName());
        binding.detail.setText(food.getName());
        binding.value.setText("Valor total R$ " + food.getValue());
        binding.btTracking.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(FinishedOrderActivity.this, getString(R.string.error_map), Toast.LENGTH_LONG).show();
            }
        });
        binding.btShare.setOnClickListener(v -> {
            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Pedido " + 251234 + " restaurante " + restaurant.getName() + " item " + food.getName());
            try {
                startActivity(whatsappIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(FinishedOrderActivity.this, getString(R.string.error_wpp), Toast.LENGTH_LONG).show();
            }
        });
    }
}