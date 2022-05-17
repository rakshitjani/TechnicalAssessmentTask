package com.technicalassessmenttask.repository.post_list

import android.util.Log
import com.technicalassessmenttask.database.post_list.CacheMapper
import com.technicalassessmenttask.database.post_list.PostsDao
import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.network.post_list.PostListAPI
import com.technicalassessmenttask.network.post_list.PostListDataMapper
import com.technicalassessmenttask.util.ResponseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepository
constructor(
    private val postsDao: PostsDao,
    private val postListAPI: PostListAPI,
    private val cacheMapper: CacheMapper,
    private val postListDataMapper: PostListDataMapper
    ){
    suspend fun getPostList(): Flow<ResponseState<List<PostData>>> = flow {
        emit(ResponseState.Loading)
        delay(1000)
        try {
            val networkBlogs = postListAPI.get()
            val posts = postListDataMapper.mapFromEntityList(networkBlogs)
            for ((index,post) in posts.withIndex()) {
                postsDao.insert(cacheMapper.mapToEntity(post))
            }
            val cachedBlogs = postsDao.get()
            Log.e("cacheMapper","Index: ${cachedBlogs.size}");
//            cacheMapper.mapFromEntityList(cachedBlogs)
            emit(ResponseState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }

    suspend fun getPostListFromDB(): Flow<ResponseState<List<PostData>>> = flow {
        emit(ResponseState.Loading)
        delay(1000)
        try {
            val cachedBlogs = postsDao.get()
            emit(ResponseState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }
}