package com.my.ekyc.cache.test.factory

import com.my.ekyc.data.model.MobileEntity
import com.my.ekyc.cache.test.factory.DataFactory.Factory.randomUuid

class Factory {

    companion object Factory {

        fun makeMobileEntity():MobileEntity{
            return MobileEntity(
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid()
            )
        }
    }

}