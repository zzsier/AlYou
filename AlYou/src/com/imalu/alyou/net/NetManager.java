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
	public static final int PERSONALINFO_REQUEST_OPERATION =15;

	//关注
	public static final int  CARE_ABOUT_CONCERN=13;
	//取消关注
	public static final int CANCEL_CARE_ABOUT_CONCERN=14;
	//热门公会
	public static final int POPULAR_ASSOCIATION_REQUEST_OPERATION=16;

	//关注公会
	public static final int CONCERN_ASSOCIATION_REQUEST_OPERATION=17;
	//绑定公会信息
	public static final int BINDING_ASSOCIATION_REQUEST_OPERATION=18;
	//所在公会
	public static final int RESIDENT_ASSOCIATION_REQUEST_OPERATION=19;

	//加入公会
	public static final int JOIN_SOCIATY_REQUEST_OPERATION=20;

	//退出公会
	public static final int OUT_SOCIATY_REQUEST_OPERATION=21;


	//公会搜索
		public static final int  ASSOCIATION_SEARCH=22;
		//公会绑定与解绑
		public static final int  BINDSOCIETY=23;
		//关注公会
		public static final int  CARE_ABOUT_CONCERN_SOCIETY =24;
		//取消关注公会
		public static final int  CANCEL_CARE_ABOUT_CONCERN_SOCIETY =25;









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

	public static final String PERSONALINFO_REQUEST_URL = "/service/GerenzhongxinServices.svc/ YonghuZiliaoOrShifouGuanzhu";
	//关注以及取消关注
	public static final String  CARE_ABOUT_CONCERN_URL="service/GerenzhongxinServices.svc/GuanzhuUser";
	public static final String  CANCEL_CARE_ABOUT_CONCERN_URL="service/GerenzhongxinServices.svc/QuxiaoguanzhuUser";
	public static final String POPULAR_ASSOCIATION_REQUEST_URL="service/GonghuiServices.svc/Gonghui";
	public static final String CONCERN_ASSOCIATION_REQUEST_URL="service/GonghuiTuisongServices.svc/GuanzhuGonghuiYe";
	public static final String	BINDING_ASSOCIATION_REQUEST_URL="service/GonghuiServices.svc/HuoQuGongHuiXinXiByKey";
	public static final String 	RESIDENT_ASSOCIATION_REQUEST_URL="service/GonghuiServices.svc/GetByMemberKey";
	public static final String JOIN_SOCIATY_REQUEST_URL="/service/GonghuiServices.svc/ApplySocietyInfo";
	public static final String OUT_SOCIATY_REQUEST_URL="/service/GerenzhongxinServices.svc/TuichuAndJiebangGonghui";
	
	//公会搜索
		public static final String ASSOCIATION_SEARCH_URL ="/service/LianxirenServices.svc/SearchSociety";
		//公会绑定与解绑
		public static final String BINDSOCIETY_URL ="/service/GerenzhongxinServices.svc/BindSociety";
		//公会关注
		public static final String  CARE_ABOUT_CONCERN_SOCIETY_URL="/service/GonghuiServices.svc/GuanzhuGonghui";
		//取消公会关注
		public static final String  CANCEL_CARE_ABOUT_CONCERN_SOCIETY_URL="/service/GonghuiServices.svc/QuxiaoGuanzhugonghui";
	
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
		case PERSONALINFO_REQUEST_OPERATION:
			return BASE_URL+PERSONALINFO_REQUEST_URL;
		case CARE_ABOUT_CONCERN :
			return BASE_URL+CARE_ABOUT_CONCERN_URL;
		case CANCEL_CARE_ABOUT_CONCERN:
			return BASE_URL+CANCEL_CARE_ABOUT_CONCERN_URL;
		case POPULAR_ASSOCIATION_REQUEST_OPERATION:
			return BASE_URL+POPULAR_ASSOCIATION_REQUEST_URL;
		case CONCERN_ASSOCIATION_REQUEST_OPERATION:
			return BASE_URL+CONCERN_ASSOCIATION_REQUEST_URL;
		case BINDING_ASSOCIATION_REQUEST_OPERATION:
			return BASE_URL+BINDING_ASSOCIATION_REQUEST_URL;
		case RESIDENT_ASSOCIATION_REQUEST_OPERATION:
			return BASE_URL+RESIDENT_ASSOCIATION_REQUEST_URL;
		case JOIN_SOCIATY_REQUEST_OPERATION:
			return BASE_URL+JOIN_SOCIATY_REQUEST_URL;
		case OUT_SOCIATY_REQUEST_OPERATION:
			return BASE_URL+OUT_SOCIATY_REQUEST_URL;
		case  ASSOCIATION_SEARCH:
			return BASE_URL+ASSOCIATION_SEARCH_URL;
		case  BINDSOCIETY:
			return BASE_URL+BINDSOCIETY_URL;	
		case  CARE_ABOUT_CONCERN_SOCIETY:
			return BASE_URL+CARE_ABOUT_CONCERN_SOCIETY_URL;	
		case  CANCEL_CARE_ABOUT_CONCERN_SOCIETY:
			return BASE_URL+CANCEL_CARE_ABOUT_CONCERN_SOCIETY_URL;	
		
		default:
			return BASE_URL+LOGIN_REQUEST_URL;
		}
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

}
