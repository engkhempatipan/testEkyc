package com.my.ekyc.ui.detail.presenter

import com.my.ekyc.domain.model.Image
import com.my.ekyc.ui.base.BasePresenter
import com.my.ekyc.ui.base.BaseView

interface DetailContract {

    interface View : BaseView<Presenter> {
        fun setImageAdapter(images: List<Image>)
    }

    interface Presenter : BasePresenter {
        fun getImagesById(id: String)
    }

}