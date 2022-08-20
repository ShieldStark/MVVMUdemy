package com.example.mvvmudemy.mvvm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;

import com.example.mvvmudemy.model.CountriesService;
import com.example.mvvmudemy.model.Country;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CountriesViewModel extends AndroidViewModel {
    private final CountriesService service;
    private final MutableLiveData<List<String>> countries=new MutableLiveData<>();
    private final MutableLiveData<Boolean> countryError=new MutableLiveData<>();
    public CountriesViewModel(Application application){
        super(application);
        service=new CountriesService();
        fetchCountries();
    }
    public LiveData<List<String>> getCountries(){
        return countries;
    }
    public LiveData<Boolean> getCountriesError(){
        return countryError;
    }
    private void fetchCountries(){
        service.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {
                    @Override
                    public void onSuccess(List<Country> values) {
                        try {
                            Log.d("Countries","fetchCountries");
                            List<String> countryNames=new ArrayList<>();
                            for (int i=0;i<values.size();i++){
                                Log.d("loop","fetchCountries "+values.get(i).countryName);
                                countryNames.add(values.get(i).countryName);
                            }
                            countries.setValue(countryNames);
                            countryError.setValue(false);
                        }
                        catch (Exception e){
                            Log.d("Exception","fetchCountries"+e);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError","fetchCountries"+e);
                        countryError.setValue(true);
                    }
                });
    }
    public void onRefresh() {
        fetchCountries();
    }
}
