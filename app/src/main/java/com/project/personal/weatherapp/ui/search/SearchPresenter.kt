package com.project.personal.weatherapp.ui.search

import com.project.personal.domain.Failure
import com.project.personal.domain.Success
import com.project.personal.domain.interactor.SearchCitiesUseCase
import com.project.personal.weatherapp.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchPresenter
@Inject
constructor(val searchCitiesUseCase: SearchCitiesUseCase,
            val searchViewModelMapper: SearchViewModelMapper) : BasePresenter<SearchContract.View>(),
        SearchContract.Presenter {

    override fun setView(view: SearchContract.View) {
        this.view = view
    }

    override fun start(cityName: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = searchCitiesUseCase.execute(cityName).await()
            when (result) {
                is Success -> {
                    view?.render(searchViewModelMapper.mapSearchResults(result.data))
                }
                is Failure -> {
                    result.error?.printStackTrace()
                }
            }
        }
    }
}