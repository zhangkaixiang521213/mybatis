
package com.ariadnethread.datacenter.usercontent.dao.mybatis;

import com.ariadnethread.datacenter.usercontent.domain.UserContent;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface UserContentDao {
 
	/**
	 * 按SESSION_ID查询记录
	 * @param sessionId 登陆会话ID
	 * @return UserContent
	 */
	public UserContent searchBySessionId(String sessionId);
	
	/**
	 * 按用户名（账号）查询记录
	 * @param userName 用户账号
	 * @return UserContent
	 */
	public UserContent searchByUserName(String userName);
	
	/**
	 * 修改记录
	 * @param userName 用户账号
	 * @return UserContent
	 */
	public void update(UserContent userContent);
	
	/**
	 * 用户ID查询记录
	 * @param userId 用户ID
	 * @return UserContent
	 */
	public UserContent searchByUserId(String userId);
	
	/**
	 * 保存记录
	 * @param UserContent 用户对象
	 * @return void
	 */
	public void save(UserContent userContent);
	
}
