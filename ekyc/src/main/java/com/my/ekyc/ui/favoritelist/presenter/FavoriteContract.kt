package com.my.ekyc.ui.favoritelist.presenter

import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.ui.base.BasePresenter
import com.my.ekyc.ui.base.BaseView

interface FavoriteContract {

    interface View : BaseView<Presenter> {
        fun showProgress()
        fun updateList(mobiles: List<Mobile>)
        fun updateFavoriteList(listFavorite: List<String>)
    }

    interface Presenter : BasePresenter {
        fun getFavorite()
        fun deleteFavorite(id: String)
        fun sortData(key: String)
    }

}