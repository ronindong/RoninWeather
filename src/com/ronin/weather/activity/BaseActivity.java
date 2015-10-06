package com.ronin.weather.activity;

import android.app.Activity;
import android.os.Bundle;

import com.lidroid.xutils.ViewUtils;
import com.ronin.weather.app.MyApplication;

public abstract class BaseActivity extends Activity {

	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivitys(this);
		initLoadLayout();
		setFindViewById();
		addListener();
	}

	protected abstract void initLoadLayout();
	protected abstract void setFindViewById();
	protected abstract void addListener();
}
