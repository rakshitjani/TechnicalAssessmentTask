package com.technicalassessmenttask.database.comment_list

import com.technicalassessmenttask.database.post_list.PostListCacheEntity
import com.technicalassessmenttask.model.comment_list.CommentsData
import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.util.EntityMapper
import javax.inject.Inject

class CommentsCacheMapper
@Inject
constructor() : EntityMapper<CommentsListCacheEntity, CommentsData> {
    override fun mapFromEntity(entity: CommentsListCacheEntity): CommentsData {
        return CommentsData(
            postId = entity.postId,
            id = entity.id,
            name = entity.name,
            email = entity.email,
            body = entity.body,
        )
    }

    override fun mapToEntity(domainModel: CommentsData): CommentsListCacheEntity {
        return CommentsListCacheEntity(
            postId = domainModel.postId,
            id = domainModel.id,
            name = domainModel.name,
            email = domainModel.email,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities: List<CommentsListCacheEntity>): List<CommentsData> {
        return entities.map { mapFromEntity(it) }
    }

}