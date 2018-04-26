package 类加载机制与反射_07_练习;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月20日
 * 
 * 
 * 	需求：开发一个工具类，该工具类提供一个eval方法，实现JavaScript中eval的函数的功能： 
 *	可以动态运行一行或多行代码。例如eval("System.out.println(\"aa\")"),将可以输出aa 
 *
 *
 *	方案：使用类动态加载机制
 */
public class EvalUtil {
	public static void main(String[] args) {
		try {
			 eval("System.out.println(\"hello world\");");
			 Class eval = Class.forName("类加载机制与反射_07_练习.Eval");
			 Method m = eval.getDeclaredMethod("eval", null);
			 m.invoke(null, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void eval(String str) throws Exception{
		// TODO Auto-generated method stub
		//动态拼接java源文件
		File file = new File("src/类加载机制与反射_07_练习/Eval.java");
		if(!file.exists()){
			file.createNewFile();
		}
		PrintWriter pw = new PrintWriter(new FileOutputStream(file));
		StringBuilder sb = new StringBuilder();
		sb.append("package 类加载机制与反射_07_练习;\n");
		sb.append("public class Eval{\n");
        sb.append("public static void eval(){\n");
        sb.append(str + "\n");
        sb.append("}\n");
        sb.append("}\n");
        pw.write(sb.toString());
        pw.close();
	}
}
