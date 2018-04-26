package 类加载机制与反射_06_反射和泛型;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月20日
 * 
 * 
 * 	Type：只能获取普通类型的成员变量的类型
 * 	ParameterizedType：被参数化的类型，也就是增加了泛型限制的类型
 * 		1.getRawtype()：返回没有泛型信息的原始类型
 * 		2.getActualtypeArguments()：返回泛型参数的类型
 */
public class GenericTest {
	private Map<String,Integer> score;
	public static void main(String[] args) throws Exception {
		Class<GenericTest> clazz = GenericTest.class;
		//直接使用getType()取出类型只对普通类型的成员变量有效
		Field f = clazz.getDeclaredField("score");
		//score的类型是：interface java.util.Map
		System.out.println("score的类型是：" + f.getType());
		//获取泛型类型
		Type gType = f.getGenericType();
		if(gType instanceof ParameterizedType){
			//强制类型转换
			ParameterizedType pType = (ParameterizedType)gType;
			//获取原始类型
			Type rawType = pType.getRawType();
			System.out.println("原始类型是：" + rawType);
			//获取泛型类型的泛型参数
			Type[] tArgs = pType.getActualTypeArguments();
			System.out.println("泛型信息是：");
			for(int i=0;i<tArgs.length;i++){
				System.out.println("第" + i + "个泛型类型是：" + tArgs[i]);
			}
		}else{
			System.out.println("获取泛型类型出错！！");
		}
	}
}
