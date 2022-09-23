package com.galleria.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galleria.R;
import com.galleria.data.Museum;
import com.galleria.databinding.FragmentHomeBinding;
import com.galleria.ui.adapter.MuseumsAdapter;
import com.galleria.ui.viewmodel.MuseumViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private MuseumViewModel museumViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        museumViewModel = new ViewModelProvider(this).get(MuseumViewModel.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_recommended));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.list;

        museumViewModel.getListRecommended().observe(getViewLifecycleOwner(), this::showMuseums);

        return root;
    }

    public void showMuseums(List<Museum> museums) {
        MuseumsAdapter adapter = new MuseumsAdapter(museums);
        /*
        adapter.setOnItemClickListener((view, position) -> {
            Restaurant restaurant = restaurants.get(position);
            Intent intent = new Intent(getActivity(), RecommendedActivity.class);
            intent.putExtra("restaurant", restaurant);

            startActivity(intent);
        });
        */

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