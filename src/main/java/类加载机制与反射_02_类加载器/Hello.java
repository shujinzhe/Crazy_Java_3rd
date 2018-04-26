package 类加载机制与反射_02_类加载器;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月19日
 */
public class Hello {
	public static void main(String[] args) {
		for(String arg : args){
			System.out.println("通过自定义类加载器运行Hello的参数：" + arg);
		}
	}
}
