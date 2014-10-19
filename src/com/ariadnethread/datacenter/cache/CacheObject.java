package com.ariadnethread.datacenter.cache;


public class CacheObject {
	
	private Object Value = null;
	private int validMins = 0;
	
	private long createTime = 0;
	private long accessTime = 0;
	
	public Object value() {
		return Value;
	}

	public void value(Object value) {
		Value = value;
	}

	public int validMins() {
		return validMins;
	}

	public void validMins(int validMins) {
		this.validMins = validMins;
	}
	
	public long accessTime() {
		return accessTime;
	}

	public void accessTime(long accessTime) {
		this.accessTime = accessTime;
	}
	
	@SuppressWarnings("unused")
	private CacheObject () {
	
	}
	
	public CacheObject (Object value, int validMins) {
		createTime = System.currentTimeMillis();
		this.value(value);
		this.validMins(validMins);
	}

	public boolean isValid () {
		return createTime + validMins*60*1000 - System.currentTimeMillis() > 0;
	}
}
