
package com.ariadnethread.activity.dao.mybatis;

import java.util.List;

import com.ariadnethread.activity.domain.Activity;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface ActivityDao {
 
	/**
	 * 通过Id获取一条记录
	 * @param id 主键
	 * @return
	 */
	public Activity findById(String id);
	
	/**
	 * 按活动类型查询记录列表
	 * @param activityType
	 * @return
	 */
	public List<Activity> findByActivityType(String activityType);
	
}
