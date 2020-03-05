package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//基于TCP协议的服务器端编程
public class TCPServer {
	private ServerSocket serverSocket;//服务器套接字
	private int port;//服务器端口号
	
	public TCPServer() {
		port = 6666;//默认值
	}
	
	public TCPServer(int port) {
		this.port = port;
	}
	
	//启动服务器
	public void start() {
		try {
			//1.创建服务器套接字对象ServerSocket,指定服务器端口号
			serverSocket = new ServerSocket(port);
			//2.不断等待客户端连接请求,连接成功,返回与客户端交互的套接字Socket对象
			while(true) {
				Socket socket = serverSocket.accept();
				
				//3.交互(IO),为了不影响其他客户端连接,应该在新的线程中
				ServerHandleRunnable r = new ServerHandleRunnable(socket);
				Thread t = new Thread(r);
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(serverSocket!=null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
