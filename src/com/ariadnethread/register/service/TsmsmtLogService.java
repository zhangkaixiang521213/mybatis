
package com.ariadnethread.register.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariadnethread.register.dao.mybatis.TsmsmtLogDao;
import com.ariadnethread.register.domain.TsmsmtLog;
import com.ariadnethread.utils.SMSSenderContants;

@Service
@Transactional
public class TsmsmtLogService {

	private static Logger logger = LoggerFactory.getLogger(TsmsmtLogService.class);

	@Autowired
	private TsmsmtLogDao tsmsmtLogDao;
	
	
	/**
	 * 保存记录
	 * @param mobile :手机号
	 * @param content 短信内容
	 * @param validCode 验证码
	 * @return
	 */
	public void save(String content,String mobile,String validCode){
		
		TsmsmtLog tsmsmtLog=new TsmsmtLog();
		tsmsmtLog.setContent(content);
		tsmsmtLog.setMobile(mobile);
		tsmsmtLog.setCreateTime(new Date());
		tsmsmtLog.setStatus(1);
		tsmsmtLog.setType(SMSSenderContants.SMSType.RED_ENVOLOP.value());
		tsmsmtLog.setValidCode(validCode);
		tsmsmtLogDao.save(tsmsmtLog);
		
	}
	
	/**
	 * 按电话号码、类型查询记录
	 * @param mobile 电话号码
	 * @param type 类型
	 * @return TsmsmtLog
	 */
	public List<TsmsmtLog> searchByMobileType(String mobile,String type){
		return tsmsmtLogDao.searchByMobileType(mobile,type);
	}
	
	
}
