package com.my.ekyc.data.repository

import com.my.ekyc.data.model.ImageEntity
import com.my.ekyc.data.model.MobileEntity
import io.reactivex.Single

interface Remote {

    fun getMobiles(): Single<List<MobileEntity>>

    fun getImagesByMobileId(id: String): Single<List<ImageEntity>>
}