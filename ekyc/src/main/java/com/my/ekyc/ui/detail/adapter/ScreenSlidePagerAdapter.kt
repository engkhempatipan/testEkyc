package com.my.ekyc.ui.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.my.ekyc.domain.model.Image
import com.my.ekyc.ui.detail.fragment.ScreenSlidePageFragment

class ScreenSlidePagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var images: List<Image>? = null

    override fun getCount(): Int {
        images?.let {
            return if (it.isEmpty()) {
                0
            } else {
                it.size - 1
            }
        }
        return 0
    }

    override fun getItem(position: Int): Fragment = ScreenSlidePageFragment(images?.get(position)?.url)
}