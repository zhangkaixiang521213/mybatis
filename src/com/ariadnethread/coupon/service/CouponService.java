
package com.ariadnethread.coupon.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariadnethread.activity.receiveredenvelop.service.ConponRedEnvelopService;
import com.ariadnethread.activity.receiveredenvelop.utils.ReceiveConstants;
import com.ariadnethread.coupon.dao.mybatis.CouponDao;
import com.ariadnethread.coupon.domain.Coupon;
import com.ariadnethread.coupon.domain.CouponPayLog;
import com.ariadnethread.coupon.utils.CouponConstants;
import com.ariadnethread.utils.Results;

import core.utils.ComUtil;

@Service
@Transactional
public class CouponService {

	private static Logger logger = LoggerFactory.getLogger(CouponService.class);

	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private CouponPayLogService couponPayLogService;
	
	@Autowired
	private ConponRedEnvelopService conponRedEnvelopService;
	
	/**
	 * 保存记录
	 * @param couponInfoId 卷类型表ID
	 * @param cardValue 红包金额
	 * @param remainValue 剩余红包金额
	 * @param expireDay 到期日期
	 * @param redEnvelopId 领红包券ID
	 * @return id 卷记录id
	 */
	public long save(long couponInfoId,float cardValue,float remainValue,Date expireDay,long redEnvelopId){
		
		Coupon coupon=new Coupon();
		coupon.setCardCount(CouponConstants.RECEIVE_RED_ENVELOP_CARD_COUNT);
		coupon.setRemainCount(CouponConstants.RECEIVE_RED_ENVELOP_CARD_COUNT);
		coupon.setCardValue(cardValue);
		coupon.setRemainValue(remainValue);
		coupon.setCreateTime(new Date());
		coupon.setExpireDate(expireDay);
		coupon.setType(couponInfoId);
		coupon.setSold(Integer.valueOf(CouponConstants.COUPON_SOLD.ACTIVATE.value()));
		coupon.setNote(CouponConstants.COUPON_NOTE);
		coupon.setCouponId(ComUtil.getId());
		coupon.setRedEnvelopId(redEnvelopId);
		
		couponDao.save(coupon);
		
		return coupon.getId();
		
	}
	
	/**
	 * 统计已领红包记录金额
	 * @param userContentId 用户ID
	 * @param activityId 活动ID
	 * @return Results 红包金额
	 */
	public Results countCardValueByUserContentId(long userContentId,long activityId){
		
		Float cardValue=couponDao.countCardValueByUserContentId(userContentId,activityId);
		Map<String,Float> map=new HashMap<String,Float>();
		map.put("cardValue", cardValue);

		return new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc(),map);

	}
	
	/**
	 * 统计已领红包可用记录金额
	 * @param userContentId 用户ID
	 * @param activityId 活动ID
	 * @return Results 剩余红包金额
	 */
	public Results countRemainValueByUserContentId(long userContentId,long activityId){
		Float remainValue=couponDao.countRemainValueByUserContentId(userContentId,activityId);
		Map<String,Float> map=new HashMap<String,Float>();
		map.put("remainValue", remainValue);

		return new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc(),map);
	}
	
	/**
	 * 红包购票支付
	 * @param userContentId 用户ID
	 * @param ticketNumber 票数
	 * @param payAmount 支付金额
	 * @param activityId 活动ID
	 * @param orderNo 订单号
	 * @return Results
	 */
	public Results doPay(int ticketNumber,long userContentId,long activityId,String orderNo,float payAmount){
		Results results=new Results(ReceiveConstants.API_RETURN_STATUS.OTHER.value(),ReceiveConstants.API_RETURN_STATUS.OTHER.desc());

		try{
			//校验券最大可支付金额（1张订单限制券最大可支付金额）
//			if(ReceiveConstants.SINGLE_ORDER_MAXIMUM_AVALIABLE_MONEY*ticketNumber<payAmount){
//				return new Results(ReceiveConstants.API_RETURN_STATUS.OVER_MAXIMUM_COUPON.value(),ReceiveConstants.API_RETURN_STATUS.OVER_MAXIMUM_COUPON.desc());
//			}
			
			if(ReceiveConstants.SINGLE_ORDER_MAXIMUM_AVALIABLE_MONEY<payAmount){
				return new Results(ReceiveConstants.API_RETURN_STATUS.OVER_MAXIMUM_COUPON.value(),ReceiveConstants.API_RETURN_STATUS.OVER_MAXIMUM_COUPON.desc());
			}
			
			List<CouponPayLog> qcplList=couponPayLogService.findByOrderNo(orderNo);
			if(qcplList!=null && qcplList.size()>0){
				return new Results(ReceiveConstants.API_RETURN_STATUS.ORDER_PAYED.value(),ReceiveConstants.API_RETURN_STATUS.ORDER_PAYED.desc());
			}
			
			Float remainValue=couponDao.countRemainValueByUserContentId(userContentId,activityId);
			if(remainValue == null){
				return new Results(ReceiveConstants.API_RETURN_STATUS.PAY_NOT_MONEY.value(),ReceiveConstants.API_RETURN_STATUS.PAY_NOT_MONEY.desc());
			}
			if(remainValue<payAmount){
				return new Results(ReceiveConstants.API_RETURN_STATUS.PAY_AMOUNT_OVER_REMAIN_VALUE.value(),ReceiveConstants.API_RETURN_STATUS.PAY_AMOUNT_OVER_REMAIN_VALUE.desc());
			}
			
			List<Coupon> list=couponDao.findByUserContentId(userContentId,activityId);
			List<CouponPayLog> cplList=new ArrayList<CouponPayLog>();
			List<Long> delList=new ArrayList<Long>();
			List<Long> redEnvelopIdList=new ArrayList<Long>();
			Long id=null;
			CouponPayLog cpl=null;
			float minusValue=0;
			if(list!=null && list.size()>0){
				float tempRemainValue=payAmount;
				for(Coupon c:list){
					if(tempRemainValue>=c.getRemainValue()){
						delList.add(c.getId());
						redEnvelopIdList.add(c.getRedEnvelopId());
						tempRemainValue=tempRemainValue-c.getRemainValue();
						
						cpl=new CouponPayLog();
						cpl.setActivityId(activityId);
						cpl.setCouponId(c.getId());
						cpl.setCreateTime(new Date());
						cpl.setOrderNo(orderNo);
						cpl.setUserId(userContentId);
						cpl.setPayValue(c.getRemainValue());
						cpl.setStatus(CouponConstants.PAY_STATUS.PAY.value());
						cpl.setRedEnvelopId(c.getRedEnvelopId());
						cpl.setRemainValue(c.getRemainValue());
						cpl.setPayTime(new Date());
						cplList.add(cpl);
					}else{
						id=c.getId();
						minusValue=c.getRemainValue()-tempRemainValue;
						
						cpl=new CouponPayLog();
						cpl.setActivityId(activityId);
						cpl.setCouponId(c.getId());
						cpl.setCreateTime(new Date());
						cpl.setOrderNo(orderNo);
						cpl.setUserId(userContentId);
						cpl.setPayValue(tempRemainValue);
						cpl.setStatus(CouponConstants.PAY_STATUS.PAY.value());
						cpl.setRedEnvelopId(c.getRedEnvelopId());
						cpl.setRemainValue(c.getRemainValue());
						cpl.setPayTime(new Date());
						cplList.add(cpl);
						break;
					}
				}
			}
			if(delList!=null && delList.size()>0){
				String delStr="";
				for(Long l:delList){
					delStr+=l+",";
				}
				if(!"".equals(delStr)){
					delStr=delStr.substring(0, delStr.length()-1);
				}
				couponDao.updateRemainValueToZero(delStr);
				
				//修改红包记录已支付
				String redEnvelopIdStr="";
				for(Long l:redEnvelopIdList){
					redEnvelopIdStr+=l+",";
				}
				if(!"".equals(redEnvelopIdStr)){
					redEnvelopIdStr=redEnvelopIdStr.substring(0, redEnvelopIdStr.length()-1);
				}
				conponRedEnvelopService.updatePayStatus(ReceiveConstants.RED_ENVELOP_IS_PAY.YES_PAY.value(),redEnvelopIdStr);
			}
			if(id!=null && !"".equals(id)){
				couponDao.updateRemainValueToSpecify(id,minusValue);
			}
			if(cplList != null && cplList.size()>0){
				couponPayLogService.saveBatch(cplList);
			}
			results=new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc());
		}catch(Exception e){
			e.printStackTrace();
			results=new Results(ReceiveConstants.API_RETURN_STATUS.LOGICAL_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.LOGICAL_ERROR.desc());
		}
		return results;
	}
	
	/**
	 * 退款
	 * @param orderNo 订单号
	 * @return Results
	 */
	public Results doRefund(String  orderNo){
		Results results=new Results(ReceiveConstants.API_RETURN_STATUS.OTHER.value(),ReceiveConstants.API_RETURN_STATUS.OTHER.desc());

		try{
			List<CouponPayLog> list=couponPayLogService.findByOrderNoAndStatus(orderNo,CouponConstants.PAY_STATUS.REFUND.value());
			if(list==null || list.size()<=0){
				return new Results(ReceiveConstants.API_RETURN_STATUS.NO_REFUND.value(),ReceiveConstants.API_RETURN_STATUS.NO_REFUND.desc());
			}
			
			List<Long> redEnvelopIdList=new ArrayList<Long>();
			for(CouponPayLog pl:list){
				if(pl.getPayValue()==pl.getRemainValue()){
					redEnvelopIdList.add(pl.getRedEnvelopId());
				}
				couponDao.updateRemainValueAddSpecify(pl.getCouponId(), pl.getPayValue());
			}
			
			couponPayLogService.updateStatusToRefund(orderNo, CouponConstants.PAY_STATUS.REFUND.value());
			
			//修改红包记录未支付
			if(redEnvelopIdList!=null && redEnvelopIdList.size()>0){
				String redEnvelopIdStr="";
				for(Long l:redEnvelopIdList){
					redEnvelopIdStr+=l+",";
				}
				if(!"".equals(redEnvelopIdStr)){
					redEnvelopIdStr=redEnvelopIdStr.substring(0, redEnvelopIdStr.length()-1);
				}
				conponRedEnvelopService.updatePayStatus(ReceiveConstants.RED_ENVELOP_IS_PAY.NOT_PAY.value(),redEnvelopIdStr);
			}
			
			results=new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc());
		}catch(Exception e){
			e.printStackTrace();
			
			results=new Results(ReceiveConstants.API_RETURN_STATUS.LOGICAL_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.LOGICAL_ERROR.desc());
		}
		return results;
	}
	
}
