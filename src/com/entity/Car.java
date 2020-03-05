package com.entity;

public class Car {
	private int id;
	private String name;
	private String note;
	private int brand;
	private int type;
	private double price;
	private int state;
	private int onSale;
	private Brand bra;
	private Type typ;
	
	
	public void setOnSale(int onSale) {
		this.onSale = onSale;
	}
	public int getOnSale() {
		return onSale;
	}
	public void setBra(Brand bra) {
		this.bra = bra;
	}
	public Brand getBra() {
		return bra;
	}
	public void setTyp(Type typ) {
		this.typ = typ;
	}
	public Type getTyp() {
		return typ;
	}
	
	public Car() {
		
	}

	public Car(int id, String name, String note, int brand, int type, double price, int state,int onSale) {
		super();
		this.id = id;
		this.name = name;
		this.note = note;
		this.brand = brand;
		this.type = type;
		this.price = price;
		this.state = state;
		this.onSale=onSale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", note=" + note + ", brand=" + brand + ", type=" + type
				+ ", price=" + price + ", state=" + state + "]";
	}
	
	
	
}
