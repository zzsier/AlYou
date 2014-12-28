package com.imalu.alyou.net;

public class NetManager {
	
	private static final String BASE_URL = "http://114.112.98.148/";
	
	public static final int LOGIN_REQUEST_OPERATION = 1;
	public static final int REGISTER_REQUEST_OPERATION = 2;
	public static final int MODIFY_USER_REQUEST_OPERATION = 3;
	//显示我的关注列表
	public static final int  CONCERN_REQUEST_OPERATION=4;
	//手机通讯录
	public static final int ADDRESS_LIST_REQUEST_OPERATION=5;
	public static final int MY_FRIEND_OPERATION=6;
	
	public static final String LOGIN_REQUEST_URL = "/service/ZhanghuServices.svc/Denglu";
	public static final String REGISTER_REQUEST_URL = "/service/ZhanghuServices.svc/Zhuce";
	public static final String MODIRY_USER_REQUEST_URL = "/service/GerenzhongxinServices.svc/InsetUsers";
	public static final String CONCERN_REQUEST_URL="/service/GerenzhongxinServices.svc/WoguanzhudeYonghuLiebiao";
	public static final String ADDRESS_LIST_REQUEST_URL="/service/LianxirenServices.svc/PhoneMailList";
	public static final String MY_FRIEND_URL = "/service/LianxirenServices.svc/HouQuAllFriends";
	
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
			case MODIFY_USER_REQUEST_OPERATION:
				return BASE_URL+MODIRY_USER_REQUEST_URL;
			case CONCERN_REQUEST_OPERATION:
				return BASE_URL+CONCERN_REQUEST_URL;
			case ADDRESS_LIST_REQUEST_OPERATION:
				return BASE_URL+ADDRESS_LIST_REQUEST_URL;
			case MY_FRIEND_OPERATION:
				return BASE_URL+MY_FRIEND_URL;
				
			default:
				return BASE_URL+LOGIN_REQUEST_URL;
		}
	}
	
	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

}
