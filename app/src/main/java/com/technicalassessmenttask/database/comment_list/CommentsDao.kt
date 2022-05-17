package com.technicalassessmenttask.database.comment_list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(commentsListCacheEntity: CommentsListCacheEntity): Long

    @Query("SELECT * FROM commentsDao")
    suspend fun get(): List<CommentsListCacheEntity>

    @Query("SELECT * FROM commentsDao WHERE postId=:post_ID")
    suspend fun getCommentsWithPostID(post_ID: Int?): List<CommentsListCacheEntity>
}