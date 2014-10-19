package com.ariadnethread.datacenter.usercontent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ariadnethread.datacenter.cache.MemCacheManager;
import com.ariadnethread.datacenter.usercontent.domain.UserContent;

@Service
public class UserContentInfoImpl implements UserContentInfo {

	@Autowired
	private UserContentService userContentService;
	
	@Override
	public UserContent getUserContentBySessionId(String sessionId) {
		return userContentService.searchBySessionId(sessionId);
	}
	
	@Override
	public long getUserContentIdBySessionId(String sessionId){
		return userContentService.searchBySessionId(sessionId).getUid();
	}
	
	@Override
	public UserContent searchByUserId(String userId){
		return userContentService.searchByUserId(userId);
	}
	
	@Override
	public UserContent searchByUserIdOrSessionId(String userId,String sessionId){
		UserContent userContent=null;
		if(userId!=null && !"".equals(userId)){
			userContent = userContentService.searchByUserId(userId);
		}else if(sessionId!=null && !"".equals(sessionId)){
			userContent = userContentService.searchBySessionId(sessionId);
		}
		return userContent;
	}

}
