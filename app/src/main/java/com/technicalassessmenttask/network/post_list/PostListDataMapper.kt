package com.technicalassessmenttask.network.post_list

import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.util.EntityMapper
import javax.inject.Inject

class PostListDataMapper
@Inject
constructor() : EntityMapper<PostDataResponse, PostData> {
    override fun mapFromEntity(entity: PostDataResponse): PostData {
        return PostData(
            userId = entity.userId,
            id = entity.id,
            title = entity.title,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: PostData): PostDataResponse {
        return PostDataResponse(
            userId = domainModel.userId,
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities: List<PostDataResponse>): List<PostData> {
        return entities.map { mapFromEntity(it) }
    }
}