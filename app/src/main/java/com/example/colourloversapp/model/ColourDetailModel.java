package com.example.colourloversapp.model;

import com.google.gson.annotations.SerializedName;

public class ColourDetailModel {

    @SerializedName("title")
    private String colourTitle;

    @SerializedName("hex")
    private String colourHexValue;

    @SerializedName("imageUrl")
    private String colourImage;

    public ColourDetailModel(String colourTitle, String colourHexValue, String colourImage) {
        this.colourTitle = colourTitle;
        this.colourHexValue = colourHexValue;
        this.colourImage = colourImage;
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


