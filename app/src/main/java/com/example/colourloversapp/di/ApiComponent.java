package com.example.colourloversapp.di;

import com.example.colourloversapp.apiservice.ColourSearchService;
import com.example.colourloversapp.viewmodel.ColourListViewModel;

import dagger.Component;

@Component(modules={ApiModule.class})
public interface ApiComponent {

    void inject(ColourSearchService service);

    void inject(ColourListViewModel viewModel);

}
