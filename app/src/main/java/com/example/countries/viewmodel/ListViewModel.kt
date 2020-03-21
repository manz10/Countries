package com.example.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.di.DaggerApiComponet
import com.example.countries.model.CountriesService
import com.example.countries.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel(){

    @Inject
    lateinit var countriesService : CountriesService

    init {
        DaggerApiComponet.create().inject(this)
    }

    private val disposable = CompositeDisposable()  //used when viewModel is closed we need to close RxJava connection

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()   //error message
    val loading = MutableLiveData<Boolean>()        //check if loading

    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries(){
        loading.value = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())        //getCountries process will be done in seperate thread
                .observeOn(AndroidSchedulers.mainThread())      //yet result will be handled in main thread/user thread
                .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(value: List<Country>?) {
                        countries.value = value
                        countryLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        countryLoadError.value = true
                        loading.value = false
                    }

                })
        )


    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}