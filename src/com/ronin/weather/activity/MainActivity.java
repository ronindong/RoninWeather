package com.ronin.weather.activity;

import java.util.List;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.ronin.weather.R;
import com.ronin.weather.model.ProvinceBean;
import com.ronin.weather.model.WeatherInfo;
import com.ronin.weather.util.LoadUtil;

public class MainActivity extends BaseActivity {

	public static final String TAG = "MainActivity";
	private String mCityInfo = null;

	@Override
	protected void initLoadLayout() {
		setContentView(R.layout.activity_main);
		mCityInfo = getResources().getString(R.string.city_code_json);
		Log.i(TAG, "mCityInfo" + mCityInfo);
		List<ProvinceBean> parseArray = JSON.parseArray(mCityInfo,
				ProvinceBean.class);
		ProvinceBean bean = (ProvinceBean) parseArray.get(5);
		Log.i(TAG, bean.city.get(0).getCode() + ":" + bean.getProvince());

	}

	public void getWeatherInfo() {
		String url = "http://www.weather.com.cn/adat/sk/101010100.html";
		HttpUtils http = new HttpUtils();
		http.configTimeout(10000);
		http.configDefaultHttpCacheExpiry(3000);
		http.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> response) {
				Log.i(TAG, "response.result" + response.result);
				WeatherInfo bean = JSON.parseObject(response.result,
						WeatherInfo.class);
				Log.i(TAG, bean.getWeatherinfo().getCity());
				// List<Object> parseArray = JSON.parseArray(response.result,
				// new Class[] { WeatherInfo.class });

			}
		});
	}

	@Override
	protected void setFindViewById() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addListener() {
		// TODO Auto-generated method stub

	}

}
