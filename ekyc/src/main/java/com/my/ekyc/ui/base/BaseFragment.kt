package com.my.ekyc.ui.base

import androidx.fragment.app.Fragment
import com.my.ekyc.ui.dialog.LoadingDialogFragment

open class BaseFragment : Fragment() {

    open fun showLoadingDialog() {
        fragmentManager?.let {
            LoadingDialogFragment.newInstance().show(it, LoadingDialogFragment::class.java.simpleName)
        }
    }

    open fun hideLoadingDialog() {
        fragmentManager?.let {
            val dialog = it.findFragmentByTag(LoadingDialogFragment::class.java.simpleName) as? LoadingDialogFragment
            dialog?.dismiss()
        }
    }
}