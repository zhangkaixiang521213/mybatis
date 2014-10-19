
package com.ariadnethread.coupon.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class CouponPayLog extends DomainBase{
	
	private long id;
	private long userId;
	private long activityId;
	private String orderNo;
	private long couponId;
	private float payValue;
	private String description;
	private Date createTime;
	private int status;
	private long redEnvelopId;
	private float remainValue;
	private Date payTime;


	

	public CouponPayLog() {
		
	}
	
	



	public float getRemainValue() {
		return remainValue;
	}





	public void setRemainValue(float remainValue) {
		this.remainValue = remainValue;
	}





	public Date getPayTime() {
		return payTime;
	}



	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}



	public long getRedEnvelopId() {
		return redEnvelopId;
	}



	public void setRedEnvelopId(long redEnvelopId) {
		this.redEnvelopId = redEnvelopId;
	}

	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public long getActivityId() {
		return activityId;
	}



	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}



	public String getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}



	public long getCouponId() {
		return couponId;
	}



	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}



	public float getPayValue() {
		return payValue;
	}



	public void setPayValue(float payValue) {
		this.payValue = payValue;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}