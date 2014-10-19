package zhangkaix.mybatistt.utils;

public class MytestConstants {
	
	public static String DESCRIPTION="红包滚滚向我来，谢了，Happy!";
	//1张订单限制券最大可支付金额
	public static float SINGLE_ORDER_MAXIMUM_AVALIABLE_MONEY=5;
	//可领红包最大记录数
	public static int MAXIMUM_RECEIVE_COUNT=15;
	//测试短信码
	public static String TEST_MOBILE_CODE="111111";
	
	/**
	 * 领红包记录表：coupon_red_envelop
	 * 字段：is_pay 支付状态
	 * 值域:0未支付，1是已支付
	 * 注：加状态log以便统计总数
	 */
	public enum RED_ENVELOP_IS_PAY{
		NOT_PAY(0,"未支付"),
		YES_PAY(1,"已支付");
		
		private int value;
		private String desc;
		RED_ENVELOP_IS_PAY(int value,String desc){
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

	/**
	 * 领红包记录表：coupon_red_envelop
	 * 字段：status 记录状态
	 * 值域:1未领取,2已领取,3日志
	 * 注：加状态log以便统计总数
	 */
	public enum RED_ENVELOP_STATUS{
		UNRECEIVE(1,"未领取"),
		RECEIVED(2,"已领取"),
		LOG(3,"日志");
		
		private int value;
		private String desc;
		RED_ENVELOP_STATUS(int value,String desc){
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
	
	/**
	 * 接口调用返回状态码
	 */
	public enum API_RETURN_STATUS{
		NORMAL("0","正常"),
		PARA_ERROR("1","参数错误"),
		LOGICAL_ERROR("2","逻辑错误"),
		OVER_MAXIMUM_COUPON("3","超过券最大可支付金额5元"),
		OVER_MAXIMUM_RECEIVE("4","最大可领取"+MAXIMUM_RECEIVE_COUNT+"个"),
		RECEIVED("5","红包已领取"),
		OTHER("6","其它错误"),
		MOBILE_CODE_EXPIRE_SENDED("8","发送短信频率过快"),
		WECHAT_NOT_RELATE("9","微信号没有与抠电影账号关联"),
		REGISTER_USER_FAILED("10","注册用户失败"),
		MOBILE_CODE_OVER_TIME("11","您所输入的验证码错误，请重新输入"),
		MOBILE_CODE_VALIDATE_ERROR("12","验证错误"),
		PARAMETER_MD5_ERROR("13","parameter md5 encode error"),
		WEIXIN_INVOKE_ERROR("14","微信调用出错"),
		NO_USER("15","没有查询到用户"),
		ORDER_PAYED("16","订单已支付"),
		NO_REFUND("17","没有已退票记录"),
		PAY_AMOUNT_OVER_REMAIN_VALUE("18","支付金额不能大于可用金额"),
		USER_RECEIVE_BIND("19","该用户已绑定过红包记录"),
		PAY_NOT_MONEY("20","没有可支付金额"),
		RED_AMOUNT_USED("21","红包金额已用完"),
		MOBILE_VALIDATE_ERROR("22","手机号不合法");
		
		private String value;
		private String desc;
		API_RETURN_STATUS(String value,String desc){
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
}
