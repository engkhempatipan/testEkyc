package com.my.ekyc.remote

import com.my.ekyc.remote.model.ImageModel
import com.my.ekyc.remote.model.MobileModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("mobiles/")
    fun getMobiles(): Single<List<MobileModel>?>

    @GET("mobiles/{id}/images")
    fun getImagesByMobileId(@Path("id") id: String): Single<List<ImageModel>?>

}
