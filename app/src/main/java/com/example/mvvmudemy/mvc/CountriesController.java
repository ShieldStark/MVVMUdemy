package com.example.mvvmudemy.mvc;

import com.example.mvvmudemy.model.CountriesService;
import com.example.mvvmudemy.model.Country;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class CountriesController {
    private MVCActivity view;
    private CountriesService service;
    public CountriesController(MVCActivity view){
        this.view=view;
        service=new CountriesService();
        fetchCountries();
    }
    private void fetchCountries(){
        service.getCountries()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {
                    @Override
                    public void onSuccess(@NonNull List<Country> countries) {
                        List<String> countryNames=new ArrayList<>();
                        for (Country country:countries){
                            countryNames.add(country.countryName);
                        }
                        view.setValues(countryNames);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
