package com.project.personal.domain.mapper

interface Mappable<out T : Any> {

    fun mapToData(): T

    fun isValid(): Boolean
}