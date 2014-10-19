package test.register;

import java.io.IOException;

import com.ariadnethread.utils.SyncHttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class WeiXinLogin {
	
	public static String WEIXINSERVER="https://api.weixin.qq.com/sns/userinfo?";
		
	public void checkLogin(String openId, String accessToken, String kkzAppCode){
		
		try
		{
			JsonNode ret = wxLogin(accessToken, openId);
			if (ret != null && !ret.get("openid").asText().equals("")) {
				System.out.println(ret.get("nickname").asText());
				System.out.println(ret.get("headimgurl").asText());
				System.out.println(ret.get("sex").asText());
				/*
				user = new UserBean();
				user.setUser_id(openId + "_weixin");
				user.setNick_name(ret.getString("nickname"));
				user.setImage_big("");
				user.setImage_small("");
				user.setHead_img(ret.getString("headimgurl"));
				user.setSex(ret.getString("sex"));
				*/
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取微信用户信息
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * */
	
	private JsonNode wxLogin(String token,  String openId) throws JsonProcessingException, IOException{
		StringBuffer sb = new StringBuffer();
		sb.append(WEIXINSERVER);
		sb.append("access_token="+ token);
		sb.append("&");
		sb.append("openid="+openId);
		
		System.out.println(sb.toString());
		String response = SyncHttpRequest.getStringFromURL(sb.toString(), 30);
		//String response = HttpUtil.getContentByUrl(sb.toString());
		System.out.println(response);
		
		if (response != null) {
			ObjectMapper mapper = new ObjectMapper();
	        JsonNode jn=mapper.readTree(response);
	        
	        //System.out.println(jn.get("status").asText());
			//return JSONObject.fromObject(response);
			
			return jn;
		} else {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		new WeiXinLogin().checkLogin(
				"oTQeEjtNH4omJuZlxUXXwp0-Hf_o", 
				"OezXcEiiBSKSxW0eoylIeH-7uuqEmTMpUn-IPTPChunawgRRGTl9gfCl1JPBHr0F05gFgU6G4Q3UXCJSiuq2oXEwfoK1LfxbkvpNsNEJrzL8CqJ01tUClayw9Ncu9HSQ-HACXTwfKNcR4KhDSI0sMQ", 
				"100100101");
		
//		System.out.println(bean);
		
	}
	
	
}
