package com.my.ekyc.remote

import com.my.ekyc.data.model.ImageEntity
import com.my.ekyc.data.model.MobileEntity
import com.my.ekyc.data.repository.Remote
import com.my.ekyc.remote.mapper.ImageEntityMapper
import com.my.ekyc.remote.mapper.MobileEntityMapper
import io.reactivex.Single
import javax.inject.Inject

class RemoteImpl @Inject constructor(
    private val service: Service,
    private val mobileEntityMapper: MobileEntityMapper,
    private val imageEntityMapper: ImageEntityMapper
) : Remote {

    override fun getMobiles(): Single<List<MobileEntity>> {
        return service.getMobiles().map {
            it.map { mobileModel ->
                mobileEntityMapper.mapFromRemote(mobileModel)
            }
        }
    }

    override fun getImagesByMobileId(id: String): Single<List<ImageEntity>> {
        return service.getImagesByMobileId(id).map {
            it.map { imageModel ->
                imageEntityMapper.mapFromRemote(imageModel)
            }
        }
    }

}