package com.thiaagodev.pokedex.service.model

sealed class ResultAPI<out R> {
    data class Success<out T>(val data: T?): ResultAPI<T?>()
    data class Error(val exception: java.lang.Exception): ResultAPI<Nothing>()
}