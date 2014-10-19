package core.utils;

import java.io.File;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class PropertiesUtil {
	private static ResourceBundle resource = ResourceBundle
			.getBundle("application");
	
	private static String contextPath = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/**
	 * 获取工程路径
	 * 
	 * @return
	 */
	public static String getContextPath() {
		if (contextPath == null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			contextPath = request.getSession().getServletContext()
					.getRealPath("");
		}
		return contextPath;
	}
	
	/**
	 * 获取部署文件路径
	 * 
	 * @return
	 */
	public static String getDeployPath() {
		return getContextPath() + File.separator + "fileResource"
				+ File.separator + "deploy";
	}

	/**
	 * 获取部署文件路径
	 * 
	 * @return
	 */
	public static String getDeployTempPath() {
		return getContextPath() + File.separator + "fileResource"
				+ File.separator + "deploy" + File.separator + "temp";
	}

	/**
	 * 获取application.properties文件属性值
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropByKey(String key) {
		if (key != null && !"".equals(key)) {
			return resource.getString(key);
		} else {
			return "";
		}
	}
	
	
	public static boolean enableCache(){
		String enable_cache=getPropByKey("enable_cache");
		if(enable_cache!=null && "true".equalsIgnoreCase(enable_cache)){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
