package 类加载机制与反射_01_类加载_连接_初始化;


/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月19日
 */
public class Test
{
	static
	{
		// 使用静态代码块为变量b指定初始值ֵ
		b = 6;
		System.out.println("----------");
	}
	// 声明变量a的初始值ֵ
	static int a = 5;
	static int b = 9;         // Test类初始化后，b变为9
	static int c;
	public static void main(String[] args)
	{
		System.out.println(Test.b);
	}
}
