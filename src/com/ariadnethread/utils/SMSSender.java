package com.ariadnethread.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SMSSender {
	private static Logger logger = LoggerFactory.getLogger(SMSSender.class);
	
	/**
	 * 发送短信
	 * @param type 短信类型
	 * @param mobile 手机号
	 * @param msg 短信内容
	 */
    public static void sendSms(String type, String mobile, String msg) {
    	int i = 1;
    	int maxLength = Integer.MAX_VALUE;
		boolean addNum = SMSSenderContants.length(msg) > maxLength;

		if (SMSSenderContants.length(msg) > 500) {
			msg = "很抱歉您的短信过长，请致电抠电影客服查询" + SMSSenderContants.HOTLINE;
		}
		
    	while (SMSSenderContants.length(msg) > 0) {
			//get sub
    		String sub = null;
			
			if (SMSSenderContants.length(msg) > maxLength) {
				sub = msg.substring(0, maxLength -1);
			} else {
				sub = msg;
			}

			//send part
			String part = (addNum? "(" + i + ") ": "") + sub + "【抠电影】";
			
			//check words
			if (part.contains("警察")) {
				part = part.replace("警察", "警 察");
			}
			
	    	String url = "http://cf.lmobile.cn/submitdata/Service.asmx/g_Submit";
	    	String postData = "sname=dlbjzdwx&spwd=6xfGaG6W&scorpid=&sprdid=1012818&sdst=" + 
	    				mobile+"&smsg=" +SMSSenderContants.urlDecode(part,"utf-8");
	        
	    	logger.info(url + "?" +postData);
			String content = SyncHttpRequest.postStringToURL(url, postData, 30);
			logger.info(content);

			logger.info(
					"----\n" + url + "?" + postData + 
					"\n----\n" + content + "\n----");
			
			/*
			if (logged) {
				SMSLog log = new SMSLog(mobile, type, part, null);
				log.save();			
			}
			*/
			
			//renew message
			msg = msg.substring(SMSSenderContants.length(sub));
			i ++;
		}
    }

	

}