package com.galleria.ui.find;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.galleria.databinding.FragmentFindBinding;
import com.galleria.ui.adapter.MuseumsAdapter;
import com.galleria.ui.viewmodel.MuseumViewModel;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {

    private MuseumViewModel museumViewModel;
    private FragmentFindBinding binding;
    private RecyclerView recyclerView;
    private MuseumsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        museumViewModel = new ViewModelProvider(this).get(MuseumViewModel.class);

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
                museumViewModel.filterMuseum(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        showMuseums(new ArrayList<>());
        museumViewModel.getListFiltered().observe(getViewLifecycleOwner(), this::showMuseums);

        return root;
    }

    public void showMuseums(List<Museum> museums) {
        adapter = new MuseumsAdapter(museums);
        /*
        adapter.setOnItemClickListener((view, position) -> {
            Museum museum = museums.get(position);
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