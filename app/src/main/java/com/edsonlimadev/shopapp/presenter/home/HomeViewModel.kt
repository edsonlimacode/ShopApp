package com.edsonlimadev.shopapp.presenter.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _homeButtonClickEvent = MutableLiveData<Unit>()
    val homeButtonClickEvent: LiveData<Unit> get() = _homeButtonClickEvent

    fun onHomeButtonClicked() {
        _homeButtonClickEvent.value = Unit
    }

}