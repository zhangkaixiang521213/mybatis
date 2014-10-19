package com.ariadnethread.register.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterConstants {
	/**
	 * 手机验证码过期时长（分钟）
	 */
	public static int  MOBILE_CODE_EXPIRE = 10;
	/**
	 * 用户登陆有效时间
	 */
	public static long  SESSION_VALIDATE_TIMES = 7*24*60*60*1000;
	
	public static Pattern mobilePattern = Pattern.compile("^1[3-8]\\d{9}$");
	
	public  static void main(String[] args){
		System.out.println(checkMobilePhone("1920047521"));
	}
	
	/**
	 * 验证手机号码是否正确
	 * @param email
	 * @return
	 */
	public static boolean checkMobilePhone (String mobile) {
		if (mobile == null || mobile.length() < 10) {
			return false;
		}
		try {
			Matcher matcher = mobilePattern.matcher(mobile);
			return matcher.matches();
		} catch (Exception e) {

		}
		return false;
	}
}
