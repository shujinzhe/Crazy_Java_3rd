package 输入_输出_02_字节流和字符流;

import java.io.FileInputStream;
import java.io.FileReader;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月22日
 * 
 * 	InputStream和Reader是所有输入流的抽象
 * 
 * 	InputStream中包含的方法：
 * 		1.int read()
 * 		2.int read(byte[] b)
 * 		3.int read(byte[] b,int off,int len)
 * 	Reader中包含的方法：
 * 		1.int read()
 * 		2.int read(char[] c)
 * 		3.int read(char[] c,int off,int len)
 */
public class FileInputStream_Reader_Test {
	public static void main(String[] args) throws Exception {
		//创建字节流
		FileInputStream in = new FileInputStream("src/输入_输出_02_字节流和字符流/FileInputStream_Reader_Test.java");
		byte[] buff = new byte[1024];
		int hasRead = 0;
		while((hasRead = in.read(buff))!=-1){
			System.out.println(new String(buff,0,hasRead));
		}
		in.close();
		System.out.println("===================================");
		//创建字符流
		FileReader fr = new FileReader("src/输入_输出_02_字节流和字符流/FileInputStream_Reader_Test.java");
		char[] cbuf = new char[32];
		while ((hasRead = fr.read(cbuf)) > 0 ){
			System.out.print(new String(cbuf , 0 , hasRead));
		}
	}
}
