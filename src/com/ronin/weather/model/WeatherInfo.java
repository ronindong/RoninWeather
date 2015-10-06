package com.ronin.weather.model;

import java.io.Serializable;

public class WeatherInfo implements Serializable {

	private WeatherInfoDetails weatherinfo = new WeatherInfoDetails();

	public WeatherInfoDetails getWeatherinfo() {
		return weatherinfo;
	}

	public void setWeatherinfo(WeatherInfoDetails weatherinfo) {
		this.weatherinfo = weatherinfo;
	}


	
}
