
package com.ariadnethread.coupon.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class Coupon extends DomainBase{
	
	private long id;
	private String couponId;
	private String couponNum;
	private String couponEncode;
	private long type;
	private int sold;
	private int cardCount;
	private long remainCount;
	private float cardValue;
	private float remainValue;
	private Date expireDate;
	private Date createTime;
	private String maker;
	private String note;
	private long channelId;
	private int del;
	private long redEnvelopId;

	public Coupon() {
		
	}
	
	

	public long getRedEnvelopId() {
		return redEnvelopId;
	}



	public void setRedEnvelopId(long redEnvelopId) {
		this.redEnvelopId = redEnvelopId;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getCouponId() {
		return couponId;
	}



	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}



	public String getCouponNum() {
		return couponNum;
	}



	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}



	public String getCouponEncode() {
		return couponEncode;
	}



	public void setCouponEncode(String couponEncode) {
		this.couponEncode = couponEncode;
	}



	public long getType() {
		return type;
	}



	public void setType(long type) {
		this.type = type;
	}



	public int getSold() {
		return sold;
	}



	public void setSold(int sold) {
		this.sold = sold;
	}



	public int getCardCount() {
		return cardCount;
	}



	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}



	public long getRemainCount() {
		return remainCount;
	}



	public void setRemainCount(long remainCount) {
		this.remainCount = remainCount;
	}



	public float getCardValue() {
		return cardValue;
	}



	public void setCardValue(float cardValue) {
		this.cardValue = cardValue;
	}



	public float getRemainValue() {
		return remainValue;
	}



	public void setRemainValue(float remainValue) {
		this.remainValue = remainValue;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getExpireDate() {
		return expireDate;
	}



	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public String getMaker() {
		return maker;
	}



	public void setMaker(String maker) {
		this.maker = maker;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public long getChannelId() {
		return channelId;
	}



	public void setChannelId(long channelId) {
		this.channelId = channelId;
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