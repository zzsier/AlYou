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
	//显示朋友圈相册内容
	public static final int PARSE_FRIEND_ALBUM = 26;
	//朋友心情并回复
	public static final int CIRCLR_OF_FRIEND = 27;
	//个人相册详细信息
	public static final int DETAILS_OF_ALBUM = 29;
	//上传图片
	public static final int UPLOAD_PICTURE_REQUEST_OPERATION=30;
	//发送心情
	public static final int SEND_MOOD_REQUEST_OPERATION=31;
	//发送验证码
	public static final int SEND_SMS_REQUEST_OPERATION=32;
	//评论心情
	public static final int  MOOD_COMMENT =33;
	//个性签名
	public static final int SIGNED_PERSONALITY=34;
	//点赞
	public static final int DIANZAN=35;

	//修改密码
	public static final int CHANGE_PWD_REQUEST_OPERATION=36;
	//重置密码
	public static final int  FORGET_PWD_REQUEST_OPERATION=37;

	//系统设置消息推送
	public static final int SYSTEM_SETTING_MSG_SENG = 40;
	//版本更新
	public static final int VERSION_UPDATE = 41;
	//好友验证
	public static final int VERIFY_FRIEND = 42;
	//评价
	public static final int ADVICE = 43;

	//删除
	public static final int SHANCHU=44;
	// 评论心情
	public static final int XINQING=45;



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
	//朋友圈相册
	public static final String PARSE_FRIEND_ALBUM_URL = "/service/PengyouquanServices.svc/Xinqing";
	private static AsyncHttpClient client = new AsyncHttpClient();
	//朋友心情并回复
	public static final String CIRCLR_OF_FRIEND_URL="/service/PengyouquanServices.svc/XinqingAndHuifu";
	//个人相册详细信息
	public static final String DETAILS_OF_ALBUM_URL = "/service/PengyouquanServices.svc/ChakanUserXingqing";
	//上传图片
	public static final String UPLOAD_PICTURE_REQUEST_URL="/service/CommonServices.svc/FileSc";
	//发送图片
	public static final String SEND_MOOD_REQUEST_URL="/service/PengyouquanServices.svc/SendMood";
	//发送验证码
	public static final String SEND_SMS_REQUEST_URL="service/ZhanghuServices.svc/SendPhoneMes";
	//评论心情
	public static final String  MOOD_COMMENT_URL ="/service/PengyouquanServices.svc/XinqingHuifuModels";
	//个性签名
	public static final String SIGNED_PERSONALITY_URL="/service/PengyouquanServices.svc/XiugaiGerenQianming";
	//点赞
	public static final String DIANZAN_URL="/service/PengyouquanServices.svc/DianzanXinqing";
	//修改密码
	public static final String CHANGE_PWD_REQUEST_URL="/service/GerenzhongxinServices.svc/UpdatePwd";
	//重置密码
	public static final String  FORGET_PWD_REQUEST_URL="/service/ZhanghuServices.svc/ResetPwd";

	//系统设置消息推送
	public static final String SYSTEM_SETTING_MSG_SENG_URL="/service/XitongServices.svc/XiugaiTuisong";
	//版本检查更新
	public static final String VERSION_UPDATE_URL="/service/XitongServices.svc/BanbenJiance";
	//好友验证
	public static final String VERIFY_FRIEND_URL="/service/ShezhiServices.svc/FriendValidate";
	//评价
	public static final String ADVICE_URL="/service/XitongServices.svc/Pingjia";

	//删除
	public static final String SHANCHU_URL="/service/PengyouquanServices.svc/ShanchuXinqing";
	// 评论心情
	public static final String  XINQING_URL="/service/PengyouquanServices.svc/PinglunXinqing";


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
		case UPLOAD_PICTURE_REQUEST_OPERATION:
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
		case  PARSE_FRIEND_ALBUM:
			return BASE_URL+PARSE_FRIEND_ALBUM_URL;	
		case  CIRCLR_OF_FRIEND:
			return BASE_URL+CIRCLR_OF_FRIEND_URL;	
		case  DETAILS_OF_ALBUM:
			return BASE_URL+DETAILS_OF_ALBUM_URL;	
		case  UPLOAD_PICTURE_REQUEST_OPERATION:
			return BASE_URL+UPLOAD_PICTURE_REQUEST_URL;	
		case  SEND_MOOD_REQUEST_OPERATION:
			return BASE_URL+SEND_MOOD_REQUEST_URL;	
		case  SEND_SMS_REQUEST_OPERATION:
			return BASE_URL+SEND_SMS_REQUEST_URL;	
		case  MOOD_COMMENT:
			return BASE_URL+MOOD_COMMENT_URL;	
		case  SIGNED_PERSONALITY:
			return BASE_URL+SIGNED_PERSONALITY_URL;	
		case  DIANZAN:
			return BASE_URL+DIANZAN_URL;	
		case CHANGE_PWD_REQUEST_OPERATION:
			return BASE_URL+CHANGE_PWD_REQUEST_URL;
		case FORGET_PWD_REQUEST_OPERATION:
			return BASE_URL+FORGET_PWD_REQUEST_URL;

		case  SYSTEM_SETTING_MSG_SENG:
			return BASE_URL+SYSTEM_SETTING_MSG_SENG_URL;	

		case  VERSION_UPDATE:
			return BASE_URL+VERSION_UPDATE_URL;	

		case  VERIFY_FRIEND:
			return BASE_URL+VERIFY_FRIEND_URL;	
		case  ADVICE:
			return BASE_URL+ADVICE_URL;

		case  SHANCHU:
			return BASE_URL+SHANCHU_URL;
		case  XINQING:
			return BASE_URL+XINQING_URL;




		default:
			return BASE_URL+LOGIN_REQUEST_URL;
		}
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

}
