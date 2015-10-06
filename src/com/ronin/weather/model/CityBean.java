package com.ronin.weather.model;

import java.io.Serializable;

public class CityBean implements Serializable {

	private String name;
	private String code;

	public CityBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
