package com.technicalassessmenttask.network.comment_list

import com.technicalassessmenttask.model.comment_list.CommentsData
import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.util.EntityMapper
import javax.inject.Inject

class CommentListDataMapper
@Inject
constructor() : EntityMapper<CommentDataResponse, CommentsData> {
    override fun mapFromEntity(entity: CommentDataResponse): CommentsData {
        return CommentsData(
            postId = entity.postId,
            id = entity.id,
            name = entity.name,
            email = entity.email,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: CommentsData): CommentDataResponse {
        return CommentDataResponse(
            postId = domainModel.postId,
            id = domainModel.id,
            name = domainModel.name,
            email = domainModel.email,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities: List<CommentDataResponse>): List<CommentsData> {
        return entities.map { mapFromEntity(it) }
    }
}