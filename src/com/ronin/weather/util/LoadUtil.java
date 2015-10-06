package com.ronin.weather.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.util.ByteArrayBuffer;

import com.ronin.weather.app.MyApplication;

import android.content.Context;

/**
 * @Description 加载数据工具栏
 * @Filename LoadUtil.java
 * @author dhl
 * @Date 2015年10月4日 下午10:21:43
 */
public final class LoadUtil {

	
	/**
	 * 按行读取txt
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	private static String readTextFromSDcard(InputStream is) throws Exception {
	    InputStreamReader reader = new InputStreamReader(is);
	    BufferedReader bufferedReader = new BufferedReader(reader);
	    StringBuffer buffer = new StringBuffer("");
	    String str;
	    while ((str = bufferedReader.readLine()) != null) {
	        buffer.append(str);
	        buffer.append("\n");
	    }
	    return buffer.toString();
	}
	
	public static final String loadProvData() {
		InputStream is = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			is = MyApplication.getContext().getAssets()
					.open("citycode.json.txt");
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is) {
					is.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bos.toString();
	}

}
