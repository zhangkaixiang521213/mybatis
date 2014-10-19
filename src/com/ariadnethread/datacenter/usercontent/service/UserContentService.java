
package com.ariadnethread.datacenter.usercontent.service;

import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariadnethread.datacenter.cache.MemCacheManager;
import com.ariadnethread.datacenter.usercontent.dao.mybatis.UserContentDao;
import com.ariadnethread.datacenter.usercontent.domain.UserContent;
import com.ariadnethread.datacenter.usercontent.utils.Config;
import com.ariadnethread.utils.MD5;
import com.ariadnethread.utils.SMSSenderContants;
import com.ariadnethread.utils.SyncHttpRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class UserContentService {

	private static Logger logger = LoggerFactory.getLogger(UserContentService.class);

	@Autowired
	private UserContentDao userContentDao;
	
	/**
	 * 按用户名（账号）查询记录
	 * @param userName 用户账号
	 * @return UserContent
	 */
	public UserContent searchByUserName(String userName){
		
		UserContent userContent=(UserContent)MemCacheManager.getInstance().getCacheForKey("user_userName_"+userName);
		if(userContent==null){
			userContent=userContentDao.searchByUserName(userName);
			MemCacheManager.getInstance().setCacheForKey("user_userName_"+userName, userContent, 60*24);
		}
		return userContent;
	}
	
	/**
	 * 按SESSION_ID查询记录
	 * @param sessionId 登陆会话ID
	 * @return UserContent
	 */
	public UserContent searchBySessionId(String sessionId){
		UserContent userContent=(UserContent)MemCacheManager.getInstance().getCacheForKey("user_sessionId_"+sessionId);
		if(userContent==null){
			userContent=userContentDao.searchBySessionId(sessionId);
			MemCacheManager.getInstance().setCacheForKey("user_sessionId_"+sessionId, userContent, 60*24);
		}
		return userContent;
	}
	
	/**
	 * 用户ID查询记录
	 * @param userId 用户ID
	 * @return UserContent
	 */
	public UserContent searchByUserId(String userId){
		UserContent userContent=(UserContent)MemCacheManager.getInstance().getCacheForKey("user_userId_"+userId);
		if(userContent==null){
			userContent=userContentDao.searchByUserId(userId);
			MemCacheManager.getInstance().setCacheForKey("user_userId_"+userId, userContent, 60*24);
		}
//		return userContent;
		return userContentDao.searchByUserId(userId);
	}
	
	/**
	 * 修改记录
	 * @param userName 用户账号
	 * @return UserContent
	 */
	public void update(UserContent userContent){
		userContentDao.update(userContent);
	}
	
	/**
	 * 保存记录
	 * @param UserContent 用户对象
	 * @return void
	 */
	public void save(UserContent userContent){
		userContentDao.save(userContent);
	}
	
	/**
	 * 用户注册(调用户中心接口)
	 * @throws Exception 
	 * */
	public int registerUserToUserCenter (String userName, String password) throws Exception{
		String info = Config.getInstance().userCenterCode() + 
				userName + password + Config.getInstance().userCenterKey();
		String myenc = MD5.getMD5(info); 
		
		StringBuilder sb = new StringBuilder();
		sb.append(Config.getInstance().userCenterUrl())
		  .append("action=user_register&mobile=")
		  .append(SMSSenderContants.urlEncode(userName, Config.USER_CENTER_ENCODING))
		  .append("&password=").append(password)
		  .append("&app_code=").append(Config.getInstance().userCenterCode())
		  .append("&enc=").append(myenc);
		
		logger.info(sb.toString());
		String content = SyncHttpRequest.getStringFromURL(sb.toString(), 30);
		
		//{"action":"user_register_response","error":"register success.","status":0}
		ObjectMapper mapper = new ObjectMapper();
        JsonNode jn=mapper.readTree(content);
        
		return jn.get("status").asInt();
		/*
		if(status == -1) {
			logger.info("用户已存在");
			return true;
			//throw new Exception("用户已存在");
		} else if (ret == -2) {
			logger.info("用户测试失败");
			return false;
			//throw new Exception("用户测试失败");
		} else if (ret == 0) {
			return true;
		}
		return false;
		*/
	}
	
}
