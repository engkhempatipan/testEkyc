package com.my.ekyc.domain.interactor.sortby

import com.my.ekyc.domain.executor.PostExecutionThread
import com.my.ekyc.domain.executor.ThreadExecutor
import com.my.ekyc.domain.interactor.SingleUseCase
import com.my.ekyc.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

open class GetSortBy @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<String, Void?>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Void?): Single<String> {
        return repository.getSortBy()
    }

}