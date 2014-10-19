
package com.ariadnethread.activity.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class Activity extends DomainBase{
	
	private long aid;
	private String activityContent;
	private String description;
	private String content;
	private String picSmall;
	private String picMid;
	private String picBig;
	private String activityUrl;
	private int activityType;
	private String orderType;
	private int needRegister;
	private String platforms;
	private String cityIds;
	private String cinemaIds;
	private String filmIds;
	private String quoteIds;
	private String screenType;
	private String couponIds;
	private float activityPrice;
	private int showType;
	private Date startTime;
	private Date endTime;
	private Date topBeginTime;
	private Date topEndTime;
	private int activityCount;
	private int activityRemain;
	private int cntPerOrder;
	private int giftPerGood;
	private int orderPerUser;
	private int totalPerUser;
	private int canBuyMore;
	private int fakeCount;
	private String channelGroups;
	private String channelIds;
	private long uid;
	private int visible;
	private Date createTime;
	private int del;
	private int cntPerUser;
	private int isvip;
	private int expireDays;

	public Activity() {
		
	}
	

	public int getExpireDays() {
		return expireDays;
	}



	public void setExpireDays(int expireDays) {
		this.expireDays = expireDays;
	}



	public long getAid() {
		return aid;
	}



	public void setAid(long aid) {
		this.aid = aid;
	}



	public String getActivityContent() {
		return activityContent;
	}



	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getPicSmall() {
		return picSmall;
	}



	public void setPicSmall(String picSmall) {
		this.picSmall = picSmall;
	}



	public String getPicMid() {
		return picMid;
	}



	public void setPicMid(String picMid) {
		this.picMid = picMid;
	}



	public String getPicBig() {
		return picBig;
	}



	public void setPicBig(String picBig) {
		this.picBig = picBig;
	}



	public String getActivityUrl() {
		return activityUrl;
	}



	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}



	public int getActivityType() {
		return activityType;
	}



	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}



	public String getOrderType() {
		return orderType;
	}



	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}



	public int getNeedRegister() {
		return needRegister;
	}



	public void setNeedRegister(int needRegister) {
		this.needRegister = needRegister;
	}



	public String getPlatforms() {
		return platforms;
	}



	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}



	public String getCityIds() {
		return cityIds;
	}



	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}



	public String getCinemaIds() {
		return cinemaIds;
	}



	public void setCinemaIds(String cinemaIds) {
		this.cinemaIds = cinemaIds;
	}



	public String getFilmIds() {
		return filmIds;
	}



	public void setFilmIds(String filmIds) {
		this.filmIds = filmIds;
	}



	public String getQuoteIds() {
		return quoteIds;
	}



	public void setQuoteIds(String quoteIds) {
		this.quoteIds = quoteIds;
	}



	public String getScreenType() {
		return screenType;
	}



	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}



	public String getCouponIds() {
		return couponIds;
	}



	public void setCouponIds(String couponIds) {
		this.couponIds = couponIds;
	}



	public float getActivityPrice() {
		return activityPrice;
	}



	public void setActivityPrice(float activityPrice) {
		this.activityPrice = activityPrice;
	}



	public int getShowType() {
		return showType;
	}



	public void setShowType(int showType) {
		this.showType = showType;
	}



	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public Date getEndTime() {
		return endTime;
	}



	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	public Date getTopBeginTime() {
		return topBeginTime;
	}



	public void setTopBeginTime(Date topBeginTime) {
		this.topBeginTime = topBeginTime;
	}



	public Date getTopEndTime() {
		return topEndTime;
	}



	public void setTopEndTime(Date topEndTime) {
		this.topEndTime = topEndTime;
	}



	public int getActivityCount() {
		return activityCount;
	}



	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}



	public int getActivityRemain() {
		return activityRemain;
	}



	public void setActivityRemain(int activityRemain) {
		this.activityRemain = activityRemain;
	}



	public int getCntPerOrder() {
		return cntPerOrder;
	}



	public void setCntPerOrder(int cntPerOrder) {
		this.cntPerOrder = cntPerOrder;
	}



	public int getGiftPerGood() {
		return giftPerGood;
	}



	public void setGiftPerGood(int giftPerGood) {
		this.giftPerGood = giftPerGood;
	}



	public int getOrderPerUser() {
		return orderPerUser;
	}



	public void setOrderPerUser(int orderPerUser) {
		this.orderPerUser = orderPerUser;
	}



	public int getTotalPerUser() {
		return totalPerUser;
	}



	public void setTotalPerUser(int totalPerUser) {
		this.totalPerUser = totalPerUser;
	}



	public int getCanBuyMore() {
		return canBuyMore;
	}



	public void setCanBuyMore(int canBuyMore) {
		this.canBuyMore = canBuyMore;
	}



	public int getFakeCount() {
		return fakeCount;
	}



	public void setFakeCount(int fakeCount) {
		this.fakeCount = fakeCount;
	}



	public String getChannelGroups() {
		return channelGroups;
	}



	public void setChannelGroups(String channelGroups) {
		this.channelGroups = channelGroups;
	}



	public String getChannelIds() {
		return channelIds;
	}



	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}



	public long getUid() {
		return uid;
	}



	public void setUid(long uid) {
		this.uid = uid;
	}



	public int getVisible() {
		return visible;
	}



	public void setVisible(int visible) {
		this.visible = visible;
	}



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



	public int getCntPerUser() {
		return cntPerUser;
	}



	public void setCntPerUser(int cntPerUser) {
		this.cntPerUser = cntPerUser;
	}



	public int getIsvip() {
		return isvip;
	}



	public void setIsvip(int isvip) {
		this.isvip = isvip;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}