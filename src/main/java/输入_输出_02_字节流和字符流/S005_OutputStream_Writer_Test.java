package 输入_输出_02_字节流和字符流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
 * 	Writer中还包含如下方法：
 * 		1.void write(String str)
 * 		2.void write(String str,int off,int length)
 * 		
 */
public class S005_OutputStream_Writer_Test {
	public static void main(String[] args){
		try {
			InputStream fis = new FileInputStream("src/main/java/输入_输出_02_字节流和字符流/S005_OutputStream_Writer_Test.java");
//			OutputStream fos = new FileOutputStream("newFile.txt");
			OutputStream fos = System.out;
			byte[] buff = new byte[1024];
			int hasRead = 0;
			while((hasRead = fis.read(buff)) != -1) {
				fos.write(buff, 0, hasRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		
		try {
			File file = new File("test.txt");
			//JVM退出文件自动销毁
			file.deleteOnExit();
			FileReader fr = new FileReader("src/main/java/输入_输出_02_字节流和字符流/S005_OutputStream_Writer_Test.java");
			char[] cbuf = new char[32];
			FileWriter fw = new FileWriter(file);
			char[] bbuf = new char[32];
			int hasRead = 0;
			// 循环从输入流中取出数据
			while ((hasRead = fr.read(bbuf)) > 0 ){
				// 每读取一次，即写入文件输出流，读了多少，就写多少。
				fw.write(bbuf, 0 , hasRead);
			}
//			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
