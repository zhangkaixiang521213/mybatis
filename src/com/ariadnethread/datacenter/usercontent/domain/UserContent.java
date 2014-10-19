
package com.ariadnethread.datacenter.usercontent.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class UserContent extends DomainBase{
	
	private long uid;
	private String userName;
	private float vipAccount;
	private String lastIP;
	private String lastSession;
	private Date loginTime;
	private String alipayId;
	private String channel;
	private String location;
	private Date createTime;
	private int del;

	public UserContent() {
		
	}
	
	

	public long getUid() {
		return uid;
	}



	public void setUid(long uid) {
		this.uid = uid;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public float getVipAccount() {
		return vipAccount;
	}



	public void setVipAccount(float vipAccount) {
		this.vipAccount = vipAccount;
	}



	public String getLastIP() {
		return lastIP;
	}



	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}



	public String getLastSession() {
		return lastSession;
	}



	public void setLastSession(String lastSession) {
		this.lastSession = lastSession;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getLoginTime() {
		return loginTime;
	}



	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}



	public String getAlipayId() {
		return alipayId;
	}



	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}



	public String getChannel() {
		return channel;
	}



	public void setChannel(String channel) {
		this.channel = channel;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public int getDel() {
		return del;
	}



	public void setDel(int del) {
		this.del = del;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}