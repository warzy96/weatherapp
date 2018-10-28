package com.project.personal.weatherapp.ui.fivedayforecast

import com.project.personal.domain.interactor.FetchFiveDayForecastUseCase
import com.project.personal.weatherapp.ui.base.BasePresenter
import com.project.personal.weatherapp.util.Android
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class FiveDayForecastPresenter @Inject
constructor(val fiveDayForecastUseCase: FetchFiveDayForecastUseCase,
            val fiveDayForecastViewModelMapper: FiveDayForecastViewModelMapper)
    : FiveDayForecastContract.Presenter, BasePresenter<FiveDayForecastContract.View>() {

    override fun setView(fiveDayForecastContractView: FiveDayForecastContract.View) {
        this.view = fiveDayForecastContractView
    }

    override fun start(cityId: Int) {
        launch(Android) {
            view?.render(fiveDayForecastViewModelMapper
                    .mapFiveDayForecastViewModels(fiveDayForecastUseCase.execute(cityId).await()))
        }
    }
}