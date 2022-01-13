package com.my.ekyc.data.mapper

import com.my.ekyc.data.model.MobileEntity
import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.data.mapper.Mapper
import javax.inject.Inject


open class MobileMapper @Inject constructor() :
    Mapper<MobileEntity, Mobile> {

    override fun mapFromEntity(type: MobileEntity): Mobile {
        return Mobile(
            type.id,
            type.thumbImageURL,
            type.description,
            type.name,
            type.brand,
            type.price,
            type.rating
        )
    }

    override fun mapToEntity(type: Mobile): MobileEntity {
        return MobileEntity(
            type.id,
            type.thumbImageURL,
            type.description,
            type.name,
            type.brand,
            type.price,
            type.rating
        )
    }

}