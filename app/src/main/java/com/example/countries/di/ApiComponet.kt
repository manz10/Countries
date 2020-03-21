package com.example.countries.di

import com.example.countries.model.CountriesService
import com.example.countries.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponet {

    fun inject(service: CountriesService)   // tells inject ApiModule to service object

    fun inject(viewModel: ListViewModel) // tells inject APImodule to viewModel object
}