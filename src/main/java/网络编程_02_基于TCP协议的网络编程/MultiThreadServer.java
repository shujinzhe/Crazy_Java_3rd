package 网络编程_02_基于TCP协议的网络编程;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月21日
 * 
 * 
 * 	多线程Socket通信
 */
public class MultiThreadServer {
	//保存Socket的ArrayList，并将其包装为线程安全的
	public static List<Socket> socketList = Collections
			.synchronizedList(new ArrayList<Socket>());
	public static void main(String[] args) throws IOException{
		ServerSocket ss = new ServerSocket(30000);
		while(true){
			Socket s = ss.accept();
			socketList.add(s);
			new Thread(new ServerThread(s)).start();
		}
	}
}


class ServerThread implements Runnable{
	private Socket s;
	private BufferedReader br;
	public ServerThread(Socket s) throws IOException {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.br = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String content = null;
			//循环不断的从Socket中读取客户端发送过来的数据
			while((content = readFromClient())!=null){
				//遍历socketList中的每个Socket
				//将读到的内容向每个Socket发送一次
				for(Socket s : MultiThreadServer.socketList){
					PrintStream ps = new PrintStream(
							s.getOutputStream());
					ps.println(content);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private String readFromClient() {
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO: handle exception
			MultiThreadServer.socketList.remove(s);
		}
		return null;
	}
	
}