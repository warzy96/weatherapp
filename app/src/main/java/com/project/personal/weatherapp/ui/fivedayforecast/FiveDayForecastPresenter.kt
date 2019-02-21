package com.project.personal.weatherapp.ui.fivedayforecast

import com.project.personal.data.Location
import com.project.personal.domain.Failure
import com.project.personal.domain.Success
import com.project.personal.domain.interactor.FetchFiveDayForecastUseCase
import com.project.personal.domain.interactor.SearchCitiesByCoordinatesUseCase
import com.project.personal.weatherapp.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FiveDayForecastPresenter @Inject
constructor(val fiveDayForecastUseCase: FetchFiveDayForecastUseCase,
            val searchCitiesByCoordinatesUseCase: SearchCitiesByCoordinatesUseCase,
            val fiveDayForecastViewModelMapper: FiveDayForecastViewModelMapper)
    : FiveDayForecastContract.Presenter, BasePresenter<FiveDayForecastContract.View>() {

    override fun setView(fiveDayForecastContractView: FiveDayForecastContract.View) {
        this.view = fiveDayForecastContractView
    }

    override fun start(location: Location) {
        GlobalScope.launch(Dispatchers.Main) {
            if (location.latitude != null && location.longitude != null) {
                val result = searchCitiesByCoordinatesUseCase.execute(location.latitude!!, location.longitude!!).await()
                when (result) {
                    is Success -> {
                        val forecast = fiveDayForecastUseCase.execute(result.data.citySearchResults.first().woeid).await()
                        when (forecast) {
                            is Success -> view?.render(fiveDayForecastViewModelMapper.mapFiveDayForecastViewModels(forecast.data))
                            is Failure -> forecast.error?.printStackTrace()
                        }
                    }
                    is Failure -> result.error?.printStackTrace()
                }
            }
        }
    }
}