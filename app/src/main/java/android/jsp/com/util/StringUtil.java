package android.jsp.com.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	private static final String CHAR_SET_UTF8 = "UTF-8";
	
	/**
	 * <i> 去除字符串首尾的空格, 包括全角空格和半角空格 </i>
	 *
	 * @param source
	 * 			  	 指定的字符串
	 * @return	去除首尾空格后的字符串
	 */
	public static String trim(String source) { 
		if (isNull(source)) {
			return "";
		}

		int length = source.length();
		boolean hasStart = true, hasEnd = true;

		for (int i = 1; i <= length; i++) {
			if (hasStart && source.length() > 0) {
				char start = source.charAt(0);
				if (start == ' ' || start == '　') {
					source = source.substring(1, source.length());
				} else {
					hasStart = false;
				}
			}

			if (hasEnd && source.length() > 1) {
				char end = source.charAt(source.length() - 1);
				if (end == ' ' || end == '　') {
					source = source.substring(0, source.length() - 1);
				} else {
					hasEnd = false;
				}
			}

			if (!hasStart && !hasEnd) {
				break;
			}
		}
		return (source);
	}	
 
	/**
	 * <i> 判断输入字符串是否为空 </i>
	 *
	 * @param inputStr
	 * 				指定的字符串
	 * 
	 * @return	如果为空则返回true  如果不为空则返回false
	 */
	public static boolean isNull(Object inputStr){
		if(null == inputStr || "".equals(inputStr) || "null".equals(inputStr.toString().toLowerCase()))
			return true;
		return false;
	}
	
	/**
	 * <i> 判断输入字符串是否为空 </i>
	 *
	 * @param inputStr
	 * 				指定的字符串
	 * 
	 * @return	如果为空则返回true  如果不为空则返回false
	 */
	public static boolean isStringNull(String inputStr){
		
		if(inputStr.length() != 0 && !inputStr.equals("")  && !inputStr.isEmpty())
			return true;
		
		return false;
		
	}
	
	
	/**
	 *  判断传入电话号码 是否合法【新】
	 *
	 * @param phoneNumber		
	 * 							指定的字符串
	 * 
	 */
	public static boolean isPhoneNumberValid(String phoneNumber){  
		boolean isValid = false;
		if (!isNumberic(phoneNumber)) return isValid;
		if (phoneNumber.length() == 11 && phoneNumber.startsWith("1")) isValid = true;
		return isValid;
	}
	
	/**
	 * 判断是否为数字
	 * 
	 */
	public static boolean isNumberic(String str) {
		if (StringUtil.isNull(str))  return false;
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 格式化日期格式
	 * 
	 * @param format
	 *            显示的日期格式
	 * @param date
	 *            待显示的日期
	 * 
	 */
	public static String format(String format, Date date) {
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 字符串转换到时间格式
	 * @param dateStr 需要转换的字符串
	 * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException 转换异常
	 */
	public static Date stringToDate(String formatStr, String dateStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
		return date;
	} 
	
	/**
	 * 获取日期差，返回相差天数。
	 * @param startDate
	 * @param endDate
	 */
	public static long getCompareDate(String startDate,String endDate) throws ParseException {
	     SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	     Date date1=formatter.parse(startDate);    
	     Date date2=formatter.parse(endDate);
	     long l = date2.getTime() - date1.getTime();
	     long d = l/(24*60*60*1000);
	     return d;
	 }
	//通过上面的方法获得两个日期的天数差，然后用计算秒不就简单了？

	
	/**
	 * 将服务器响应内容进行UTF-8转码处理，并将转码后的字符串进行格式化处理，以符合JSON数据格式。
	 * 
	 * （格式化处理：删除{}前后可能存在的任意字符，删除服务器端响应时多余的 \ 符号）
	 * 
	 * @param responseContent
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String formatJSON(byte[] responseContent) throws UnsupportedEncodingException {
		String str = new String(responseContent, CHAR_SET_UTF8);
		str = formatJSON(str);
		return str;
	}
	
	/**
	 * 将指定的字符串进行格式化处理，以符合JSON数据格式。
	 * 
	 * （格式化处理：删除{}前后可能存在的任意字符，删除服务器端响应时多余的 \ 符号）
	 * 
	 * @param json
	 * @return
	 */
	public static String formatJSON(String json) {
		
		int idxLeft = json.indexOf("{");
		if (idxLeft>0) {
			json = json.substring(idxLeft);
		}
		
		int idxRight = json.lastIndexOf("}");
		if (idxRight>0) {
			json = json.substring(0, idxRight+1);
		}
		
		json = json.replace("\\\"", "\"");
		
		return json;
	}

}
