package 网络编程_01_Java的基本网络支持;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月21日
 * 
 * 	URLDecoderhe URLEncoder用于完成普通字符串和
 * 	application/x-www-form-urlencoded MIME字符串之间的相互转换
 * 
 */
public class URLDecoder_EncoderTest {
	public static void main(String[] args) throws Exception {
		String word = URLDecoder.decode(
				"%E6%88%91%E8%A6%81%E5%AD%A6Java", "UTF-8");
		//application/x-www-form-urlencoded MIME字符串
		System.out.println();
		//String字符串
		System.out.println(URLEncoder.encode("我要学Java","UTF-8"));
	}
}
