package com.carDealer.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Service;

import com.carDealer.application.CarDealershipApplication;
import com.carDealer.info.Address;
import com.carDealer.info.Seller;
import com.carDealer.info.Vehicle;
import com.carDealer.service.CarDealerService;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.carDealer")
public class CarDealershipApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application) {
		return application.sources(CarDealershipApplication.class);
	}
	
	public static void main(String[] args) throws Exception {
//		Seller seller = new Seller();
//		seller.setFirstName("kaleb");
//		seller.setLastName("kinskey");
//		seller.setAge("21");
//		seller.setEmail("kaleb@email.com");
//		seller.setPassword("pass");
//		Address address = new Address();
//		address.setStreet("1234 main street");
//		address.setCity("chicago");
//		address.setState("Illnois");
//		address.setZipCode("12345");
//		
//		seller.setAddress(address);
//		
//		CarDealerService cds = new CarDealerService();
//		cds.saveSeller(seller);
//		
//		Vehicle vehicle = new Vehicle();
//		vehicle.setYear("2015");
//		vehicle.setMake("Honda");
//		vehicle.setModel("Accord");
//		vehicle.setMiles("80,000");
//		
//		Vehicle vehicle1 = new Vehicle();
//		vehicle1.setYear("2020");
//		vehicle1.setMake("Jeep");
//		vehicle1.setModel("Wrangler");
//		vehicle1.setMiles("20,000");
//		
//		cds.saveVehicle(vehicle);
//		cds.saveVehicle(vehicle1);
//		
//		ArrayList<Seller>sellers = cds.findAllS();
//		for(Seller s : sellers) {
//			System.out.println(s.toString());
//		}
		
		
		SpringApplication.run(CarDealershipApplication.class, args);
	}
	
}
