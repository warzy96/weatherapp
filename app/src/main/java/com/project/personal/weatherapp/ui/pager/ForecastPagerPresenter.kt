package com.project.personal.weatherapp.ui.pager

import com.project.personal.domain.interactor.GetCitiesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastPagerPresenter
@Inject
constructor(val getCitiesUseCase: GetCitiesUseCase) : PagerContract.Presenter {

    lateinit var forecastPagerAdapter: PagerContract.Adapter

    fun setAdapter(forecastPagerAdapter: ForecastPagerAdapter) {
        this.forecastPagerAdapter = forecastPagerAdapter
    }

    override fun start() {
        GlobalScope.launch(Dispatchers.Default) {
            forecastPagerAdapter.update(getCitiesUseCase.execute())
        }
    }
}