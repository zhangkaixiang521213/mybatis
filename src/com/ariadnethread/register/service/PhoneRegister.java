/**
 * 
 */
package com.ariadnethread.register.service;

import com.ariadnethread.datacenter.usercontent.domain.UserContent;
import com.ariadnethread.utils.Results;

/**
 * @author koko zu
 *
 */
public interface PhoneRegister {
	
	/**
	 * 以微信号登陆
	 * @param wechatUid（处部登陆账号，如微信）
	 * @return Results
	 */
	public Results doLoginByWechatUid(String wechatUid);
	
	/**
	 * 发送手机验证码
	 * @param mobile :手机号
	 * @return Results
	 */
	public Results doSendPhoneValidateCode(String mobile);
	
	/**
	 * 手机码验证并返回用户信息
	 * @param mobile :手机号
	 * @param code :验证码
	 * @param wechatUid（处部登陆账号，如微信）
	 * @return Results
	 */
	public Results doMobileValidate(String mobile,String code,String wechatUid);
	
	/**
	 * 注册用户
	 * @param mobile :手机号
	 * @return UserContent
	 * @throws Exception 
	 */
	public UserContent registerUser(String mobile) throws Exception;

}
