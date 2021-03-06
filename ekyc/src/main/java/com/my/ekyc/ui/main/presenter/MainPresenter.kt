package com.my.ekyc.ui.main.presenter

import com.my.ekyc.cache.PreferencesHelper.Companion.SORT_BY_PRICE_HIGH_TO_LOW
import com.my.ekyc.cache.PreferencesHelper.Companion.SORT_BY_PRICE_LOW_TO_HIGH
import com.my.ekyc.cache.PreferencesHelper.Companion.SORT_BY_RATING
import com.my.ekyc.domain.interactor.CompletableUseCase
import com.my.ekyc.domain.interactor.SingleUseCase
import com.my.ekyc.ui.main.bus.Sort
import com.my.ekyc.ui.main.presenter.MainContract
import io.reactivex.observers.DisposableSingleObserver
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val view: MainContract.View,
    private val setCachedSortByUseCase: CompletableUseCase<String>,
    private val getCachedSortByUseCase: SingleUseCase<String, Void?>
) : MainContract.Presenter {

    var sortByKey = ""

    init {
        view.setPresenter(this)
    }

    override fun start() {
        getCachedSortByUseCase.execute(object : DisposableSingleObserver<String>() {
            override fun onSuccess(key: String) {
                setSortBy(key)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })

    }

    override fun stop() {
        setCachedSortByUseCase.unsubscribe()
        getCachedSortByUseCase.dispose()
    }

    override fun setSortByPriceLowToHigh() {
        setSortBy(SORT_BY_PRICE_LOW_TO_HIGH)
    }

    override fun setSortByPriceHighToLow() {
        setSortBy(SORT_BY_PRICE_HIGH_TO_LOW)
    }

    override fun setSortByRating() {
        setSortBy(SORT_BY_RATING)
    }

    private fun setSortBy(key: String) {
        sortByKey = key
        setCachedSortByUseCase.execute(key).doOnComplete {
            EventBus.getDefault().post(Sort(key))
        }.subscribe()
    }

    override fun onSortMenuClicked() {
        view.showSortByDialog(sortByKey)
    }

}