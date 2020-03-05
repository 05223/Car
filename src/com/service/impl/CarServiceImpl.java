package com.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import com.dao.IBrandDao;
import com.dao.ICarDao;
import com.dao.IRecordDao;
import com.dao.ITypeDao;
import com.dao.IUserDao;
import com.dao.impl.BrandDaoImpl;
import com.dao.impl.CarDaoImpl;
import com.dao.impl.RecordDaoImpl;
import com.dao.impl.TypeDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.entity.Brand;
import com.entity.Car;
import com.entity.Record;
import com.entity.Type;
import com.service.ICarService;
import com.util.ConnectionFactory;
import com.util.CarGetBrandAndTypeUtil;
import com.util.RecordGetBrandAndType;

public class CarServiceImpl implements ICarService {
	
	ICarDao carDao=new CarDaoImpl();
	IRecordDao recordDao=new RecordDaoImpl();
	IUserDao userDao=new UserDaoImpl();
	IBrandDao brandDao=new BrandDaoImpl();
	ITypeDao typeDao=new TypeDaoImpl();
	UserServiceImpl userservice=new UserServiceImpl();
	
	/**
	 * 查看已上架汽车信息
	 */
	@Override
	public List<Car> selectCarByState() {
		
		try {
			return CarGetBrandAndTypeUtil.getBrandAndType(
					carDao.selectCarByState());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询所有车辆
	 */
	public List<Car> selectAllCar(){
		try {
			return CarGetBrandAndTypeUtil.getBrandAndType(
					carDao.selectAllCar());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 */
	@Override
	public Car selectCarByCarId(int carId) {
		try {
			return CarGetBrandAndTypeUtil.getBrandAndType(
					carDao.selectCarByCarId(carId));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查看已上架汽车信息,价格升序
	 */
	@Override
	public List<Car> selectCarByStateAndOrderByPrice(){
		try {
			return CarGetBrandAndTypeUtil.getBrandAndType(
					carDao.selectCarByStateAndOrderByPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查看已上架汽车信息,价格降序
	 */
	@Override
	public List<Car> selectCarByStateAndOrderByPriceDesc() {
		
		try {
			return CarGetBrandAndTypeUtil.getBrandAndType(
					carDao.selectCarByStateAndOrderByPriceDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 品牌查询
	 * @param name
	 * @return
	 */
	@Override
	public List<Car> selectCarByBrand(String name){
		try {
			Brand brand = brandDao.selectBrandByName(name);
			return CarGetBrandAndTypeUtil.getBrandAndType(
					carDao.selectCarByBrand(brand.getId()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 类型查询
	 */
	@Override
	public List<Car> selectCarByType(String name) {
		try {
			Type type = typeDao.selecTypeByName(name);
			//验证该类型是否存在
			if (carDao.selectCarByType(type.getId())==null) {
				return null;
			}
			
			return CarGetBrandAndTypeUtil.getBrandAndType(
					carDao.selectCarByType(type.getId()));
			
		} catch (Exception e) {
			System.out.println("输入有误!");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 借车服务
	 */
	@Override
	public Record borrowCar(int carId) {
		Connection conn=null;
		int userId=userservice.getUser().getId();
		
		try {
			if (carDao.selectCarByCarId(carId)!=null && carDao.selectCarByCarId(carId).getState()==0 && 
					carDao.selectCarByCarId(carId).getOnSale()==0) {
				conn=ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				
				//将车辆状态设为1
				carDao.updateCarState(conn, carId);
				
				//record表中,添加借车记录
				recordDao.insertBorrowRecord(conn, userId, carId,
						new Timestamp(System.currentTimeMillis()));
				
				conn.commit();
				
				Record record = recordDao.selectLastRecordByCarId(carId);
				record.setCar(carDao.selectCarByCarId(carId));
				return RecordGetBrandAndType.recordGetBrandAndType(record);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 还车服务
	 */
	@Override
	public Record returnCar(int carId) {
		Connection conn=null;
		try {
			conn=ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			
			Record record = recordDao.selectLastRecordByCarId(carId);
			
			if (carDao.selectCarByCarId(carId).getState() == 1 && 
					record.getUserId() == userservice.getUser().getId() && 
					record.getReturnTime() == null) {
				
				//将车辆租借状态设为0
				carDao.updateCarStateReturn(conn, carId);
				
				//还车时间
				Timestamp returnTime=new Timestamp(System.currentTimeMillis());
				//租金总额
				double payment=Math.ceil((returnTime.getTime()-
						record.getBorrowTime().getTime())*1.0/(1000*60*60*24))*carDao.selectCarByCarId(carId).getPrice();
				
				//更新租车记录表
				recordDao.updateRecordReturnTimeByCarId(conn, carId,returnTime,payment);
				
				conn.commit();
				
				Record rec = recordDao.selectLastRecordByCarId(carId);
				rec.setCar(carDao.selectCarByCarId(carId));
				return RecordGetBrandAndType.recordGetBrandAndType(rec);
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		return null;
	}
	
	/**
	 * 用户查询租车记录
	 */
	@Override
	public List<Record> userRecords() {
		int userId=userservice.getUser().getId();
		try {
			List<Record> list = recordDao.selectRecordByUserId(userId);
			/*for (Record record : list) {
				record.setUser(userDao.selecUserById(record.getUserId()));
				record.setCar(carDao.selectCarByCarId(record.getCarId()));
			}
			*/
			return RecordGetBrandAndType.recordGetBrandAndType(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 添加汽车服务
	 */
	@Override
	public int addCar(String name, String note, int brand, int type, double price, int state,int onSale) {
		//添加到car表中
		return carDao.insertCar(name, note, brand, type, price, state,onSale);
	}
	/**
	 * 上架
	 */
	@Override
	public int updateCarOnSale0(int carId) {
		// TODO Auto-generated method stub
		try {
			if (carDao.selectCarByCarId(carId).getOnSale()==1) {
				return carDao.updateCarOnSale0(carId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 下架
	 */
	@Override
	public int updateCarOnSale1(int carId) {
		
		try {
			//判断车有没有被借出,如果就可以下架,否则不可以
			if(carDao.selectCarByCarId(carId).getState()==0) {
				return carDao.updateCarOnSale1(carId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 修改汽车价格
	 */
	@Override
	public int updateCarPrice(int carId, double price) {
		
		try {
			return carDao.updateCarPrice(carId, price);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
