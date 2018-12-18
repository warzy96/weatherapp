package com.project.personal.weatherapp.ui.fivedayforecast

import com.project.personal.domain.Success
import com.project.personal.domain.interactor.FetchFiveDayForecastUseCase
import com.project.personal.weatherapp.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FiveDayForecastPresenter @Inject
constructor(val fiveDayForecastUseCase: FetchFiveDayForecastUseCase,
            val fiveDayForecastViewModelMapper: FiveDayForecastViewModelMapper)
    : FiveDayForecastContract.Presenter, BasePresenter<FiveDayForecastContract.View>() {

    override fun setView(fiveDayForecastContractView: FiveDayForecastContract.View) {
        this.view = fiveDayForecastContractView
    }

    override fun start(cityId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val forecast = fiveDayForecastUseCase.execute(cityId).await()

            when (forecast) {
                is Success -> view?.render(fiveDayForecastViewModelMapper.mapFiveDayForecastViewModels(forecast.data))
            }
        }
    }
}