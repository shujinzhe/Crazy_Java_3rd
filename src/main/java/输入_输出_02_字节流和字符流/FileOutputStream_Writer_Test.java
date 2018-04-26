package 输入_输出_02_字节流和字符流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月22日
 * 
 * 	OutputStream和Writer是所有输入流的抽象
 * 
 * 	OutputStream和Writer中包含的方法：
 * 		1.void write(int c)
 * 		2.void write(byte[]/char[] buff)
 * 		3.void write(byte[]/char[] buff,int off,int len)
 * 	Writer中还包含如下方法：
 * 		1.void write(String str)
 * 		2.void write(String str,int off,int length)
 */
public class FileOutputStream_Writer_Test {
	public static void main(String[] args) throws Exception {
		File file = new File("test.txt");
		//JVM退出文件自动销毁
		file.deleteOnExit();
		// 创建字节输入流
		FileInputStream fis = new FileInputStream(
			"src/输入_输出_02_字节流和字符流/FileOutputStream_Writer_Test.java");
		// 创建字节输出流
		FileOutputStream fos = new FileOutputStream(file);
		byte[] bbuf = new byte[32];
		int hasRead = 0;
		// 循环从输入流中取出数据
		while ((hasRead = fis.read(bbuf)) > 0 ){
			// 每读取一次，即写入文件输出流，读了多少，就写多少。
			fos.write(bbuf , 0 , hasRead);
		}
			
		FileWriter fw = new FileWriter(file);
		fw.write("Hello FileWriter");
		
	}
}
