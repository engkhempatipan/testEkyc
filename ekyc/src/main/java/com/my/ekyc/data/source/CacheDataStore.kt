package com.my.ekyc.data.source

import com.my.ekyc.data.model.ImageEntity
import com.my.ekyc.data.model.MobileEntity
import com.my.ekyc.data.repository.Cache
import com.my.ekyc.data.repository.DataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [DataStore] interface to provide a means of communicating
 * with the local data source
 */
open class CacheDataStore @Inject constructor(private val cache: Cache) :
    DataStore {

    override fun getMobiles(): Single<List<MobileEntity>> {
        return cache.getMobiles()
    }

    override fun getImagesByMobileId(id: String): Single<List<ImageEntity>> {
        throw UnsupportedOperationException()
    }

    override fun getFavoriteList(): Single<List<MobileEntity>> {
        return cache.getMobiles()
    }

    override fun saveMobile(mobile: MobileEntity): Completable {
        return cache.saveMobile(mobile)
    }

    override fun deleteMobile(id: String): Completable {
        return cache.deleteMobile(id)
    }

    override fun setSortBy(key: String): Completable {
        return cache.setSortBy(key)
    }

    override fun getSortBy(): Single<String> {
        return cache.getSortBy()
    }

}