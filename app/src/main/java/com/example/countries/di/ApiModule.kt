package com.example.countries.di

import com.example.countries.model.CountriesApi
import com.example.countries.model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesApi() : CountriesApi{
        return Retrofit.Builder()        //creates framework for Retrofit
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())     //to convert received Json to required objects
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())      //to use observables and observes of RxJava
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    fun provideCountriesService():CountriesService{
        return CountriesService()
    }
}