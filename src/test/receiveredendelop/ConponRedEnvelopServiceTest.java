package test.receiveredendelop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ariadnethread.activity.receiveredenvelop.dao.mybatis.ConponRedAmountDao;
import com.ariadnethread.activity.receiveredenvelop.service.ConponRedEnvelopService;
import com.ariadnethread.utils.Results;

import core.utils.jackson.JacksonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ConponRedEnvelopServiceTest extends AbstractJUnit4SpringContextTests{
	private static Logger logger = LoggerFactory.getLogger(ConponRedEnvelopServiceTest.class);
	
	@Resource
	ConponRedAmountDao conponRedAmountDao;
	
	@Resource
	ConponRedEnvelopService conponRedEnvelopService;
	
	@Test
    public void saveTest() {
		//ConponRedAmount conponRedAmount=conponRedAmountService.searchRandomAmount("");
		//logger.info(new JacksonUtil().getJson(conponRedAmount));
		
		//logger.info(new JacksonUtil().getJson(conponRedEnvelopService.doReceiveAdd(839375, 617, "18600957521")));
		//logger.info(new JacksonUtil().getJson(conponRedEnvelopService.findById(10)));
		
//		int count=conponRedEnvelopService.countByDistribute(204899, 617);
//		System.out.println(count);
		Results results=conponRedEnvelopService.searchByUserContentId(839389, 617,"oLXmcjhB6DJR8rjj1wbm-rrRKPOQ");
		logger.info(new JacksonUtil().getJson(results));
		
    }
}
