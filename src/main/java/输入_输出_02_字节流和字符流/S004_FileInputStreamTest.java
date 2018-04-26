package 输入_输出_02_字节流和字符流;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * InputStream
 * @author Eric_Junior
 * 创建时间：2018年4月26日
 * 
 * 	InputStream中的3个方法：
 * 		1.int read()：从输入流读取一个字节，返回读取的字节数据（字节可直接转为int）
 * 		2.int read(byte[] b)：从输入流最多读取b.length个字节数据，存储在b中，返回读取的字节个数
 * 		2.int read(byte[] b,int off,int len)：最多读取len个字节，从b的off位置放入，返回读取的字符个数
 * 	Reader中的3个方法：
 * 		1.int read()：从输入流读取一个字符，返回读取的字符数据（字符可直接转为int）
 * 		2.int read(char[] b)：从输入流最多读取b.length个字符数据，存储在b中，返回读取的字符个数
 * 		2.int read(char[] b,int off,int len)：最多读取len个字符，从b的off位置放入，返回读取的字符个数
 */
public class S004_FileInputStreamTest {
	public static void main(String[] args) throws Exception{
		InputStream fis = new FileInputStream("src/main/java/输入_输出_01_File类/S004_FileInputStreamTest.java");
		// 缓冲区
		byte[] buff = new byte[1024];
		// 实际读取的字节数
		int hasRead = 0;
		while((hasRead = fis.read(buff)) != -1) {
			System.out.print(new String(buff, 0, hasRead));
		}
		// 关闭文件输入流，放在finally块中更安全
		fis.close();
	}
}
