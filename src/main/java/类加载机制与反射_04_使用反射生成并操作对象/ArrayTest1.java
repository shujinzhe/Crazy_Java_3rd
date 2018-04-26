package 类加载机制与反射_04_使用反射生成并操作对象;

import java.lang.reflect.Array;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月20日
 * 
 * 
 * 	1.static Object	newInstance(Class<?> componentType, int length)
 * 		创建一个具有指定的元素类型，指定维度的新数组
 * 	2.static xxx getXxx(Object array, int index)
 * 		返回数组第index个元素，若数组元素为引用类型，则去掉Xxx
 * 	3.static void set(Object array, int index, Object value)
 * 		将array中的第index个元素的值设为val，若数组元素为引用类型，则去掉Xxx
 */
public class ArrayTest1 {
	public static void main(String args[]) {
		try {
			// 创建一个元素类型为String ，长度为10的数组
			Object arr = Array.newInstance(String.class, 10);
			// 依次为arr数组中index为5、6的元素赋值
			Array.set(arr, 5, "Java");
			Array.set(arr, 6, "J2EE");
			// 依次取出arr数组中index为5、6的元素的值
			Object book1 = Array.get(arr, 5);
			Object book2 = Array.get(arr, 6);
			// 输出arr数组中index为5、6的元素
			System.out.println(book1);
			System.out.println(book2);
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}