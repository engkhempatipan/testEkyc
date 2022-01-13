package com.my.ekyc.ui.mobiles

import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.ui.test.DataFactory.Factory.randomInt
import com.my.ekyc.ui.test.DataFactory.Factory.randomUuid


class MobilesFactory {

    companion object Factory {

        fun makeMobile(): Mobile {
            return Mobile(
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomInt().toString(),
                randomInt().toString()
            )
        }

        fun makeListMobile(count: Int): List<Mobile> {
            val list = mutableListOf<Mobile>()
            repeat(count) {
                list.add(makeMobile())
            }
            return list
        }

    }

}