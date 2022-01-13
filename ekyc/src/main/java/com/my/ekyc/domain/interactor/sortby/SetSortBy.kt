package com.my.ekyc.domain.interactor.sortby

import com.my.ekyc.domain.executor.PostExecutionThread
import com.my.ekyc.domain.executor.ThreadExecutor
import com.my.ekyc.domain.interactor.CompletableUseCase
import com.my.ekyc.domain.repository.Repository
import io.reactivex.Completable
import javax.inject.Inject

open class SetSortBy @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: String): Completable {
        return repository.setSortBy(params)
    }

}