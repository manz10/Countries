package com.example.countries.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {

    private val BASE_URL = "https://raw.githubusercontent.com"

    private val api: CountriesApi

    init {
        api = Retrofit.Builder()        //creates framework for Retrofit
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())     //to convert received Json to required objects
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())      //to use observables and observes of RxJava
            .build()
            .create(CountriesApi::class.java)
    }

    //Single only returns one observable value and omits
    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}