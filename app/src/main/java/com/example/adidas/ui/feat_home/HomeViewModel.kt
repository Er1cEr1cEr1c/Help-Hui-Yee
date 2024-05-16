package com.example.adidas.ui.feat_home

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    fun sayHi() {
        Log.d("Ohoy", "Home VM initiated")
    }
}