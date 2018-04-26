package 类加载机制与反射_02_类加载器;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月20日
 * 	
 * 	ClassLoader的核心方法：
 * 		Class definaClass(String name,byte[] b,int off,int len)
 * 	该方法负责将指定类的字节码文件读入字节数组b中，并转为Class对象。该字节码对象
 * 	可以来源于文件、网络等
 * 
 * 
 * 	自定义ClassLoader可重写的方法
 * 	1.loadClass(String name,boolean resolve)：ClassLoader入口点
 * 	系统通过调用CLassLoader的该方法获取指定类对应的Class对象
 * 	2.findClass(String name)：根据名称查找类，通常推荐重写该方法
 * 
 * 
 * 	loadClass()方法的执行步骤：
 * 	1.用findLoaderClass(String)检查是否已经加载类，是则直接返回
 * 	2.父类加载器上调用loadClass()方法。如父类加载器为null，则使用根类加载器
 * 	3.调用findClass(String)方法查找类
 */

public class CompileClassLoader extends ClassLoader{

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class clazz = null;
		//将包路径中的.换成/
		//这里传入的一定是全限定名
		String fileStub = name.replace(".", "/");
		//.java文件名
		String javaFilename = fileStub + ".java";
		//.class文件名
		String classFilename = fileStub + ".class";
		File javaFile = new File(javaFilename);
		File classFile = new File(classFilename);
		//指定Java源文件存在，且Class文件不存在，或者Java源文件
		//的修改时间比Class文件的修改时间更晚，重新编译
		if(javaFile.exists() && (!classFile.exists()
				|| javaFile.lastModified() > classFile.lastModified())){
			try {
				if(!compile(javaFilename) || !classFile.exists()){
					throw new ClassNotFoundException(
							"ClassNotFoundException" + javaFilename);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(classFile.exists()){
			try {
				byte[] raw = getBytes(classFilename);
				//调用ClassLoader的defineClass方法将二进制数据转为Class对象
				defineClass(name, raw, 0,raw.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return clazz;
	}

	private byte[] getBytes(String classFilename) throws IOException{
		// TODO Auto-generated method stub
		File file = new File(classFilename);
		long len = file.length();
		byte[] raw = new byte[(int)len];
		FileInputStream in = new FileInputStream(file);
		int r = in.read(raw);
		if(r!=len){
			throw new IOException("无法读取全部文件："
					+ r + " != " + len);
		}
		return raw;
	}

	private boolean compile(String javaFile) throws IOException{
		// TODO Auto-generated method stub
		System.out.println("CompileClassLoader：正在编译 " 
				+ javaFile + "...");
		Process p = Runtime.getRuntime().exec("java " + javaFile);
		try {
			//其它线程等待这个子进程完成
			p.waitFor();
		} catch (Exception e) {
			System.out.println(e);
		}
		int ret = p.exitValue();
		return ret == 0;
	}
	
	public static void main(String[] args) throws Exception{
		if(args.length < 1){
			System.out.println("缺少目标类，请按如下格式运行Java源文件：");
			System.out.println("java CompileClassLoader ClassName");
		}
		
		//第一个参数是需要运行的类
		String progClass = args[0];
		String[] progArgs = new String[args.length-1];
		System.arraycopy(args, 1, progArgs, 0, progArgs.length);
		CompileClassLoader ccl = new CompileClassLoader();
		Class<?> clazz = ccl.loadClass(progClass);
		
		Method main = clazz.getMethod("main", String[].class);
		Object argsArray[] = {progArgs};
		//非静态方法需要提供底层的类对象,静态方法可省略对象
		main.invoke(null, argsArray);
	}

}
