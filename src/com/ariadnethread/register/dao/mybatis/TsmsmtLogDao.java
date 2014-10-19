
package com.ariadnethread.register.dao.mybatis;

import java.util.List;

import com.ariadnethread.register.domain.TsmsmtLog;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface TsmsmtLogDao {
 
	/**
	 * 按电话号码、类型查询记录
	 * @param mobile 电话号码
	 * @param type 类型
	 * @return TsmsmtLog
	 */
	public List<TsmsmtLog> searchByMobileType(String mobile,String type);
	
	/**
	 * 保存记录
	 * @param tsmsmtLog 对象
	 * @return void
	 */
	public void save(TsmsmtLog tsmsmtLog);
	
}
