package com.server;

public class ServerMain {
	public static void main(String[] args) {
		TCPServer tcpServer = new TCPServer();
		tcpServer.start();
	}
}
