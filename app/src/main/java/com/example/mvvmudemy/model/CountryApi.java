package com.example.mvvmudemy.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface CountryApi {
    @GET("all")
    Single<List<Country>> getCountries();
}
