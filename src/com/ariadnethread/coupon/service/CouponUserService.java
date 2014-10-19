
package com.ariadnethread.coupon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariadnethread.coupon.dao.mybatis.CouponUserDao;
import com.ariadnethread.coupon.domain.CouponUser;

@Service
@Transactional
public class CouponUserService {

	private static Logger logger = LoggerFactory.getLogger(CouponUserService.class);

	@Autowired
	private CouponUserDao couponUserDao;
	
	/**
	 * 保存记录
	 * @param couponUser 记录对象
	 * @param activityId 活动ID
	 * return id 记录id
	 */
	public void save(long couponId,long userContentUid,long activityId){
		
		CouponUser couponUser=new CouponUser();
		couponUser.setCouponId(couponId);
		couponUser.setUserContentUid(userContentUid);
		couponUser.setActivityId(activityId);
		
		couponUserDao.save(couponUser);
		
	}
	
}
