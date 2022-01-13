package com.my.ekyc.domain.interactor.getImagebymobileid

import com.my.ekyc.domain.executor.PostExecutionThread
import com.my.ekyc.domain.executor.ThreadExecutor
import com.my.ekyc.domain.interactor.SingleUseCase
import com.my.ekyc.domain.model.Image
import com.my.ekyc.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

open class GetImagesByMobileId @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Image>, String?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(id: String?): Single<List<Image>> {
        return repository.getImagesByMobileId(id ?: "")
    }

}