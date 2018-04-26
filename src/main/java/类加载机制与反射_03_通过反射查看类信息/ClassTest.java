package 类加载机制与反射_03_通过反射查看类信息;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月20日
 * 
 * 	
 * 	获取Class对象：
 * 	1.Class.forName(全限定名)--->ClassNotFountException
 * 	2.类.class
 * 	3.对象.getCLass()方法
 * 
 * 
 * 	从Class中获取信息
 * 	获取构造器：
 * 		1.Constructor<T> getConstructor(Class<?>... parameterTypes)
 * 		2.Constructor<?>[] getConstructors()
 * 		3.Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
 * 		4.Constructor<?>[] getDeclaredConstructors()
 * 	获取方法：
 * 		1.Method getMethod(String name, Class<?>... parameterTypes)
 * 		2.Method[] getMethods()
 * 		3.Method getDeclaredMethod(String name, Class<?>... parameterTypes)
 * 		4.Method[] getDeclaredMethods()
 * 	获取成员变量：
 * 		1.Field	getField(String name)
 * 		2.Field[] getFields()
 * 		3.Field	getDeclaredField(String name)
 * 		4.Field[] getDeclaredFields()
 * 	获取Annotation：
 * 		1.<A extends Annotation> A getAnnotation(Class<A> annotationClass)
 * 		2.Annotation[] getAnnotations()
 * 		3.Annotation[] getDeclaredAnnotations()
 * 	获取内部类...
 * 		类似的方法
 * 	...
 */
//使用注解修饰该类
@SuppressWarnings(value = "unchecked")
@Deprecated
public class ClassTest {
	// 为该类定义一个私有的构造器
	private ClassTest() {
	}

	// 定义一个有参数的构造器
	public ClassTest(String name) {
		System.out.println("执行有参数的构造器");
	}

	// 定义一个无参数的info方法
	public void info() {
		System.out.println("执行无参数的info方法");
	}

	// 定义一个有参数的info方法
	public void info(String str) {
		System.out.println("执行有参数的info方法" + "，其str参数值：" + str);
	}

	// 定义一个测试用的内部类
	class Inner {
	}

	public static void main(String[] args) throws Exception {
		// 下面代码可以获取ClassTest对应的Class
		Class<ClassTest> clazz = ClassTest.class;
		// 获取该Class对象所对应类的全部构造器
		Constructor[] ctors = clazz.getDeclaredConstructors();
		System.out.println("ClassTest的全部构造器如下：");
		for (Constructor c : ctors) {
			System.out.println(c);
		}
		// 获取该Class对象所对应类的全部public构造器
		Constructor[] publicCtors = clazz.getConstructors();
		System.out.println("ClassTest的全部public构造器如下：");
		for (Constructor c : publicCtors) {
			System.out.println(c);
		}
		// 获取该Class对象所对应类的全部public方法
		Method[] mtds = clazz.getMethods();
		System.out.println("ClassTest的全部public方法如下：");
		for (Method md : mtds) {
			System.out.println(md);
		}
		// 获取该Class对象所对应类的指定方法
		System.out.println("ClassTest里带一个字符串参数的info()方法为："
				+ clazz.getMethod("info", String.class));
		// 获取该Class对象所对应类的上的全部注解
		Annotation[] anns = clazz.getAnnotations();
		System.out.println("ClassTest的全部Annotation如下：");
		for (Annotation an : anns) {
			System.out.println(an);
		}
		// 获取该Class对象所对应类的全部内部类
		Class<?>[] inners = clazz.getDeclaredClasses();
		System.out.println("ClassTest的全部内部类如下：");
		for (Class c : inners) {
			System.out.println(c);
		}
		// 使用Class.forName方法加载ClassTest的Inner内部类
		Class inClazz = Class.forName("类加载机制与反射_03_通过反射查看类信息.ClassTest$Inner");
		// 通过getDeclaringClass()访问该类所在的外部类
		System.out.println("inClazz对应类的外部类为：" + inClazz.getDeclaringClass());
		System.out.println("ClassTest的包为：" + clazz.getPackage());
		System.out.println("ClassTest的父类为：" + clazz.getSuperclass());
	}
}
