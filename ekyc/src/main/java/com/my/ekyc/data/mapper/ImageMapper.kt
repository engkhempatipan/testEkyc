package com.my.ekyc.data.mapper

import com.my.ekyc.data.model.ImageEntity
import com.my.ekyc.domain.model.Image
import javax.inject.Inject


open class ImageMapper @Inject constructor() :
    Mapper<ImageEntity, Image> {
    override fun mapFromEntity(type: ImageEntity): Image {
        return Image(
            type.id ?: "",
            type.url ?: "",
            type.mobile_id ?: ""
        )
    }

    override fun mapToEntity(type: Image): ImageEntity {
        return ImageEntity(
            type.id ?: "",
            type.url ?: "",
            type.mobile_id ?: ""
        )
    }

}