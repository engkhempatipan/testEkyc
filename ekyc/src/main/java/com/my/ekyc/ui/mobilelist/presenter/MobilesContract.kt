package com.my.ekyc.ui.mobilelist.presenter

import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.ui.base.BasePresenter
import com.my.ekyc.ui.base.BaseView

interface MobilesContract {

    interface View : BaseView<Presenter> {
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun updateList(mobiles: List<Mobile>)
        fun updateFavoriteList(listFavorite: List<String>)
    }

    interface Presenter : BasePresenter {
        fun getMobiles()
        fun sortData(key:String)
        fun onFavoriteClick(mobile:Mobile)
        fun onUnFavoriteClick(id: String)
    }

}