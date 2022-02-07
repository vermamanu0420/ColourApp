package com.example.colourloversapp.apiservice;

import com.example.colourloversapp.di.DaggerApiComponent;
import com.example.colourloversapp.model.ColourDetailModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class ColourSearchService {

    private static ColourSearchService instance;

    @Inject
    public ColourSearchApi api;

    public ColourSearchService() {

        DaggerApiComponent.create().inject(this);
    }

    public static ColourSearchService getInstance() {
        if (instance == null) {
            instance = new ColourSearchService();
        }
        return instance;
    }

    public Single<List<ColourDetailModel>> getColours(String searchString, String format, int numResults) {
        return api.getColours(searchString, format, numResults);
    }
}
