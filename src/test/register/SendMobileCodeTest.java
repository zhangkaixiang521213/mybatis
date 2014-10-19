package test.register;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ariadnethread.register.service.PhoneRegister;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SendMobileCodeTest extends AbstractJUnit4SpringContextTests{

	private static Logger logger = LoggerFactory.getLogger(SendMobileCodeTest.class);
	

	@Resource
	PhoneRegister phoneRegisterImpl;
	
	@Test
    public void doSendPhoneValidateCodeTest() {
//		ApiReturnBean apiReturnBean=phoneRegisterImpl.doSendPhoneValidateCode("18611693623");
//		
//		logger.info(new JacksonUtil().getJson(apiReturnBean));
	}
	
	
}
