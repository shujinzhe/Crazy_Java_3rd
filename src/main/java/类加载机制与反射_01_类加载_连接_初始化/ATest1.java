package 类加载机制与反射_01_类加载_连接_初始化;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月19日
 */
public class ATest1
{
	public static void main(String[] args)
	{	
		
		A a = new A();
		
		a.a ++;
		// 输出结果为7
		System.out.println(a.a);
		// JVM进程结束，内存状态将会丢失，对类做的修改也全部消失
	}
}
