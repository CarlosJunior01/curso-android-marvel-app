package com.example.core.usecase.base

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

//classe abstrada no qual quem a implementar vai passar um parêmetro e vai retornar alguma coisa.
// Um caso de uso executa apenas uma coisa (responsabilidade única)

abstract class UseCase<in Parameters, Results> {

    operator fun invoke(params: Parameters): Flow<ResultStatus<Results>> = flow { 
        emit(ResultStatus.Loading)
        emit(doWork(params))
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }
    
    protected abstract suspend fun doWork(params: Parameters): ResultStatus<Results>
}

abstract class PagingUseCase<in Parameters, Results : Any> {

    operator fun invoke(params: Parameters): Flow<PagingData<Results>> = createFlowObservable(params)

    protected abstract fun createFlowObservable(params: Parameters): Flow<PagingData<Results>>
}