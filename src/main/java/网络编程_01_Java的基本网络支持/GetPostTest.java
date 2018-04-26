package 网络编程_01_Java的基本网络支持;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月21日
 * 
 * 	读取URL引用的资源的步骤：
 * 		1.URL对象的openConnection()方法创建URLConnection对象
 * 		2.设置URLConnection的参数和普通请求属性
 * 		3.GET方式请求：connect()建立连接即可
 * 		  POST方式请求：需要获取URLConnection实例对应的输出流发送请求参数
 * 		4.远程资源变为可用，程序可以访问远程资源的投资端或输入流读取远程资源的数据
 * 
 * 
 * 	设置请求头字段：
 * 		setAllowUserInteraction()
 * 		setDoInput()
 * 		setDoOutput()
 * 		setIfModifiedSince()
 * 		setUseCaches()
 * 
 * 	设置或增加通用头字段：
 * 		setRequestProperty(String key,String value)
 * 			设置请求头字段的值为value
 * 		addRequestProperty(String key,String value)
 * 			新增，不会覆盖原请求头字段的值，而是追加
 * 	
 * 	远程资源可用时，可以通过以下方法访问头字段和内容：
 * 		Object getContent()
 * 		String getHeaderField(String name)
 * 			获取具体的响应头字段：
 * 			1.getContentEncoding()
 * 			2.getContentLength()
 * 			3.getContentType()
 * 			4.getDate()
 * 			5.getExpiration()
 * 			6.getLastModified()
 * 		getInputStream()
 * 		getOutputStream()
 * 		
 */
public class GetPostTest {
	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数，格式满足name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendGet(String url,String param){
		String result = "";
		String urlName = url + "?" + param;
		try {
			URL realURL = new URL(urlName);
			URLConnection conn = realURL.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent"
				, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段		
			for(String key : map.keySet()){
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			while((line = in.readLine())!=null){
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 向指定URL发送POST方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数，格式应该满足name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url , String param)
	{
		String result = "";
		try
		{
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			try(
				// 获取URLConnection对象对应的输出流
				PrintWriter out = new PrintWriter(conn.getOutputStream()))
			{
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			try(
				// 定义BufferedReader输入流来读取URL的响应
				BufferedReader in = new BufferedReader(new InputStreamReader
					(conn.getInputStream() , "utf-8")))
			{
				String line;
				while ((line = in.readLine())!= null)
				{
					result += "\n" + line;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		return result;
	}
	// 提供主方法，测试发送GET请求和POST请求
	public static void main(String args[])
	{
		// 发送GET请求
		String s = GetPostTest.sendGet("http://www.baidu.com"
			, null);
		System.out.println(s);
		System.out.println("=============================================");
		// 发送POST请求
		String s1 = GetPostTest.sendPost("https://www.baidu.com/index.php"
			, "tn=20041099_oem_dg&ch=33");
		System.out.println(s1);
	}
}
