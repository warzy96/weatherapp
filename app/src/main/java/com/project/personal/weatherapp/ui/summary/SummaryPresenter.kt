package com.project.personal.weatherapp.ui.summary

import com.project.personal.domain.Failure
import com.project.personal.domain.Success
import com.project.personal.domain.interactor.FetchFiveDayForecastUseCase
import com.project.personal.domain.interactor.GetCitiesUseCase
import com.project.personal.weatherapp.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SummaryPresenter
@Inject
constructor(val fiveDayForecastUseCase: FetchFiveDayForecastUseCase,
            val getCitiesUseCase: GetCitiesUseCase,
            val citiesSummaryModelMapper: CitiesSummaryViewModelMapper)
    : SummaryContract.Presenter, BasePresenter<SummaryContract.View>
() {

    override fun setView(view: SummaryContract.View) {
        this.view = view
    }

    override fun start() {
        GlobalScope.launch(Dispatchers.Main) {
            val citiesList = CitySummaryListViewModel(ArrayList())
            withContext(Dispatchers.IO) {
                val cities = getCitiesUseCase.execute()
                cities.forEach {
                    val result = fiveDayForecastUseCase.execute(it.woeid).await()
                    when (result) {
                        is Success -> citiesList.add(citiesSummaryModelMapper
                                .mapCitySummaryViewModel(result.data))
                        is Failure -> result.error?.printStackTrace()
                    }
                }
            }
            view?.render(citiesList)
        }
    }
}