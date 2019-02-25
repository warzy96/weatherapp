package com.project.personal.weatherapp.ui.search

import com.project.personal.domain.model.CitySearchResults

class SearchViewModelMapper {
    fun mapSearchResults(data: CitySearchResults): SearchItemsViewModel {
        val result = ArrayList<SearchItemViewModel>()
        data.citySearchResults.forEach {
            result.add(SearchItemViewModel(it.title, it.woeid))
        }
        return SearchItemsViewModel(result)
    }


}