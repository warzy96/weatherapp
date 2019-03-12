package com.project.personal.domain.interactor.type

interface CompletableUseCase<Param> {

    fun execute(param: Param)
}