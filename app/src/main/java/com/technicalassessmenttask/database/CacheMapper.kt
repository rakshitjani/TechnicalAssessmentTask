package com.technicalassessmenttask.database

import com.technicalassessmenttask.database.post_list.PostListCacheEntity
import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<PostListCacheEntity, PostData> {
    override fun mapFromEntity(entity: PostListCacheEntity): PostData {
        return PostData(
            userId = entity.userId,
            id = entity.id,
            title = entity.title,
            body = entity.body,
        )
    }

    override fun mapToEntity(domainModel: PostData): PostListCacheEntity {
        return PostListCacheEntity(
            userId = domainModel.userId,
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities: List<PostListCacheEntity>): List<PostData> {
        return entities.map { mapFromEntity(it) }
    }

}