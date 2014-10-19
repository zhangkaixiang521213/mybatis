package com.ariadnethread.datacenter.usercontent.service;

import com.ariadnethread.datacenter.usercontent.domain.UserContent;

public interface UserContentInfo {

	/**
	 * 根据登陆会话ID查询用户信息
	 * @param sessionId
	 * @return 用户信息对象
	 */
	public UserContent getUserContentBySessionId(String sessionId);
	
	/**
	 * 根据登陆会话ID查询用户信息
	 * @param sessionId
	 * @return 用户ID
	 */
	public long getUserContentIdBySessionId(String sessionId);

	/**
	 * 用户ID查询记录
	 * @param userId 用户ID
	 * @return UserContent
	 */
	public UserContent searchByUserId(String userId);
	
	/**
	 * 根据用户ID或会话ID查询记录
	 * @param userId 用户ID
	 * @param sessionId 会话ID
	 * @return UserContent
	 */
	public UserContent searchByUserIdOrSessionId(String userId,String sessionId);
}
