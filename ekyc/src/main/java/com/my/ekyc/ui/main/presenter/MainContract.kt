package com.my.ekyc.ui.main.presenter

import com.my.ekyc.ui.base.BasePresenter
import com.my.ekyc.ui.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showSortByDialog(key: String)
    }

    interface Presenter : BasePresenter {
        fun onSortMenuClicked()
        fun setSortByPriceLowToHigh()
        fun setSortByPriceHighToLow()
        fun setSortByRating()
    }
}