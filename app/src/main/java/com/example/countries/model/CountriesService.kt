package com.example.countries.model

import com.example.countries.di.DaggerApiComponet
import io.reactivex.Single
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponet.create().inject(this)
    }

    //Single only returns one observable value and omits
    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}