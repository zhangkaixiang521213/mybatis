
package com.ariadnethread.coupon.dao.mybatis;

import java.util.List;

import com.ariadnethread.coupon.domain.Coupon;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface CouponDao {
 
	/**
	 * 按外键couponInfoId查询记录列表
	 * @param couponInfoId
	 * @return 记录列表
	 */
	public List<Coupon> findByCouponInfoId(long couponInfoId);
	
	/**
	 * 修改剩余金额为指定金额
	 * @param id 记录ID
	 * @param minusValue 减去的指定金额
	 */
	public void updateRemainValueToSpecify(long id,float minusValue);
	
	/**
	 * 修改剩余金额为加指定金额
	 * @param id 记录ID
	 * @param addValue 加指定金额
	 */
	public void updateRemainValueAddSpecify(long id,float addValue);
	
	/**
	 * 修改剩余金额为0
	 * @param item 主键集合
	 */
//	public void updateRemainValueToZero(List<Long> list);
	public void updateRemainValueToZero(String ids);
	
	/**
	 * 查询已领可支付红包
	 * @param userContentId 用户ID
	 * @param activityId 活动ID
	 * @return 记录列表
	 */
	public List<Coupon> findByUserContentId(long userContentId,long activityId);
	
	/**
	 * 保存记录
	 * @param coupon 记录对象
	 */
	public void save(Coupon coupon);
	
	/**
	 * 统计领红包记录金额
	 * @param userContentId 用户id
	 * @param activityId 活动ID
	 * @return 红包金额
	 */
	public Float countCardValueByUserContentId(long userContentId,long activityId);
	
	/**
	 * 统计已领红包可用记录金额
	 * @param userContentId 用户id
	 * @param activityId 活动ID
	 * @return 剩余红包金额
	 */
	public Float countRemainValueByUserContentId(long userContentId,long activityId);
	
}
