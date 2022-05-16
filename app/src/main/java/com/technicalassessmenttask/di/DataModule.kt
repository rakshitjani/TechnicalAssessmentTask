package com.technicalassessmenttask.di

import android.content.Context
import androidx.room.Room
import com.technicalassessmenttask.database.post_list.PostsDao
import com.technicalassessmenttask.database.PostsDatabase
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
    fun provideBlogDb(@ApplicationContext context: Context): PostsDatabase {
        return Room.databaseBuilder(
            context, PostsDatabase::class.java,
            PostsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: PostsDatabase): PostsDao {
        return blogDatabase.postsDao()
    }
}