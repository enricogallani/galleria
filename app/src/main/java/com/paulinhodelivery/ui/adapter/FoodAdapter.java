package com.paulinhodelivery.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.paulinhodelivery.R;
import com.paulinhodelivery.data.Food;
import com.paulinhodelivery.data.Restaurant;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<Food> foods;
    private OnItemClickListener listener;

    public FoodAdapter(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_food, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Food model = foods.get(position);

        /*
        if (model.getImageObject() != null) {
            Glide.with(holder.ivImage.getContext())
                    .load(model.getImageObject().getLarge())
                    .into(holder.ivImage);
        }
        */

        //holder.ivImage.setImageResource();
        holder.tvValue.setText("R$ " + model.getValue());
        holder.tvName.setText(model.getName());
    }

    public void updateData(List<Food> foods) {
        this.foods.addAll(foods);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        OnItemClickListener mListener;
        ImageView ivImage;
        TextView tvName;
        TextView tvValue;
        ConstraintLayout click;

        ViewHolder(View v, OnItemClickListener listener) {
            super(v);
            ivImage = v.findViewById(R.id.image);
            tvValue = v.findViewById(R.id.value);
            tvName = v.findViewById(R.id.name);
            click = v.findViewById(R.id.click);
            mListener = listener;

            click.setOnClickListener(view -> mListener.onClick(view, getAdapterPosition()));
        }
    }
}
