package com.technicalassessmenttask.network.post_list

import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.util.EntityMapper
import javax.inject.Inject

class PostListDataMapper
@Inject
constructor()  : EntityMapper<PostDataResponse, PostData> {
    override fun mapFromEntity(entity: PostDataResponse): PostData {
        return PostData(
//            id = entity.id,
//            title = entity.title,
//            body = entity.body,
//            image = entity.image,
//            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: PostData): PostDataResponse {
        return PostDataResponse(
//            id = domainModel.id,
//            title = domainModel.title,
//            body = domainModel.body,
//            image = domainModel.image,
//            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<PostDataResponse>): List<PostData> {
        return entities.map { mapFromEntity(it) }
    }
}