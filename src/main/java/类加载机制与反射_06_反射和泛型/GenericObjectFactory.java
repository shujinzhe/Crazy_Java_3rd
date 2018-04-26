package 类加载机制与反射_06_反射和泛型;

import java.lang.reflect.Array;
import java.util.Date;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月20日
 * 
 * 	使用Class<T>泛型可以避免强制类型转换
 */

public class GenericObjectFactory {
	public static Object getInstance(String clsName){
		try {
			Class clazz = Class.forName(clsName);
			return clazz.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	//使用泛型
	public static <T> T getInstanceUsingGeneric(Class<T> cls){
		try {
			return cls.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	//使用Array的newInstance返回泛型数组
	public static <T> T[] getInstanceUsingGeneric_Array(
			Class<T> cls,int length){
		try {
			return (T[]) Array.newInstance(cls, length);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		//不使用泛型，需要强制类型转换
		Date d = (Date) getInstance("java.util.Date");
		//使用了泛型的Class,不需要强制类型转换
		Date d2 = getInstanceUsingGeneric(Date.class);
		
		String[] arr = getInstanceUsingGeneric_Array(String.class, 5);
		int[][] intArr = getInstanceUsingGeneric_Array(int[].class, 5);
	}
}
