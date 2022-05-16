package com.technicalassessmenttask.network.post_list

import retrofit2.http.GET

interface PostListAPI {
    @GET("posts")
    suspend fun get(): List<PostDataResponse>
}

