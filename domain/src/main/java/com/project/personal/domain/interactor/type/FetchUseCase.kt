package com.project.personal.domain.interactor.type

interface FetchUseCase<Result> {

    fun execute(): Result
}