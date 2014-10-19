package test.receiveredendelop;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ariadnethread.activity.receiveredenvelop.dao.mybatis.ConponRedEnvelopDao;
import com.ariadnethread.activity.receiveredenvelop.domain.ConponRedEnvelop;
import com.ariadnethread.activity.receiveredenvelop.service.ConponRedEnvelopService;

import core.utils.ComUtil;
import core.utils.jackson.JacksonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ConponRedAmountServiceTest extends AbstractJUnit4SpringContextTests{
	private static Logger logger = LoggerFactory.getLogger(ConponRedAmountServiceTest.class);
	
	@Resource
	ConponRedEnvelopService conponRedEnvelopService;
	@Resource
	ConponRedEnvelopDao conponRedEnvelopDao;
	
	@Test
    public void saveTest() {
		/*
		 */
		ConponRedEnvelop conponRedEnvelop=conponRedEnvelopDao.findById(1);
		logger.info(new JacksonUtil().getJson(conponRedEnvelop));

		/*
		//List<ConponRedEnvelop> list=conponRedEnvelopDao.searchByUserContentId(1L);
		List<ConponRedEnvelop> list=conponRedEnvelopDao.searchByUserContentIdStatus(1,1);
		Map<String,List<ConponRedEnvelop>> map=new HashMap<String,List<ConponRedEnvelop>>();
		map.put("distributes", list);
		logger.info(new JacksonUtil().getJson(map));
//		logger.info(new JacksonUtil().getJson(list));
		 */
		
		/*
		ConponRedEnvelop conponRedEnvelop=new ConponRedEnvelop();
		conponRedEnvelop.setActivityId(Long.valueOf("1"));
		conponRedEnvelop.setCreateTime(new Date());
		conponRedEnvelop.setDescription(ReceiveConstants.DESCRIPTION);
		conponRedEnvelop.setStatus(Integer.valueOf(ReceiveConstants.RED_ENVELOP_STATUS.UNRECEIVE.value()));
		conponRedEnvelop.setUserContentId(Long.valueOf("1"));
		conponRedEnvelop.setUserName("test123");
		conponRedEnvelopDao.save(conponRedEnvelop);
		logger.info(String.valueOf(conponRedEnvelop.getId()));
		*/
		
		//int c=conponRedEnvelopDao.countByUserContentId(1L);
		//logger.info(String.valueOf(c));
		
		//ApiReturnBean rb=conponRedEnvelopService.doReceiveAdd("1", "1", "this is mytest");
		//logger.info(new JacksonUtil().getJson(rb));
		
		/*
		ConponRedEnvelop conponRedEnvelop=conponRedEnvelopDao.findById("1");
		conponRedEnvelop.setStatus(Integer.valueOf(ReceiveConstants.RED_ENVELOP_STATUS.RECEIVED.value()));
		conponRedEnvelop.setRedAmount(ReceiveRedEnvelopValue.receiveValue());
		
		conponRedEnvelopDao.update(conponRedEnvelop);
		
		logger.info("ok");
		*/
		
		//Date date;
		try {
//			date = (new SimpleDateFormat("yyyy-MM-dd")).parse(ComUtil.Date2String(new Date()));
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(date);
//			cal.add(Calendar.DATE, 1);
			System.out.println((new SimpleDateFormat("yyyy-MM-dd")).format(ComUtil.Date2Days(5)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}
