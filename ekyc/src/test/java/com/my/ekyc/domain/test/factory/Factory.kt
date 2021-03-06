package com.my.ekyc.domain.test.factory

import com.my.ekyc.domain.model.Image
import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.domain.test.factory.DataFactory.Factory.randomUuid


class Factory {

    companion object Factory {

        fun makeListImage(count:Int):List<Image>{
            val list = mutableListOf<Image>()
            repeat(count){
                list.add(makeImage())
            }
            return list
        }

        fun makeImage(): Image{
            return Image(
                randomUuid(),
                randomUuid(),
                randomUuid()
            )
        }

        fun makeMobile(): Mobile{
            return Mobile(
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid()
            )
        }

        fun makeListMobile(count:Int):List<Mobile>{
            val list = mutableListOf<Mobile>()
            repeat(count){
                list.add(makeMobile())
            }
            return list
        }

    }

}