package 类加载机制与反射_02_类加载器;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月19日
 * 
 * 	类加载机制：
 * 	1.全盘负责：一个类加载某个Class时，该Class所依赖的和引用的其它Class也由
 * 	该类加载器负责载入，除非显式使用另外一个类加载器载入
 * 	2.父类委托：先让父类加载器试图加载该Class，只有在父类加载器无法加载该类时才
 * 	尝试从自己的类路径中加载该类
 * 	3.缓存机制：保证所有加载过的Class都会被缓存，当需要使用某个Class时，类加载
 * 	器先从缓存区搜寻该Class，只有缓存区不存在时系统才会读取类对应的二进制数据，并
 * 	转为Class对象，存入缓存中。这就是为什么修改了Class后，必须重新启动JVM，程序
 * 	做出的修改才会生效的原因
 * 
 * 
 * 	类加载器之间的父子关系并不是类继承上的父子关系，而是加载器类实例之间的关系
 * 	JVM中4种类加载器的层次结构：用户类加载器-->系统类加载器-->扩展类加载器-->根类加载器
 * 
 * 
 * 	因为根类加载器没有继承ClassLoader抽象类，所以扩展类加载器的getParent()
 * 	方法返回null；实际上，扩展类加载器的父类加载器是根类加载器，只不过根类加载器
 * 	不是Java实现的
 * 
 * 
 * 	类加载器加载Class的8个步骤：
 * 	1.缓存中是否有此Class，若有直接进入第8步；
 * 	2.父类加载器不存在(要么parent一定是根类加载器，要么就是根类加载器)，跳至第4步；
 * 	3.使用父类加载器载入，载入成功跳至第8步；否则跳至第5步；
 * 	4.使用根类加载器载入，载入成功跳至第8步；否则跳至第7步；
 * 	5.当前类加载器尝试寻找Class文件，找到跳至第6步；否则第7步
 * 	6.从文件中载入Class，成功跳至第8步；
 * 	7.抛出ClassNotFoundException异常；
 * 	8.返回对应的Class对象；
 */
public class ClassLoaderPropTest {
	public static void main(String[] args) throws IOException {
		//获取系统类加载器
		ClassLoader sysLoader = ClassLoader.getSystemClassLoader();
		System.out.println("系统类加载器：" + sysLoader);
		/**
		 * 获取系统类加载器的加载路径——通常由CLASSPATH环境变量指定
		 * 如果系统没有指定的CLASSPATH环境变量，则默认以当前路径作为
		 * 类系统类加载器的加载路径
		 */
		Enumeration<URL> eml = sysLoader.getResources("");
		while(eml.hasMoreElements()){
			System.out.println(eml.nextElement());
		}
		//获取系统类加载器的父类加载器，得到扩展类加载器
		ClassLoader extLoader = sysLoader.getParent();
		System.out.println("扩展类加载器：" + extLoader);
		System.out.println("扩展类加载器的加载路径：" 
				+ System.getProperty("java.ext.dir"));
		//扩展类加载器的父类为null，BootStrapLoader没有继承ClassLoader
		System.out.println("扩展类加载器的父类：" 
				+ extLoader.getParent());
	}
}
