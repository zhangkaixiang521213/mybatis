package com.ariadnethread.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class SMSSenderContants {

	public final static String HOTLINE = "4000009666";
	
	public static int length (String string) {
		return string == null? 0: string.length();
	}
	
	public static String urlDecode (String source, String enc) {
		String decode = null;
		try {
			decode = URLDecoder.decode(source, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			
		}
		return decode;
	}
	
	/**
	 * 短信类型
	 */
	public enum SMSType{
		REGISTER("Register","Register"),
		RESET_PASSWORD("ResetPassword","ResetPassword"),
		ORDER_INFO("OrderInfo","OrderInfo"),
		SYSTEM("System","System"),
		RED_ENVOLOP("RedEnvolop","红包短信校验");
		
		private String value;
		private String desc;
		SMSType(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		public String value(){
			return this.value;
		}
		public String desc(){
			return this.desc;
		}
	}
	
	public static String urlEncode (String source, String enc) {
		if (source == null || enc == null) {
			return source;
		}
		String encode = null;
		try {
			encode = URLEncoder.encode(source, enc);
			encode = replace(encode, "+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			
		}
		return encode;
	}
	
	public static String replace(String strSource, String strFrom, String strTo) {
		if (strSource == null) { 
			return null;
		}
		
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString(); 
	    } 
	    return strSource;
	}
}
