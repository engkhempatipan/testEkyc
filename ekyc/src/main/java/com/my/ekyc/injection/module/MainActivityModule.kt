package com.my.ekyc.injection.module

import com.my.ekyc.domain.interactor.sortby.GetSortBy
import com.my.ekyc.domain.interactor.sortby.SetSortBy
import com.my.ekyc.injection.scopes.PerActivity
import com.my.ekyc.ui.main.activity.MainActivity
import com.my.ekyc.ui.main.presenter.MainContract
import com.my.ekyc.ui.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
open class MainActivityModule {

    @Provides
    @PerActivity
    internal fun provideMainView(mainActivity: MainActivity): MainContract.View {
        return mainActivity
    }

    @Provides
    @PerActivity
    internal fun provideMainPresenter(
        mainView: MainContract.View,
        setSortBy: SetSortBy,
        getSortBy: GetSortBy
    ): MainContract.Presenter {
        return MainPresenter(mainView, setSortBy, getSortBy)
    }

}
