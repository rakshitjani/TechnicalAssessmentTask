package com.technicalassessmenttask.database.post_list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postListCacheEntity: PostListCacheEntity): Long

    @Query("SELECT * FROM postsDao")
    suspend fun get(): List<PostListCacheEntity>
}