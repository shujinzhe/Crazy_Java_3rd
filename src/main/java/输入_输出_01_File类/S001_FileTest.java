package 输入_输出_01_File类;

import java.io.File;
import java.io.IOException;

/**
 * File类，访问文件与目录
 * @author Eric_Junior
 * 创建时间：2017年9月22日
 * 
 * 	访问文件名相关的方法
 * 		1.String getName()：文件名或路径名(如果是路径，返回最后一级子路径名)
 * 		2.String getPath()：路径名
 * 		3.File getAbsoluteFile()：绝对路径
 * 		4.String getAbsolutePath()：绝对路径名
 * 		5.String getParent()：父目录名
 * 		6.boolean renameTo(File newName)：重命名
 * 
 * 	文件检测相关方法
 * 		1.boolean exists()：文件或目录是否存在
 * 		2.boolean canWrite()：是否可写
 * 		3.boolean canRead()：是否可读
 * 		4.boolean isFile()：是否是文件
 * 		5.boolean isDirectory()：是否是目录
 * 		6.boolean isAbsolute()：是否是绝对路径
 * 
 * 	获取常规文件信息
 * 		1.long lastModified()：最后修改时间
 * 		2.long length()：长度
 * 
 * 	文件操作相关方法
 * 		1.boolean createNewFile()：新建File对象所指定的文件
 * 		2.boolean delete()：删除
 * 		3.static File createTempFile(String prefix,String suffix)
 * 			默认的临时文件目录中创建一个临时的空文件，使用指定前缀后缀，默认后缀为.tmp
 * 		4.static File createTempFile(String prefix,String suffix,File directory)
 * 			指定目录中创建临时文件
 * 		5.void deleteOnExit()：当JVM退出时，删除File对象对应的文件和目录
 * 
 * 	目录操作相关方法
 * 		1.boolean mkdir()：创建目录
 * 		2.String[] list()：列出File对象所有子文件和路径，返回String数组
 * 		3.File[] listFiles()：范围File数组
 * 		4.static File[] listRoots()：列出系统所有的根路径
 */
public class S001_FileTest {
	public static void main(String[] args) throws IOException{
		//使用当前路径创建一个File对象
		File file = new File(".");
		//获取文件名
		System.out.println(file.getName());
		//获取父路径
		System.out.println(file.getParent());
		//获取绝对路径
		System.out.println(file.getAbsoluteFile());
		//获取上一级路径
		System.out.println(file.getAbsoluteFile().getParent());
		//当前路径下创建一个临时文件
		File tmpFile = file.createTempFile("aaa", ".txt",file);
		//指定JVM退出时删除
		tmpFile.deleteOnExit();
		//以系统当前时间作为新文件名创建文件
		File newFile = new File(System.currentTimeMillis() + "");
		//文件是否存在
		System.out.println("newFile对象是否存在：" + newFile.exists());
		//创建新文件
		newFile.createNewFile();
		//此时newFile存在，创建目录失败
		newFile.mkdir();
		//列出所有文件和路径
		String[] fileList = file.list();
		System.out.println("===当前路径下所有文件和路径如下===");
		for(String fileName : fileList){
			System.out.println(fileName);
		}
		//列出所有磁盘根路径
		System.out.println("===系统根路径如下===");
		for(File root : File.listRoots()){
			System.out.println(root);
		}
	}
}
