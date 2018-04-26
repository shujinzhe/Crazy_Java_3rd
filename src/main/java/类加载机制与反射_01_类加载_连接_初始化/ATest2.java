package 类加载机制与反射_01_类加载_连接_初始化;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月19日
 * 
 */
public class ATest2
{
	public static void main(String[] args)
	{
		// 在一个新的JVM中创建A的实例
		A b = new A();
		// 结果为6ֵ
		System.out.println(b.a);
	}
}
