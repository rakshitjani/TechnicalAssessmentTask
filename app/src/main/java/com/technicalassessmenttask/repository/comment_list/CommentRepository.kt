package com.technicalassessmenttask.repository.comment_list

import android.util.Log
import com.technicalassessmenttask.database.comment_list.CommentsCacheMapper
import com.technicalassessmenttask.database.comment_list.CommentsDao
import com.technicalassessmenttask.database.post_list.CacheMapper
import com.technicalassessmenttask.database.post_list.PostsDao
import com.technicalassessmenttask.model.comment_list.CommentsData
import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.network.comment_list.CommentListAPI
import com.technicalassessmenttask.network.comment_list.CommentListDataMapper
import com.technicalassessmenttask.network.post_list.PostListAPI
import com.technicalassessmenttask.network.post_list.PostListDataMapper
import com.technicalassessmenttask.util.ResponseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CommentRepository
constructor(
    private val commentsDao: CommentsDao,
    private val commentListAPI: CommentListAPI,
    private val commentsCacheMapper: CommentsCacheMapper,
    private val commentListDataMapper: CommentListDataMapper
) {
    suspend fun getCommentList(postID: String): Flow<ResponseState<List<CommentsData>>> = flow {
        emit(ResponseState.Loading)
        try {
            val networkPosts = commentListAPI.get(postID)
            val posts = commentListDataMapper.mapFromEntityList(networkPosts)
            for ((index, post) in posts.withIndex()) {
                commentsDao.insert(commentsCacheMapper.mapToEntity(post))
            }
            val cachedBlogs = commentsDao.get()
            commentsCacheMapper.mapFromEntityList(cachedBlogs)
            emit(ResponseState.Success(posts))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }

    suspend fun getCommentListFromDB(postID: Int?): Flow<ResponseState<List<CommentsData>>> = flow {
        emit(ResponseState.Loading)
        try {
            val cachedBlogs = commentsDao.getCommentsWithPostID(postID)
            emit(ResponseState.Success(commentsCacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }
}