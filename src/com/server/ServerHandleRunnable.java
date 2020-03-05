package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.entity.User;
import com.service.IBrandService;
import com.service.ICarService;
import com.service.IRecordService;
import com.service.ITypeService;
import com.service.IUserService;
import com.service.impl.BrandServiceImpl;
import com.service.impl.CarServiceImpl;
import com.service.impl.RecordServiceImpl;
import com.service.impl.TypeServiceImpl;
import com.service.impl.UserServiceImpl;

public class ServerHandleRunnable implements Runnable{ 
	
	IUserService userService=new UserServiceImpl();
	ICarService carService=new CarServiceImpl();
	IRecordService recordService=new RecordServiceImpl();
	IBrandService brandService=new BrandServiceImpl();
	ITypeService typeService=new TypeServiceImpl();
	
	private Socket socket = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	
	private static User user=null;
	
	public ServerHandleRunnable(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {	
		try {
			br = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
			//true:自动刷新缓存
			pw = new PrintWriter(socket.getOutputStream(), true);
			//交互(IO)
			//接收客户端请求
			String request = br.readLine();
			//处理请求,生成响应,不同的请求做不同的处理
			String response = handleRequest(request);
			//发送响应给客户端
			pw.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	/**
	 * 关闭流		
	 */
	public void close() {
		if(br!=null) {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pw!=null) {
			pw.close();
		}
		if(socket!=null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 请求处理
	 * @param request
	 * @return
	 */
	public String handleRequest(String request) {
		//按照+拆分
		String[] array = request.split("\\+");
		switch(array[0]) {
		case "0"://登录,注册
			switch (array[1]) {
			case "1"://登录
				user = userService.userLogOn(array[2], array[3]);
				return JSON.toJSONString(userService.userLogOn(array[2], array[3]));
			case "2":
				return userService.userRegister(array[2], array[3])+"";
			default:
				break;
			}
			return null;
		case "1"://进入租车订单
			return JSON.toJSONString(carService.borrowCar(Integer.valueOf(array[1])));
			
		case "2"://按价格降序排序
			switch (array[1]) {
			case "1":
				return JSON.toJSONString(
						carService.selectCarByStateAndOrderByPriceDesc());
			case "2":
				return JSONArray.toJSONString(
						carService.selectCarByStateAndOrderByPrice());
			default:
				break;
			}
		case "3"://按类型搜索
			return JSON.toJSONString(
					carService.selectCarByType(array[1]));
		case "4"://按品牌搜索
			return JSON.toJSONString(
					carService.selectCarByBrand(array[1]));
		case "5"://查看全部汽车
			return JSON.toJSONString(
					carService.selectCarByState());
		case "6"://查看我的租车记录
			return JSON.toJSONString(
					recordService.selectRecordByUser(user.getId()));
		case "7"://还车
			return JSON.toJSONString(
					carService.returnCar(Integer.valueOf(array[1])));
		case "10":
			return JSON.toJSONString(userService.adminLogOn(array[1], array[2]));
		
		case "11":
			return JSON.toJSONString(carService.selectCarByCarId(
					Integer.valueOf(array[1])));
		case "15":
			return JSON.toJSONString(carService.selectAllCar());
		case "16":
			switch (array[1]) {
			case "1":
				return JSON.toJSONString(brandService.selectAllBrand());
			case "2":
				return JSON.toJSONString(typeService.selectAllType());
			case "3":
				return JSON.toJSONString(carService.addCar(array[2], 
						array[3], Integer.valueOf(array[4]), 
						Integer.valueOf(array[5]), Double.valueOf(array[6]), 
						Integer.valueOf(array[7]), Integer.valueOf(array[8])));
			default:
				break;
			}
		case "17":
			switch (array[1]) {
			case "1":
				return JSON.toJSONString(carService.selectCarByCarId(
						Integer.valueOf(array[2])));
			case "2":
				return JSON.toJSONString(carService.updateCarPrice(
						Integer.valueOf(array[2]), Double.valueOf(array[3])));
			case "3":
				return JSON.toJSONString(carService.updateCarOnSale0(
						Integer.valueOf(array[2])));
			case "4":
				return JSON.toJSONString(carService.updateCarOnSale1(
						Integer.valueOf(array[2])));
			default:
				break;
			}
		case "18":
			return JSON.toJSONString(
					recordService.selectRecordByUser(Integer.valueOf(array[1])));
		case "19":
			return JSON.toJSONString(
					recordService.selectRecordByCarId(Integer.valueOf(array[1])));
		case "20":
			return JSON.toJSONString(
					recordService.selectAllRecord());
		default:
			
			break;
		}
		return null;
	}
}
