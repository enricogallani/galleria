package com.galleria.ui.adapter;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.galleria.R;
import com.galleria.data.Museum;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MuseumsAdapter extends RecyclerView.Adapter<MuseumsAdapter.ViewHolder> {

    private List<Museum> museums;
    private OnItemClickListener listener;
    private Double latitude;
    private Double longitude;

    public List<Museum> getMuseums() {
        return museums;
    }

    public void setMuseums(List<Museum> museums) {
        this.museums = museums;
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public MuseumsAdapter(List<Museum> museums) {
        this.museums = museums;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_museums, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Museum model = museums.get(position);

        setLatitude(model.getLatitude());
        setLongitude(model.getLongitude());

        final Resources resources = holder.ivImage.getResources();
        final String packages =  holder.ivImage.getContext().getPackageName();
        Log.d("LSDLSKDLSDLS", "SLDSLDLSKDSLDK LSDKSL DK " + packages + " " + model.getImage());
        int idImage = resources.getIdentifier(model.getImage(), "drawable", packages);
        Drawable drawable = resources.getDrawable(idImage, null);

        if (model.getImage() != null) {
            Glide.with(holder.ivImage.getContext())
                    .load(drawable)
                    .into(holder.ivImage);
        }

        /*
        GoogleMapOptions options = new GoogleMapOptions();
        options.liteMode(true);
        SupportMapFragment mapFrag = SupportMapFragment.newInstance(options);
        mapFrag.
        mapFrag.getMapAsync(new MapReadyCallback());
        */
        /*
        Glide.with(holder.map.getContext())
                .load("http://maps.google.com/maps/api/staticmap?center=" +
                        model.getLatitude() +
                        "," +
                        model.getLongitude() +
                        "&zoom=15&size=200x200&sensor=false" +
                        "&markers=color:red%7Clabel:C%" +
                        model.getLatitude() +
                        "," +
                        model.getLongitude())
                .centerCrop()
                .into(holder.ivImage);
                *
         */
        //holder.ivImage.setImageResoxurce(model.getImage());
        holder.tvDescription.setText(model.getDescription().substring(0, 200));
        holder.tvSubDescription.setText(model.getDescription().substring(201));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Museum> foods) {
        this.museums.addAll(foods);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return museums.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        OnItemClickListener mListener;
        ImageView ivImage;
        TextView tvDescription;
        TextView tvSubDescription;
        ConstraintLayout click;
        ImageView map;

        ViewHolder(View v, OnItemClickListener listener) {
            super(v);
            map = v.findViewById(R.id.map);
            ivImage = v.findViewById(R.id.museum_image);
            tvDescription = v.findViewById(R.id.description);
            tvSubDescription = v.findViewById(R.id.sub_description);
            click = v.findViewById(R.id.click);
            mListener = listener;

//            click.setOnClickListener(view -> mListener.onClick(view, getAdapterPosition()));
        }
    }

    private class MapReadyCallback implements OnMapReadyCallback {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(getLatitude(), getLongitude()))
                    .title("Marker"));
        }
    }
}
