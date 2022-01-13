package com.my.ekyc.util

import com.my.ekyc.cache.PreferencesHelper.Companion.SORT_BY_PRICE_HIGH_TO_LOW
import com.my.ekyc.cache.PreferencesHelper.Companion.SORT_BY_PRICE_LOW_TO_HIGH
import com.my.ekyc.cache.PreferencesHelper.Companion.SORT_BY_RATING
import com.my.ekyc.domain.model.Mobile

fun List<Mobile>?.sortBy(key: String): List<Mobile>? {
    this?.let { list ->
        when (key) {
            SORT_BY_PRICE_HIGH_TO_LOW -> {
                return list.sortedByDescending {
                    it.price?.toDouble()
                }
            }

            SORT_BY_PRICE_LOW_TO_HIGH -> {
                return list.sortedBy {
                    it.price?.toDouble()
                }
            }

            SORT_BY_RATING -> {
                return list.sortedByDescending {
                    it.rating?.toDouble()
                }
            }

            else -> return list.sortedBy {
                it.price?.toDouble()
            }
        }
    }
    return null
}