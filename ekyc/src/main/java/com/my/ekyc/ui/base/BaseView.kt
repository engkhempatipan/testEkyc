package com.my.ekyc.ui.base

interface BaseView<in T : BasePresenter> {

    fun setPresenter(presenter: T)

}