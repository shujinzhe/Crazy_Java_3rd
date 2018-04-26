package 类加载机制与反射_04_使用反射生成并操作对象;

import java.lang.reflect.Field;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月20日
 * 
 * 
 * 	1.getXxx(Object obj)：获取obj对象的该成员变量的值。此处的Xxx对应8种基本类型，
 * 	如果该成员变量的类型是引用类型，则取消get后面的Xxx
 * 	2.setXxx(Object obj,Xxx val)：将obj对象的该成员变量设置成val值。此处的Xxx
 * 	对应8种基本类型，如果该成员变量的类型是引用类型，则取消set后面的Xxx
 */
class Person {
	private String name;
	private int age;

	public String toString() {
		return "Person[name:" + name + " , age:" + age + " ]";
	}
}

public class FieldTest {
	public static void main(String[] args) throws Exception {
		// 创建一个Person对象
		Person p = new Person();
		// 获取Person类对应的Class对象
		Class<Person> personClazz = Person.class;
		// 获取Person的名为name的成员变量
		// 使用getDeclaredField()方法表明可获取各种访问控制符的成员变量
		Field nameField = personClazz.getDeclaredField("name");
		// 设置通过反射访问该成员变量时取消访问权限检查
		nameField.setAccessible(true);
		// 调用set()方法为p对象的name成员变量设置值
		nameField.set(p, "Yeeku.H.Lee");
		// 获取Person类名为age的成员变量
		Field ageField = personClazz.getDeclaredField("age");
		// 设置通过反射访问该成员变量时取消访问权限检查
		ageField.setAccessible(true);
		// 调用setInt()方法为p对象的age成员变量设置值
		ageField.setInt(p, 30);
		System.out.println(p);
	}
}
