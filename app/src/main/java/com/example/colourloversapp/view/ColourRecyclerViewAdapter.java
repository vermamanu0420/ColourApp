package com.example.colourloversapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.colourloversapp.R;
import com.example.colourloversapp.model.ColourDetailModel;
import com.example.colourloversapp.utils.Util;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ColourRecyclerViewAdapter extends RecyclerView.Adapter<ColourRecyclerViewAdapter.ColourViewHolder>{

    private List<ColourDetailModel> colourList;


    public ColourRecyclerViewAdapter(List<ColourDetailModel> colourList) {
        this.colourList = colourList;
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
        holder.bind(colourList.get(position));

    }

    @Override
    public int getItemCount() {
        return colourList.size();
    }

    public class ColourViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.imageView)
            ImageView imageView;

            @BindView(R.id.colourTitleValue)
            TextView colourTitleValue;

            @BindView(R.id.colourHexValue)
            TextView colourHexValue;

            public ColourViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void bind(ColourDetailModel colourDetailModel) {
                colourTitleValue.setText(colourDetailModel.getColourTitle());
                colourHexValue.setText(colourDetailModel.getColourHexValue());
                Util.loadImage(imageView, colourDetailModel.getColourImage(),Util.getProgressDrawable(imageView.getContext()));


            }

    }
}
