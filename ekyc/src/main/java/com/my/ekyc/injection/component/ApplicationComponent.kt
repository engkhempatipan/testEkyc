package com.my.ekyc.injection.component

import android.app.Application
import com.my.ekyc.injection.module.ApplicationModule
import com.my.ekyc.injection.module.BindingModule
import com.my.ekyc.injection.scopes.PerApplication
import com.my.ekyc.ui.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        BindingModule::class
    ]
)

interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MainApplication)

}
