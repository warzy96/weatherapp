package com.project.personal.weatherapp.di.application.module

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.project.personal.data.network.client.WeatherClient
import com.project.personal.data.network.configuration.Urls
import com.project.personal.data.network.mappers.WeatherMapper
import com.project.personal.data.network.model.ApiCities
import com.project.personal.data.network.service.SearchResultDeserializer
import com.project.personal.data.network.service.WeatherService
import com.project.personal.data.repository.WeatherRepositoryImpl
import com.project.personal.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Urls.RETROFIT_BASE_URL)
                .addConverterFactory(provideGsonConverter())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }

    private fun provideGsonConverter(): GsonConverterFactory {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(ApiCities::class.java, SearchResultDeserializer())

        return GsonConverterFactory.create(builder.create())
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherClient: WeatherClient): WeatherRepository {
        return WeatherRepositoryImpl(weatherClient)
    }

    @Provides
    @Singleton
    fun provideWeatherClient(weatherService: WeatherService): WeatherClient {
        return WeatherClient(weatherService)
    }

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherMapper(): WeatherMapper {
        return WeatherMapper()
    }
}