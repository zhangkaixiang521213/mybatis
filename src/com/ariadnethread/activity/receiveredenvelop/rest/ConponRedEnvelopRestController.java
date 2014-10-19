package com.ariadnethread.activity.receiveredenvelop.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import com.ariadnethread.activity.receiveredenvelop.domain.ConponRedEnvelop;
import com.ariadnethread.activity.receiveredenvelop.service.ConponRedEnvelopService;
import com.ariadnethread.activity.receiveredenvelop.utils.ReceiveConstants;
import com.ariadnethread.coupon.service.CouponService;
import com.ariadnethread.datacenter.usercontent.domain.UserContent;
import com.ariadnethread.datacenter.usercontent.service.UserContentInfoImpl;
//import com.ariadnethread.utils.ApiReturnBean;

/**
 * Restful API的Controller.
 * 
 */
@RestController
@RequestMapping(value = "/receiveredenvelopAPI")
public class ConponRedEnvelopRestController {
//
//	private static Logger logger = LoggerFactory.getLogger(ConponRedEnvelopRestController.class);
//
//	@Autowired
//	private ConponRedEnvelopService conponRedEnvelopService;
//	
//	@Autowired
//	private CouponService couponService;
//	
//	@Autowired
//	private UserContentInfoImpl userContentInfoImpl;
//	
//	/**
//	 * test
//	 * @param session_id 用户登陆会话ID
//	 * @param activityId 活动id
//	 * @return list<ConponRedEnvelop>
//	 */
//	@RequestMapping(value = "/test/{activityId}", produces = MediaTypes.JSON_UTF_8)
//	public ApiReturnBean test(@PathVariable("activityId") String activityId,@RequestHeader("session_id") String sessionId) {
//		
//		ApiReturnBean rb=new ApiReturnBean(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc());
//		logger.info(activityId);
//		logger.info(sessionId);
//		
//		UserContent userContent=userContentInfoImpl.getUserContentBySessionId(sessionId);
//		
//		return rb;
//	}
//	
//	/**
//	 * 发出红包记录列表
//	 * @param session_id 用户登陆会话ID
//	 * @param activityId 活动id
//	 * @return list<ConponRedEnvelop>
//	 */
//	@RequestMapping(value = "/distributelist/{activityId}",produces = MediaTypes.JSON_UTF_8)
//	public Map<String,List<ConponRedEnvelop>> distributelist(@PathVariable("activityId") String activityId,@RequestHeader("session_id") String sessionId) {
//		if(sessionId == null || "".equals(sessionId) ||
//				activityId ==null || "".equals(activityId)){
//			return null;
//		}
//		List<ConponRedEnvelop> list = conponRedEnvelopService.searchByUserContentId(userContentInfoImpl.getUserContentIdBySessionId(sessionId),Long.valueOf(activityId));
//		
//		Map<String,List<ConponRedEnvelop>> map=new HashMap<String,List<ConponRedEnvelop>>();
//		map.put("distributes", list);
//
//		return map;
//	}
//	
//	/**
//	 * 领红包记录列表
//	 * @param session_id 用户登陆会话ID
//	 * @param activityId 活动id
//	 * @return list<ConponRedEnvelop>
//	 */
//	@RequestMapping(value = "/receivelist/{activityId}", produces = MediaTypes.JSON_UTF_8)
//	public Map<String,List<ConponRedEnvelop>> receivelist(@PathVariable("activityId") String activityId,@RequestHeader("session_id") String sessionId) {
//		if(sessionId == null || "".equals(sessionId) ||
//				activityId ==null || "".equals(activityId)){
//			return null;
//		}
//		List<ConponRedEnvelop> list = conponRedEnvelopService.searchByUserContentIdStatus(userContentInfoImpl.getUserContentIdBySessionId(sessionId),Long.valueOf(activityId));
//		
//		Map<String,List<ConponRedEnvelop>> map=new HashMap<String,List<ConponRedEnvelop>>();
//		map.put("receives", list);
//		
//		return map;
//	}
//	
//	
//	/**
//	 * 增加领红包记录
//	 * @param session_id 用户登陆会话ID
//	 * @param activityId 活动id
//	 * @return ApiReturnBean
//	 */
//	@RequestMapping(value = "/receiveadd/{activityId}", produces = MediaTypes.JSON_UTF_8)
//	public Map<String,String> receiveadd(@PathVariable("activityId") String activityId,@RequestHeader("session_id") String sessionId) {
//		if(sessionId == null || "".equals(sessionId) ||
//				activityId ==null || "".equals(activityId)){
//			return null;
//		}
////		return conponRedEnvelopService.doReceiveAdd(userContentInfoImpl.getUserContentIdBySessionId(sessionId), Long.valueOf(activityId), userContentInfoImpl.getUserContentBySessionId(sessionId).getUserName());
//		return null;
//	}
//	
//	
//	/**
//	 * 领红包
//	 * @param redEnvelopId 领红包记录ID
//	 * @return ApiReturnBean
//	 */
//	@RequestMapping(value = "/receive/{activityId}/{redEnvelopId}",  produces = MediaTypes.JSON_UTF_8)
//	public ApiReturnBean receive(@PathVariable("redEnvelopId") String redEnvelopId,@PathVariable("activityId") String activityId,@RequestHeader("session_id") String sessionId) {
//		if(redEnvelopId == null || "".equals(redEnvelopId) ||
//				sessionId == null || "".equals(sessionId)){
//			return null;
//		}
//		return conponRedEnvelopService.doReceive(userContentInfoImpl.getUserContentIdBySessionId(sessionId),Long.valueOf(activityId),Long.valueOf(redEnvelopId));
//	}
//	
//	/**
//	 * 统计已领红包可用记录金额
//	 * @param session_id 用户登陆会话ID
//	 * @param activityId 活动id
//	 * @return float 剩余红包金额
//	 */
//	@RequestMapping(value = "/remainValue/{activityId}", produces = MediaTypes.JSON_UTF_8)
//	public Map<String,Float> remainValue(@PathVariable("activityId") String activityId,@RequestHeader("session_id") String sessionId) {
//		if(sessionId == null || "".equals(sessionId) ||
//				activityId ==null || "".equals(activityId)){
//			return null;
//		}
//		float remainValue=couponService.countRemainValueByUserContentId(userContentInfoImpl.getUserContentIdBySessionId(sessionId),Long.valueOf(activityId));
//		Map<String,Float> map=new HashMap<String,Float>();
//		map.put("remainValue", remainValue);
//		
//		return map;
//	}
//	
//	/**
//	 * 统计领红包记录金额
//	 * @param session_id 用户登陆会话ID
//	 * @param activityId 活动id
//	 * @return float 红包金额
//	 */
//	@RequestMapping(value = "/cardValue/{activityId}", produces = MediaTypes.JSON_UTF_8)
//	public Map<String,Float> cardValue(@PathVariable("activityId") String activityId,@RequestHeader("session_id") String sessionId) {
//		if(sessionId == null || "".equals(sessionId) ||
//				activityId ==null || "".equals(activityId)){
//			return null;
//		}
//		float cardValue=couponService.countCardValueByUserContentId(userContentInfoImpl.getUserContentIdBySessionId(sessionId),Long.valueOf(activityId));
//		Map<String,Float> map=new HashMap<String,Float>();
//		map.put("cardValue", cardValue);
//		
//		return map;
//	}
//	
//	/**
//	 * 红包购票支付
//	 * @param session_id 用户登陆会话ID
//	 * @param ticketNumber 票张数
//	 * @param payAmount 支付金额
//	 * @param activityId 活动id
//	 * @param orderId 订单id
//	 * @return ApiReturnBean
//	 */
//	@RequestMapping(value = "/pay/{ticketNumber}/{payAmount}/{activityId}/{orderId}",produces = MediaTypes.JSON_UTF_8)
//	public ApiReturnBean pay(@PathVariable("ticketNumber") String ticketNumber,@PathVariable("payAmount") String payAmount,@PathVariable("activityId") String activityId,@PathVariable("orderId") String orderId,@RequestHeader("session_id") String sessionId) {
//		if(sessionId == null || "".equals(sessionId) ||
//				ticketNumber ==null || "".equals(ticketNumber) ||
//				activityId ==null || "".equals(activityId) ||
//				orderId ==null || "".equals(orderId) ||
//				payAmount == null || "".equals(payAmount)){
//			return null;
//		}
//		ApiReturnBean rb=couponService.doPay(Integer.valueOf(ticketNumber),userContentInfoImpl.getUserContentIdBySessionId(sessionId),Long.valueOf(activityId),Long.valueOf(orderId),Float.valueOf(payAmount));
//		return rb;
//	}
//	
//	/**
//	 * 退款
//	 * @param session_id 用户登陆会话ID
//	 * @param orderId 订单id
//	 * @return ApiReturnBean
//	 */
//	@RequestMapping(value = "/refund/{orderId}",produces = MediaTypes.JSON_UTF_8)
//	public ApiReturnBean refund(@PathVariable("orderId") String orderId,@RequestHeader("session_id") String sessionId) {
//		if(sessionId == null || "".equals(sessionId) ||
//				orderId ==null || "".equals(orderId) ){
//			return null;
//		}
//		ApiReturnBean rb=couponService.doRefund(Long.valueOf(orderId));
//		return rb;
//	}

}
