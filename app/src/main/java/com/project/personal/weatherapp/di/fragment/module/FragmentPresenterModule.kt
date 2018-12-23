package com.project.personal.weatherapp.di.fragment.module

import com.project.personal.domain.interactor.FetchFiveDayForecastUseCase
import com.project.personal.domain.interactor.SearchCitiesByCoordinatesUseCase
import com.project.personal.weatherapp.di.fragment.DaggerFragment
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.di.fragment.FragmentScope
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastContract
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastPresenter
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastViewModelMapper
import dagger.Module
import dagger.Provides

@Module
class FragmentPresenterModule(private val daggerFragment: DaggerFragment) {

    private fun getFragmentComponent(): FragmentComponent? {
        return daggerFragment.fragmentComponent
    }

    @Provides
    @FragmentScope
    fun provideFiveDayForecastPresenter(fiveDayForecastUseCase: FetchFiveDayForecastUseCase,
                                        searchCitiesByCoordinatesUseCase: SearchCitiesByCoordinatesUseCase,
                                        fiveDayForecastViewModelMapper: FiveDayForecastViewModelMapper):
            FiveDayForecastContract.Presenter {
        val presenter = FiveDayForecastPresenter(fiveDayForecastUseCase, searchCitiesByCoordinatesUseCase, fiveDayForecastViewModelMapper)
        getFragmentComponent()?.inject(presenter)
        return presenter
    }
}