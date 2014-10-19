
package com.ariadnethread.coupon.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class CouponUser extends DomainBase{
	
	private long id;
	private long couponId;
	private long userContentUid;
	private long activityId;
	

	public CouponUser() {
		
	}

	

	public long getActivityId() {
		return activityId;
	}



	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getCouponId() {
		return couponId;
	}



	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}



	public long getUserContentUid() {
		return userContentUid;
	}



	public void setUserContentUid(long userContentUid) {
		this.userContentUid = userContentUid;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}