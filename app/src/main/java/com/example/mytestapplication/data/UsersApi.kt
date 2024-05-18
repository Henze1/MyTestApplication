package com.example.mytestapplication.data

import retrofit2.http.GET

interface UsersApi {
    @GET("/randomuser")
    suspend fun getUser(): User

    companion object{
        //home
//        var BASE_URL = "http://10.0.0.43:8080"
        //picsart
        var BASE_URL = "http://192.168.33.183:8080"
    }
}