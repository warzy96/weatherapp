package com.project.personal.weatherapp.di.fragment.module

import com.project.personal.domain.interactor.FetchFiveDayForecastUseCase
import com.project.personal.domain.interactor.InsertCityUseCase
import com.project.personal.domain.interactor.SearchCitiesByCoordinatesUseCase
import com.project.personal.domain.interactor.SearchCitiesUseCase
import com.project.personal.weatherapp.di.fragment.DaggerFragment
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.di.fragment.FragmentScope
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastContract
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastPresenter
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastViewModelMapper
import com.project.personal.weatherapp.ui.fivedayforecast.cityforecast.FiveDayCityForecastPresenter
import com.project.personal.weatherapp.ui.search.SearchPresenter
import com.project.personal.weatherapp.ui.search.SearchViewModelMapper
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
                                        fiveDayForecastViewModelMapper: FiveDayForecastViewModelMapper,
                                        insertCityUseCase: InsertCityUseCase):
            FiveDayForecastContract.Presenter {
        val presenter = FiveDayForecastPresenter(fiveDayForecastUseCase,
                searchCitiesByCoordinatesUseCase, fiveDayForecastViewModelMapper, insertCityUseCase)
        getFragmentComponent()?.inject(presenter)
        return presenter
    }

    @Provides
    @FragmentScope
    fun provideSearchPresenter(searchCitiesUseCase: SearchCitiesUseCase, searchViewModelMapper: SearchViewModelMapper): SearchPresenter {
        return SearchPresenter(searchCitiesUseCase, searchViewModelMapper)
    }

    @Provides
    @FragmentScope
    fun provideFiveDayCityForecastPresenter(fiveDayForecastViewModelMapper: FiveDayForecastViewModelMapper,
                                            fiveDayForecastUseCase: FetchFiveDayForecastUseCase,
                                            insertCityUseCase: InsertCityUseCase):
            FiveDayCityForecastPresenter {
        return FiveDayCityForecastPresenter(fiveDayForecastViewModelMapper,
                fiveDayForecastUseCase, insertCityUseCase)
    }
}