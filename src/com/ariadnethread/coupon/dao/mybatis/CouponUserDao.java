
package com.ariadnethread.coupon.dao.mybatis;

import com.ariadnethread.coupon.domain.CouponUser;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface CouponUserDao {
 
	/**
	 * 保存记录
	 * @param couponUser 记录对象
	 */
	public void save(CouponUser couponUser);
	
}
