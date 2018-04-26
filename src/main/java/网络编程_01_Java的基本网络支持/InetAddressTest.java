package 网络编程_01_Java的基本网络支持;

import java.net.InetAddress;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月21日
 */
public class InetAddressTest {
	public static void main(String[] args) throws Exception {
		//根据主机名获取对应的InetAddress实例
		InetAddress ip = InetAddress.getByName("localhost");
		//判断是否可达
		System.out.println("本机是否可达：" + ip.isReachable(5000));
		//获取IP字符串
		System.out.println(ip.getHostAddress());
		//根据原始IP获取对应的InetAddress实例
		InetAddress local = InetAddress.getByAddress(
				new byte[]{127,0,0,1});
		System.out.println("本机是否可达：" + local.isReachable(2000));
		//获取全限定域名
		System.out.println(local.getCanonicalHostName());
	}
}
