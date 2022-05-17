package com.technicalassessmenttask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technicalassessmenttask.database.comment_list.CommentsDao
import com.technicalassessmenttask.database.comment_list.CommentsListCacheEntity
import com.technicalassessmenttask.database.post_list.PostListCacheEntity
import com.technicalassessmenttask.database.post_list.PostsDao

@Database(entities = [PostListCacheEntity::class, CommentsListCacheEntity::class], version = 1)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun commentsDao(): CommentsDao

    companion object {
        const val DATABASE_NAME: String = "posts_db"
    }
}