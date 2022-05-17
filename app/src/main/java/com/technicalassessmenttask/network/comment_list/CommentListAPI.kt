package com.technicalassessmenttask.network.comment_list

import retrofit2.http.GET
import retrofit2.http.Path

interface CommentListAPI {
    @GET("posts/{postID}/comments")
    suspend fun get(@Path("postID") postID: String): List<CommentDataResponse>

}

