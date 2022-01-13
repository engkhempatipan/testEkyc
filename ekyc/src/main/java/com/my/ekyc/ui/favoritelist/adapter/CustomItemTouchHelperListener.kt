package com.my.ekyc.ui.favoritelist.adapter

interface CustomItemTouchHelperListener {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)
}