package com.project.personal.domain.interactor.type

interface SingleQueryMultipleParamsUseCase<Param, Result> {

    suspend fun execute(param1: Param, param2: Param): Result
}