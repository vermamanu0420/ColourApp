package com.example.colourloversapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colourloversapp.R;
import com.example.colourloversapp.model.ColourDetailModel;
import com.example.colourloversapp.utils.Util;
import com.example.colourloversapp.viewmodel.ColourListViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ColourRecyclerViewAdapter extends RecyclerView.Adapter<ColourRecyclerViewAdapter.ColourViewHolder>{

    private List<ColourDetailModel> colourList;
    private final OnItemClickListener listener;
    private Context context;
    private SharedPreferences sharedpreferences;
    ArrayList<String> favorites = new ArrayList<>();


    public ColourRecyclerViewAdapter(Context context, List<ColourDetailModel> colourList, OnItemClickListener listener) {
        this.colourList = colourList;
        this.listener = listener;
        this.context = context;
        sharedpreferences = context.getSharedPreferences("Favourite_colour", Context.MODE_PRIVATE);
        favorites = getFavorites();
    }

    private ArrayList<String> getFavorites(){
        Gson gson = new Gson();
        String json = sharedpreferences.getString("colour_list", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> favourites = gson.fromJson(json, type);
        if (favourites == null)
            return new ArrayList<String>();
        return favourites;
    }

    public void updateColours(List<ColourDetailModel> newColours) {
        colourList.clear();
        colourList.addAll(newColours);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ColourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.colour_item, parent, false);
        return new ColourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColourViewHolder holder, int position) {
        holder.bind(colourList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return colourList.size();
    }

    public class ColourViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.imageView)
            ImageView imageView;

            @BindView(R.id.imageHeart)
            ImageView imageHeart;

            @BindView(R.id.imageHeartFilled)
            ImageView imageHeartFilled;

            @BindView(R.id.colourTitleValue)
            TextView colourTitleValue;

            @BindView(R.id.colourHexValue)
            TextView colourHexValue;

            public ColourViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void bind(ColourDetailModel colourDetailModel,final OnItemClickListener listener) {
                colourTitleValue.setText(colourDetailModel.getColourTitle());
                colourHexValue.setText(colourDetailModel.getColourHexValue());
                colourDetailModel.setFavourite(favorites.contains(colourDetailModel.getColourId()));
                Util.loadImage(imageView, colourDetailModel.getColourImage(),Util.getProgressDrawable(imageView.getContext()));
                itemView.setOnClickListener(v -> listener.onItemClick(colourDetailModel));
                imageHeartFilled.setVisibility(colourDetailModel.isFavourite() ? View.VISIBLE : View.GONE);
                imageHeart.setVisibility(colourDetailModel.isFavourite() ? View.GONE : View.VISIBLE);

                imageHeart.setOnClickListener(view -> {
                    colourDetailModel.setFavourite(true);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    if (favorites != null && !favorites.contains(colourDetailModel.getColourId())){
                        favorites.add(colourDetailModel.getColourId());
                        saveFavorite(favorites);
                    }
                    editor.apply();
                    notifyDataSetChanged();
                });

                imageHeartFilled.setOnClickListener(view -> {
                    colourDetailModel.setFavourite(false);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    if (favorites.contains(colourDetailModel.getColourId())){
                        favorites.remove(colourDetailModel.getColourId());
                        saveFavorite(favorites);
                    }
                    editor.apply();
                    notifyDataSetChanged();
                });
            }

            private void saveFavorite(List<String> fav){
                Gson gson = new Gson();
                String json = gson.toJson(fav);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("colour_list", json );
                editor.apply();
            }


    }
}
