package 网络编程_02_基于TCP协议的网络编程;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月21日
 */
public class MultiThreadClient {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("127.0.0.1",30000);
		//不断的读取来自服务器的数据
		new Thread(new ClientThread(s)).start();
		PrintStream ps = new PrintStream(s.getOutputStream());
		String line = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in));
		while((line = br.readLine())!=null){
			ps.println(line);
		}
	}
}

class ClientThread implements Runnable{
	private Socket s;
	private BufferedReader br;
	public ClientThread(Socket s) throws Exception {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.br = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
	}

	@Override
	public void run() {
		try {
			String content = null;
			//循环不断的从Socket中读取客户端发送过来的数据
			while((content = br.readLine())!=null){
				System.out.println("接收服务器端的消息：" + content);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}
