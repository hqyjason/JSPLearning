package android.jsp.com.util;

import android.os.Build;

/**
 * <p> 全局配置类 </p>
 */
public interface Configuration {
	
	/** 设置是否打开系统通知 **/
	public static final boolean IS_OPEN_NOTICE = true;
	/** 摆渡车开关 **/
	public static final boolean IS_OPEN_FERRYCAR	= true;
	
	/**
	 * BRCY的IMEI验证服务器和软件更新地址
	 */
	// 2012-08-23 release V7 Boruicy Demo
	public static final String SERVER_URL_FOR_VERIFY_IMEI     = "http://m.boruicy.com/dds/imei/guangdong/guangzhousuitong";//"http://m.boruicy.com/dds/imei/beijing/demo";
	
	

	//软件更新提示地址
	/*  apk下载地址json   SERVER_URL_FOR_UPDATE_SOFTWARE*/
//	public static final String SERVER_URL_FOR_UPDATE_SOFTWARE = "http://101.200.90.137:20067/xks/update/android/";
	public static final String SERVER_URL_FOR_UPDATE_SOFTWARE = "http://123.57.81.32:20067/xks/update/android/";

	/*  判断是否下载  apk（包含下载地址）  */
	public static final String APK_URL = "http://101.200.90.137:20067/xks/update/android/XKSAndroid.apk";
	
	/*
	 * 主地址【正式新版平台：  http://txbd.xiakesong.cn/peisong/index.jsp  】
	 */
	//新建正式域名
//	public static final String ADDRESS_MAIN 			= "http://mobile.xiakesong.cn"; 
//	public static final String ADDRESS_MAIN 			= "http://123.57.81.32:20166/"; 
//	public static final String ADDRESS_MAIN 			= "http://123.57.176.45:20166"; 
//	public static final String ADDRESS_MAIN 			= "http://123.57.81.32:20056/"; 
//	public static final String ADDRESS_MAIN 			= "http://101.200.90.137:20066"; 
//	public static final String ADDRESS_MAIN 			= "http://123.57.176.45:20166/"; 
//	public static final String ADDRESS_MAIN 			= "http://test.xiakesong.cn/";  
//	public static final String ADDRESS_MAIN 			= "http://vpc2.xiakesong.cn/"; 
	 
	//原先正式域名
//	public static final String ADDRESS_MAIN 			= "http://txbd.xiakesong.cn"; 
//	public static final String ADDRESS_MAIN 			= "http://123.57.176.45:20066";
//	public static final String ADDRESS_MAIN 			= "http://101.200.90.137:20066";
	//正式域名https  2
//	public static final String ADDRESS_MAIN 			= "https://txbd.xiakesong.cn";
	//汤
//	public static final String ADDRESS_MAIN 			= "http://192.168.81.101:9080/client"; 
//	public static final String ADDRESS_MAIN 			= "http://192.168.81.100:9080/client"; 
	//宋测试 td-2
//	public static final String ADDRESS_MAIN 			= "http://192.168.1.163:9080/client"; 
	//魏测试 td-2
//	public static final String ADDRESS_MAIN 			= "http://192.168.1.176:9080/client"; 
	//赵测试	
//	public static final String ADDRESS_MAIN 			= "http://192.168.1.163:9080/client"; 
	//毛 td-2
//	public static final String ADDRESS_MAIN 			= "http://192.168.1.169:8080/client/"; 
//	public static final String ADDRESS_MAIN 			= "http://192.168.1.147:9080/client"; 
//	public static final String ADDRESS_MAIN 			= "http://192.168.81.112:9080/client";
	//任测试
//	public static final String ADDRESS_MAIN 			= "http://192.168.0.103:9080/client/";
	//测试 域名 现在用mobile		
//	public static final String ADDRESS_MAIN 			= "http://test.xiakesong.cn/mobile/"; 
//	public static final String ADDRESS_MAIN 			= "http://txbd.xiakesong.cn/"; 
	//同达在线
	public static final String ADDRESS_MAIN 			= "http://elearning.xiakesong.cn/manage"; 
	//众包测试
//	public static final String ADDRESS_MAIN 			= "http://test.xiakesong.cn/csms"; 	
	
//	public static final String ADDRESS_MAIN 			= "http://192.168.81.101:9080/client/"; 
	
	//public static final String ADDRESS_MAIN 			= "http://192.168.1.124:8080/client";
	
	//大连测试   //测试下单pc地址：  http://test.xiakesong.cn/customerpc/order.html
//	public static final String ADDRESS_MAIN 			= "http://123.57.81.32:20068/client"; 
	
	//北京测试   //测试下单pc地址：  http://test.xiakesong.cn/customerpc/order.html
//	public static final String ADDRESS_MAIN 			= "http://101.200.90.137:20066/client"; 
	//培训平台
//	public static final String TRAIN_ONLINE 			= "http://e-learning.xiakesong.cn"; 
	
	/** 用户登录 **/
	public static final String ADDRESS_LOGIN 			= "/user.login";
	
	/** 用户退出 **/
	public static final String ADDRESS_LOGOUT	 		= "/m/logout";
	
	/** 修改用户密码 **/
	public static final String ADDRESS_UPDATEPWD   		= "/m/setting/pwd";
	
	/** 百度  用户经纬度发送地址 **/
	public static final String ADDRESS_LOCATION 		= "/m/p";
	/** 高德   用户经纬度发送地址 **/
	public static final String ADDRESS_GD_LOCATION 		= "/mm/gaode";
	
	/** 切换设置快递员在线状态 **/
	public static final String ADDRESS_ONLINE_STATUS	= "/m/s";
	
	
	/** 账户信息 **/
	public static final String ADDRESS_MY_ACCOUNT       = "/m/driveraccount/index";
	
	/** 账户的收支明细信息 **/
	public static final String ADDRESS_ACCOUNT_DETAIL   = "/m/driveraccount/monthhistory";
	
	
	/** 获取考勤 **/
	public static final String ADDRESS_MY_ATTENDANCE	= "/m/driverattendance/index";
	
	/** 设置考勤为请假或取消请假 **/
	public static final String ADDRESS_ATTENDANCE_SET	= "/m/driverattendance/set";
	
	
	/** 我的订单（获取历史订单总数） **/
	public static final String ADDRESS_ORDER_COUNT		= "/m/order/driverorder";
	
	/** 我的订单（手动同步新订单和正在执行订单信息） **/
	public static final String ADDRESS_ORDER_SYN		= "/m/order/driverordersyn";
	
	/** 获取分页的月订单列表 **/
	public static final String ADDRESS_ORDER_TIME_DATA  = "/m/order/month";
	
	/** 接收新订单 【新订单中的抢单】**/
	public static final String ADDRESS_ORDER_RECEIVE	= "/m/neworder/receive";
	
	/** 拒收新订单 **/
	public static final String ADDRESS_ORDER_REJECT		= "/m/neworder/reject";
	
	/** 到达出发地点 **/
	public static final String ADDRESS_ORDER_ARRIVED	= "/m/order/arrived";
	
	/** 开始服务（出发） **/
	public static final String ADDRESS_ORDER_START		= "/m/order/start";
	
	/** 结束服务（到达目的地点） **/
	public static final String ADDRESS_ORDER_COMPLETE	= "/m/order/complete";
	
	/** 提交订单 **/
	public static final String ADDRESS_ORDER_SUBMIT 	= "/m/order/submit";
	
	/** 取消订单 **/
	public static final String ADDRESS_ORDER_CANCEL		= "/m/order/cancel";
	
	/** 订单详细信息 **/
	public static final String ADDRESS_ORDER_DETAIL 	= "/m/order/detail";
	
	
	/** 订单轨迹 **/
	public static final String ADDRESS_ORDER_TRACE      = "/m/order/trace";
	
	/** 摆渡车位置信息 **/
	public static final String ADDRESS_FERRYCAR 		= "/m/ferrycar/info";
	
	/** 通知通告 **/
	public static final String ADDRESS_NOTICE           = "/m/notice/info";
	
	/** 价格计算 **/
	public static final String ADDRESS_CALCULATE		= "/m/order/calculate";
	
	/** 快递员在线时长 **/
	public static final String ADDRESS_LINE_TIME		= "/m/driveronlinetime/info";
	
	/** 设备绑定 **/
	public static final String ADDRESS_BIND_DEVICES		= "/m/binddevice/info";
	
	/** 云呼叫的号码 **/
	public static final String ADDRESS_CLOUD_CALL 		= "/dds/cloudcall/info";
	
	/** 待接订单**/
	public static final String ADDRESS_ORDER_ASSIGN 	= "/m/order/assign";
	
	/** 上传图片 **/
	public static final String ADDRESS_ORDER_CAMERA 	= "/m/order/receive";
	
	/** 价格表 **/
	public static final String ADDRESS_ORDER_AUTOPRICING 	= "/dds/autopricing/mobile";
	
	/** 二次等候 **/
	public static final String ADDRESS_ORDER_WAITING        = "/m/order/midwaywaiting";
	
	/** 显示当前登录快递员用户的提成账户信息 **/
	public static final String ADDRESS_DRIVERDEDUCT_ACCOUNT = "/m/driverdeductaccount/index";
	
	/**显示当前登录快递员用户的账户提成交易历史记录**/
	public static final String ADDRESS_DRIVERDEDUCT_MONTHHISTORY = "/m/driverdeductaccount/monthhistory";
	
	public static final String GETYZM = "/dds/driver/voice/2/edit";
	
	
	/** 提现明细接口  */
	public static final String BALANCE_MONEY="/m/driverApplyAccount/applyAccount/index";
	
	
	/**配送员  在线、休息、收工 状态*/
	public static final String DRIVER_STATE="/m/driverLoginStatus/controlStatus";
	
	/** 侠刻值的规则*/
	public static final String XKZ_RULE="/m/driverXkzRule/info";
	
	/** 侠刻值的明细*/
//	public static final String XKZ_DETAILS="/m/driverXkzHistory/info/pageNo=1";
	public static final String XKZ_DETAILS="/m/driverXkzHistory/info";
		
	/**分享*/
	public static final String SHARE_FRIEND = "/m/tpromocode/info";
	
	
	/**上传绑定channelId*/
	public  static final String BUND_CHANNEL_ID = "/m/tchanelId/tbind";
	
	/**驻店订单*/
	public  static final String RESIDENT_ORDERS = "/m/order/assignZd";
	
	/**抽奖订单的接口*/
	public  static final String AWARD_ORDERS = "/m/order/assignAm";
	
	/**抽奖历史订单的分享内容*/
	public  static final String AWARD_ORDERS_SHARE = "/m/order/winprizeShare";
	
	
	/**是否补单的接口*/
	public  static final String IS_OR_NO_RESUPPLY = "/m/tmore/driverCanBd";
	
	
	/**补单的接口  【前、后】*/
	public  static final String RESUPPLY_ORDER  = "/m/order/submitNewOrderInfo";
	
	/** 新增订单信息 **/
//	public static final String ADDRESS_ORDER_NEW 		= "/m/order/submitNewOrderInfo";
	
	
	
	/**获取店家地址信息【驻店的补单】*/
	public  static final String SHOP_ADDRESS  = "/m/tmore/driverGetCustomer";
	
	/**签到功能*/
	public  static final String SIGN_IN  = "/m/driverSignIn/driverSignIn";
	
	/**选择配送员所属门店  +  上传选择所属门店*/
	public  static final String SELECT_SHOP  = "/m/tmore/driverSelectCustomer";	
	
	/**配送员签到历史查询*/
	public  static final String SIGN_IN_DETAILS  = "/dds/record/attendReport/check";
	
	/**配送员个人资料*/
	public  static final String PERSON_DEATILS  = "/m/perdata/driverInfoPersonData";
	
	/**历史单量数*/
	public  static final String HISTORY_AMOUNT  = "/m/orderCount/count";
	
	/**督导定位*/
	public  static final String SUPERVISOR_LOCATION  = "/m/supervisorLocation/location";
	
	/**组长 填  单量、工时*/
	public  static final String WRITE_HOUR_NUM  = "/m/leaderBD/BD";
	
	
	/**账单明细*/
	public  static final String ACCOUNT_DETAILS  = "/m/MonthSalary/MS";
	
	/**扫一扫——门店二维码*/
	public  static final String SCAN_CODE  = "/m/scanningTwoDimensionCode/createOrder";
	
	/**判断是否有海报活动*/
	public  static final String JUDGE_PLAYBILL  = "/m/driveractivity/playbill";
	
	/**岗位晋级*/
	public  static final String POST_PROMOTION_WEB  = "/promotion/index.html";
	
	
	/**请求注册*/
	public  static final String REGISTER_START  = "/m/tregistinit/info";
	
	/**注册提交*/
	public  static final String REGISTER_SUBMIT  = "/m/onlineregister/info";
	
	/**驻店订单数量*/
	public  static final String ORDER_REGISTER_NUM  = "/m/order/executingOrdersZd";
	
	/**考试*/
	public  static final String JOIN_EXAM = "/m/wenjuan/getEpaper";
	
	/**找回密码短信验证*/
	public  static final String FIND_PASS_CODE = "/m/setting/findpwd";
	
	/**实时监控*/
	public  static final String ORDER_MONITOR  = "/m/ordersControl/showOrders";
	
	/**实时监控详情*/
	public  static final String ORDER_MONITOR_SHOP  = "/m/ordersControl/showOrders";
	
	/**单量录入 加入订单编号*/
	public  static final String ORDER_NO_IN  = "/m/order/orderInputing";
	
	/**转单*/
	public  static final String ORDER_REDEPLOY  = "/m/order/driverorder";
	
//	/**获取骑手ID*/
//	public  static final String DRIVER_ID  = "/m/findDriverId/findDriverInfo";
	
	/**考勤 选择 骑手班表*/
	public  static final String SCHEDULE_DRIVER  = "/dds/driverWorkTimes/app";
	
	/**考勤 骑手日历班表*/
	public  static final String SCHEDULE_CALENDAR  = "/dds/driverWorkTimes/calendar";
	
	/**考勤 选择  周几弹出签到详情*/
	public  static final String SCHEDULE_SHOP_SIGN  = "/dds/driverWorkTimes/sign";
	
	/**运营分析*/
	public  static final String OPERATE_ANALYZE  = "/m/operation/order";
	
	/**KPI录入*/
	public  static final String WRITE_KPI_DATA  = "/m/leaderPG/info";
	
	/**考勤地图坐标*/
	public  static final String SCHEDULE_COORDINATE = "/m/attendceRecord/info";
	
	/**门店服务指标*/
	public  static final String COFIRM_CUSTOMER_KPI = "/m/leaderPG/cus";
	
	/**考勤 请假 设置骑手请假类别*/
	public  static final String SCHEDULE_DRIVER_LEAVE = "/m/askForLeave/info";
	
	/**组长 查看门店工时单量*/
	public  static final String SEARCH_CUSTOMER_DATA  = "/m/leaderBD/BD/new";
	
	/**个人数据*/
	public  static final String PERSONAL_DATA  = "/m/viewWorkHours/info";
	
	/**历史数据*/
	public  static final String HISTROY_DATA  = "/m/viewHistoryData/info";
	
	/**实时监控城市和品牌*/
	public  static final String MONITOR_DATA  = "/m/viewDriverCityAndBrand/info";
	
	/**每日考勤*/
	public  static final String DAILY_ATTEND  = "/m/driverAttendanceException/info";
	
	/**返店*/
	public  static final String BACK_SHOP  = "/m/record/service/switchover";
	
	/**地图提示距离*/
	public  static final String NOTICE_DISTANCE  = "/m/locating/check";
	
	/**获取手机信息*/
	public  static final String MOBILE_INFO  = "/m/information/collection";
	
	/**获取问题单原因列表*/
	public  static final String GET_ISSUE_REASON  = "/m/issueOrders/operation";
	
	/**设置订单为问题单*/
	public  static final String SET_ISSUE_REASON  = "/m/issueOrders/operation";
	
	/**获取问题单列表*/
	public  static final String GET_ISSUE_ORDER  = "/m/issueOrders/query";
	
	/**获取订单餐品*/
	public  static final String GET_ORDER_DETAILS  = "/dds/dispatchcenter/orderDetails";
	
	/**调度获取门店订单*/
	public  static final String GET_CUSTOMER_ORDER = "/m/dispatch/unassign";
	
	/**调度获取骑手列表*/
	public  static final String GET_CUSTOMER_DRIVER = "/m/dispatch/driver";
		
	/**调度指派骑手*/
	public  static final String PUSH_ORDER_TO_DRIVER = "/m/dispatch/designate";
	
	/**调度退回订单*/
	public  static final String DO_BACK_PRDER = "/m/dispatch/doBackOrder";
	
	/**定向转单*/
	public  static final String TRANS_DRIVER_DATA ="/m/order/orientatedTurn"; 
	
	/**获取本月取消订单*/
	public static final String  GET_CANCEL_ORDER_MONTH = "/m/order/historyCancel";
	
	/**离店操作*/
	public static final String  LEAVE_CUSTOMER ="/m/order/leaveCus";
	
}

