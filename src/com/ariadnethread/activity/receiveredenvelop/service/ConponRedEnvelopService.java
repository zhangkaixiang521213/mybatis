
package com.ariadnethread.activity.receiveredenvelop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariadnethread.activity.domain.Activity;
import com.ariadnethread.activity.receiveredenvelop.dao.mybatis.ConponRedEnvelopDao;
import com.ariadnethread.activity.receiveredenvelop.domain.ConponRedAmount;
import com.ariadnethread.activity.receiveredenvelop.domain.ConponRedEnvelop;
import com.ariadnethread.activity.receiveredenvelop.utils.ReceiveConstants;
import com.ariadnethread.activity.service.ActivityService;
import com.ariadnethread.coupon.service.CouponService;
import com.ariadnethread.coupon.service.CouponUserService;
import com.ariadnethread.exception.WeixinInvokeException;
import com.ariadnethread.utils.Results;
import com.ariadnethread.utils.WeiXinLogin;
import com.ariadnethread.utils.WeiXinUserBean;

import core.utils.ComUtil;

@Service
@Transactional
public class ConponRedEnvelopService {

	private static Logger logger = LoggerFactory.getLogger(ConponRedEnvelopService.class);

	@Autowired
	private ConponRedEnvelopDao conponRedEnvelopDao;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private CouponUserService couponUserService;
	
	@Autowired
	private ConponRedAmountService conponRedAmountService;
	
	/**
	 * 通过Id获取一条记录
	 * @param id 主键
	 * @return Results
	 */
	public Results findById(long id) {
		return new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc(),conponRedEnvelopDao.findById(id));

	}
	
	/**
	 * 按用户userContentId查询记录列表
	 * @param userContentId 用户主键（外键）
	 * @param openId 微信openId
	 * @return Results
	 */
	public Results searchByUserContentId(long userContentId,long activityId,String openId){
		List<ConponRedEnvelop> list = conponRedEnvelopDao.searchByUserContentId(userContentId,activityId,ReceiveConstants.RED_ENVELOP_STATUS.UNRECEIVE.value(),ReceiveConstants.RED_ENVELOP_STATUS.RECEIVED.value(),openId);
		int distributeCount=countByDistribute(userContentId,activityId);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("disCount", distributeCount);
		return new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc(),map);
	}
	
	/**
	 * 统计发出红包记录
	 * @param userContentId 用户主键（外键）
	 * @param activityId 活动主键（外键）
	 * @return
	 */
	public int countByDistribute(long userContentId,long activityId){
		return conponRedEnvelopDao.countByDistribute(userContentId, activityId);
	}
	
	/**
	 * 按用户userContentId、status查询记录列表
	 * @param userContentId 用户主键（外键）
	 * @param status 记录状态
	 * @return Results
	 */
	public Results searchByUserContentIdStatus(long userContentId,long activityId){
		
		List<ConponRedEnvelop> list = conponRedEnvelopDao.searchByUserContentIdStatus(userContentId,activityId,ReceiveConstants.RED_ENVELOP_STATUS.RECEIVED.value());
		
		return new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc(),list);
	}
	
	/**
	 * 修改记录
	 * @param Coupon ：记录对象实体
	 */
	public void update(ConponRedEnvelop conponRedEnvelop){
		conponRedEnvelopDao.update(conponRedEnvelop);
	}
	
	/**
	 * 修改领红包记录
	 * @param payStatus 支付状态
	 * @param redEnvelopId 红包记录ID集
	 */
	public void updatePayStatus(int payStatus,String redEnvelopId){
		conponRedEnvelopDao.updatePayStatus(payStatus,redEnvelopId);
	}
	
	/**
	 * 增加可领红包记录
	 * @param userContentId 用户id
	 * @param activityId 活动id
	 * @param userName 用户名称
	 * @param userId 用户id
	 * @param openId 微信openId
	 * @param token 微信登陆token
	 * @param sourceUserId 源用户id（转发用户）
	 * @return Results
	 */
	public Results doReceiveAdd(long userContentId,long activityId,String userName,String openId,String token,long sourceUserId){
		String redEnvelopId="";
		try{
			
			List<ConponRedEnvelop> list=conponRedEnvelopDao.searchByWeixinOpenId(userContentId, openId, activityId);
			if(list!=null && list.size()>0){
				return new Results(ReceiveConstants.API_RETURN_STATUS.USER_RECEIVE_BIND.value(),ReceiveConstants.API_RETURN_STATUS.USER_RECEIVE_BIND.desc());
			}
				
			ConponRedEnvelop conponRedEnvelop=new ConponRedEnvelop();
			conponRedEnvelop.setActivityId(Long.valueOf(activityId));
			conponRedEnvelop.setCreateTime(new Date());
			conponRedEnvelop.setDescription(ReceiveConstants.DESCRIPTION);
			conponRedEnvelop.setUserContentId(Long.valueOf(userContentId));
			conponRedEnvelop.setUserName(userName);
			conponRedEnvelop.setSourceUserId(sourceUserId);
			conponRedEnvelop.setOpenId(openId);
			conponRedEnvelop.setIsPay(ReceiveConstants.RED_ENVELOP_IS_PAY.NOT_PAY.value());
			
			//查询活动记录
			Activity activity=activityService.findById(String.valueOf(Long.valueOf(activityId)));
			conponRedEnvelop.setExpireDate(ComUtil.Date2Days(activity.getExpireDays()));
			
			
			
			int receiveCount=conponRedEnvelopDao.countByUserContentId(userContentId,activityId,ReceiveConstants.RED_ENVELOP_STATUS.UNRECEIVE.value(),ReceiveConstants.RED_ENVELOP_STATUS.RECEIVED.value());
			if(receiveCount>=ReceiveConstants.MAXIMUM_RECEIVE_COUNT){
				conponRedEnvelop.setStatus(ReceiveConstants.RED_ENVELOP_STATUS.LOG.value());
				conponRedEnvelopDao.save(conponRedEnvelop);
			}else{
				//计算随机抽取金额
				ConponRedAmount conponRedAmount=conponRedAmountService.searchRandomAmount(Long.valueOf(activityId));
				
				if(conponRedAmount == null){
					return new Results(ReceiveConstants.API_RETURN_STATUS.RED_AMOUNT_USED.value(),ReceiveConstants.API_RETURN_STATUS.RED_AMOUNT_USED.desc());
				}
				
				conponRedEnvelop.setStatus(ReceiveConstants.RED_ENVELOP_STATUS.UNRECEIVE.value());
				conponRedEnvelop.setRedAmount(conponRedAmount.getRedAmount());
				
				//调微信接口取微信信息
				try{
					WeiXinUserBean wub=WeiXinLogin.checkLogin(openId, token);
					conponRedEnvelop.setNickName(wub.getNickName());
					conponRedEnvelop.setWechatUid(wub.getWechatUid());
					conponRedEnvelop.setSex(wub.getSex());
					conponRedEnvelop.setHeadImgUrl(wub.getHeadImgUrl());
				
				}catch(WeixinInvokeException e){
					e.printStackTrace();
					logger.error(e+"");
					return new Results(ReceiveConstants.API_RETURN_STATUS.WEIXIN_INVOKE_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.WEIXIN_INVOKE_ERROR.desc());
				}
				
				conponRedEnvelopDao.save(conponRedEnvelop);
				
				//修改红包抽取量
				conponRedAmountService.update(conponRedAmount.getId());
			}
			redEnvelopId=String.valueOf(conponRedEnvelop.getId());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e+"");
			return new Results(ReceiveConstants.API_RETURN_STATUS.LOGICAL_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.LOGICAL_ERROR.desc());
		}
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("redEnvelopId", redEnvelopId);
		return new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc(),map);
	}
	
	/**
	 * 领红包
	 * @param userContentId 用户id
	 * @param redEnvelopId ：红包ID
	 * @return Results
	 */
	public Results doReceive(long userContentId,long activityId,long redEnvelopId){
		Results results=new Results(ReceiveConstants.API_RETURN_STATUS.OTHER.value(),ReceiveConstants.API_RETURN_STATUS.OTHER.desc());

		try{
				//检验已领红包数
			/*
				int receiveCount=conponRedEnvelopDao.countByUserContentId(userContentId,activityId,ReceiveConstants.RED_ENVELOP_STATUS.RECEIVED.value(),ReceiveConstants.RED_ENVELOP_STATUS.RECEIVED.value());
				if(receiveCount>=ReceiveConstants.MAXIMUM_RECEIVE_COUNT){
					results=new Results(ReceiveConstants.API_RETURN_STATUS.OVER_MAXIMUM_RECEIVE.value(),ReceiveConstants.API_RETURN_STATUS.OVER_MAXIMUM_RECEIVE.desc());	
					return results;
				}
				*/
				//查询红包记录
				ConponRedEnvelop conponRedEnvelop=conponRedEnvelopDao.findById(redEnvelopId);
				//当前状态为未领取红包并且在有效期内
				if(conponRedEnvelop.getStatus() == ReceiveConstants.RED_ENVELOP_STATUS.UNRECEIVE.value()){
					//修改为已领取红包
					conponRedEnvelop.setStatus(ReceiveConstants.RED_ENVELOP_STATUS.RECEIVED.value());
					conponRedEnvelopDao.update(conponRedEnvelop);
					//查询活动记录
					Activity activity=activityService.findById(String.valueOf(conponRedEnvelop.getActivityId()));
					//增加coupon记录
					long couponId=couponService.save(Long.valueOf(activity.getCouponIds()), conponRedEnvelop.getRedAmount(), conponRedEnvelop.getRedAmount(), conponRedEnvelop.getExpireDate(),conponRedEnvelop.getId());
					
					//增加coupon_user
					couponUserService.save(couponId, conponRedEnvelop.getUserContentId(),activity.getAid());
					
					results=new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc());	
				}else{
					results=new Results(ReceiveConstants.API_RETURN_STATUS.RECEIVED.value(),ReceiveConstants.API_RETURN_STATUS.RECEIVED.desc());				
				}
		}catch(Exception e){
			e.printStackTrace();
			
			results=new Results(ReceiveConstants.API_RETURN_STATUS.LOGICAL_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.LOGICAL_ERROR.desc());
		}
		return results;
	}
	
}
