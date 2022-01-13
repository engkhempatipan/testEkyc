package com.my.ekyc.data.repository

import com.my.ekyc.data.model.MobileEntity
import io.reactivex.Completable
import io.reactivex.Single

interface Cache {

    fun getMobiles(): Single<List<MobileEntity>>

    fun saveMobile(mobileEntity: MobileEntity): Completable

    fun deleteMobile(id: String): Completable

    fun setSortBy(sortBy: String): Completable

    fun getSortBy(): Single<String>

}