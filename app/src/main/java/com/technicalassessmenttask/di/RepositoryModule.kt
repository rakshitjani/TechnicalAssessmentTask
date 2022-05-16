package com.technicalassessmenttask.di

import com.technicalassessmenttask.database.CacheMapper
import com.technicalassessmenttask.database.post_list.PostsDao
import com.technicalassessmenttask.network.post_list.PostListAPI
import com.technicalassessmenttask.network.post_list.PostListDataMapper
import com.technicalassessmenttask.repository.post_list.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        postsDao: PostsDao,
        postListAPI: PostListAPI,
        cacheMapper: CacheMapper,
        postListDataMapper: PostListDataMapper
    ): PostRepository {
        return PostRepository(postsDao, postListAPI, cacheMapper, postListDataMapper)
    }
}