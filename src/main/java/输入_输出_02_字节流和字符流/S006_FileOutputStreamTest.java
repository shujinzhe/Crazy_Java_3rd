package 输入_输出_02_字节流和字符流;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * FileOutputStream
 * @author Eric_Junior
 * 创建时间：2018年4月26日
 * 
 * 	OutputStream中的3个方法：
 * 		1.void write(int c)：向输出流写一个字节
 * 		2.void write(byte[] b)：将字节数组写入输出流
 * 		2.void write(byte[] b,int off,int len)：从off开始，将b中的len个字节写入输出流
 * 	Writer中的3个方法：
 * 		1.void write()：向输出流写一个字符
 * 		2.void write(char[] b)：将字符数组写入输出流
 * 		2.void write(char[] b,int off,int len)：从off开始，将b中的len个字符写入输出流
 * 		
 */
public class S006_FileOutputStreamTest {
	public static void main(String[] args){
		try {
			InputStream fis = new FileInputStream("src/main/java/输入_输出_01_File类/S006_FileOutputStreamTest.java");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
