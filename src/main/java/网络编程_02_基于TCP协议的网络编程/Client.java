package 网络编程_02_基于TCP协议的网络编程;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月21日
 */
public class Client {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 30000);
		//10秒后即认定为超时
		socket.setSoTimeout(10000);
		/**
		 * 为连接服务器指定超时时长，可以先建立一个无连接的socket
		 * Socket s = new Socket();
		 * 连接远程服务器，如果10秒还未连上，认为超时
		 * s.connect(new InetAddress(host,port), 10000);
		 */
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		String line = br.readLine();
		System.out.println(line);
		br.close();
		socket.close();
	}
}
