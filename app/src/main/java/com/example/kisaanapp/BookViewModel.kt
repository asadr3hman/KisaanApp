package com.example.kisaanapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kisaanapp.network.BookApi
import kotlinx.coroutines.launch
import java.lang.Exception


class BookViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    init {
        getApiBook()
    }

    private fun getApiBook() {
        viewModelScope.launch {
            try {
                val listResult = BookApi.retrofitService.getBooks()
                _status.value = "Data is ${listResult[0].author}"
            }catch (e: Exception){
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}