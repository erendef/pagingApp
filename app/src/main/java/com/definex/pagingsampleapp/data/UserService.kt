package com.definex.pagingsampleapp.data

import com.definex.pagingsampleapp.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("https://randomuser.me/api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): UserResponse
}