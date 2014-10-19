package test.receiveredendelop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ariadnethread.coupon.dao.mybatis.CouponDao;
import com.ariadnethread.coupon.domain.CouponPayLog;
import com.ariadnethread.coupon.service.CouponPayLogService;

import core.utils.jackson.JacksonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ConponPayLogServiceTest extends AbstractJUnit4SpringContextTests{
	private static Logger logger = LoggerFactory.getLogger(ConponPayLogServiceTest.class);
	
//	@Resource
//	CouponPayLogService couponPayLogService;
	
	@Resource
	private CouponDao couponDao;
	
	@Test
    public void saveTest() {
		List<Long> delList=new ArrayList<Long>();
		delList.add(1L);
		String s="";
		for(Long l:delList){
			s+=l+",";
		}
		if(!"".equals(s)){
			s=s.substring(0, s.length()-1);
		}
		couponDao.updateRemainValueToZero(s);
//		List<CouponPayLog> list=new ArrayList<CouponPayLog>();
//		CouponPayLog cp=new CouponPayLog();
//		cp.setActivityId(1);
//		cp.setCouponId(1);
//		cp.setCreateTime(new Date());
//		cp.setOrderNo("1");
//		cp.setPayValue(2.99F);
//		cp.setUserId(1);
//		//list.add(cp);
//		
//		cp=new CouponPayLog();
//		cp.setActivityId(1);
//		cp.setCouponId(2);
//		cp.setCreateTime(new Date());
//		cp.setOrderNo("1");
//		cp.setPayValue(3.99F);
//		cp.setUserId(1);
		
		//list.add(cp);
		
		//couponPayLogService.saveBatch(list);
		
//		list=couponPayLogService.findByOrderId(1);
//		logger.info(new JacksonUtil().getJson(list));
    }
}
