package com.my.ekyc.domain.interactor.getFavoritelist

import com.my.ekyc.domain.executor.PostExecutionThread
import com.my.ekyc.domain.executor.ThreadExecutor
import com.my.ekyc.domain.interactor.SingleUseCase
import com.my.ekyc.domain.model.Mobile
import com.my.ekyc.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

open class GetFavoriteList @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Mobile>, Void?>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Void?): Single<List<Mobile>> {
        return repository.getFavoriteList()
    }

}