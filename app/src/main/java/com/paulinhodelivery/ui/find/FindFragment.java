package com.paulinhodelivery.ui.find;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paulinhodelivery.R;
import com.paulinhodelivery.data.Restaurant;
import com.paulinhodelivery.databinding.FragmentFindBinding;
import com.paulinhodelivery.databinding.FragmentHomeBinding;
import com.paulinhodelivery.ui.RestaurantActivity;
import com.paulinhodelivery.ui.adapter.RestaurantAdapter;
import com.paulinhodelivery.ui.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentFindBinding binding;
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.find));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = FragmentFindBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.list;

        binding.find.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                homeViewModel.findRestaurant(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        showRestaurants(new ArrayList<>());
        homeViewModel.getListFiltered().observe(getViewLifecycleOwner(), this::showRestaurants);

        return root;
    }

    public void showRestaurants(List<Restaurant> restaurants) {
        adapter = new RestaurantAdapter(restaurants);
        adapter.setOnItemClickListener((view, position) -> {
            Restaurant restaurant = restaurants.get(position);
            Intent intent = new Intent(getActivity(), RestaurantActivity.class);
            intent.putExtra("restaurant", restaurant);

            startActivity(intent);
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}