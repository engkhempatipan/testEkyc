package com.my.ekyc.injection.module

import android.app.Application
import android.content.Context
import com.my.ekyc.BuildConfig
import com.my.ekyc.cache.CacheImpl
import com.my.ekyc.cache.PreferencesHelper
import com.my.ekyc.cache.db.DbOpenHelper
import com.my.ekyc.cache.db.mapper.CachedMobilesMapper
import com.my.ekyc.data.DataRepository
import com.my.ekyc.data.executor.JobExecutor
import com.my.ekyc.data.mapper.ImageMapper
import com.my.ekyc.data.mapper.MobileMapper
import com.my.ekyc.data.repository.Cache
import com.my.ekyc.data.repository.Remote
import com.my.ekyc.data.source.DataStoreFactory
import com.my.ekyc.domain.executor.PostExecutionThread
import com.my.ekyc.domain.executor.ThreadExecutor
import com.my.ekyc.domain.repository.Repository
import com.my.ekyc.remote.RemoteImpl
import com.my.ekyc.remote.Service
import com.my.ekyc.remote.ServiceFactory
import com.my.ekyc.remote.mapper.MobileEntityMapper
import com.my.ekyc.ui.UiThread
import com.my.ekyc.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {
    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun provideRepository(
        factory: DataStoreFactory,
        mobileMapper: MobileMapper,
        imageMappper: ImageMapper
    )
            : Repository {
        return DataRepository(factory, mobileMapper, imageMappper)
    }

    @Provides
    @PerApplication
    internal fun provideCache(
        factory: DbOpenHelper,
        helper: PreferencesHelper,
        mobileEntityMapper: com.my.ekyc.cache.mapper.MobileEntityMapper,
        cachedMobilesMapper: CachedMobilesMapper
    ): Cache {
        return CacheImpl(
            factory,
            helper,
            mobileEntityMapper,
            cachedMobilesMapper
        )
    }

    @Provides
    @PerApplication
    internal fun provideRemote(
        service: Service,
        mobileEntityMapper: MobileEntityMapper,
        imageEntityMapper: com.my.ekyc.remote.mapper.ImageEntityMapper
    ): Remote {
        return RemoteImpl(
            service,
            mobileEntityMapper,
            imageEntityMapper
        )
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideService(): Service {
        return ServiceFactory.makeService(BuildConfig.DEBUG)
    }
}
