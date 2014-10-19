package zhangkaix.mybatistt.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zhangkaix.mybatistt.service.MytestService;
import zhangkaix.mybatistt.utils.Results;

/**
 * Restful API的Controller.
 * 
 */
@Controller
@RequestMapping(value = "/test")
public class MyTestController {

	private static Logger logger = LoggerFactory.getLogger(MyTestController.class);

	@Autowired
	private MytestService mytestService;
	
	/**
	 * 测试
	 * @param mobile :手机号
	 * @return Results
	 */
	@RequestMapping(value = "/ok")
	@ResponseBody
	public Results ok() throws IOException {
		//业务逻辑
//		Mytest mytest=null; 
//		try{
//		mytest=mytestService.findById(1);
//		logger.info(new JacksonUtil().getJson(mytest));
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error(e+"");
//		}
		
//		return new Results("","",mytest);
//		List<Mytest> list=mytestService.searchByParas(1,1);
//		return new Results("","",list);
		return null;
	}
	
}
