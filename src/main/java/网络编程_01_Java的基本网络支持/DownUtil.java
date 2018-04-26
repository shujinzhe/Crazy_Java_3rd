package 网络编程_01_Java的基本网络支持;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月21日
 * 
 * 	URL(Uniform Resource Locator)对象代表统一资源定位器，指向互联网资源的指针
 * 		格式：protocol://host:port/resourceName
 * 		示例：http://www.baidu.com/index.php
 * 
 * 	URL对象的方法：
 * 		1.String getFile()：获取URL资源名
 * 		2.String getHost()：主机名
 * 		3.String getPath()：资源路径
 * 		4.int getPort()：端口
 * 		5.String getProtocol()：协议
 * 		6.String getQuery()：查询字符串部分
 * 	****7.URLConnection openConnection()：与URL所引用的远程对象的连接
 * 	****8.InputStream openSource()：打开URL的连接，返回一个输入流
 * 
 * 
 * 	多线程下载的步骤：
 * 		1.URL对象
 * 		2.获取资源大小
 * 		3.本地磁盘新建一个与网络资源相同大小的文件
 * 		4.计算每个线程应该下载资源的哪个部分
 * 		5.依次创建启动多个线程下载网络资源的指定部分
 * 
 * 
 * 	断点下载原理：
 * 		增加配置文件，记录每个线程下载到哪个字节，当网络断开后再次开始下载
 * 		时，每个线程根据配置文件里记录的位置向后下载即可
 */
public class DownUtil {
	//定义下载资源的路径
	private String path;
	//下载文件保存位置
	private String targetFile;
	//下载线程数量
	private int threadNum;
	//下载线程对象
	private DownThread[] threads;
	//下载文件总大小
	private int fileSize;
	
	public DownUtil(String path,String targetFile,int threadNum){
		this.path = path;
		this.threadNum = threadNum;
		//初始化Thread数组
		threads = new DownThread[threadNum];
		this.targetFile = targetFile;
	}
	
	public void download() throws Exception{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//设置超时时间
		conn.setConnectTimeout(5*1000);
		//请求方式
		conn.setRequestMethod("GET");
		//设置才能得到文件长度
		conn.setRequestProperty(
				"Accept",
				"image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
				+ "application/x-shockwave-flash, application/xaml+xml, "
				+ "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
				+ "application/x-ms-application, application/vnd.ms-excel, "
				+ "application/vnd.ms-powerpoint, application/msword, */*");
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Connection", "Keep-Alive");
		//得到文件大小
		fileSize = conn.getContentLength();
		System.out.println("文件大小为： " + fileSize);
		conn.disconnect();
		int currentPartSize = fileSize/threadNum + 1;
		RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
		//设置本地文件大小
		file.setLength(fileSize);
		file.close();
		for(int i=0;i<threadNum;i++){
			//计算每个线程下载的开始位置
			int startPos = i*currentPartSize;
			//每个线程使用一个RandomAccessFile进行下载
			RandomAccessFile currentPart = new RandomAccessFile(targetFile,"rw");
			//定位线程下载位置
			currentPart.seek(startPos);
			//创建线程下载
			threads[i] = new DownThread(startPos,currentPartSize,currentPart);
			threads[i].start();
		}
	}
	
	//获取下载的百分比
	public double getCompleteRate(){
		//统计多个线程已经下载的总大小
		int sumSize = 0;
		for(int i=0;i<threadNum;i++){
			sumSize += threads[i].length;
		}
		return sumSize * 1.0/fileSize;
	}
	
	
	private class DownThread extends Thread{
		//当前线程下载位置
		private int startPos;
		//当前线程需要下载的文件大小
		private int currentPartSize;
		//当前线程需要下载的文件块
		private RandomAccessFile currentPart;
		//已经下好的字节数
		public int length;

		public DownThread(int startPos, int currentPartSize,
				RandomAccessFile currentPart) {
			this.startPos = startPos;
			this.currentPartSize = currentPartSize;
			this.currentPart = currentPart;
		}
		
		public void run(){
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				//设置超时时间
				conn.setConnectTimeout(5*1000);
				//请求方式
				conn.setRequestMethod("GET");
				conn.setRequestProperty(
						"Accept",
						"image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
						+ "application/x-shockwave-flash, application/xaml+xml, "
						+ "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
						+ "application/x-ms-application, application/vnd.ms-excel, "
						+ "application/vnd.ms-powerpoint, application/msword, */*");
				conn.setRequestProperty("Accept-Language", "zh-CN");
				conn.setRequestProperty("Charset", "UTF-8");
				InputStream in = conn.getInputStream();
				//跳过startPos个字节，线程只下载自己负责的那一部分数据
				in.skip(this.startPos);
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				//读取网络数据，并写入本地文件
				while(length<currentPartSize
						&& (hasRead = in.read(buffer))!=-1){
					currentPart.write(buffer,0,hasRead);
					length += hasRead;
				}
				currentPart.close();
				in.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
}
