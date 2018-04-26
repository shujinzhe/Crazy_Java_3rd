package 类加载机制与反射_01_类加载_连接_初始化;


/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月19日
 */
class Tester
{
	static
	{
		System.out.println("Tester类的静态初始块...");
	}
}
public class ClassLoaderTest
{
	public static void main(String[] args)
		throws ClassNotFoundException
	{
		// 得到系统类加载器
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		// 加载Tester类
		// 并不会导致初始化
		cl.loadClass("类加载机制与反射_01_类加载_连接_初始化.Tester");
		System.out.println("系统加载Tester类");
		// 初始化Tester类
		Class.forName("类加载机制与反射_01_类加载_连接_初始化.Tester");
		/**
		 * 	输出：
		  	系统加载Tester类
		  	Tester类的静态初始块...
		 */
	}
}

