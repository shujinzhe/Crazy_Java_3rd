package 类加载机制与反射_02_类加载器;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月20日
 * 
 * 	系统类加载器以及扩展类加载器都继承了URLClassLoader。
 * 	URLClassLoader既可从本地文家系统也可从远程主机获取二进制文件加载类
 * 
 * 
 * 	URLClassLoader的两个构造器
 * 	1.URLClassLoader(URL[] urls)：使用默认的父类加载器创建一个ClassLoader
 * 	对象，该对象从urls指定的路径查询并加载类
 * 	2.URLClassLoader(URL[] urls,ClassLoader parent)：使用指定的父类
 * 	加载器创建一个ClassLoader对象
 * 	
 */
public class URLClassLoaderTest {
	private static Connection conn;
	public static Connection getConnection(String url,
			String user,String passwd) throws Exception{
		if(conn == null){
			//file为协议类型,本地文件
			//http：互联网通过HTTP访问
			//ftp
			URL[] urls = {new URL("file:src/类加载机制与反射_02_类加载器/mysql-connector-java-5.1.30-bin.jar")};
			URLClassLoader loader = new URLClassLoader(urls);
			Driver driver = (Driver)loader.loadClass("com.mysql.jdbc.Driver")
					.newInstance();
			Properties prop = new Properties();
			prop.setProperty("user", user);
			prop.setProperty("password", passwd);
			/**
			 * Normally at least a "user" and
			 * "password" property should be included.
			 */
			conn = driver.connect(url, prop);
		}
		return conn;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(getConnection(
				"jdbc:mysql://localhost:3306/mysql",
				"root", "123456"));
	}
}
