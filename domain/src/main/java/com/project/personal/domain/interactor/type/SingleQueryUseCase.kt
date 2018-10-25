package com.project.personal.domain.interactor.type

interface SingleQueryUseCase<Param, Result> {

    suspend fun execute(param: Param): Result
}