package com.example.mytestapplication.data

import com.google.gson.annotations.SerializedName

data class User(
    val name: String,
    val email: String,
    val dob: String,
    val picture: String
)
//
//data class PersonResponse(
//    @SerializedName("results") val results: List<Person>,
//    @SerializedName("info") val info: Info
//)
//
//data class Person(
//    @SerializedName("name") val name: Name,
//    @SerializedName("email") val email: String,
//    @SerializedName("dob") val dob: Dob,
//    @SerializedName("picture") val picture: Picture
//)
//
//data class Name(
//    @SerializedName("title") val title: String,
//    @SerializedName("first") val first: String,
//    @SerializedName("last") val last: String
//)
//
//data class Dob(
//    @SerializedName("date") val date: String,
//    @SerializedName("age") val age: Int
//)
//
//data class Picture(
//    @SerializedName("large") val large: String,
//    @SerializedName("medium") val medium: String,
//    @SerializedName("thumbnail") val thumbnail: String
//)
//
//data class Info(
//    @SerializedName("seed") val seed: String,
//    @SerializedName("results") val results: Int,
//    @SerializedName("page") val page: Int,
//    @SerializedName("version") val version: String
//)