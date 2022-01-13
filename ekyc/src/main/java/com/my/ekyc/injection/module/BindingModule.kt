package com.my.ekyc.injection.module

import com.my.ekyc.injection.module.DetailActivityModule
import com.my.ekyc.injection.module.FavoriteModule
import com.my.ekyc.injection.module.MainActivityModule
import com.my.ekyc.injection.module.MobilesModule
import com.my.ekyc.injection.scopes.PerActivity
import com.my.ekyc.ui.detail.activity.DetailActivity
import com.my.ekyc.ui.favoritelist.fragment.FavoriteFragment
import com.my.ekyc.ui.main.activity.MainActivity
import com.my.ekyc.ui.mobilelist.fragment.MobilesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity


    @PerActivity
    @ContributesAndroidInjector(modules = [MobilesModule::class])
    abstract fun bindMobilesFragment(): MobilesFragment


    @PerActivity
    @ContributesAndroidInjector(modules = [FavoriteModule::class])
    abstract fun bindFavoriteFragment(): FavoriteFragment

    @PerActivity
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun bindDetailActivity(): DetailActivity


}