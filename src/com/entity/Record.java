package com.entity;

import java.sql.Timestamp;

public class Record {
	private int id;
	private int userId;
	private int carId;
	private Timestamp borrowTime;
	private Timestamp returnTime;
	private double payment;
	private User user;
	private Car car;
	public Record() {
		// TODO Auto-generated constructor stub
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public double getPayment() {
		return payment;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Car getCar() {
		return car;
	}
	
	public Record(int id, int userId, int carId, Timestamp borrowTime, Timestamp returnTime,double payment) {
		super();
		this.id = id;
		this.userId = userId;
		this.carId = carId;
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
		this.payment = payment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public Timestamp getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Timestamp borrowTime) {
		this.borrowTime = borrowTime;
	}
	public Timestamp getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}
	
}
