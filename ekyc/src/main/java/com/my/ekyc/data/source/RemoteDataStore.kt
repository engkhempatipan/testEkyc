package com.my.ekyc.data.source

import com.my.ekyc.data.model.ImageEntity
import com.my.ekyc.data.model.MobileEntity
import com.my.ekyc.data.repository.DataStore
import com.my.ekyc.data.repository.Remote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

open class RemoteDataStore @Inject constructor(
    private val remote: Remote
) : DataStore {

    override fun getMobiles(): Single<List<MobileEntity>> {
        return remote.getMobiles()
    }

    override fun getImagesByMobileId(id: String): Single<List<ImageEntity>> {
        return remote.getImagesByMobileId(id)
    }

    override fun saveMobile(mobile: MobileEntity): Completable {
        throw UnsupportedOperationException()
    }

    override fun deleteMobile(id: String): Completable {
        throw UnsupportedOperationException()
    }

    override fun setSortBy(key: String): Completable {
        throw UnsupportedOperationException()
    }

    override fun getSortBy(): Single<String> {
        throw UnsupportedOperationException()
    }

    override fun getFavoriteList(): Single<List<MobileEntity>> {
        throw UnsupportedOperationException()
    }

}