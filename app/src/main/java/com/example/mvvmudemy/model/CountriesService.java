package com.example.mvvmudemy.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {
    public static final String BASE_URL="https://restcountries.com/v3.1/";
    private CountryApi api;
    public CountriesService(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api=retrofit.create(CountryApi.class);
    }
    public Single<List<Country>> getCountries(){
        return api.getCountries();
    }
}
