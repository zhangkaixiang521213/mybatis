
package com.ariadnethread.activity.receiveredenvelop.dao.mybatis;

import java.util.List;

import com.ariadnethread.activity.receiveredenvelop.domain.ConponRedAmount;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface ConponRedAmountDao {
 
	/**
	 * 随机抽取金额 
	 * @param activityId 活动主键
	 * @return
	 */
	public ConponRedAmount searchRandomAmount(long activityId);
	
	/**
	 * 新增红包金额记录
	 * @param conponRedAmount 实体对象
	 * @return
	 */
	public void save(ConponRedAmount conponRedAmount);
	
	/**
	 * 新增红包金额记录(批量提交)
	 * @param list 实体对象集合
	 * @return
	 */
	public void saveBatch(List<ConponRedAmount> list);
	
	/**
	 * 修改红包抽取量
	 * @param id 记录主键
	 * @return
	 */
	public void  update(long id);
	
}
