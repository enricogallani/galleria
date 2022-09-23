package com.galleria.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.galleria.R;
import com.galleria.data.Museum;
import com.galleria.databinding.ActivityRecommendedBinding;
import com.galleria.ui.adapter.MuseumsAdapter;
import com.galleria.ui.viewmodel.MuseumViewModel;

import java.util.List;

public class MuseumActivity extends AppCompatActivity {

    private ActivityRecommendedBinding binding;
    private MuseumViewModel museumViewModel;
    private RecyclerView recyclerView;
    private MuseumsAdapter adapter;
    private Museum museum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);

        getSupportActionBar().setTitle(getString(R.string.selection));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        museum = (Museum) getIntent().getSerializableExtra("museum");

        museumViewModel = new ViewModelProvider(this).get(MuseumViewModel.class);
        //recommendedViewModel.filterRestaurant(restaurant.getId());

        binding = ActivityRecommendedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.list;

        //museumViewModel.getList().observe(this, this::showMuseums);
    }

    public void showMuseums(List<Museum> museums) {

        adapter = new MuseumsAdapter(museums);
        /*
        adapter.setOnItemClickListener((view, position) -> {
            final Museum museum = museums.get(position);

            Intent intent = new Intent(RecommendedActivity.this, OrderActivity.class);
            intent.putExtra("restaurant", restaurant);
            intent.putExtra("food", food);

            startActivity(intent);
        });
        */

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
}