package com.ariadnethread.register.service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ariadnethread.activity.receiveredenvelop.utils.ReceiveConstants;
import com.ariadnethread.datacenter.cache.MemCacheManager;
import com.ariadnethread.datacenter.usercontent.domain.UserContent;
import com.ariadnethread.datacenter.usercontent.service.UserContentService;
import com.ariadnethread.datacenter.usercontent.utils.UserConstants;
import com.ariadnethread.register.domain.TsmsmtLog;
import com.ariadnethread.register.domain.UserWechatRelate;
import com.ariadnethread.register.utils.RegisterConstants;
import com.ariadnethread.utils.MD5;
import com.ariadnethread.utils.Results;
import com.ariadnethread.utils.SMSSender;
import com.ariadnethread.utils.SMSSenderContants;

import core.utils.ComUtil;
import core.utils.PropertiesUtil;
@Service
public class PhoneRegisterImpl implements PhoneRegister {
	
	private static Logger logger = LoggerFactory.getLogger(PhoneRegisterImpl.class);
	
	@Autowired
	private UserContentService userContentService;
	
	@Autowired
	private TsmsmtLogService tsmsmtLogService;
	
	@Autowired
	private UserWechatRelateService userWechatRelateService;
	
	/**
	 * 以微信号登陆,如返回空,说明没有与微信号相关联的抠电影账号
	 * @param wechatUid（处部登陆账号，如微信）
	 * @return UserContent
	 */
	public Results doLoginByWechatUid(String wechatUid){
		//验证第三方账号(微信号)是否已绑定抠电影账号
		UserWechatRelate userWechatRelate=validateRegister(wechatUid);
		if(userWechatRelate!=null){
			return new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),
					ReceiveConstants.API_RETURN_STATUS.NORMAL.desc(),
					localLogin(userWechatRelate.getMobile()));
		}else{
			return new Results(ReceiveConstants.API_RETURN_STATUS.WECHAT_NOT_RELATE.value(),
					ReceiveConstants.API_RETURN_STATUS.WECHAT_NOT_RELATE.desc());
		}
	}
	
	/**
	 * 发送手机验证码
	 * @param mobile :手机号
	 * @return Results
	 */
	public Results doSendPhoneValidateCode(String mobile){
		try{
			//读表t_sms_mt_log校验在指定时间内是否发送过短信，如发送过则不发送短信
			List<TsmsmtLog> tsmsmtLogs=tsmsmtLogService.searchByMobileType(mobile, SMSSenderContants.SMSType.RED_ENVOLOP.value());
			if(tsmsmtLogs.size()>0){
				TsmsmtLog tsmsmtLog=tsmsmtLogs.get(0);
				//验证已有发送信息在0.5分钟内
				if(tsmsmtLog.getCreateTime().getTime()<new Date().getTime() && new Date().getTime()<ComUtil.DatePlusMinute(tsmsmtLog.getCreateTime(),0.5f).getTime()){
					return new Results(ReceiveConstants.API_RETURN_STATUS.MOBILE_CODE_EXPIRE_SENDED.value(),ReceiveConstants.API_RETURN_STATUS.MOBILE_CODE_EXPIRE_SENDED.desc());
				}
			}
			
			//发送信息并写入发送日志表
			String validCode=String.format("%06d", new Random().nextInt(1000000));
			
			if(RegisterConstants.checkMobilePhone(mobile)){
				boolean isTest=(PropertiesUtil.getPropByKey("debug")!=null && "true".equalsIgnoreCase(PropertiesUtil.getPropByKey("debug")))?true:false;
				validCode=isTest?ReceiveConstants.TEST_MOBILE_CODE:validCode;
				String content="欢迎您参与领红包活动, 本次操作验证码为: "+validCode+", 有效时间为"+RegisterConstants.MOBILE_CODE_EXPIRE+"分钟";
				if(!isTest){
					SMSSender.sendSms(SMSSenderContants.SMSType.RED_ENVOLOP.value(),mobile,content);
				}
				
				//写入短信发送日志表：t_sms_mt_log
				tsmsmtLogService.save(content,mobile,validCode);
			}else{
				return new Results(ReceiveConstants.API_RETURN_STATUS.MOBILE_VALIDATE_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.MOBILE_VALIDATE_ERROR.desc());
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return new Results(ReceiveConstants.API_RETURN_STATUS.OTHER.value(),ReceiveConstants.API_RETURN_STATUS.OTHER.desc());
		}
		return new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc());
	}
	
	/**
	 * 手机码验证并返回用户信息
	 * @param mobile :手机号
	 * @param code :验证码
	 * @param wechatUid（处部登陆账号，如微信）
	 * @return Results
	 */
	public Results doMobileValidate(String mobile,String code,String wechatUid){
		Results results=new Results(ReceiveConstants.API_RETURN_STATUS.OTHER.value(),ReceiveConstants.API_RETURN_STATUS.OTHER.desc());
		
		if(!RegisterConstants.checkMobilePhone(mobile)){
			return new Results(ReceiveConstants.API_RETURN_STATUS.MOBILE_VALIDATE_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.MOBILE_VALIDATE_ERROR.desc());
		}
		//读表t_sms_mt_log校验
		List<TsmsmtLog> tsmsmtLogs=tsmsmtLogService.searchByMobileType(mobile, SMSSenderContants.SMSType.RED_ENVOLOP.value());
		
		//验证
		if(tsmsmtLogs.size()>0){
			TsmsmtLog tsmsmtLog=tsmsmtLogs.get(0);
			if(mobile!=null && mobile.equals(tsmsmtLog.getMobile()) 
					&& code!=null && code.equals(tsmsmtLog.getValidCode())){
//				if(code!=null && code.equals(tsmsmtLog.getValidCode()) && 
//						tsmsmtLog.getCreateTime().getTime()<new Date().getTime() && new Date().getTime()<ComUtil.DatePlusMinute(tsmsmtLog.getCreateTime(),RegisterConstants.MOBILE_CODE_EXPIRE).getTime()){
				//抠电影账号(手机号)与微信号关联
				UserWechatRelate userWechatRelate = userWechatRelateService.searchByWechatUid(wechatUid);
				if(userWechatRelate == null){
					userWechatRelateService.save(mobile,wechatUid);
				}
				
				try {
					UserContent userContent = registerUser(mobile);
					results=new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc(),userContent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					results=new Results(ReceiveConstants.API_RETURN_STATUS.REGISTER_USER_FAILED.value(),ReceiveConstants.API_RETURN_STATUS.REGISTER_USER_FAILED.desc());
					e.printStackTrace();
				}
			}else{
				results=new Results(ReceiveConstants.API_RETURN_STATUS.MOBILE_CODE_OVER_TIME.value(),ReceiveConstants.API_RETURN_STATUS.MOBILE_CODE_OVER_TIME.desc());
			}
		}else{
			results=new Results(ReceiveConstants.API_RETURN_STATUS.MOBILE_CODE_VALIDATE_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.MOBILE_CODE_VALIDATE_ERROR.desc());
		}
		return results;
	}
	
	/**
	 * 验证第三方账号(微信号)是否已绑定抠电影账号
	 * @param wechatUid（处部登陆账号，如微信）
	 * @return boolean
	 */
	private UserWechatRelate validateRegister(String wechatUid){
		return userWechatRelateService.searchByWechatUid(wechatUid);
	}
	
	/**
	 * 注册用户
	 * @param mobile :手机号
	 * @return UserContent
	 * @throws Exception 
	 */
	public UserContent registerUser(String mobile) throws Exception{
		
		UserContent userContent=null;
		try {
			String rawPW = String.format("%06d", new Random().nextInt(1000000));
			String password = MD5.getMD5(rawPW);
			int registerStatus = userContentService.registerUserToUserCenter(mobile, password);
			
			if(registerStatus == UserConstants.REGISTER_USER_STATUS.USER_CREATED.value()){
				//发送信息并写入发送日志表
				String validCode=String.format("%06d", new Random().nextInt(1000000));
				if(RegisterConstants.checkMobilePhone(mobile)){
					String content="你的抠电影红包账号已创建。用户名："+mobile+"，初始密码："+rawPW+"，请登录抠电影网站http://komovie.cn以及抠电影客户端http://t.cn/RhocOGa并修改密码！";
//					String content="你好！抠电影账户已创建, 您可以使用手机号：" + mobile + " " +"和密码：" + rawPW + " " +"登录抠电影网站 http://komovie.cn 以及抠电影客户端";
					SMSSender.sendSms(SMSSenderContants.SMSType.RED_ENVOLOP.value(),mobile,content);
					
					//写入短信发送日志表：t_sms_mt_log
					tsmsmtLogService.save(content,mobile,validCode);
				}
				
				userContent = new UserContent();
				String sessionId = UUID.randomUUID().toString().replaceAll("-", "");
				userContent.setLastSession(sessionId);
				userContent.setLoginTime(new Date());
				userContent.setUserName(mobile);
				userContent.setCreateTime(new Date());
				userContent.setDel(0);
				userContentService.save(userContent);
				
				MemCacheManager.getInstance().setCacheForKey("user_userName_"+userContent.getUserName(), userContent, 60*24);
				MemCacheManager.getInstance().setCacheForKey("user_sessionId_"+userContent.getLastSession(), userContent, 60*24);
				MemCacheManager.getInstance().setCacheForKey("user_userId_"+userContent.getUid(), userContent, 60*24);
				
//				userContent=localLogin(mobile);
			}else if(registerStatus == UserConstants.REGISTER_USER_STATUS.USER_EXISTS.value()){
				userContent=localLogin(mobile);
			}else{
				throw new Exception("注册用户失败");
			}
		} catch (Exception e) {
			logger.error(e+"");
			e.printStackTrace();
			throw new Exception(e);
		}
		return userContent;
	}

	/**
	 * 用户登陆
	 * @param mobile :账户
	 * @return UserContent
	 */
	private UserContent localLogin(String mobile){
		UserContent userContent=userContentService.searchByUserName(mobile);
		if (userContent != null) {
			
			String sessionId = userContent.getLastSession();
			
			//如果时间不在7天内则更新
			boolean isExpire=!(userContent.getLoginTime().getTime()>(System.currentTimeMillis()-RegisterConstants.SESSION_VALIDATE_TIMES));
			if (sessionId == null || "".equals(sessionId) || isExpire) {
				
				sessionId = UUID.randomUUID().toString().replaceAll("-", "");
				userContent.setLastSession(sessionId);
				userContent.setLoginTime(new Date());
				
				userContentService.update(userContent);	
				
				MemCacheManager.getInstance().setCacheForKey("user_userName_"+userContent.getUserName(), userContent, 60*24);
				MemCacheManager.getInstance().setCacheForKey("user_sessionId_"+userContent.getLastSession(), userContent, 60*24);
				MemCacheManager.getInstance().setCacheForKey("user_userId_"+userContent.getUid(), userContent, 60*24);
			}
		}
		
		return userContent;
	}
	
	
}
