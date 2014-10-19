
package com.ariadnethread.activity.receiveredenvelop.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class ConponRedEnvelop extends DomainBase{
	
	private long id;
	private long userContentId;
	private long activityId;
	private String userName;
	private float redAmount;
	private int status;
	private String description;
	private Date createTime;
	private String wechatUid;
	private String nickName;
	private String headImgUrl;
	private String sex;
	private Date expireDate;
	private long sourceUserId;
	private String openId;
	private long isPay;
	
	public ConponRedEnvelop() {
		
	}
	
	
	
	public long getIsPay() {
		return isPay;
	}



	public void setIsPay(long isPay) {
		this.isPay = isPay;
	}



	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public long getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public String getWechatUid() {
		return wechatUid;
	}



	public void setWechatUid(String wechatUid) {
		this.wechatUid = wechatUid;
	}



	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public String getHeadImgUrl() {
		return headImgUrl;
	}



	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getUserContentId() {
		return userContentId;
	}



	public void setUserContentId(long userContentId) {
		this.userContentId = userContentId;
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



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
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