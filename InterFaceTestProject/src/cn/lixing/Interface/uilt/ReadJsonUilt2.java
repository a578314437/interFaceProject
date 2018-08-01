package cn.lixing.Interface.uilt;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static cn.lixing.Interface.uilt.PropertiesDataUilt.*;

import java.io.IOException;

public class ReadJsonUilt2 {
	public static String getHttpJsonData(String requsetData,String requsetUrl) {
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType=MediaType.parse(getPropertiesData("Content-Type"));
		RequestBody body =RequestBody.create(mediaType,requsetData);
		String responseData=null;
		Request requset= new Request.Builder()
				.url(requsetUrl)
				.post(body)
				.addHeader(getPropertiesData("cacheHeaderKey"), getPropertiesData("cacheHeaderValue"))
				.addHeader(getPropertiesData("tokenHeaderKey"), getPropertiesData("tokenHeaderValue"))
				.addHeader("Cookie","JSESSIONID=2E5B15B6F279FDD471A7A06CBAA29B17; userPwd=123456; userName=admin" )
				.addHeader("Accept","application/json, text/plain, */*")
				.build();
		
		try {
			Response response = client.newCall(requset).execute();
			responseData=response.body().string();
			
		} catch (IOException e) {
			getHttpJsonData(requsetData,requsetUrl);
			e.printStackTrace();
		}
		return responseData;
	}
	
}
