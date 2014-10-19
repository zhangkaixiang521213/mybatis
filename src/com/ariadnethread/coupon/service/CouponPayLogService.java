
package com.ariadnethread.coupon.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariadnethread.activity.receiveredenvelop.utils.ReceiveConstants;
import com.ariadnethread.coupon.dao.mybatis.CouponPayLogDao;
import com.ariadnethread.coupon.domain.CouponPayLog;
import com.ariadnethread.coupon.utils.CouponConstants;
import com.ariadnethread.utils.Results;

import core.utils.ComUtil;

@Service
@Transactional
public class CouponPayLogService {

	private static Logger logger = LoggerFactory.getLogger(CouponPayLogService.class);

	@Autowired
	private CouponPayLogDao ouponPayLogDao;
	
	/**
	 * 按订单号查询记录
	 * @param orderNo 订单号
	 * @return 记录列表
	 */
	public List<CouponPayLog> findByOrderNo(String orderNo){
		return ouponPayLogDao.findByOrderNo(orderNo);
	}
	
	/**
	 * 增加券支付日志
	 * @param couponPayLog 记录对象
	 * @param 
	 */
	public void save(CouponPayLog couponPayLog){
		ouponPayLogDao.save(couponPayLog);
	}
	
	/**
	 * 增加券支付日志(批量提交)
	 * @param list 记录对象列表
	 * @param 
	 */
	public void saveBatch(List<CouponPayLog> list){
		ouponPayLogDao.saveBatch(list);
	}
	
	/**
	 * 按订单号和状态查询记录
	 * @param orderNo 订单号
	 * @param status 状态
	 * @return 记录列表
	 */
	public List<CouponPayLog> findByOrderNoAndStatus(String orderNo,int status){
		return ouponPayLogDao.findByOrderNoAndStatus(orderNo, status);
	}
	
	/**
	 * 修改为退款状态
	 * @param orderNo 订单号
	 * @param status 状态
	 */
	public void updateStatusToRefund(String orderNo,int status){
		ouponPayLogDao.updateStatusToRefund(orderNo, status);
	}
	
	/**
	 * 统计当天还可支付金额
	 * @return 金额
	 */
	public Results todayCanPayMoney(){
		
		Float todayPayMoney=ouponPayLogDao.countPayMoneyByDate(CouponConstants.PAY_STATUS.PAY.value(), ComUtil.Time2StringYMDHMS(new Date()));
		
		float todayPayOnly=0;
		if(todayPayMoney!=null){
			todayPayOnly=CouponConstants.DAY_MAX_ACCOUNT-todayPayMoney;
		}
		
		Map<String,Float> map=new HashMap<String,Float>();
		map.put("todayCanPayMoney", todayPayOnly);

		return new Results(ReceiveConstants.API_RETURN_STATUS.NORMAL.value(),ReceiveConstants.API_RETURN_STATUS.NORMAL.desc(),map);

	}
	
}
