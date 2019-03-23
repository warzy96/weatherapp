package com.project.personal.weatherapp.di.fragment.module

import com.project.personal.domain.interactor.*
import com.project.personal.weatherapp.di.fragment.DaggerFragment
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.di.fragment.FragmentScope
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastContract
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastPresenter
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastViewModelMapper
import com.project.personal.weatherapp.ui.fivedayforecast.cityforecast.FiveDayCityForecastPresenter
import com.project.personal.weatherapp.ui.search.SearchPresenter
import com.project.personal.weatherapp.ui.search.SearchViewModelMapper
import com.project.personal.weatherapp.ui.summary.CitiesSummaryViewModelMapper
import com.project.personal.weatherapp.ui.summary.SummaryPresenter
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
    fun provideSearchPresenter(searchCitiesUseCase: SearchCitiesUseCase, searchViewModelMapper:
    SearchViewModelMapper, insertCityUseCase: InsertCityUseCase): SearchPresenter {
        return SearchPresenter(searchCitiesUseCase, searchViewModelMapper, insertCityUseCase)
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

    @Provides
    @FragmentScope
    fun provideSummaryPresenter(citiesSummaryViewModelMapper: CitiesSummaryViewModelMapper,
                                fiveDayForecastUseCase: FetchFiveDayForecastUseCase,
                                getCitiesUseCase: GetCitiesUseCase): SummaryPresenter {
        return SummaryPresenter(fiveDayForecastUseCase, getCitiesUseCase, citiesSummaryViewModelMapper)
    }
}