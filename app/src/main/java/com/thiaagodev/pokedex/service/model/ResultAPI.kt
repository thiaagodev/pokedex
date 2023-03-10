package com.thiaagodev.pokedex.service.model

sealed class ResultAPI<out R> {
    data class Success<T>(var data: T? = null): ResultAPI<T?>()
    data class Error(val exception: java.lang.Exception): ResultAPI<Nothing>()
}