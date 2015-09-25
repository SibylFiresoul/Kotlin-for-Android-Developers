package com.antonioleiva.weatherapp.domain.mappers

import com.antonioleiva.weatherapp.data.server.Forecast
import com.antonioleiva.weatherapp.data.server.ForecastResult
import com.antonioleiva.weatherapp.domain.model.Forecast as ModelForecast
import com.antonioleiva.weatherapp.domain.model.ForecastList

class ForecastDataMapper {

    fun convertFromDataModel(zipCode: Long, forecast: ForecastResult): ForecastList = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast = with(forecast) {
        ModelForecast(dt * 1000, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}