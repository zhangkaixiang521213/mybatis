package com.ariadnethread.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.utils.jackson.JacksonUtil;

public class ParameterValidate {
	
	private static Logger logger = LoggerFactory.getLogger(ParameterValidate.class);
	
	@SuppressWarnings("unchecked")
	public boolean validate(List<KeyValueBean> parameters,String enc){
		//按参数排序操作  
        Collections.sort(parameters, new ParameterOrder());
		
        StringBuilder paramKeys=new StringBuilder("");
        StringBuilder paramValues=new StringBuilder("");
        
        for(KeyValueBean kv:parameters){
        	paramKeys.append(kv.getKey());
        	paramValues.append(kv.getValue());
        }
        
//        String paramKeysMD5=MD5.getMD5(paramKeys.toString());
//        String paramValuesMD5=MD5.getMD5(paramValues.toString()+paramKeysMD5).toLowerCase();
        String paramValuesMD5=MD5.getMD5(paramValues.toString()+"receiveredenvolop").toLowerCase();
        
        logger.info(paramValuesMD5);
        if(paramValuesMD5.equals(enc)){
        	return true;
        }else{
        	return false;
        }
	}
	
	@SuppressWarnings("unchecked")
	public boolean validate(HttpServletRequest request,String enc){
		
		List<KeyValueBean> parameters=new ArrayList<KeyValueBean>();
		
		Enumeration<String> pns=request.getParameterNames();
		while(pns.hasMoreElements())
		{
			String parameterKey = (String)pns.nextElement();
			String parameterValue = request.getParameter(parameterKey);
			
			parameters.add(new KeyValueBean(parameterKey, parameterValue));
		}
		
		
		//按参数排序操作  
        Collections.sort(parameters, new ParameterOrder());
		
        StringBuilder paramKeys=new StringBuilder("");
        StringBuilder paramValues=new StringBuilder("");
        
        for(KeyValueBean kv:parameters){
        	paramKeys.append(kv.getKey());
        	paramValues.append(kv.getValue());
        }
        
        logger.info(new JacksonUtil().getJson(parameters));
        
        //String paramKeysMD5=MD5.getMD5(paramKeys.toString());
        //String paramValuesMD5=MD5.getMD5(paramValues.toString()+paramKeysMD5).toLowerCase();
        String paramValuesMD5=MD5.getMD5(paramValues.toString()+"receiveredenvolop").toLowerCase();
        
        logger.info(paramValuesMD5);
        if(paramValuesMD5.equals(enc)){
        	return true;
        }else{
        	return false;
        }
	}
	
	class ParameterOrder implements Comparator<Object> {  
	    @Override  
	    public int compare(Object arg0, Object arg1) {  
	    	KeyValueBean a = (KeyValueBean) arg0;  
	    	KeyValueBean b = (KeyValueBean) arg1; 
	    	return a.getKey().compareToIgnoreCase(b.getKey());
	    }  
	} 
	/*
	public String paramToEnc(){
		ParameterValidate pv=new ParameterValidate();
	}
	*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<KeyValueBean> parameters=new ArrayList<KeyValueBean>();
		
		//parameters.add(new KeyValueBean("wechatUid", "oeqTFsz7WD2aEsWVGAbzgsj28SmI"));
		parameters.add(new KeyValueBean("mobile", "18600947521"));
		//parameters.add(new KeyValueBean("c", "c"));
		//parameters.add(new KeyValueBean("d", "d"));
		
		
		ParameterValidate pv=new ParameterValidate();
		System.out.println(pv.validate(parameters, "d2359f86196385199c17f7bfb6db91eb"));
	}

}
