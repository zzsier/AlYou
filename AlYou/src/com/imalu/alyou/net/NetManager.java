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
	//粉丝列表
	public static final int FANS_REQUEST_OPERATION=7;
	public static final int ADDFRIEND_FRIEND_REQUEST_OPERATION = 8;

	//修改个人设置
	public static final int UPDATE_USERDATA_REQUEST_OPERATION=9;
	
	public static final int GROUPS_REQUEST_OPERATION=10;
	public static final int GROUP_MEMBER_REQUEST_OPERATION=11;
	
	public static final int ADD_FRIEND_REQUEST_OPERATION=12;


	public static final String LOGIN_REQUEST_URL = "/service/ZhanghuServices.svc/Denglu";
	public static final String REGISTER_REQUEST_URL = "/service/ZhanghuServices.svc/Zhuce";
	public static final String MODIRY_USER_REQUEST_URL = "/service/GerenzhongxinServices.svc/InsetUsers";
	public static final String CONCERN_REQUEST_URL="/service/GerenzhongxinServices.svc/WoguanzhudeYonghuLiebiao";
	public static final String ADDRESS_LIST_REQUEST_URL="/service/LianxirenServices.svc/PhoneMailList";
	public static final String MY_FRIEND_URL = "/service/LianxirenServices.svc/HouQuAllFriends";
	public static final String FANS_REQUEST_URL="/service/GerenzhongxinServices.svc/GuanzhuwodeYonghuLiebiao";
	public static final String ADDFRIEND_FRIEND_REQUEST_URL = "/service/LianxirenServices.svc/SearchUser";
	public static final String UPDATE_USERDATA_REQUEST_URL="service/GerenzhongxinServices.svc/InsetUsers";
	public static final String GROUPS_REQUEST_URL="service/LianxirenServices.svc/GetForumInfo";
	public static final String GROUP_MEMBER__REQUEST_URL="service/LianxirenServices.svc/HouQuTaoLunZhuChengYuan";
	public static final String ADD_FRIEND_REQUEST_URL="service/LianxirenServices.svc/BecomeFriend";



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
		case FANS_REQUEST_OPERATION:
			return BASE_URL+FANS_REQUEST_URL;	
		case ADDFRIEND_FRIEND_REQUEST_OPERATION:
			return BASE_URL+ADDFRIEND_FRIEND_REQUEST_URL;
		case UPDATE_USERDATA_REQUEST_OPERATION:
			return BASE_URL+UPDATE_USERDATA_REQUEST_URL;
		case GROUPS_REQUEST_OPERATION:
			return BASE_URL+GROUPS_REQUEST_URL;
		case GROUP_MEMBER_REQUEST_OPERATION:
			return BASE_URL+GROUP_MEMBER__REQUEST_URL;
		case ADD_FRIEND_REQUEST_OPERATION:
			return BASE_URL+ADD_FRIEND_REQUEST_URL;
			
		default:
			return BASE_URL+LOGIN_REQUEST_URL;
		}
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

}
