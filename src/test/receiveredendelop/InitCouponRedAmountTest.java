package test.receiveredendelop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ariadnethread.activity.receiveredenvelop.dao.mybatis.ConponRedAmountDao;
import com.ariadnethread.activity.receiveredenvelop.domain.ConponRedAmount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class InitCouponRedAmountTest extends AbstractJUnit4SpringContextTests{
	private static Logger logger = LoggerFactory.getLogger(InitCouponRedAmountTest.class);
	
	@Resource
	ConponRedAmountDao conponRedAmountDao;
	
	@Test
    public void saveTest() {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("G:\\threadWorkSpace\\movie_server\\src\\sql\\mysql\\couponRedAmount.txt")));
			String data = null;
			List<ConponRedAmount> list=new ArrayList<ConponRedAmount>();
			while((data = br.readLine())!=null)
			{
				System.out.println(data);
				String[] fields=data.split(",");
				ConponRedAmount conponRedAmount=new ConponRedAmount();
				conponRedAmount.setCreateTime(new Date());
				conponRedAmount.setQuantity(Integer.valueOf(fields[1]));
				conponRedAmount.setRedAmount(Float.valueOf(fields[0]));
				conponRedAmount.setTotalAmount(Float.valueOf(fields[2]));
				conponRedAmount.setActivityId(1);
				//conponRedAmount.setDescription("领红包活动");
				list.add(conponRedAmount);
				if(list.size()>500){
					conponRedAmountDao.saveBatch(list);
					list.clear();
				}
			}
			if(list.size()>0){
				conponRedAmountDao.saveBatch(list);
				list.clear();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    }
}
