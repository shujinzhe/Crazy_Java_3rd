package 输入_输出_02_字节流和字符流;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileReader
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
 * 
 * 	共有的方法：
 * 		1.void mark(int readAheadLimit)：在记录指针当前位置记录一个标记(mark)
 * 		2.boolean markSupported()：判断输入流是否支持mark()操作
 * 		3.void reset()：将此流的记录指针重新定位到上一次mark的位置
 * 		4.long skip(long n)：记录指针向后移动n个字节/字符
 */
public class S005_FileReaderTest {
	public static void main(String[] args){
		try {
			FileReader fr = new FileReader("src/main/java/输入_输出_01_File类/S005_FileReaderTest.java");
			// 缓冲区
			char[] buff = new char[32];
			// 实际读取的字符数
			int hasRead = 0;
			while((hasRead = fr.read(buff)) != -1) {
				// fr.skip(2);
				System.out.print(new String(buff, 0, hasRead));
			}
		}catch (IOException e) {
			// Java7中所有的IO资源类，都实现了AutoClose接口，可以通过自动关闭资源的try语句关闭这些流
			e.printStackTrace();
		}
		
	}
}
