package com.paulinhodelivery.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.paulinhodelivery.R;
import com.paulinhodelivery.data.Restaurant;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Restaurant> restaurants;
    private OnItemClickListener listener;

    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_restaurant, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Restaurant model = restaurants.get(position);

        /*
        if (model.getImageObject() != null) {
            Glide.with(holder.ivImage.getContext())
                    .load(model.getImageObject().getLarge())
                    .into(holder.ivImage);
        }
        */

        //holder.ivImage.setImageResource();
        holder.tvDetail.setText(model.getStats() + " - " + model.getType() + " - " + model.getDistance());
        holder.tvName.setText(model.getName());
    }

    public void updateData(List<Restaurant> restaurants) {
        this.restaurants.addAll(restaurants);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        OnItemClickListener mListener;
        ImageView ivImage;
        TextView tvName;
        TextView tvDetail;
        ConstraintLayout click;

        ViewHolder(View v, OnItemClickListener listener) {
            super(v);
            ivImage = v.findViewById(R.id.logo);
            tvDetail = v.findViewById(R.id.detail);
            tvName = v.findViewById(R.id.name);
            click = v.findViewById(R.id.restaurant);
            mListener = listener;

            click.setOnClickListener(view -> mListener.onClick(view, getAdapterPosition()));
        }
    }
}
