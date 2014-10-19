
package com.ariadnethread.activity.receiveredenvelop.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class ConponRedAmount extends DomainBase{
	
	private long id;
	private long activityId;
	private float redAmount;
	private int quantity;
	private int receivedQuantity;
	private float totalAmount;
	private String description;
	private Date createTime;

	public ConponRedAmount() {
		
	}
	
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getActivityId() {
		return activityId;
	}



	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}



	public float getRedAmount() {
		return redAmount;
	}



	public void setRedAmount(float redAmount) {
		this.redAmount = redAmount;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getReceivedQuantity() {
		return receivedQuantity;
	}



	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}



	public float getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
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