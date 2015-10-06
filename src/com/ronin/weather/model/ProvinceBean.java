package com.ronin.weather.model;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class ProvinceBean implements Serializable {

	/**
	 * 
	 */
	private String province;
	private String data;

	// private String city;
	public ArrayList<CityBean> city = new ArrayList<CityBean>();

	public ProvinceBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	// public String getCity() {
	// return city;
	// }
	//
	// public void setCity(String city) {
	// this.city = city;
	// }

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
