
package com.ariadnethread.activity.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariadnethread.activity.dao.mybatis.ActivityDao;
import com.ariadnethread.activity.domain.Activity;
import com.ariadnethread.datacenter.cache.MemCacheManager;
import com.ariadnethread.datacenter.usercontent.domain.UserContent;

@Service
@Transactional
public class ActivityService {

	private static Logger logger = LoggerFactory.getLogger(ActivityService.class);

	@Autowired
	private ActivityDao ativityDao;
	
	/**
	 * 通过Id获取一条记录
	 * @param aid 主键
	 * @return
	 */
	public Activity findById(String aid) {
		Activity activity=(Activity)MemCacheManager.getInstance().getCacheForKey("activity_aid_"+aid);
		if(activity==null){
			activity=ativityDao.findById(aid);
			MemCacheManager.getInstance().setCacheForKey("activity_aid_"+aid, activity, 60);
		}
		return activity;
	}
	
	/**
	 * 按活动类型查询记录列表
	 * @param activityType:活动类型
	 * @return
	 */
	public List<Activity> findByActivityType(String activityType){
		return ativityDao.findByActivityType(activityType);
	}
	
}
