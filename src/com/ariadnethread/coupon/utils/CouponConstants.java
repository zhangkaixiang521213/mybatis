package com.ariadnethread.coupon.utils;

public class CouponConstants {

	/**
	 * 抢票活动卷可用次数
	 */
	public static int RECEIVE_RED_ENVELOP_CARD_COUNT=1;
	
	/**
	 * 领红包活动
	 */
	public static String COUPON_NOTE="领红包活动";
	
	/**
	 * 一天最大可用券金额
	 */
	public static float DAY_MAX_ACCOUNT=25;
	
	/**
	 * 卷激活状态
	 * 字段：sold 记录状态
	 * 值域:1激活, 0 未激活
	 */
	public enum COUPON_SOLD{
		ACTIVATE("1","激活"),
		UNACTIVATE("0","未激活");
		
		private String value;
		private String desc;
		COUPON_SOLD(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		public String value(){
			return this.value;
		}
		public String desc(){
			return this.desc;
		}
	}
	
	/**
	 * 券支付日志状态
	 * 字段：status 记录状态
	 * 值域:1为支付,2为退款
	 */
	public enum PAY_STATUS{
		PAY(1,"支付"),
		REFUND(2,"退款");
		
		private int value;
		private String desc;
		PAY_STATUS(int value,String desc){
			this.value=value;
			this.desc=desc;
		}
		public int value(){
			return this.value;
		}
		public String desc(){
			return this.desc;
		}
	}
}
