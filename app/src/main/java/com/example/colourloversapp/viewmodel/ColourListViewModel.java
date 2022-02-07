package com.example.colourloversapp.viewmodel;

import com.example.colourloversapp.apiservice.ColourSearchService;
import com.example.colourloversapp.di.DaggerApiComponent;
import com.example.colourloversapp.model.ColourDetailModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ColourListViewModel extends ViewModel {
    public MutableLiveData<List<ColourDetailModel>> mutableColourList = new MutableLiveData<List<ColourDetailModel>>();
    public MutableLiveData<Boolean> ColourLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();


    @Inject
    public ColourSearchService colourSearchService;

    private CompositeDisposable disposable = new CompositeDisposable();

    public ColourListViewModel() {
        super();
        DaggerApiComponent.create().inject(this);
    }

    public void fetchColour(String searchTerm, String type, int numResults) {
        loading.setValue(true);
        disposable.add(
                colourSearchService.getColours(searchTerm, type, numResults)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<ColourDetailModel>>() {
                            @Override
                            public void onSuccess(@NonNull List<ColourDetailModel> colourList ) {
                                ColourLoadError.setValue(false);
                                loading.setValue(false);
                                mutableColourList.setValue(colourList);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                ColourLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }
}
