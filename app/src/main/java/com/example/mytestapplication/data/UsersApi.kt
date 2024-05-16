package com.example.mytestapplication.data

import retrofit2.http.GET

interface UsersApi {
    @GET("/api/?inc=name,dob,email,picture")
    suspend fun getUser(): User

    companion object{
        var BASE_URL = "https://randomuser.me"
    }
}