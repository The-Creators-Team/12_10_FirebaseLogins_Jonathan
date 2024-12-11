package com.example.firebasesamplejonathan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Click to CRASH THE FREAKIN APPLICATION"
    }
    val text: LiveData<String> = _text
}