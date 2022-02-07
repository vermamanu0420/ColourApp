package com.example.colourloversapp.apiservice;

import com.example.colourloversapp.model.ColourDetailModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ColourSearchApi {

    @GET("api/colors")
    Single<List<ColourDetailModel>> getColours (
            @Query("keywords") String searchTerm,
            @Query("format") String format,
            @Query("numResults") int numResults );
}
