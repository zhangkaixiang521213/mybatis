package core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterEncodingFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(CharacterEncodingFilter.class);
	private FilterConfig filterConfig;
    private String encoding = null;
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * post,get式提交乱码处理
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest rqt = (HttpServletRequest) request;
	    
//	    logger.info(rqt.getScheme()+"://"+rqt.getServerName()+":"+rqt.getServerPort()+rqt.getContextPath());
//	    //参数合法校验
//	    String enc=rqt.getHeader("enc");
//		if(enc!=null && !"".equals(enc) && "true".equalsIgnoreCase(PropertiesUtil.getPropByKey("para_enc_validate"))  && !new ParameterValidate().validate(rqt, enc)){
//			response.getWriter().write(new JacksonUtil().getJson(new Results(ReceiveConstants.API_RETURN_STATUS.PARAMETER_MD5_ERROR.value(),ReceiveConstants.API_RETURN_STATUS.PARAMETER_MD5_ERROR.desc())));
//			return;
//		}
		
	    
    	String method = rqt.getMethod();
        if(this.encoding!=null){
            if(method.equalsIgnoreCase("post")){
	            request.setCharacterEncoding(encoding);
	            response.setCharacterEncoding(encoding);
            }else{
            	request=new GetHttpServletRequestWrapper((HttpServletRequest) request,encoding);
            }
        }
        
        chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
        this.filterConfig=arg0;
        this.encoding=filterConfig.getInitParameter("encoding");

	}

}
