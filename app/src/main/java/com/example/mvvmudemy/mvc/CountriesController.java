package com.example.mvvmudemy.mvc;

import android.util.Log;

import com.example.mvvmudemy.model.CountriesService;
import com.example.mvvmudemy.model.Country;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {
                    @Override
                    public void onSuccess(List<Country> countries) {
                        try {
                            Log.d("Countries","fetchCountries");
                            List<String> countryNames=new ArrayList<>();
                            for (int i=0;i<countries.size();i++){
                                Log.d("loop","fetchCountries "+countries.get(i).countryName);
                                countryNames.add(countries.get(i).countryName);
                            }
                            view.setValues(countryNames);
                        }
                        catch (Exception e){
                            Log.d("Exception","fetchCountries"+e);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError","fetchCountries"+e);
                        view.onError();
                    }
                });
    }
    public void onRefresh() {
        fetchCountries();
    }
}
