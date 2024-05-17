package com.example.mytestapplication.data

import retrofit2.http.GET

interface UsersApi {
    @GET("/randomuser")
    suspend fun getUser(): User

    companion object{
        var BASE_URL = "http://10.0.0.43:8080"
    }
}