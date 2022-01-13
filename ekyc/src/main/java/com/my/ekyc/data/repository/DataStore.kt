package com.my.ekyc.data.repository

import com.my.ekyc.data.model.ImageEntity
import com.my.ekyc.data.model.MobileEntity
import io.reactivex.Completable
import io.reactivex.Single

interface DataStore {

    fun getMobiles(): Single<List<MobileEntity>>

    fun getImagesByMobileId(id: String): Single<List<ImageEntity>>

    fun getFavoriteList(): Single<List<MobileEntity>>

    fun saveMobile(mobile: MobileEntity): Completable

    fun deleteMobile(id:String):Completable

    fun setSortBy(key:String):Completable

    fun getSortBy():Single<String>

}