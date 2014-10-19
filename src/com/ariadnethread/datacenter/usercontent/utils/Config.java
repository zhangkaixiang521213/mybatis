package com.ariadnethread.datacenter.usercontent.utils;

import core.utils.PropertiesUtil;

public class Config {
	
	public final static long sessionValidTimes = 7*24*60*60*1000;
	public final static long deviceTokenValidTimes = 1*60*1000;
	public final static long seatValidMins = 15;
	
	public final static String HOTLINE = "4000009666";
	public final static String PAY_CENTER_ENCODING = "utf-8";
	public final static String USER_CENTER_ENCODING = "utf-8";

	public final static int defaultChannelId = 9;

	private boolean _debug = true;
	private boolean _buyTrueTicket = false;
	private boolean _lockTrueSeat = true;
	private boolean _enableCache = true;
	
	private String _serverUrl, _qrUrl;
	private String _userCenterUrl, _userCenterCode, _userCenterKey;
	private String _payCenterUrl, _payNotifyUrl;
		
	private String _localStoragePath, _mediaServerUrl;
	private String _cacheFilePath;

	public boolean isDebuging () {
		return _debug;		
	}

	public boolean buyTrueTicket() {
		return _buyTrueTicket;
	}
	
	public boolean lockTrueSeat() {
		return _lockTrueSeat;
	}
	
	public boolean enableCache() {
		return _enableCache;
	}
	
	public String serverUrl() {
		return _serverUrl;
	}
	
	public String qrUrl() {
		return _qrUrl;
	}
	
	public String payCenterUrl() {
		return _payCenterUrl;
	}

	public String payNotifyUrl() {
		return _payNotifyUrl;
	}
	
	public String localStoragePath() {
		return _localStoragePath;
	}
	
	public String mediaServerUrl() {
		return _mediaServerUrl;
	}
	
	public String cacheFilePath() {
		return _cacheFilePath;
	}
	
	public String userCenterUrl() {
		return _userCenterUrl;
	}
	
	public String userCenterCode() {
		return _userCenterCode;
	}
	
	public String userCenterKey() {
		return _userCenterKey;
	}
	
	private static Config uniqueInstance = null;
	
	private Config() {
		try {
			String debug=PropertiesUtil.getPropByKey("debug");
			if(debug!=null && "true".equalsIgnoreCase(debug)){
				_debug = true;
			}else{
				_debug = false;
			}
		} catch (Exception e) {
			_debug = true;
			System.err.println("****can not load context****");
			//e.printStackTrace();
		} finally {
			
		}
		
		if (_debug) {
			_buyTrueTicket = false;
			_lockTrueSeat = false;
			_enableCache = true;

			_serverUrl = "http://test.komovie.cn/api_movie/service?";
			_qrUrl = "http://test.komovie.cn/api_movie/qr?";
			_payNotifyUrl = "http://test.komovie.cn/api_movie/service?action=order_callback";
			
			_userCenterUrl = "http://121.199.43.143:8081/user/services.jsp?";
			_userCenterCode = "100100101";
			_userCenterKey = "LGD!S2ds";
			
			//_payCenterUrl = "http://127.0.0.1/pay/service?";
			//_payCenterUrl = "http://pay.kokozu.cn/service?";
			_payCenterUrl = "http://121.199.43.143:8081/pay/service?";

			_localStoragePath = "/data/media/";
			_cacheFilePath = "/data/cache/";
			_mediaServerUrl = "http://media.kokozu.net/";
		} else {
			_buyTrueTicket = true;
			_lockTrueSeat = true;
			_enableCache = true;
			
			_serverUrl = "http://api.komovie.cn/movie/service?";
			_qrUrl = "http://api.komovie.cn/movie/qr?";
			_payNotifyUrl = "http://api.komovie.cn/movie/service?action=order_callback";

			_userCenterUrl = "http://121.199.43.143:8080/user_center/services.jsp?";
			_userCenterCode = "100100101";
			_userCenterKey = "LGD!S2ds";
			
			//_payCenterUrl = "http://115.29.193.101:8080/api_pay/service?";
			_payCenterUrl = "http://121.199.43.143:8080/pay/service?";

			_localStoragePath = "/data/media/";
			_cacheFilePath = "/data/cache/";
			_mediaServerUrl = "http://media.kokozu.net/";
		}

	}
	
    private static synchronized void syncInit() {  
        if (uniqueInstance == null) {
        	uniqueInstance = new Config();
        }
    }

	public static synchronized Config getInstance() {
		if (uniqueInstance == null) {
			syncInit();
		}
		return uniqueInstance;
	}

	public static void main (String[] args) {

	}
}
