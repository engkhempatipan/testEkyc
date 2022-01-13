package com.my.ekyc.injection.module

import com.my.ekyc.domain.interactor.deletefavorite.DeleteFavoriteById
import com.my.ekyc.domain.interactor.getFavoritelist.GetFavoriteList
import com.my.ekyc.domain.interactor.mobiles.GetMobiles
import com.my.ekyc.domain.interactor.savemobile.Favorite
import com.my.ekyc.domain.interactor.sortby.GetSortBy
import com.my.ekyc.injection.scopes.PerActivity
import com.my.ekyc.ui.mobilelist.fragment.MobilesFragment
import com.my.ekyc.ui.mobilelist.presenter.MobilesContract
import com.my.ekyc.ui.mobilelist.presenter.MobilesPresenter
import dagger.Module
import dagger.Provides

@Module
open class MobilesModule {

    @Provides
    @PerActivity
    internal fun provideMainView(mobilesFragment: MobilesFragment): MobilesContract.View {
        return mobilesFragment
    }

    @Provides
    @PerActivity
    internal fun provideMainPresenter(
        mainView: MobilesContract.View,
        getMobiles: GetMobiles,
        saveToFavorite: Favorite,
        queryFavoriteList: GetFavoriteList,
        deleteFavoriteById: DeleteFavoriteById,
        getSortBy: GetSortBy
    ): MobilesContract.Presenter {
        return MobilesPresenter(
            mainView,
            getMobiles,
            saveToFavorite,
            queryFavoriteList,
            deleteFavoriteById,
            getSortBy
        )
    }
}