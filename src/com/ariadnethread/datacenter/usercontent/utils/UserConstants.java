package com.ariadnethread.datacenter.usercontent.utils;


public class UserConstants {
	
	/**
	 * 注册用户状态
	 */
	public enum REGISTER_USER_STATUS{
		USER_EXISTS(-1,"用户已存在"),
		USER_CREATED(0,"用户创建成功"),
		USER_FAILED(-2,"用户注册失败");
		
		private int value;
		private String desc;
		REGISTER_USER_STATUS(int value,String desc){
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
