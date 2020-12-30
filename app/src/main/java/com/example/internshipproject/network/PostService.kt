package com.example.internshipproject.network

import com.example.internshipproject.model.Post
import com.example.internshipproject.model.User
import retrofit2.Response
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    suspend fun getPosts() : Response<List<Post>>

    @GET("users")
    suspend fun getUsers() : Response<List<User>>
}