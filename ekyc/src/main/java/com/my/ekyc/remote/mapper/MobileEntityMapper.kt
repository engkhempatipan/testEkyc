package com.my.ekyc.remote.mapper

import com.my.ekyc.data.model.MobileEntity
import com.my.ekyc.remote.model.MobileModel
import javax.inject.Inject

open class MobileEntityMapper @Inject constructor() : EntityMapper<MobileModel?, MobileEntity> {

    override fun mapFromRemote(type: MobileModel?): MobileEntity {
        return MobileEntity(
            type?.id ?: "",
            type?.thumbImageURL ?: "",
            type?.description ?: "",
            type?.name ?: "",
            type?.brand ?: "",
            type?.price ?: "",
            type?.rating ?: ""
        )
    }

}