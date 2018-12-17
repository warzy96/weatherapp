package com.project.personal.weatherapp.ui.fivedayforecast

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
            view?.render(fiveDayForecastViewModelMapper
                    .mapFiveDayForecastViewModels(fiveDayForecastUseCase.execute(cityId).await()))
        }
    }
}