package android.jsp.com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;





import com.google.gson.Gson;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * <p> 软件常用工具和常量 </p>
 *
 * @Package com.android.xks.mobile.util
 * @date 2011-12-9 上午09:49:57
 * @version 1.0
 */
public class ManagerUtil {

	private static String tag = "ManagerUtil";

	private static String IMEI = "";

	/** 登录或者修改密码状态：驻店登录成功 **/
	public static final int STATUS_SUCCESS 			= 1;
	/** 登录或者修改密码状态：众包登录成功 **/
	public static final int STATUS_SUCCESS_ZB		= 32;
	/** 登录或者修改密码状态：网络错误 **/
	public static final int STATUS_NET_FAIL 		= 2;
	/** 登录状态：用户不存在或者已经注销 **/
	public static final int STATUS_NOIN 			= 3;
	/** 登录状态：用户名或者密码错误 **/
	public static final int STATUS_NOMATCH 			= 4;
	/** 登录状态：访问地址错误 **/
	public static final int STATUS_ADDRESS_FAIL 	= 5;
	/** 登录状态：成功，但未通过考试 **/
	public static final int STATUS_SUCCESS_EXAM 	= 6;
	/** 登录状态：成功，但密码仍为原始密码123456 (更改)**/
	public static final int STATUS_CHANGE_PASS_7 	= 7;
	/** 禁止用户登录 **/
	public static final int STATUS_PROHIBIT_LOGIN 	= 74;
	/** 修改密码状态：旧密码错误 **/
	public static final int STATUS_OLDPWD_FAIL		= 3;
	/** 心跳检测时间  300000  5 分钟 **/
	public static final int HEARTBEAT_TIME		= 300000;//现在5分钟；30s---10s上传一次或8s
	/** 休息时长 **/
	public static final int REST_MINUTES		= 15*60*1000;//小休时间15分钟

	private static Gson gson = new Gson();

	/**
	 * 检查网络状态
	 * 
	 * @return true 网络正常 false 网络异常
	 */
	public static boolean checkNetState(Context mContext) {
		// 在已打开WiFi开关的情况下，当WiFi源设备未连接外部网络时网络仍为不可用状态，因此注释掉以下WiFi检测代码
		//		WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
		//		if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) { // wifi可用
		//			Log.d(tag, "wifi可用");
		//			return true;
		//		}

		// SIM卡已就绪的情况下，网络可能仍未连接上网络（恢复网络连接需要一定时间），因此注释掉以下SIM卡检测代码
		//		TelephonyManager mTelephonyManager = (TelephonyManager) mContext.getSystemService(Service.TELEPHONY_SERVICE);
		//		if (mTelephonyManager.getSimState() != TelephonyManager.SIM_STATE_READY) { // SIM卡没有就绪
		//			Log.d(tag, "SIM卡没有就绪");
		//			return false;
		//		}

		ConnectivityManager cManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable() && info.isConnected()) { // 已联网
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为wifi连接网络
	 */
	public static boolean isWifiConnected(Context mContext) {
		ConnectivityManager connManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);  
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
		return mWifi.isConnected();  
	}

	private static boolean isShowingGPSDialog = false;	

	/**
	 * <p> 根据给定日期判断是否需要验证IMEI号（判断日期是否相同：相同则不需要验证IMEI号，否则需要验证IMEI号） </p>
	 *
	 * @date 2011-12-13 下午05:00:59
	 * @param lastDate 给定日期（日期格式：yyyy-MM-dd）
	 * @return true-需要验证IMEI号；false-不需要验证IMEI号
	 */
	public static boolean isNeedValidateIMEI(String lastDate) {

		if (lastDate==null || lastDate.trim().length()==0) {
			// 没有日期，则需要验证IMEI号
			return true;
		}

		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		final String today = df.format(new Date());

		if (today.equals(lastDate)) {
			// 日期相同，表示不再需要验证IMEI（每天只验证一次IMEI号）
			return false;
		}

		return true;
	}

	/**
	 * <p> 获取IMEI号 </p>
	 * @date 2011-12-9 下午03:45:26
	 * @param mContext
	 * @return
	 */
	public static String getIMEI (Context mContext) {
		if (StringUtil.isNull(IMEI)) {
			IMEI = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		}
		return IMEI;
	}


	/**
	 * 解决ListView和ScrollView冲突的问题
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {

		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}


	/**
	 * 计算两个坐标点之间的距离
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
		float[] results = new float[1];
		Location.distanceBetween(lat1, lon1, lat2, lon2, results);
		return results[0];
	}

	/**
	 * 获取软件版本
	 * @return
	 */
	public static String getVersionCode(Context context) {
		int localVersionCode = 0;
		String version = "";
		try {
			localVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0 ).versionCode;
			version= localVersionCode+"";
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}

	private static double[] LON_STEPS = { 360D, 180D, 90.0D, 40.0D, 20.0D,
		10.0D, 5.0D, 2.0D, 1.0D, 0.5D, 0.2D, 0.1D, 0.05D, 0.02D, 0.01D,
		0.005D };
	private static double[] LAT_STEPS = { 360D, 180D, 90.0D, 32.0D, 16.0D,
		8.0D, 4.0D, 1.6D, 0.8D, 0.4D, 0.16D, 0.08000000000000002D,
		0.04000000000000001D, 0.016D, 0.008D, 0.004D };

	private static int IMAGE_WIDTH = 300;
	private static int IMAGE_HEIGHT = 300;

	/**
	 * 计算地图显示级别
	 * 
	 * @param width
	 *            地图宽度
	 * @param height
	 *            地图高度
	 * @param minLat
	 *            最小纬度
	 * @param minLon
	 *            最小经度
	 * @param maxLat
	 *            最大纬度
	 * @param maxLon
	 *            最大经度
	 * @return 地图显示级别
	 */
	public static int getLevel(int width, int height, double minLat,
			double minLon, double maxLat, double maxLon) {
		double unitLat = 1.1D * (maxLat - minLat) * IMAGE_HEIGHT / height;
		double unitLon = 1.1D * (maxLon - minLon) * IMAGE_WIDTH / width;
		int zmLat = getFitLevel(unitLat, LAT_STEPS);
		int zmLon = getFitLevel(unitLon, LON_STEPS);
		int zmFit = Math.min(zmLat, zmLon);
		return zmFit;
	}

	private static int getFitLevel(double dValue, double[] arrayLevels) {
		int iLevel = 1;
		int levelLen = (arrayLevels == null ? 0 : arrayLevels.length);
		while ((iLevel < levelLen) && (dValue <= arrayLevels[iLevel])) {
			++iLevel;
		}
		return (iLevel - 1);
	}

	/**
	 * 计算地图显示中心点经纬度
	 * 
	 * @param minLat
	 *            最小纬度
	 * @param minLon
	 *            最小经度
	 * @param maxLat
	 *            最大纬度
	 * @param maxLon
	 *            最大经度
	 * @return 中心点经纬度 double[lon,lat]
	 */
	public static int[] getCenter(int minLat, int minLon, int maxLat, int maxLon) {
		int centerlat = 0, centerlon = 0;
		if (minLat > 0 && minLon > 0 && maxLat > 0 && maxLon > 0) {
			centerlat = (minLat + maxLat) / 2;
			centerlon = (minLon + maxLon) / 2;
		}
		int[] center = { centerlon, centerlat };
		return center;
	}

	/*
	 * 把经纬度String分别转成double数组
	 */
	public static double[] getLngLtd(String ll){

		String [] s=ll.split(",");
		double[] num=new double[2];
		num[0]=Double.parseDouble(s[0]);
		num[1]=Double.parseDouble(s[1]);

		return num;
	}


	/**
	 * 将对象数据转换成json 字符串
	 * @param clazz
	 * @return
	 */
	public static String Bean2Json(Object clazz){

		String json="";
		try {
			json = gson.toJson(clazz);	
		} catch (Exception e) {
			e.printStackTrace();
		}


		return json;
	}


	/**
	 * 是否是合法的手机号
	 * @param context
	 * @return
	 */
	//	public static boolean isMobileNum(String mobiles) {
	//
	//		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	//		Matcher m = p.matcher(mobiles);
	//		return m.matches();
	//	} 


	/**
	 * 只允许数字和字母
	 * @param context
	 * @return
	 */
	public static String isNumLetter(String scanOrderId) throws PatternSyntaxException{

		// 只允许字母、数字    
		String   regEx  =  "[^a-zA-Z0-9]";                     
		Pattern   p   =   Pattern.compile(regEx);     
		Matcher   m   =   p.matcher(scanOrderId);

		return   m.replaceAll("").trim();  
	}


	/**
	 * 月份、当日【11--11； 8--08】
	 * 
	 */
	public static String zeroNum(int num) {

		String twoNum = "";

		if(num < 10 && num > -1){

			twoNum = "0"+num;
		}else{

			twoNum = ""+num;
		}

		return twoNum;
	} 



	/**
	 * 判断网络是否连接.
	 */
	public static  boolean isNetworkConnected(Context context ) {

		try {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			if (null != info && info.isConnected() && info.isAvailable()) {
				return true;
			}
		} catch (Exception e) {
			
//			Log.e("网络连接", "网络不可用");
			
			return false;
		}
		return false;

	}
	

    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
//    public static int getVersionCode(Context mContext) {
//        int versionCode = 0;
//        try {
//            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
//            versionCode = mContext.getPackageManager().
//                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return versionCode;
//    }
 
    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

}
