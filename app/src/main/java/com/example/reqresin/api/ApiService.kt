package com.example.reqresin.api

import com.example.reqresin.model.*
import com.example.reqresin.request.LoginRequest
import com.example.reqresin.request.RequestCreateUser
import com.example.reqresin.request.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("login")
    fun login(@Body data: LoginRequest) : Call<LoginResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("users?page=2")
    fun getUsers() : Call<UserModel>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("users")
    fun createData(@Body data: UserRequest) : Call<RequestCreateUser>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("users/{id}")
    fun detailUser(@Path("id") id : Int) : Call<DetailUser>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id : Int) : Call<DetailUser>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @PATCH("users/{id}")
    fun updateUser(@Body data: UserRequest,@Path("id") id : Int) : Call<RequestCreateUser>
}