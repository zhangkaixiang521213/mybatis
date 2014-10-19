package com.ariadnethread.activity.receiveredenvelop.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ariadnethread.activity.receiveredenvelop.service.ConponRedEnvelopService;
import com.ariadnethread.activity.receiveredenvelop.utils.ReceiveConstants;
import com.ariadnethread.coupon.service.CouponPayLogService;
import com.ariadnethread.coupon.service.CouponService;
import com.ariadnethread.datacenter.usercontent.domain.UserContent;
import com.ariadnethread.datacenter.usercontent.service.UserContentInfoImpl;
import com.ariadnethread.register.service.PhoneRegisterImpl;
import com.ariadnethread.utils.Results;

import core.utils.PropertiesUtil;
import core.utils.jackson.JacksonUtil;

/**
 * Restful API的Controller.
 * 
 */
@Controller
@RequestMapping(value = "/receiveredenvelop")
public class ConponRedEnvelopController {

	private static Logger logger = LoggerFactory.getLogger(ConponRedEnvelopController.class);

	@Autowired
	private ConponRedEnvelopService conponRedEnvelopService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private UserContentInfoImpl userContentInfoImpl;
	
	@Autowired
	private PhoneRegisterImpl phoneRegisterImpl;
	
	@Autowired
	private CouponPayLogService couponPayLogService;
	
	/**
	 * 发送手机验证码
	 * @param mobile :手机号
	 * @return Results
	 */
	@RequestMapping(value = "/sendMobileValidateCode")
	@ResponseBody
	public Results sendMobileValidateCode(@RequestParam("mobile") String mobile) throws IOException {
		
		//参数非空校验
		if(mobile ==null || "".equals(mobile)){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		//业务逻辑
		Results results=phoneRegisterImpl.doSendPhoneValidateCode(mobile);
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * 校验手机验证码并返回用户信息
	 * @param mobile :手机号
	 * @param code :验证码
	 * @param wechatUid（处部登陆账号，如微信）
	 * @return Results
	 */
	@RequestMapping(value = "/mobileValidate")
	@ResponseBody
	public Results mobileValidate(@RequestParam("mobile") String mobile,
			@RequestParam("code") String code,
			@RequestParam("wechatUid") String wechatUid,HttpServletResponse response) throws IOException {
		
		//参数非空校验
		if(code == null || "".equals(code) ||
				wechatUid == null || "".equals(wechatUid) ||
				mobile ==null || "".equals(mobile)){
			return  new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		//业务逻辑
		Results results=phoneRegisterImpl.doMobileValidate(mobile, code, wechatUid);
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * 以微信号登陆
	 * @param wechatUid（处部登陆账号，如微信）
	 * @return Results
	 */
	@RequestMapping(value = "/loginByWechatUid")
	@ResponseBody
	public Results loginByWechatUid(@RequestParam("wechatUid") String wechatUid,HttpServletResponse response)throws IOException{
		
		//参数非空校验
		if(wechatUid == null || "".equals(wechatUid)){
			return  new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		//业务逻辑
		Results results = phoneRegisterImpl.doLoginByWechatUid(wechatUid);
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * 发出红包记录列表
	 * @param session_id 用户登陆会话ID
	 * @return Results
	 * @throws IOException 
	 */
	@RequestMapping(value = "/distributelist")
	@ResponseBody
	public Results distributelist(HttpServletRequest request,HttpServletResponse response){
		//@RequestHeader("session_id") String sessionId
		String sessionId=request.getHeader("session_id");
		String openId=request.getParameter("openId");
		
		logger.info("sessionId="+sessionId);
		logger.info("openId="+openId);
		
		//参数非空校验
		if(sessionId == null || "".equals(sessionId) ||
				openId == null || "".equals(openId)){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		UserContent userContent=userContentInfoImpl.getUserContentBySessionId(sessionId);
		if(userContent == null){
			return new Results(ReceiveConstants.API_RETURN_STATUS.NO_USER.value(),ReceiveConstants.API_RETURN_STATUS.NO_USER.desc());
		}
		//业务逻辑
		Results results= conponRedEnvelopService.searchByUserContentId(userContent.getUid(),Long.valueOf(PropertiesUtil.getPropByKey("activity_id")),openId);
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}
	
	/**
	 * 领红包记录列表
	 * @param session_id 用户登陆会话ID
	 * @return Results
	 */
	@RequestMapping(value = "/receivelist")
	@ResponseBody
	public Results receivelist(@RequestHeader("session_id") String sessionId){
		
		//参数非空校验
		if(sessionId == null || "".equals(sessionId)){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		UserContent userContent=userContentInfoImpl.getUserContentBySessionId(sessionId);
		if(userContent == null){
			return new Results(ReceiveConstants.API_RETURN_STATUS.NO_USER.value(),ReceiveConstants.API_RETURN_STATUS.NO_USER.desc());
		}
		//业务逻辑
		Results results = conponRedEnvelopService.searchByUserContentIdStatus(userContent.getUid(),Long.valueOf(PropertiesUtil.getPropByKey("activity_id")));
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	
	/**
	 * 增加领红包记录
	 * @param session_id 用户登陆会话ID
	 * @param userId 用户id
	 * @param openId 微信openId
	 * @param token 微信登陆token
	 * @return Results
	 */
	@RequestMapping(value = "/receiveadd")
	@ResponseBody
	public Results receiveadd(@RequestParam("userId") String userId,
			@RequestParam("openId") String openId,
			@RequestParam("token") String token){
		
		//参数非空校验
		if(openId ==null || "".equals(openId)||
						token ==null || "".equals(token)||
						userId ==null || "".equals(userId)){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		//业务逻辑
		UserContent userContent=userContentInfoImpl.searchByUserId(userId);
		if(userContent == null){
			return new Results(ReceiveConstants.API_RETURN_STATUS.NO_USER.value(),ReceiveConstants.API_RETURN_STATUS.NO_USER.desc());
		}
		Results results = conponRedEnvelopService.doReceiveAdd(Long.valueOf(userId), Long.valueOf(PropertiesUtil.getPropByKey("activity_id")), userContent.getUserName(),openId,token,userContent.getUid());
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	
	/**
	 * 领红包
	 * @param redEnvelopId 领红包记录ID
	 * @return Results
	 */
	@RequestMapping(value = "/receive")
	@ResponseBody
	public Results receive(@RequestParam("redEnvelopId") String redEnvelopId,
			@RequestHeader("session_id") String sessionId){
		
		//参数非空校验
		if(redEnvelopId == null || "".equals(redEnvelopId) ||
				sessionId == null || "".equals(sessionId)){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		UserContent userContent=userContentInfoImpl.getUserContentBySessionId(sessionId);
		if(userContent == null){
			return new Results(ReceiveConstants.API_RETURN_STATUS.NO_USER.value(),ReceiveConstants.API_RETURN_STATUS.NO_USER.desc());
		}
		//业务逻辑
		Results results =  conponRedEnvelopService.doReceive(userContent.getUid(),Long.valueOf(PropertiesUtil.getPropByKey("activity_id")),Long.valueOf(redEnvelopId));
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * 统计已领红包可用记录金额
	 * @param session_id 用户登陆会话ID
	 * @return Results 剩余红包金额
	 */
	@RequestMapping(value = "/remainValue")
	@ResponseBody
//	public Results remainValue(@RequestHeader("session_id") String sessionId){
	public Results remainValue(HttpServletRequest request,HttpServletResponse response){
		String sessionId=request.getHeader("session_id");
		String userId=request.getHeader("user_id");
		
		logger.info("sessionId="+sessionId);
		logger.info("userId="+userId);
		
		//参数非空校验
		if((sessionId == null || "".equals(sessionId)) && (userId == null || "".equals(userId))){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		UserContent userContent = userContentInfoImpl.searchByUserIdOrSessionId(userId,sessionId);
		if(userContent == null){
			return new Results(ReceiveConstants.API_RETURN_STATUS.NO_USER.value(),ReceiveConstants.API_RETURN_STATUS.NO_USER.desc());
		}
		//业务逻辑
		Results results = couponService.countRemainValueByUserContentId(userContent.getUid(),Long.valueOf(PropertiesUtil.getPropByKey("activity_id")));
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * 统计领红包记录金额
	 * @param session_id 用户登陆会话ID
	 * @return Results 红包金额
	 */
	@RequestMapping(value = "/cardValue")
	@ResponseBody
	public Results cardValue(HttpServletRequest request,HttpServletResponse response){
		
		String sessionId=request.getHeader("session_id");
		String userId=request.getHeader("user_id");
		
		logger.info("sessionId="+sessionId);
		logger.info("userId="+userId);
		
		//参数非空校验
		if((sessionId == null || "".equals(sessionId)) && (userId == null || "".equals(userId))){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		UserContent userContent = userContentInfoImpl.searchByUserIdOrSessionId(userId,sessionId);
		if(userContent == null){
			return new Results(ReceiveConstants.API_RETURN_STATUS.NO_USER.value(),ReceiveConstants.API_RETURN_STATUS.NO_USER.desc());
		}
		//业务逻辑
		Results results = couponService.countCardValueByUserContentId(userContent.getUid(),Long.valueOf(PropertiesUtil.getPropByKey("activity_id")));
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * 红包购票支付
	 * @param session_id 用户登陆会话ID
	 * @param ticketNumber 票数
	 * @param payAmount 支付金额
	 * @param orderNo 订单号
	 * @return Results
	 */
	@RequestMapping(value = "/pay")
	@ResponseBody
	public Results pay(HttpServletRequest request,HttpServletResponse response) {
		
		
		String sessionId=request.getHeader("session_id");
		String userId=request.getHeader("user_id");
		
		String ticketNumber=request.getParameter("ticketNumber");
		String payAmount=request.getParameter("payAmount");
		String orderNo=request.getParameter("orderNo");
		
		logger.info("sessionId="+sessionId);
		logger.info("userId="+userId);
		logger.info("ticketNumber="+ticketNumber);
		logger.info("orderNo="+orderNo);
		logger.info("payAmount="+payAmount);
		
		//参数非空校验
		boolean isUserId=(sessionId == null || "".equals(sessionId)) && (userId == null || "".equals(userId));
		if( isUserId ||
				ticketNumber ==null || "".equals(ticketNumber) ||
						orderNo ==null || "".equals(orderNo) ||
				payAmount == null || "".equals(payAmount)){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		UserContent userContent=userContentInfoImpl.searchByUserIdOrSessionId(userId,sessionId);
		if(userContent == null){
			return new Results(ReceiveConstants.API_RETURN_STATUS.NO_USER.value(),ReceiveConstants.API_RETURN_STATUS.NO_USER.desc());
		}
		//业务逻辑
		Results results = couponService.doPay(Integer.valueOf(ticketNumber),userContent.getUid(),Long.valueOf(PropertiesUtil.getPropByKey("activity_id")),orderNo,Float.valueOf(payAmount));
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * 退款
	 * @param session_id 用户登陆会话ID
	 * @param orderNo 订单号
	 * @return Results
	 */
	@RequestMapping(value = "/refund")
	@ResponseBody
	public Results refund(HttpServletRequest request,HttpServletResponse response){
		
		String sessionId=request.getHeader("session_id");
		String userId=request.getHeader("user_id");
		
		String orderNo=request.getParameter("orderNo");
		
		logger.info("sessionId="+sessionId);
		logger.info("userId="+userId);
		logger.info("orderNo="+orderNo);
		
		//参数非空校验
		boolean isUserId=(sessionId == null || "".equals(sessionId)) && (userId == null || "".equals(userId));
		if(isUserId ||
				orderNo ==null || "".equals(orderNo) ){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		//业务逻辑
		Results results = couponService.doRefund(orderNo);
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * 红包详细
	 * @param session_id 用户登陆会话ID
	 * @param redEnvelopId 红包id
	 * @return ConponRedEnvelop
	 */
	@RequestMapping(value = "/redEnvelop")
	@ResponseBody
	public Results redEnvelop(HttpServletRequest request,HttpServletResponse response){
		
		String sessionId=request.getHeader("session_id");
		String userId=request.getHeader("user_id");
		
		String redEnvelopId=request.getParameter("redEnvelopId");
		
		logger.info("sessionId="+sessionId);
		logger.info("userId="+userId);
		logger.info("redEnvelopId="+redEnvelopId);
		
		//参数非空校验
		boolean isUserId=(sessionId == null || "".equals(sessionId)) && (userId == null || "".equals(userId));
		if(isUserId ||
				redEnvelopId ==null || "".equals(redEnvelopId) ){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		//业务逻辑
		Results results = conponRedEnvelopService.findById(Long.valueOf(redEnvelopId));
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * 统计当天还可支付金额
	 * @param session_id 用户登陆会话ID
	 * @return todayCanPayMoney
	 */
	@RequestMapping(value = "/todayCanPayMoney")
	@ResponseBody
	public Results countPayMoneyByDate(HttpServletRequest request,HttpServletResponse response){
		
		String sessionId=request.getHeader("session_id");
		
		logger.info("sessionId="+sessionId);
		
		//参数非空校验
		if(sessionId == null || "".equals(sessionId)){
			return new Results(ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARA_ERROR.desc());
		}
		
		//业务逻辑
		Results results = couponPayLogService.todayCanPayMoney();
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
}
