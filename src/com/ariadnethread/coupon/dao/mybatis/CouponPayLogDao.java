
package com.ariadnethread.coupon.dao.mybatis;

import java.util.List;

import com.ariadnethread.coupon.domain.CouponPayLog;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface CouponPayLogDao {
 
	/**
	 * 按订单号查询记录
	 * @param orderNo 订单号
	 * @return 记录列表
	 */
	public List<CouponPayLog> findByOrderNo(String orderNo);
	
	/**
	 * 按订单号和状态查询记录
	 * @param orderNo 订单号
	 * @param status 状态
	 * @return 记录列表
	 */
	public List<CouponPayLog> findByOrderNoAndStatus(String orderNo,int status);
	
	/**
	 * 增加券支付日志
	 * @param couponPayLog 记录对象
	 * @param 
	 */
	public void save(CouponPayLog couponPayLog);
	
	/**
	 * 增加券支付日志(批量提交)
	 * @param list 记录对象列表
	 * @param 
	 */
	public void saveBatch(List<CouponPayLog> list);
	
	/**
	 * 统计指定天支付总额度
	 * @param status 状态为已支付
	 * @param payTime 支付时间
	 * @return 金额
	 */
	public Float countPayMoneyByDate(int status,String payTime);
	
	/**
	 * 修改为退款状态
	 * @param orderNo 订单号
	 * @param status 状态
	 */
	public void updateStatusToRefund(String orderNo,int status);
	
}
