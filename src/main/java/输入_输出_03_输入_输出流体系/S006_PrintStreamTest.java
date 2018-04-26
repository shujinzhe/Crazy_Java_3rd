package 输入_输出_03_输入_输出流体系;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 处理流
 * @author Eric_Junior
 * 创建时间：2018年4月26日
 */
public class S006_PrintStreamTest {
	public static void main(String[] args) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
			/**
			 * 关闭最上层的处理流时，系统会自动关闭被该处理流包装的节点流
			 */
			PrintStream printStream = new PrintStream(fileOutputStream);
			printStream.println("普通字符串");
			printStream.print(new S006_PrintStreamTest());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
