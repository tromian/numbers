package com.tromian.test.numberfacts.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.test.numberfacts.model.Number
import com.tromian.test.numberfacts.model.NumbersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenVM(
    private val repository: NumbersRepository
) : ViewModel() {

    private val _history = MutableLiveData<List<Number>>()
    val history = _history

    private val _number = MutableLiveData<Number?>()
    val number = _number

    init {
        val historyList = repository.getSearchHistory()
        _history.postValue(historyList)
    }

    fun searchFactAboutNumber(number: String) = viewModelScope.launch(Dispatchers.IO) {

        val remoteData = repository.getFactAboutNumber(number)
        if (remoteData != null){
            _number.postValue(remoteData)
            repository.saveNumberToDB(remoteData)
            val historyList = repository.getSearchHistory()
            _history.postValue(historyList)
        }

    }

    fun searchFactAboutRandomNumber() = viewModelScope.launch(Dispatchers.IO) {

        val remoteData = repository.getFactAboutRandomNumber()
        if (remoteData != null){
            _number.postValue(remoteData)
            repository.saveNumberToDB(remoteData)
            val historyList = repository.getSearchHistory()
            _history.postValue(historyList)
        }

    }
}