
package com.ariadnethread.register.dao.mybatis;

import com.ariadnethread.register.domain.UserWechatRelate;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface UserWechatRelateDao {
 
	/**
	 * 按微信号查询记录 
	 * @param wechatUid 微信号
	 * @return UserWechatRelate
	 */
	public UserWechatRelate searchByWechatUid(String wechatUid);
	
	/**
	 * 按手机号码查询记录
	 * @param mobile 手机号码
	 * @return UserWechatRelate
	 */
	public UserWechatRelate searchByMobile(String mobile);
	
	/**
	 * 按电话号码、微信号查询记录
	 * @param mobile 手机号码
	 * @param wechatUid 微信号
	 * @return UserWechatRelate
	 */
	public UserWechatRelate searchByMobileWechatUid(String mobile,String wechatUid);
	
	/**
	 * 保存记录
	 * @param userWechatRelate 对象
	 * @return void
	 */
	public void save(UserWechatRelate userWechatRelate);
	
}
