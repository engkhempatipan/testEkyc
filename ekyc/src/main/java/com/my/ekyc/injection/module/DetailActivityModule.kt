package com.my.ekyc.injection.module

import android.content.Context
import com.my.ekyc.domain.interactor.getImagebymobileid.GetImagesByMobileId
import com.my.ekyc.injection.scopes.PerActivity
import com.my.ekyc.ui.detail.activity.DetailActivity
import com.my.ekyc.ui.detail.presenter.DetailContract
import com.my.ekyc.ui.detail.presenter.DetailPresenter
import dagger.Module
import dagger.Provides

@Module
open class DetailActivityModule {

    @Provides
    @PerActivity
    internal fun provideMainView(activity: DetailActivity): DetailContract.View {
        return activity
    }

    @Provides
    @PerActivity
    internal fun provideMainPresenter(
        context: Context,
        view: DetailContract.View,
        getImagesByMobileId: GetImagesByMobileId
    ): DetailContract.Presenter {
        return DetailPresenter(context, view, getImagesByMobileId)
    }

}
