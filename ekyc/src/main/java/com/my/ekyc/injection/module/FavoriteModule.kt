package com.my.ekyc.injection.module

import com.my.ekyc.domain.interactor.deletefavorite.DeleteFavoriteById
import com.my.ekyc.domain.interactor.getFavoritelist.GetFavoriteList
import com.my.ekyc.domain.interactor.sortby.GetSortBy
import com.my.ekyc.injection.scopes.PerActivity
import com.my.ekyc.ui.favoritelist.fragment.FavoriteFragment
import com.my.ekyc.ui.favoritelist.presenter.FavoriteContract
import com.my.ekyc.ui.favoritelist.presenter.FavoritePresenter
import dagger.Module
import dagger.Provides

@Module
open class FavoriteModule {

    @Provides
    @PerActivity
    internal fun provideFavoriteView(favoriteFragment: FavoriteFragment): FavoriteContract.View {
        return favoriteFragment
    }

    @Provides
    @PerActivity
    internal fun provideFavoritePresenter(
        mainView: FavoriteContract.View,
        queryFavoriteList: GetFavoriteList,
        deleteFavoriteById: DeleteFavoriteById,
        getSortBy: GetSortBy
    ): FavoriteContract.Presenter {
        return FavoritePresenter(
            mainView,
            queryFavoriteList,
            deleteFavoriteById,
            getSortBy
        )
    }
}