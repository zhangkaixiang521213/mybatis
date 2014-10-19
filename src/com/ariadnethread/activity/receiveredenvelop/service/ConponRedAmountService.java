
package com.ariadnethread.activity.receiveredenvelop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariadnethread.activity.receiveredenvelop.dao.mybatis.ConponRedAmountDao;
import com.ariadnethread.activity.receiveredenvelop.domain.ConponRedAmount;

@Service
@Transactional
public class ConponRedAmountService {

	private static Logger logger = LoggerFactory.getLogger(ConponRedAmountService.class);

	@Autowired
	private ConponRedAmountDao conponRedAmountDao;
	
	/**
	 * 随机抽取金额 
	 * @param activityId 活动主键
	 * @return
	 */
	public ConponRedAmount searchRandomAmount(long activityId){
		return conponRedAmountDao.searchRandomAmount(activityId);
	}
	
	/**
	 * 新增红包金额记录
	 * @param conponRedAmount 实体对象
	 * @return
	 */
	public void save(ConponRedAmount conponRedAmount){
		conponRedAmountDao.save(conponRedAmount);
	}
	
	/**
	 * 修改红包抽取量
	 * @param id 记录主键
	 * @return
	 */
	public void  update(Long id){
		conponRedAmountDao.update(id);
	}
	
}
