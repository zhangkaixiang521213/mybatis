package test.mybatist;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class TestHttpClient {
	
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };


	public static void main(String[] args) throws Exception {
		//test
		String testUrl="http://localhost:8087/mybatis";
		//增加领红包记录
		String test=testUrl+"/test/ok";
		
//		executeRequest(remainValue,"f07f841822a8a090511d46af36ddf839","7336ae66acf24d0baaa967a80c18b8b9");
		executeRequest(test);
		
//		System.out.println(getRequest(20));
	}
	
	private static void executeRequest(String url){
		try{
			HttpClient client=new DefaultHttpClient();
			//建立HttpPost对象
//			HttpPost httpPost=new HttpPost(url);
			HttpGet httpGet = new HttpGet(url);
			//建立一个NameValuePair数组，用于存储欲传递的参数
//			List params=new ArrayList();
			//添加参数
//			params.add(new BasicNameValuePair("start", "0"));
//			params.add(new BasicNameValuePair("size", "14"));
//			params.add(new BasicNameValuePair("sortType", "hot"));
			//设置编码
			//httpGet.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			//httpGet.setParams(params);
			//httpPost.setHeader("session_id", "81de7aaf12614206a40c3874012eeaf5");
//			httpPost.setHeader("enc", "f07f841822a8a090511d46af36ddf839");
//			httpPost.setHeader("session_id", "81bbdbda648342b08d16677336a1124f");
//			httpGet.setHeader("enc", enc);
//			httpGet.setHeader("session_id", session_id);
//			httpGet.setHeader("user_id", "839375");
//			httpGet.setHeader("user_id", "187108");
			//httpPost.setHeader("session_id", "12345613811918422");
			//发送Post,并返回一个HttpResponse对象 
			//httpPost.setHeader("session_id", "81de7aaf12614206a40c3874012eeaf5");
			//HttpResponse response=new DefaultHttpClient().execute(httpPost);
//			HttpResponse response=new DefaultHttpClient().execute(httpPost);
			HttpResponse response=new DefaultHttpClient().execute(httpGet);
			//可以得到指定的header
			//Header header = response.getFirstHeader("Content-Length");
			// String Length=header.getValue(); 
			// System.out.println(header.getName());
			//如果状态码是200，则正常返回
			if(response.getStatusLine().getStatusCode()==200){
			//获得返回的字符串
			String result=EntityUtils.toString(response.getEntity());
			//打印输出
			System.err.println(result);
			//如果是下载的文件，可以用response.getEntity().getContent返回InputStream 
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
