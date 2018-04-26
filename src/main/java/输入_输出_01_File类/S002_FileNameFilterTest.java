package 输入_输出_01_File类;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 使用文件过滤器
 * @author Eric_Junior
 * 创建时间：2017年9月22日
 * 
 * 	文件过滤器：实现FilenameFilter接口
 */
public class S002_FileNameFilterTest {
	public static void main(String[] args) {
		File file = new File(".");
//		String[] fileList = file.list(new FilenameFilter() {
//			@Override
//			public boolean accept(File dir, String name) {
//				// TODO Auto-generated method stub
//				return name.endsWith(".java")
//						||new File(name).isDirectory();
//			}
//		});
		/**
		 * Java8中使用lambda表达式
		 */
		String[] fileList = file.list((dir,name) -> name.endsWith(".java")
				|| new File(name).isDirectory());
		for(String filename : fileList){
			System.out.println(filename);
		}
	}
}
