package 类加载机制与反射_02_类加载器;

import java.net.URL;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月19日
 * 
 * 	JVM启动时，会形成由3个类加载器组成的初始类加载器层次结构
 * 	1.BootStrap ClassLoader：根类加载器
 * 		不是java.lang.ClassLoader的子类，而是由JVM自身实现的
 * 	2.Extension ClassLoader：扩展类加载器
 * 		加载JRE的扩展目录(%JAVA_HOME%/jre/lib/ext或者由
 * 		java.ext.dir系统属性指定的目录)中的Jar包的类
 * 	3.System ClassLoader：系统类加载器
 * 		JVM启动时加载java命令的-classpath选项、java.class.path
 * 		系统属性，或者CLASSPATH环境变量指定的Jar包和类路径。
 * 		
 * 
 * 	如没有特别指定，则用户自定义的类加载器都以类加载器(ClassLoader)作为父加载器
 */
public class BotStrapTest {
	public static void main(String[] args) {
		//	获取根类加载器所加载的全部URL数组
		URL[] urls = sun.misc.Launcher
				.getBootstrapClassPath().getURLs();
		for(URL url : urls){
			System.out.println(url.toExternalForm());
		}
	}
	/**
	 * 	输出：
	 * 	file:/C:/Program%20Files/Java/jdk1.7.0_25/jre/lib/resources.jar
		file:/C:/Program%20Files/Java/jdk1.7.0_25/jre/lib/rt.jar
		file:/C:/Program%20Files/Java/jdk1.7.0_25/jre/lib/sunrsasign.jar
		file:/C:/Program%20Files/Java/jdk1.7.0_25/jre/lib/jsse.jar
		file:/C:/Program%20Files/Java/jdk1.7.0_25/jre/lib/jce.jar
		file:/C:/Program%20Files/Java/jdk1.7.0_25/jre/lib/charsets.jar
		file:/C:/Program%20Files/Java/jdk1.7.0_25/jre/lib/jfr.jar
		file:/C:/Program%20Files/Java/jdk1.7.0_25/jre/classes
	 */
}
