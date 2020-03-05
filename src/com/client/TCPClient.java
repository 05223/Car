package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//基于TCP协议的客户端编程
public class TCPClient {
	private Socket socket;// 套接字对象
	private String serverIP;// 服务器端ip地址
	private int serverPort;// 服务器端端口号
	private PrintWriter pw;
	private BufferedReader br;
	
	public TCPClient() {
		this.serverIP = "127.0.0.1";
		this.serverPort = 6666;
	}
	
	public TCPClient(String serverIP,int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}

	// 建立连接,发送msg,接收处理结果返回
	public String connectAndSendMsg(String msg) {
		try {
			//与服务器端建立连接
			socket = new Socket(serverIP, serverPort);
			// true:表示自动刷新缓存,写一次就flush()一次
			pw = new PrintWriter(socket.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 2.交互(IO)
			pw.println(msg);
			String line = br.readLine();
			return line;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	
	public void close() {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pw != null) {
			pw.close();
		}
		// 3.关闭socket
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		TCPClient tcpClient = new TCPClient("192.168.50.228", 8888);
		String result = tcpClient.connectAndSendMsg("测试");
		System.out.println(result);
	}
}
