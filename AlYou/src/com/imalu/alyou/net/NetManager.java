package com.imalu.alyou.net;

public class NetManager {
	
	private static final String BASE_URL = "http://114.112.98.148/";
	
	public static final int LOGIN_REQUEST_OPERATION = 1;
	public static final int REGISTER_REQUEST_OPERATION = 2;
	
	
	public static final String LOGIN_REQUEST_URL = "/service/ZhanghuServices.svc/Denglu";
	public static final String REGISTER_REQUEST_URL = "/service/ZhanghuServices.svc/Zhuce";
	
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	public static void get(int operationCode, NetObject netObj, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams(netObj.getParams());
		client.get(getUrl(operationCode), params, responseHandler);
	}
	
	public static void post(int operationCode, NetObject netObj, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams(netObj.getParams());
		params.setUseJsonStreamer(true);
		client.post(getUrl(operationCode), params, responseHandler);
	}
	
	
	public static void execute(int operationCode, NetObject netObj, AsyncHttpResponseHandler responseHandler) {
		switch(operationCode)
		{
			case LOGIN_REQUEST_OPERATION:
				NetManager.post(operationCode, netObj, responseHandler);
			case REGISTER_REQUEST_OPERATION:
				NetManager.post(operationCode, netObj, responseHandler);
			default:
				NetManager.get(operationCode, netObj, responseHandler);
		}
	}
	
	public static String getUrl(int operationCode)
	{
		switch(operationCode)
		{
			case LOGIN_REQUEST_OPERATION:
				return BASE_URL+LOGIN_REQUEST_URL;
			case REGISTER_REQUEST_OPERATION:
				return BASE_URL+REGISTER_REQUEST_URL;
			default:
				return BASE_URL+LOGIN_REQUEST_URL;
		}
	}
	
	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
	

}
