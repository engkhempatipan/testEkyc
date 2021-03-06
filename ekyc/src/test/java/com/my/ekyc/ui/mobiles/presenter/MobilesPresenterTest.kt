package com.my.ekyc.ui.mobiles.presenter

import com.my.ekyc.domain.interactor.deletefavorite.DeleteFavoriteById
import com.my.ekyc.domain.interactor.getFavoritelist.GetFavoriteList
import com.my.ekyc.domain.interactor.mobiles.GetMobiles
import com.my.ekyc.domain.interactor.savemobile.Favorite
import com.my.ekyc.domain.interactor.sortby.GetSortBy
import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.ui.mobilelist.presenter.MobilesContract
import com.my.ekyc.ui.mobilelist.presenter.MobilesPresenter
import com.my.ekyc.ui.mobiles.MobilesFactory.Factory.makeListMobile
import com.my.ekyc.ui.mobiles.MobilesFactory.Factory.makeMobile
import com.my.ekyc.ui.test.DataFactory.Factory.randomUuid
import com.my.ekyc.util.sortBy
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class MobilesPresenterTest {

    lateinit var presenter: MobilesPresenter
    lateinit var view: MobilesContract.View
    lateinit var getMobilesUseCase: GetMobiles
    lateinit var saveCachedFavoriteSigleUseCase: Favorite
    lateinit var getCachedFavoriteListUseCase: GetFavoriteList
    lateinit var getCachedSortByUseCase: GetSortBy
    lateinit var deleteCachedFavoriteByIdUseCase: DeleteFavoriteById

    lateinit var captorGetMobilesUseCase: KArgumentCaptor<DisposableSingleObserver<List<Mobile>>>
    lateinit var captorSaveCachedFavorite: KArgumentCaptor<DisposableSingleObserver<Boolean>>

    @Before
    fun setUp() {
        view = mock()
        getMobilesUseCase = mock()
        saveCachedFavoriteSigleUseCase = mock()
        getCachedFavoriteListUseCase = mock()
        getCachedSortByUseCase = mock()
        deleteCachedFavoriteByIdUseCase = mock()

        captorGetMobilesUseCase = argumentCaptor()
        captorSaveCachedFavorite = argumentCaptor()

        presenter = MobilesPresenter(
            view,
            getMobilesUseCase,
            saveCachedFavoriteSigleUseCase,
            getCachedFavoriteListUseCase,
            deleteCachedFavoriteByIdUseCase,
            getCachedSortByUseCase
        )
    }

    @Test
    fun getMobileList_onSuccess() {
        // GIVEN
        val listMobile = makeListMobile(10)
        // WHEN
        presenter.getMobiles()

        // THEN
        verify(getMobilesUseCase).execute(captorGetMobilesUseCase.capture(), eq(null))
        captorGetMobilesUseCase.firstValue.onSuccess(listMobile)
        verify(view).hideLoadingDialog()
        assertEquals(presenter.mobiles, listMobile)
    }

    @Test
    fun getMobileList_onError() {
        // WHEN
        presenter.getMobiles()

        // THEN
        verify(getMobilesUseCase).execute(captorGetMobilesUseCase.capture(), eq(null))
        captorGetMobilesUseCase.firstValue.onError(Throwable())
        verify(view).hideLoadingDialog()
    }

    @Test
    fun sortData() {
        // GIVEN
        val listMobile = makeListMobile(10)
        val key = randomUuid()
        presenter.mobiles = listMobile
        val sortList = listMobile.sortBy(key)

        // WHEN
        presenter.sortData(key)

        // THEN
        assertEquals(presenter.sortByKey, key)
        verify(view).updateList(sortList!!)
    }

    @Test
    fun onStart() {
        // WHEN
        presenter.start()

        // THEN
        verify(view).showLoadingDialog()
    }

    @Test
    fun onFavoriteClick() {
        // GIVEN
        val mobile = makeMobile()
        val mobiles = makeListMobile(10)

        // WHEN
        presenter.onFavoriteClick(mobile)

        // THEN
        verify(saveCachedFavoriteSigleUseCase).execute(captorSaveCachedFavorite.capture(), eq(mobile))
        captorSaveCachedFavorite.firstValue.onSuccess(true)
        verify(getCachedFavoriteListUseCase).execute(captorGetMobilesUseCase.capture(), eq(null))
        captorGetMobilesUseCase.firstValue.onSuccess(mobiles)
        verify(view).updateFavoriteList(any())
    }
}