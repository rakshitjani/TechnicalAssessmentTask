package com.technicalassessmenttask.util

sealed class ResponseState<out R> {

    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val exception: Exception) : ResponseState<Nothing>()
    object Loading : ResponseState<Nothing>()
}