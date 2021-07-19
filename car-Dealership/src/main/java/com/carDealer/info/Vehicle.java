package com.carDealer.info;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class Vehicle {
	
	private String sellerEmail;
	
	private String customerEmail;
	
	private double price;
	
	private double discountedPrice;
	
	private String make;
	
	private String model;
	
	private String year;
	
	private String miles;
	
	private String status;
	
	private String condition;
	
	private LocalDate purchaseDate;
	
	private LocalDate postedDate;
	
	private int vinNumber;
	
	private MultipartFile picture;
	
	private String carPicture;
	
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getMiles() {
		return miles;
	}
	public void setMiles(String miles) {
		this.miles = miles;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate localDate) {
		this.purchaseDate = localDate;
	}
	
	public LocalDate getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(LocalDate localDate) {
		this.postedDate = localDate;
	}
	
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public int getVinNumber() {
		return vinNumber;
	}
	public void setVinNumber(int vinNumber) {
		this.vinNumber = vinNumber;
	}
	public double getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public String getCarPicture() {
		return carPicture;
	}
	public void setCarPicture(String carPicture) {
		this.carPicture = carPicture;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
		
	
}
