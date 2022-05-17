package com.technicalassessmenttask.di

import android.content.Context
import androidx.room.Room
import com.technicalassessmenttask.database.post_list.PostsDao
import com.technicalassessmenttask.database.PostsDatabase
import com.technicalassessmenttask.database.comment_list.CommentsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun providePostDb(@ApplicationContext context: Context): PostsDatabase {
        return Room.databaseBuilder(
            context, PostsDatabase::class.java,
            PostsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostsDAO(postsDatabase: PostsDatabase): PostsDao {
        return postsDatabase.postsDao()
    }

    @Singleton
    @Provides
    fun provideCommentsDAO(postsDatabase: PostsDatabase): CommentsDao {
        return postsDatabase.commentsDao()
    }
}