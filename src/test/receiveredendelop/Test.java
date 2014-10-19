package test.receiveredendelop;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.ariadnethread.coupon.domain.Coupon;
import com.ariadnethread.register.utils.RegisterConstants;
import com.ariadnethread.utils.KeyValueBean;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.utils.ComUtil;
import core.utils.jackson.JacksonUtil;

public class Test {
	private static JsonGenerator jsonGenerator = null;
	private static ObjectMapper objectMapper = null;

	/**
	 * @param args
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		System.out.println(URLEncoder.encode("中国", "UTF-8"));
		
		System.out.println(String.format("%06d", new Random().nextInt(1000000)));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date now = new Date();
		  System.out.println("当前时间：" + sdf.format(now));
		Date afterDate = new Date(now .getTime() + 5*60*1000);
		System.out.println(sdf.format(afterDate ));
		
		//System.out.println(sdf.format(ComUtil.DatePlusMinute(5)));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("redid", "1");
		
		System.out.println(new JacksonUtil().getJson(new KeyValueBean("redEnvelopId", "123")));
		
		Coupon c=new Coupon();
		c.setCardCount(123);
		
		Coupon b=new Coupon();
		b.setCardCount(1234);
		
		List<Coupon> list=new ArrayList<Coupon>();
		list.add(c);
		list.add(b);
		
		System.out.println(sdf.format(ComUtil.DatePlusMinute(new Date(), 0.5f)));
		
//		Map<String,List<Coupon>> maps=new HashMap<String,List<Coupon>>();
//		maps.put("distributes", list);
		
//		Results r=new Results(ReceiveConstants.API_RETURN_STATUS.MOBILE_CODE_EXPIRE_SENDED.value(),ReceiveConstants.API_RETURN_STATUS.MOBILE_CODE_EXPIRE_SENDED.desc(),list);
//
//		System.out.println(new JacksonUtil().getJson(r));
		
		try {
//			WeiXinUserBean WeiXinUserBean=WeiXinLogin.checkLogin("ohOoxt_oKFX7ENzsI5WN-xGj0qB8", "OezXcEiiBSKSxW0eoylIeJilB5g4ph7MmegHKHGUDY3eYtbDi03tNQH56ZhWUsugLC1COCYLA9h1WeEL3Dg1KFdKrZe_f4Z3-OWbe7si1FgS0TT7R_36OLAj_FXkZ4Ic1KIpHq6f0fpcFtIUkUJiYg");
//			System.out.println(new JacksonUtil().getJson(WeiXinUserBean));
			//int i=9/0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("注册用户失败");
		}
		System.out.println("adasf");
		
		
		
		
		//sonGenerator json=new JsonGenerator();
		/*
		System.out.println("使用JsonGenerator转化实体为json串-------------");
        //writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
        //jsonGenerator.writeObject("{'a','a'}");
        System.out.println();
        System.out.println("使用ObjectMapper-----------");
        //writeValue具有和writeObject相同的功能
        //objectMapper.writeValue(System.out, "{'status','a'}");
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jn=mapper.readTree("{\"action\":\"user_register_response\",\"error\":\"register success.\",\"status\":0}");
        
        System.out.println(jn.get("status").asText());
        
        TestBean bean = mapper.readValue("{\"status\":\"a\"}", TestBean.class);
        
        System.out.println(bean.getStatus());
        */
		
		Date adate=ComUtil.Date2Days(-7);
		System.out.println(ComUtil.Date2StringHMS(adate));
		System.out.println(adate.getTime());
		System.out.println(adate.getTime());
		
//		long loginTime=ComUtil.Date2YMD(adate).getTime();
//		long expireTime=ComUtil.Date2Days(adate,7).getTime();
//		//如果时间在7天内则更新
//		boolean isExpire=!(loginTime <= new Date().getTime() 
//							&& new Date().getTime() <= expireTime);
//		System.out.println(isExpire);
//		System.out.println(loginTime);
//		System.out.println(expireTime);
		System.out.println(ComUtil.Date2StringHMS(new Date()));
		System.out.println(ComUtil.Date2StringHMS(new Date(System.currentTimeMillis() - RegisterConstants.SESSION_VALIDATE_TIMES)));
		
		
		boolean isExpire=!(adate.getTime()>(System.currentTimeMillis()-RegisterConstants.SESSION_VALIDATE_TIMES));
		System.out.println(isExpire);
//		long sessionValidateTimes=7*24*60*60*1000;
//		long currenTimes=System.currentTimeMillis();
//		
//		if(adate.getTime()>(currenTimes-sessionValidateTimes)){
//			System.out.println("inner seven day");
//		}else{
//			System.out.println("out seven day");
//		}
		
		List<Long> delList=new ArrayList<Long>();
		delList.add(1L);
		delList.add(2L);
		String s="";
		for(Long l:delList){
			s+=l+",";
		}
		if(!"".equals(s)){
			s=s.substring(0, s.length()-1);
		}
		
		System.out.println(s);
		
	}
	
}
