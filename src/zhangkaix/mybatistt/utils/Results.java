package zhangkaix.mybatistt.utils;

public class Results {
	/**
	 * 错误代码
	 */
	private String errCode;
	
	/**
	 * 错误描述
	 */
	private String errMsg;
	
	/**
	 * 返回结果对象
	 */
	private Object data;
	
	public Results() {
		super();
	}
	
	public Results(String errCode, String errMsg, Object data) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.data = data;
	}
	
	

	public Results(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.data = null;
	}



	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
