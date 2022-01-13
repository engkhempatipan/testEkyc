package com.my.ekyc.domain.usecase.getimagesbymobileid

import com.my.ekyc.domain.executor.PostExecutionThread
import com.my.ekyc.domain.executor.ThreadExecutor
import com.my.ekyc.domain.interactor.getImagebymobileid.GetImagesByMobileId
import com.my.ekyc.domain.model.Image
import com.my.ekyc.domain.repository.Repository
import com.my.ekyc.domain.test.factory.DataFactory.Factory.randomUuid
import com.my.ekyc.domain.test.factory.Factory
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetImagesByMobileIdTest {

    private lateinit var useCase: GetImagesByMobileId
    @Mock
    private lateinit var mockThreadExecutor: ThreadExecutor
    @Mock
    private lateinit var mockPostExecutionThread: PostExecutionThread
    @Mock
    private lateinit var mockRepository: Repository
    private val userId = randomUuid()
    @Before
    fun setUp() {
        useCase = GetImagesByMobileId(
            mockRepository,
            mockThreadExecutor,
            mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        // WHEN
        useCase.buildUseCaseObservable(userId)
        // THEN
        verify(mockRepository).getImagesByMobileId(userId)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        // GIVEN
        val images = Factory.makeListImage(2)
        stubRepositoryGetImageByMobileId(userId, Single.just(images))
        // WHEN
        val testObserver = useCase.buildUseCaseObservable(userId).test()
        // THEN
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        // GIVEN
        val images = Factory.makeListImage(2)
        stubRepositoryGetImageByMobileId(userId, Single.just(images))
        // WHEN
        val testObserver = useCase.buildUseCaseObservable(userId).test()
        // THEN
        testObserver.assertValue(images)
    }

    private fun stubRepositoryGetImageByMobileId(id: String, single: Single<List<Image>>) {
        whenever(mockRepository.getImagesByMobileId(id)).thenReturn(single)
    }

}