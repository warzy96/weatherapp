package com.project.personal.weatherapp.ui.fivedayforecast.cityforecast

import com.project.personal.domain.Failure
import com.project.personal.domain.Success
import com.project.personal.domain.interactor.FetchFiveDayForecastUseCase
import com.project.personal.domain.interactor.InsertCityUseCase
import com.project.personal.domain.model.City
import com.project.personal.weatherapp.ui.base.BasePresenter
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastViewModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FiveDayCityForecastPresenter
@Inject
constructor(val fiveDayForecastViewModelMapper: FiveDayForecastViewModelMapper,
            val fiveDayForecastUseCase: FetchFiveDayForecastUseCase,
            val insertCityUseCase: InsertCityUseCase) :
        FiveDayCityForecastContract.Presenter, BasePresenter<FiveDayCityForecastContract.View>() {

    override fun setView(fiveDayCityForecastContractView: FiveDayCityForecastContract.View) {
        this.view = fiveDayCityForecastContractView
    }

    override fun start(id: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = fiveDayForecastUseCase.execute(id).await()
            when (result) {
                is Success -> {
                    withContext(Dispatchers.Default) {
                        insertCityUseCase.execute(City(woeid = id, title = result.data.title))
                    }
                    view?.render(fiveDayForecastViewModelMapper
                            .mapFiveDayForecastViewModels(result.data))
                }
                is Failure -> result.error?.printStackTrace()
            }
        }
    }


}