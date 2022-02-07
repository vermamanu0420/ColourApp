package com.example.colourloversapp.di;

import com.example.colourloversapp.apiservice.ColourSearchApi;
import com.example.colourloversapp.apiservice.ColourSearchService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    public static final String BASE_URL ="https://www.colourlovers.com/";

    @Provides
    public ColourSearchApi provideColourSearchApi(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ColourSearchApi.class);

    }

    @Provides
    public ColourSearchService provideColourSearchService(){
        return ColourSearchService.getInstance();
    }
}
