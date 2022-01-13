package com.my.ekyc.remote.mapper

import com.my.ekyc.data.model.ImageEntity
import com.my.ekyc.remote.model.ImageModel
import javax.inject.Inject

open class ImageEntityMapper @Inject constructor() : EntityMapper<ImageModel?, ImageEntity> {

    override fun mapFromRemote(type: ImageModel?): ImageEntity {
        return ImageEntity(
            type?.id ?: "",
            type?.url ?: "",
            type?.mobile_id ?: ""
        )
    }

}