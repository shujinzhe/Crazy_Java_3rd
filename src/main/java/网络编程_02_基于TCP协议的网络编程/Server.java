package 网络编程_02_基于TCP协议的网络编程;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月21日
 * 
 * 	创建TCP服务器端
 * 
 * 	ServerSocket包含一个监听来自客户端连接请求的方法
 * 		Socket accept()
 * 	ServerSocket的构造器：
 * 		1.ServerSocket(int port)
 * 		2.ServerSocket(int port,int backlog)：增加一个用来改变队列长度的参数
 * 		3.ServerSocket(int port,int backlog,InetAddress localAddr)
 * 			主机存在多个IP的情况下，使用localAddr参数将ServerSocket绑定到指定的IP上
 * 
 * 
 * 	使用Socket进行通信
 * 	Socket的两个构造器：
 * 		1.Socket(InetAddress/String remoteAddress,int port)
 * 			默认使用本地主机的默认IP，默认使用系统分配的端口
 * 		2.Socket(InetAddress/String remoteAddress,int port,
 * 				InetAddress localAddr,int localPort)
 * 			本地主机有多个IP的情况
 * 
 * 
 * 	Socket获取输入流和输出流：
 * 		InputStream getInputStream()
 * 		OutputStream getOutputStream()
 */
public class Server {
	public static void main(String[] args) throws IOException {
		//创建一个SocketServer，用于监听客户端Socket的连接请求
		ServerSocket ss = new ServerSocket(30000);
		//循环不断的接收用户的客户端的请求
		while(true){
			//受到客户端Socket请求时，服务器端也产生一个Socket
			Socket s = ss.accept();
			//将Socket对应的输出流包装成PrintStream
			PrintStream ps = new PrintStream(s.getOutputStream());
			ps.println("来自服务器的响应");
			ps.close();
			s.close();
		}
	}
}
