package com.ariadnethread.register.domain;

import java.util.Date;

import core.mybatis.MyBatisDomain;
@MyBatisDomain
public class UserWechatRelate {

	private long id;
	private String mobile;
	private String wechatUid;
	private String description;
	private Date createTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getWechatUid() {
		return wechatUid;
	}
	public void setWechatUid(String wechatUid) {
		this.wechatUid = wechatUid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
