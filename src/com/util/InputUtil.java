package com.util;

import java.util.Scanner;
//Scanner的工具类---一般工具类的方法都是静态方法
public class InputUtil {
	//static:只有一份,一份即可
	private static Scanner input = new Scanner(System.in);
	
	//输入int
	public static int nextInt() {
		return input.nextInt();
	}
	
	//输入double
	public static double nextDouble() {
		return input.nextDouble();
	}
	
	//输入String
	public static String next() {
		return input.next();
	}
	
	public static long nextLong() {
		return input.nextLong();
	}
}
