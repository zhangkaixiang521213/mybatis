package test.receiveredendelop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ariadnethread.coupon.service.CouponService;

import core.utils.jackson.JacksonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ConponServiceTest extends AbstractJUnit4SpringContextTests{
	private static Logger logger = LoggerFactory.getLogger(ConponServiceTest.class);
	
	@Resource
	CouponService couponService;
	
	@Test
    public void saveTest() {
//		float rv=couponService.countRemainValueByUserContentId(2,617);
//		
//		logger.info(new JacksonUtil().getJson(rv));
    }
}
