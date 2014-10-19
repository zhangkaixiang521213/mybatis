
package com.ariadnethread.register.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariadnethread.register.dao.mybatis.UserWechatRelateDao;
import com.ariadnethread.register.domain.UserWechatRelate;

@Service
@Transactional
public class UserWechatRelateService {

	private static Logger logger = LoggerFactory.getLogger(UserWechatRelateService.class);

	@Autowired
	private UserWechatRelateDao userWechatRelateDao;
	
	
	/**
	 * 保存记录
	 * @param mobile :手机号
	 * @param wechatUid（处部登陆账号，如微信）
	 * @return void
	 */
	public void save(String mobile,String wechatUid){
		UserWechatRelate userWechatRelate=new UserWechatRelate();
		userWechatRelate.setCreateTime(new Date());
		userWechatRelate.setMobile(mobile);
		userWechatRelate.setWechatUid(wechatUid);
		userWechatRelateDao.save(userWechatRelate);
		
	}
	
	/**
	 * 按微信号查询记录 
	 * @param wechatUid 微信号
	 * @return UserWechatRelate
	 */
	public UserWechatRelate searchByWechatUid(String wechatUid){
		return userWechatRelateDao.searchByWechatUid(wechatUid);
	}
	
	/**
	 * 按手机号码查询记录
	 * @param mobile 手机号码
	 * @return UserWechatRelate
	 */
	public UserWechatRelate searchByMobile(String mobile){
		return userWechatRelateDao.searchByMobile(mobile);
	}
	
	/**
	 * 按电话号码、微信号查询记录
	 * @param mobile 手机号码
	 * @param wechatUid 微信号
	 * @return UserWechatRelate
	 */
	public UserWechatRelate searchByMobileWechatUid(String mobile,String wechatUid){
		return userWechatRelateDao.searchByMobileWechatUid(mobile, wechatUid);
	}
	
	
}
