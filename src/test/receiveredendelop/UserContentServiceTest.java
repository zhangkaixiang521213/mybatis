package test.receiveredendelop;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ariadnethread.datacenter.usercontent.domain.UserContent;
import com.ariadnethread.datacenter.usercontent.service.UserContentService;

import core.utils.jackson.JacksonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserContentServiceTest extends AbstractJUnit4SpringContextTests{
	private static Logger logger = LoggerFactory.getLogger(UserContentServiceTest.class);
	
//	@Resource
//	UserContentInfo userContentInfo;
	
	@Autowired
	private UserContentService userContentService;
	
	@Test
    public void saveTest() {
//		UserContent uc=userContentInfo.getUserContentBySessionId("81de7aaf12614206a40c3874012eeaf5");
		
//		UserContent uc=userContentService.searchByUserId("839375");
//		UserContent uc=new UserContent();
//		uc.setUid(839375);
//		uc.setLastSession("81bbdbda648342b08d16677336a1124f");
//		uc.setLoginTime(new Date());
//		userContentService.update(uc);
//		logger.info(new JacksonUtil().getJson(uc));
		
		
		UserContent userContent = new UserContent();
		String sessionId = UUID.randomUUID().toString().replaceAll("-", "");
		userContent.setLastSession(sessionId);
		userContent.setLoginTime(new Date());
		userContent.setUserName("18600947522");
		userContent.setCreateTime(new Date());
		userContent.setDel(0);
		userContentService.save(userContent);
		//userContent.setUid(uid);
		logger.info(new JacksonUtil().getJson(userContent));
		
    }
}
