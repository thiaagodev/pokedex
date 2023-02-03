package com.thiaagodev.pokedex.service.repository

import com.thiaagodev.pokedex.service.model.ResultAPI
import retrofit2.Response
import java.lang.Exception

open class BaseRepository {

    fun <T> execute(response: Response<T>): ResultAPI<T?> {
        if (response.isSuccessful) {
            return ResultAPI.Success(response.body())
        }

        return ResultAPI.Error(Exception("Erro ao executar a chamada."))
    }
}