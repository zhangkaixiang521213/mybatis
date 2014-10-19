package test.mybatist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zhangkaix.mybatistt.domain.Mytest;
import zhangkaix.mybatistt.service.MytestService;
import core.utils.jackson.JacksonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MytestTest extends AbstractJUnit4SpringContextTests{
	private static Logger logger = LoggerFactory.getLogger(MytestTest.class);
	
	@Resource
	private MytestService mytestService;
	
	@Test
    public void test() {
//		Mytest mytest=mytestService.findById(1);
//		logger.info(new JacksonUtil().getJson(mytest));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("para1", 1);
		map.put("para2", 1);
//		List<Mytest> list=mytestService.searchByPara(map);
//		logger.info(new JacksonUtil().getJson(list));
		List<Mytest> list=mytestService.searchByParas(map);
		logger.info(new JacksonUtil().getJson(list));
    }
}
