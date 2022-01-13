package com.my.ekyc.data.source

import com.my.ekyc.data.repository.Cache
import com.my.ekyc.data.repository.DataStore
import javax.inject.Inject

open class DataStoreFactory @Inject constructor(
    private val cache: Cache,
    private val cacheDataStore: CacheDataStore,
    private val remoteDataStore: RemoteDataStore
) {

    open fun retrieveDataStore(): DataStore {
        return retrieveRemoteDataStore()
    }

    open fun retrieveCacheDataStore(): DataStore {
        return cacheDataStore
    }

    open fun retrieveRemoteDataStore(): DataStore {
        return remoteDataStore
    }

}