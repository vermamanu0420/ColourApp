package com.example.colourloversapp.model;

import com.google.gson.annotations.SerializedName;

public class ColourDetailModel {

    @SerializedName("title")
    private String colourTitle;

    @SerializedName("id")
    private String colourId;

    @SerializedName("hex")
    private String colourHexValue;

    @SerializedName("imageUrl")
    private String colourImage;

    @SerializedName("url")
    private String colourDetailUrl;

    public String getColourDetailUrl() {
        return colourDetailUrl;
    }

    public void setColourDetailUrl(String colourDetailUrl) {
        this.colourDetailUrl = colourDetailUrl;
    }

    private boolean favourite;

    public ColourDetailModel(String colourTitle, String colourId, String colourHexValue, String colourImage, String colourDetailUrl, boolean favourite) {
        this.colourTitle = colourTitle;
        this.colourId = colourId;
        this.colourHexValue = colourHexValue;
        this.colourImage = colourImage;
        this.colourDetailUrl = colourDetailUrl;
        this.favourite = favourite;
    }

    public String getColourId() {
        return colourId;
    }

    public void setColourId(String colourId) {
        this.colourId = colourId;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getColourTitle() {
        return colourTitle;
    }

    public void setColourTitle(String colourTitle) {
        this.colourTitle = colourTitle;
    }

    public String getColourHexValue() {
        return colourHexValue;
    }

    public void setColourHexValue(String colourHexValue) {
        this.colourHexValue = colourHexValue;
    }

    public String getColourImage() {
        return colourImage;
    }

    public void setColourImage(String colourImage) {
        this.colourImage = colourImage;
    }

}


