package test.register;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ariadnethread.datacenter.usercontent.domain.UserContent;
import com.ariadnethread.register.service.PhoneRegister;

import core.utils.jackson.JacksonUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MobileValidateTest extends AbstractJUnit4SpringContextTests{

	private static Logger logger = LoggerFactory.getLogger(MobileValidateTest.class);
	

	@Resource
	PhoneRegister phoneRegisterImpl;
	
	@Test
    public void doSendPhoneValidateCodeTest() {
		
//		UserContent UserContent=phoneRegisterImpl.doMobileValidate("18600947521","801613","wx18600947521");
//		
//		logger.info(new JacksonUtil().getJson(UserContent));
		try {
			UserContent uc=phoneRegisterImpl.registerUser("18600947521");
			logger.info(new JacksonUtil().getJson(uc));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
