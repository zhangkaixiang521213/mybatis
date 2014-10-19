package core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.utils.id.UUIDHexGenerator;

/**
 *<p>Title: 常用工具类</p>
 * <p>Description: 常用工具类</p>
 * <p>Copyriht: Copyright (c) 2012</p>
 * <p>Company: XXXX Co., Ltd</p>
 * @author wangxz
 * @version 1.0 Date: 2012-11-27 15:42
 * (non-javadoc)
 */
public class ComUtil {
	private static final Log log = LogFactory.getLog(ComUtil.class);
    private static final String DATE_STYLE = "yyyy-MM-dd";
    private static final String TIME_STYLE = "yyyy-MM-dd HH:mm:ss";
    
    public static String EMPTY = "";
    public static String SEMICOLON = ";";
    
    /**
     * 取得不重复的32位字符串
     * @return
     */
    public static String getId() {
        UUIDHexGenerator uuid = new UUIDHexGenerator();
        return (String) uuid.generate();
    }
    /**
     * null字符串转化为""
     * @param s 输入字符串
     * @return 输出字符串
     */
    public static String replaceNull2Space(String s) {
        if (s == null)
            return "";
        if (s.trim().toUpperCase().equals("NULL"))
            return "";
        return s.trim();
    }
    /**
     * 日期转字符串
     * @param dt 日期类型
     * @return yyyy-MM-dd类型
     */
    public static String Date2String(Date dt) {
        if (dt == null || dt.equals(""))
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STYLE);
        try {
            return sdf.format(dt);
        } catch (Exception ex) {
            log.error("==ComUtil:Date2String==：" + ex);
            return "";
        }
    }
    
    /**
     * 日期转字符串
     * @param dt 日期类型
     * @return yyyy-MM-dd HH:mm:ss类型
     */
    public static String Date2StringHMS(Date dt) {
        if (dt == null || dt.equals(""))
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_STYLE);
        try {
            return sdf.format(dt);
        } catch (Exception ex) {
            log.error("==ComUtil:Date2String==：" + ex);
            return "";
        }
    }
    
    /**
     * 日期转yyyy-MM-dd HH:mm:ss类型格式
     * @param dt 日期类型
     * @return yyyy-MM-dd HH:mm:ss类型
     */
    public static Date Date2YMD(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_STYLE);
        try {
            return String2Date(sdf.format(dt));
        } catch (Exception ex) {
            log.error("==ComUtil:Date2YMD==：" + ex);
            return null;
        }
    }
    
    /**
     * 指定从当前日期后几天日期
     * @param days 指定天数
     * @return yyyy-MM-dd HH:mm:ss类型
     */
    public static Date Date2Days(int days) {
    	Date rdate = new Date();
		try {
			Date date = (new SimpleDateFormat(TIME_STYLE)).parse(ComUtil.Date2StringHMS(new Date()));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, days);
			rdate=cal.getTime();
		} catch (ParseException e) {
			log.error("==ComUtil:Date2Days==：" + e);
			e.printStackTrace();
		}
		return rdate;
    }
    
    /**
     * 指定指定日期后几天日期
     * @param days 指定天数
     * @return yyyy-MM-dd HH:mm:ss类型
     */
    public static Date Date2Days(Date adate,int days) {
    	Date rdate = new Date();
		try {
			Date date = (new SimpleDateFormat(TIME_STYLE)).parse(ComUtil.Date2String(adate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, days);
			rdate=cal.getTime();
		} catch (ParseException e) {
			log.error("==ComUtil:Date2Days==：" + e);
			e.printStackTrace();
		}
		return rdate;
    }
    
    /**
     * 指定分钟后的时间
     * @param date 参照时间
     * @param minute 分钟
     * @return yyyy-MM-dd类型
     */
    public static Date DatePlusMinute(Date date,float minute) {
    	Date rdate = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_STYLE);
			Float f = new Float(minute*60*1000);
		    rdate = new Date(date .getTime() + f.longValue());
		} catch (Exception e) {
			log.error("==ComUtil:DatePlusMinute==：" + e);
			e.printStackTrace();
		}
		return rdate;
    }
    
    /**
     * 时间转字符串
     * @param dt 日期类型
     * @return yyyy-MM-dd HH:mm:ss类型
     */
    public static String Time2String(Date dt) {
        if (dt == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_STYLE);
        try {
            return sdf.format(dt);
        } catch (Exception ex) {
            log.error("==ComUtil:Time2String==：" + ex);
            return "";
        }
    }
    
    /**
     * 时间转字符串
     * @param dt 日期类型
     * @return yyyy-MM-dd HH:mm:ss类型
     */
    public static String Time2StringYMDHMS(Date dt) {
        if (dt == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STYLE);
        try {
            return sdf.format(dt);
        } catch (Exception ex) {
            log.error("==ComUtil:Time2String==：" + ex);
            return "";
        }
    }
    
    
    
    /**
     * 字符串转时间
     * @param date String类型
     * @return Date
     */
    public static Date String2Time(String time) {
    	if("".equals(replaceNull2Space(time))){
    		return null;
    	}
        SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE);
        Date d = null;
        if (time != null && !time.equals("")) {
            try {
                d = format.parse(time);
            } catch (Exception ex) {
                log.error("==ComUtil:String2Time==：" + ex);
            }
        }
        return d;
    }
    
    
    /**
     * 字符串转时间(yyyy-MM-dd)
     * @param date String类型
     * @return Date
     */
    public static Date String2Date(String date) {
    	if("".equals(replaceNull2Space(date))){
    		return null;
    	}
        SimpleDateFormat format = new SimpleDateFormat(DATE_STYLE);
        Date d = null;
        if (date != null && !date.equals("")) {
            try {
                d = format.parse(date);
            } catch (Exception ex) {
                log.error("==ComUtil:String2Date==：" + ex);
            }
        }
        return d;
    }
    
	
    
    /**
     * 字符串中的汉字转化成16进制的ascii编码
     * @param s
     * @return
     */
    public static String str2HexAscii(String s) {
		String str = "";
		if (s == null || s.trim().equals(""))
			return str;
		for (int i = 0; i < s.length(); i++) {
			int ch;
			String s4;
			byte[] bytes = (String.valueOf(s.charAt(i))).getBytes();
			if (bytes.length == 1) { //英文字符不转化

				s4 = String.valueOf(s.charAt(i));
			} else {
				ch = (int) s.charAt(i);
				s4 = "\\u" + Integer.toHexString(ch);
			}
			str = str + s4;
		}
		return str;
	}  
    
	public static String getRequestPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}
	
	
	
	
	public static int getPageSize(String pageSize){
		return Integer.parseInt((pageSize == null || pageSize == "0") ? "20":pageSize); 
	}

	
	public static int getPageNo(String pageNo){
		return Integer.parseInt((pageNo == null || pageNo == "0") ? "1":pageNo); 
	}
}
