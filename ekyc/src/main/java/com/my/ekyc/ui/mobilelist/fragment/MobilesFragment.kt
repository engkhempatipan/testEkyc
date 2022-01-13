package com.my.ekyc.ui.mobilelist.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.R
import com.my.ekyc.ui.base.BaseFragment
import com.my.ekyc.ui.detail.activity.DetailActivity
import com.my.ekyc.ui.main.bus.Reload
import com.my.ekyc.ui.main.bus.ReloadMobile
import com.my.ekyc.ui.main.bus.Sort
import com.my.ekyc.ui.mobilelist.adapdter.MobilesRecyclerAdapter
import com.my.ekyc.ui.mobilelist.presenter.MobilesContract
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_mobiles.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject


class MobilesFragment : BaseFragment(), MobilesContract.View {

    @Inject
    lateinit var mobilePresenter: MobilesContract.Presenter

    var mobileAdapter = MobilesRecyclerAdapter()

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mobilePresenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mobilePresenter.stop()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mobiles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mobileAdapter.apply {
            onFavoriteClickListener = {
                mobilePresenter.onFavoriteClick(it)
                reloadFavoriteFragment()
            }

            onUnFavoriteClickListener = {
                it.id?.let { id ->
                    mobilePresenter.onUnFavoriteClick(id)
                    reloadFavoriteFragment()
                }
            }

            onItemClickListener = { mobile ->
                activity?.let {
                    DetailActivity.goToDetailActivity(
                        it,
                        mobile.id,
                        mobile.name,
                        mobile.description,
                        mobile.price,
                        mobile.rating
                    )
                }
            }

        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mobileAdapter
        }
    }

    override fun updateList(mobiles: List<Mobile>) {
        mobileAdapter.submitList(mobiles)
        mobileAdapter.notifyDataSetChanged()
    }

    override fun updateFavoriteList(listFavorite: List<String>) {
        mobileAdapter.listFavorite = listFavorite.toMutableList()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MobilesFragment()
    }

    override fun setPresenter(presenter: MobilesContract.Presenter) {
        mobilePresenter = presenter
    }

    private fun reloadFavoriteFragment() {
        EventBus.getDefault().post(Reload())
    }

    @Subscribe
    fun subScribReload(obj: ReloadMobile) {
        mobilePresenter.getMobiles()
    }

    @Subscribe
    fun sort(obj: Sort) {
        mobilePresenter.sortData(obj.key)
    }

}
