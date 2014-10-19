
package com.ariadnethread.activity.receiveredenvelop.dao.mybatis;

import java.util.List;

import com.ariadnethread.activity.receiveredenvelop.domain.ConponRedEnvelop;
import com.ariadnethread.activity.receiveredenvelop.utils.ReceiveConstants;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface ConponRedEnvelopDao {
 
	/**
	 * 通过Id获取一条记录
	 * @param id 主键
	 * @return
	 */
	public ConponRedEnvelop findById(long id);
	
	/**
	 * 按用户userContentId查询记录列表
	 * @param userContentId 用户主键（外键）
	 * @param activityId 活动主键（外键）
	 * @param statusUnrecevive 未领取
	 * @param statusReceived 已领取
	 * @param openId 微信openId
	 * @return
	 */
	public List<ConponRedEnvelop> searchByUserContentId(long userContentId,long activityId,int statusUnrecevive,int statusReceived,String openId);
	
	/**
	 * 按用户userContentId、sourceUserId查询记录列表
	 * @param userContentId 用户主键（外键）
	 * @param sourceUserId 源用户id（转发用户）
	 * @param activityId 活动主键（外键）
	 * @return
	 */
	public List<ConponRedEnvelop> searchBySourceUserId(long userContentId,long sourceUserId,long activityId);
	
	/**
	 * 按用户userContentId、openId查询记录列表
	 * @param userContentId 用户主键（外键）
	 * @param openId 微信用户ID
	 * @param activityId 活动主键（外键）
	 * @return
	 */
	public List<ConponRedEnvelop> searchByWeixinOpenId(long userContentId,String openId,long activityId);
	
	/**
	 * 统计领红包记录
	 * @param userContentId 用户主键（外键）
	 * @param activityId 活动主键（外键）
	 * @param statusUnrecevive 未领取
	 * @param statusReceived 已领取
	 * @return
	 */
	public int countByUserContentId(long userContentId,long activityId,int statusUnrecevive,int statusReceived);
	
	/**
	 * 统计发出红包记录
	 * @param userContentId 用户主键（外键）
	 * @param activityId 活动主键（外键）
	 * @return
	 */
	public int countByDistribute(long userContentId,long activityId);
	
	/**
	 * 修改领红包记录
	 * @param payStatus 支付状态
	 * @param redEnvelopId 红包记录ID集
	 * @return
	 */
	public int updatePayStatus(int payStatus,String redEnvelopId);
	
	/**
	 * 按用户userContentId、status查询记录列表
	 * @param userContentId 用户主键（外键）
	 * @param activityId 活动主键（外键）
	 * @param status 记录状态
	 * @return
	 */
	public List<ConponRedEnvelop> searchByUserContentIdStatus(long userContentId,long activityId,int status);
	
	/**
	 * 保存一条记录
	 * @param Coupon ：记录对象实体
	 */
	public void save(ConponRedEnvelop conponRedEnvelop);
	
	/**
	 * 修改记录
	 * @param Coupon ：记录对象实体
	 */
	public void update(ConponRedEnvelop conponRedEnvelop);
	
}
