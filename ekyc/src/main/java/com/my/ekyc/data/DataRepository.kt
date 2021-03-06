package com.my.ekyc.data

import com.my.ekyc.data.mapper.ImageMapper
import com.my.ekyc.data.mapper.MobileMapper
import com.my.ekyc.data.source.DataStoreFactory
import com.my.ekyc.domain.model.Image
import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.domain.repository.Repository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [Repository] interface for communicating to and from
 * data sources
 */
class DataRepository @Inject constructor(
    private val factory: DataStoreFactory,
    private val mobileMapper: MobileMapper,
    private val imageMapper: ImageMapper
) : Repository {

    override fun getMobiles(): Single<List<Mobile>> {
        val dataStore = factory.retrieveRemoteDataStore()
        return dataStore.getMobiles().map {
            it.map { mobileEntity ->
                mobileMapper.mapFromEntity(mobileEntity)
            }

        }
    }

    override fun getImagesByMobileId(id: String): Single<List<Image>> {
        val dataStore = factory.retrieveRemoteDataStore()
        return dataStore.getImagesByMobileId(id).map {
            it.map { imageEntity ->
                imageMapper.mapFromEntity(imageEntity)
            }
        }
    }

    override fun saveMobile(mobile: Mobile): Completable {
        val dataStore = factory.retrieveCacheDataStore()
        return dataStore.saveMobile(mobileMapper.mapToEntity(mobile))
    }

    override fun deleteMobile(id: String): Completable {
        val dataStore = factory.retrieveCacheDataStore()
        return dataStore.deleteMobile(id)
    }

    override fun getFavoriteList(): Single<List<Mobile>> {
        val dataStore = factory.retrieveCacheDataStore()
        return dataStore.getFavoriteList().map {
            it.map {
                mobileMapper.mapFromEntity(it)
            }
        }
    }

    override fun setSortBy(key: String): Completable {
        val dataStore = factory.retrieveCacheDataStore()
        return dataStore.setSortBy(key)
    }

    override fun getSortBy(): Single<String> {
        val dataStore = factory.retrieveCacheDataStore()
        return dataStore.getSortBy()
    }


}