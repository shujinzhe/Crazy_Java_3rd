package 类加载机制与反射_01_类加载_连接_初始化;


/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月19日
 */
class MyTest
{
	static
	{
		System.out.println("静态初始化块...");
	}
	// 使用final修饰，且其值在编译时即可确定，程序使用该类变量时,
	// 实际并没有使用该变量值，而是相当于使用常量
	static final String compileConstant = "Java";
}
public class CompileConstantTest
{
	public static void main(String[] args)
	{
		// 不会导致初始化MyTest类
		System.out.println(MyTest.compileConstant);   
	}
}
