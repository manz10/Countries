package com.example.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.Country

class ListViewModel: ViewModel(){

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()   //error message
    val loading = MutableLiveData<Boolean>()        //check if loading

    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries(){
        val mockData: List<Country> = listOf(Country(countryName = "CountryA"),
            Country(countryName = "CountryB"),
            Country(countryName = "CountryC"),
            Country(countryName = "CountryD"),
            Country(countryName = "CountryE"),
            Country(countryName = "CountryF"),
            Country(countryName = "CountryG"),
            Country(countryName = "CountryH"),
            Country(countryName = "CountryI")
        )

        countryLoadError.value = false   //no error in loading data
        loading.value = false

        countries.value = mockData

    }


}