package 类加载机制与反射_05_JDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月20日
 * 
 * 
 * 	JDK动态代理只能为接口创建动态代理
 * 
 * 	创建动态代理类的流程
 * 	1.创建一个InvocationHandler对象
 * 	2.使用Proxy.getProxyClass(类加载器,接口Class对象数组)生成一个动态代理类的Class对象
 * 	3.得到Class对象的一个构造方法
 * 	4.调用newInstance()创建动态实例
 * 
 * 	也可以直接使用Proxy的静态方法newProxyInstance方法
 */
interface Person {
	void walk();

	void sayHello(String name);
}

class Student implements Person {
	@Override
	public void walk() {
		// TODO Auto-generated method stub
		System.out.println("学生走路");
	}

	@Override
	public void sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("学生sayHello:" + name);
	}
}

class StudentUtil {
	// 第一个拦截器方法
	public void method1() {
		System.out.println("=====模拟第一个通用方法=====");
	}

	// 第二个拦截器方法
	public void method2() {
		System.out.println("=====模拟通用方法二=====");
	}
}

public class ProxyTest implements InvocationHandler {
	private Student s = new Student();
	private StudentUtil su = new StudentUtil();
	public static void main(String[] args) {
		Person p = (Person) Proxy.newProxyInstance(
				Person.class.getClassLoader(), new Class[] { Person.class },
				new ProxyTest());
		p.walk();
		p.sayHello("Eric");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("----正在执行的方法:" + method);
		if (args != null) {
			System.out.println("调用方法时传入的实参为：");
			for (Object val : args) {
				System.out.println(val);
			}
		} else {
			System.out.println("调用该方法没有实参！");
		}
		
		su.method1();
		Object rtn = method.invoke(s, args);
		su.method2();
		return rtn;
	}
}
