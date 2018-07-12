package com.androidhuman.example.simplegithub.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.data.local.SearchHistoryDao
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(val searchHistoryDao: SearchHistoryDao)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(searchHistoryDao) as T
    }
}
