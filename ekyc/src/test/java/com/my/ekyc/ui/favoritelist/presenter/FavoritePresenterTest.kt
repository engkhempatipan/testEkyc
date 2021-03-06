package com.my.ekyc.ui.favoritelist.presenter

import com.my.ekyc.domain.executor.ThreadExecutor
import com.my.ekyc.domain.interactor.deletefavorite.DeleteFavoriteById
import com.my.ekyc.domain.interactor.getFavoritelist.GetFavoriteList
import com.my.ekyc.domain.interactor.sortby.GetSortBy
import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.ui.mobiles.MobilesFactory.Factory.makeListMobile
import com.my.ekyc.ui.test.DataFactory
import com.my.ekyc.ui.test.DataFactory.Factory.randomUuid
import com.my.ekyc.util.sortBy
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class FavoritePresenterTest {

    lateinit var presenter: FavoritePresenter
    lateinit var view: FavoriteContract.View
    lateinit var threadExecutor: ThreadExecutor
    lateinit var getCachedSortByUseCase: GetSortBy
    lateinit var queryFavoriteListUseCase: GetFavoriteList
    lateinit var deleteFavoriteSingleById: DeleteFavoriteById

    lateinit var captorSetCachedSortByUseCase: KArgumentCaptor<Completable>
    lateinit var captorGetCachedSortByUseCase: KArgumentCaptor<DisposableSingleObserver<String>>
    lateinit var captorGetFavoriteList: KArgumentCaptor<DisposableSingleObserver<List<Mobile>>>
    lateinit var captorDeleteFavoriteById:KArgumentCaptor<DisposableSingleObserver<Boolean>>
    @Before
    fun setUp() {
        view = mock()
        queryFavoriteListUseCase = mock()
        deleteFavoriteSingleById = mock()
        getCachedSortByUseCase = mock()
        threadExecutor = mock()

        captorSetCachedSortByUseCase = argumentCaptor()
        captorGetCachedSortByUseCase = argumentCaptor()
        captorGetFavoriteList = argumentCaptor()
        captorDeleteFavoriteById = argumentCaptor()

        presenter = FavoritePresenter(
            view,
            queryFavoriteListUseCase,
            deleteFavoriteSingleById,
            getCachedSortByUseCase
        )
    }

    @Test
    fun onSortMenuClicked() {
        // GIVEN
        val listMobile = makeListMobile(10)
        val key = DataFactory.randomUuid()
        presenter.mobiles = listMobile
        val sortList = listMobile.sortBy(key)

        // WHEN
        presenter.sortData(key)

        // THEN
        assertEquals(presenter.sortByKey, key)
        verify(view).updateList(sortList!!)
    }


    @Test
    fun getFavorite() {
        // GIVEN
        val listMobile = makeListMobile(10)

        // WHEN
        presenter.getFavorite()

        // THEN
        verify(queryFavoriteListUseCase).execute(captorGetFavoriteList.capture(), eq(null))
        captorGetFavoriteList.firstValue.onSuccess(listMobile)
        assertEquals(presenter.mobiles, listMobile)
        verify(view).updateList(any())
    }

    @Test
    fun delete() {
        // GIVEN
        val id = randomUuid()
        val listMobile = makeListMobile(10)

        // WHEN
        presenter.deleteFavorite(id)

        // THEN
        verify(deleteFavoriteSingleById).execute(captorDeleteFavoriteById.capture(),eq(id))
        captorDeleteFavoriteById.firstValue.onSuccess(true)

        verify(queryFavoriteListUseCase).execute(captorGetFavoriteList.capture(), eq(null))
        captorGetFavoriteList.firstValue.onSuccess(listMobile)

    }

}