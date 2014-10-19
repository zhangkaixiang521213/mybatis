package com.ariadnethread.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ariadnethread.exception.WeixinInvokeException;
import com.ariadnethread.register.service.PhoneRegisterImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class WeiXinLogin {
	private static Logger logger = LoggerFactory.getLogger(PhoneRegisterImpl.class);
	public static String WEIXINSERVER="https://api.weixin.qq.com/sns/userinfo?";
		
	public static WeiXinUserBean checkLogin(String openId, String accessToken) throws Exception{
		WeiXinUserBean wxu=new WeiXinUserBean();
		try
		{
			JsonNode jsonNode = wxLogin(accessToken, openId);
			if (jsonNode != null && !jsonNode.get("openid").asText().equals("")) {
				
				wxu.setHeadImgUrl(jsonNode.get("headimgurl").asText());
				wxu.setNickName(jsonNode.get("nickname").asText());
				wxu.setSex(jsonNode.get("sex").asText());
				wxu.setWechatUid(jsonNode.get("openid").asText());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e+"");
			throw new WeixinInvokeException("微信接口调用出错");
		}
		return wxu;
	}
	
	
	/**
	 * 获取微信用户信息
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * */
	
	private static JsonNode wxLogin(String token,  String openId) throws JsonProcessingException, IOException{
		StringBuffer sb = new StringBuffer();
		sb.append(WEIXINSERVER);
		sb.append("access_token="+ token);
		sb.append("&");
		sb.append("openid="+openId);
		
		logger.info(sb.toString());
		String response = SyncHttpRequest.getStringFromURL(sb.toString(), 30);
		logger.info(response);
		
		if (response != null) {
			ObjectMapper mapper = new ObjectMapper();
	        JsonNode jn=mapper.readTree(response);
	        
			return jn;
		} else {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		try {
			new WeiXinLogin().checkLogin(
					"oTQeEjtNH4omJuZlxUXXwp0-Hf_o", 
					"OezXcEiiBSKSxW0eoylIeH-7uuqEmTMpUn-IPTPChunawgRRGTl9gfCl1JPBHr0F05gFgU6G4Q3UXCJSiuq2oXEwfoK1LfxbkvpNsNEJrzL8CqJ01tUClayw9Ncu9HSQ-HACXTwfKNcR4KhDSI0sMQ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(bean);
		
	}
	
	
}
