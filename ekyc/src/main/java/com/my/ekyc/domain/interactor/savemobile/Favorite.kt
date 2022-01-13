package com.my.ekyc.domain.interactor.savemobile

import com.my.ekyc.domain.executor.PostExecutionThread
import com.my.ekyc.domain.executor.ThreadExecutor
import com.my.ekyc.domain.interactor.SingleUseCase
import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

open class Favorite @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Boolean, Mobile>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Mobile?): Single<Boolean> {
        params?.let {
            repository.saveMobile(it).doOnComplete {
                Single.just(true)
            }.subscribe()
        }
        return Single.just(false)
    }

}