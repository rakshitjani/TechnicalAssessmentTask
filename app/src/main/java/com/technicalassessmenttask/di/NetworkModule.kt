package com.technicalassessmenttask.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.technicalassessmenttask.network.comment_list.CommentListAPI
import com.technicalassessmenttask.network.post_list.PostListAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun getGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun getRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun getPostList(retrofit: Retrofit.Builder): PostListAPI {
        return retrofit
            .build()
            .create(PostListAPI::class.java)
    }

    @Singleton
    @Provides
    fun getCommentsList(retrofit: Retrofit.Builder): CommentListAPI {
        return retrofit
            .build()
            .create(CommentListAPI::class.java)
    }
}