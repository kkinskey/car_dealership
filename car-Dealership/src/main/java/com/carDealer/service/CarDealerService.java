package com.carDealer.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carDealer.info.Address;
import com.carDealer.info.Customer;
import com.carDealer.info.Seller;
import com.carDealer.info.Vehicle;

@Service
public class CarDealerService extends SpringBootServletInitializer{
	
	@Autowired
	ServletContext servletContext;
	
	ArrayList<Seller>sellers = new ArrayList<Seller>();
	public ArrayList<Vehicle>vehicles = new ArrayList<Vehicle>();
	ArrayList<Customer>customers = new ArrayList<Customer>();
	
	public void saveSeller(Seller seller) {
		sellers.add(seller);
		
	}
	
public Seller findByEmail(String email) {
		
		for(int i= 0; i < sellers.size(); i++) {
			Seller seller = sellers.get(i);
			if(seller.getEmail().equals(email)) {
				return seller;
			}
		}
		return null;
	}

public boolean duplicateEmail(String email) {
	for(Seller s: sellers) {
		if(s.getEmail().equals(email)) {
			return true;
		}
	}
	
	return false;
	
}
public void saveCustomer(Customer customer) {
	customers.add(customer);
}

//public String customerContact() {
//	for (Vehicle v: vehicles) {
//		v.setCustomerEmail(customers.getEmail());
//	}
//	
//	return;
//}

public void saveVehicle(Vehicle vehicle) {
	
	vehicles.add(vehicle);
}

public ArrayList<Seller> findAllS() {
	return sellers;
}

public ArrayList<Vehicle> findAll() {
	return vehicles;
}
public ArrayList<Customer> findAllC() {
	return customers;
}

public Vehicle findOne(String email, String miles, double price) {
	Vehicle s = new Vehicle();
	for(Vehicle v: vehicles) {
		if(v.getSellerEmail().equals(email) && v.getMiles().equals(miles) && v.getPrice() == price) {
			s = v;
		}
	}
	
	return s;
}

public Vehicle findByVinNumber(int vin) {
	Vehicle s = new Vehicle();
	for(Vehicle v: vehicles) {
		if(v.getVinNumber() == vin) {
			s = v;
		}
	}
	
	return s;
}

public boolean bidPrice(int vin) {
	if (ChronoUnit.DAYS.between(findByVinNumber(vin).getPostedDate(), java.time.LocalDate.now()) > 120) {
		return true;
	}
	return false;
}


public ArrayList<Vehicle> store() {
	ArrayList<Vehicle> availableVehicles = new ArrayList<Vehicle>();
	for (Vehicle v : vehicles) {
		if (v.getStatus().equals("For Sale")) {
			availableVehicles.add(v);
		}
			if (bidPrice(v.getVinNumber())) {
				double newPrice = v.getPrice() * .9;
				v.setDiscountedPrice(newPrice);
			} 
	}
	return availableVehicles;
}

public ArrayList<Vehicle> inventory() {
	ArrayList<Vehicle> inventory = new ArrayList<Vehicle>();
	for (Vehicle v : vehicles) {
		if (v.getStatus().equals("Sold")) {
			inventory.add(v);
			if (bidPrice(v.getVinNumber())) {
				double newPrice = v.getPrice() * .9;
				v.setDiscountedPrice(newPrice);
			} else {
				v.setDiscountedPrice(v.getPrice());
			}
			for(Customer c : customers) {
				v.setCustomerEmail(c.getEmail());
			}
			} else if (v.getStatus().equals("For Sale")) {
				double newPrice = 0.0;
				v.setDiscountedPrice(newPrice);
			}
	}
	return inventory;
}

public void addPic(MultipartFile picture) {
	if(picture != null || picture.isEmpty()) {
		String picName = servletContext.getRealPath("/")+"resources\\images\\"+ picture.getOriginalFilename();
		try {
		picture.transferTo(new File(picName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

public boolean searchForVehicle(String make, String model, String year) {
	for(Vehicle v: vehicles) {
		if(v.getMake().equals(make) || v.getModel().equals(model) || v.getYear().equals(year)) {
			return true;
		}
	}
	
	return false;
}

//public ArrayList<Vehicle> findByMakeOrModel(String keyword, String condition) {
//	ArrayList<Vehicle> search = new ArrayList<Vehicle>();
//	if(condition.equals(condition)) {
//		for(Vehicle v: vehicles) {
//			if(v.getMake().equalsIgnoreCase(keyword) || v.getModel().equalsIgnoreCase(keyword)) {
//				search.add(v);
//			}
//		}
//	} else if (condition.equals("NEW")) {
//		for(Vehicle v: vehicles) {
//			if(v.getMake().equalsIgnoreCase(keyword) || v.getModel().equalsIgnoreCase(keyword)) {
//				search.add(v);
//			}
//		}
//	} else if (condition.equals("USED")) {
//		for(Vehicle v: vehicles) {
//			if(v.getMake().equalsIgnoreCase(keyword) || v.getModel().equalsIgnoreCase(keyword)) {
//				search.add(v);
//			}
//		}
//	}
//	return search;
//}
public Vehicle findByPrice(double price) {
	Vehicle s = new Vehicle();
	for(Vehicle v: vehicles) {
		if(v.getPrice() == price) {
			s = v;
		}
	}
	
	return s;
}

public Vehicle findByMake(String make) {
	Vehicle s = new Vehicle();
	for(Vehicle v: vehicles) {
		if(v.getMake() == make) {
			s = v;
		}
	}
	
	return s;
}

public Vehicle findByModel(String model) {
	Vehicle s = new Vehicle();
	for(Vehicle v: vehicles) {
		if(v.getModel() == model) {
			s = v;
		}
	}
	
	return s;
}

public Vehicle findByYear(String year) {
	Vehicle s = new Vehicle();
	for(Vehicle v: vehicles) {
		if(v.getYear() == year) {
			s = v;
		}
	}
	
	return s;
}

public Vehicle findByCondition(String condition) {
	Vehicle s = new Vehicle();
	for(Vehicle v: vehicles) {
		if(v.getCondition() == condition) {
			s = v;
		}
	}
	
	return s;
}



//@SuppressWarnings("unchecked")
public ArrayList<Vehicle> findAllMatches(Vehicle vehicle) {
	double price = vehicle.getPrice();
	String make = vehicle.getMake();
	System.out.println(make);
	String model = vehicle.getModel();
	String year = vehicle.getYear();
	String condition = vehicle.getCondition();
	
	ArrayList<Vehicle> searchVehicles = new ArrayList<Vehicle>();
	
		for(Vehicle v: vehicles) {
			if(v.getStatus().equals("For Sale")) {
				if(price > 0 && v.getPrice() == price) {
					searchVehicles.add(v);
				}
				if(make != null && v.getMake().trim().equalsIgnoreCase(make.trim())) {
					searchVehicles.add(v);
				}
				if(model != null && v.getModel().trim().equalsIgnoreCase(model.trim())) {
					searchVehicles.add(v);
				}
//				if(year != null && v.getYear().trim().equalsIgnoreCase(year.trim())) {
//					searchVehicles.add(findByYear(year));
//				}
//				if(condition != null && v.getCondition().trim().equalsIgnoreCase(condition.trim())) {
//					searchVehicles.add(findByCondition(condition));
//				}
				
			}
		}
		return searchVehicles;
}

{Seller seller = new Seller();
seller.setFirstName("Enzo");
seller.setLastName("Ferrari");
seller.setAge("80");
seller.setEmail("enzo@email.com");
seller.setPassword("pass");
Address address = new Address();
address.setStreet("1234 main street");
address.setCity("Los Angeles");
address.setState("California");
address.setZipCode("12345");

seller.setAddress(address);

Vehicle vehicle = new Vehicle();
vehicle.setPrice(625000.00);
vehicle.setDiscountedPrice(0);
vehicle.setMake("Ferrari");
vehicle.setModel("SF90 Stradale");
vehicle.setYear("2020");
vehicle.setMiles("0");
vehicle.setStatus("For Sale");
vehicle.setCarPicture("2020-ferrari-sf90-stradale.jpeg");
vehicle.setSellerEmail(seller.getEmail());
LocalDate localDate1 = LocalDate.of(2021, 6, 1);
LocalDate localDate2 = LocalDate.of(2021, 7, 15);
vehicle.setPostedDate(localDate1);
vehicle.setPurchaseDate(localDate2);
vehicle.setVinNumber(1);
vehicle.setCondition("NEW");


vehicles.add(vehicle);

sellers.add(seller);

Seller seller2 = new Seller();
seller2.setFirstName("jack");
seller2.setLastName("smith");
seller2.setAge("21");
seller2.setEmail("smith@email.com");
seller2.setPassword("pass");
Address address2 = new Address();
address2.setStreet("1234 main street");
address2.setCity("chicago");
address2.setState("Illnois");
address2.setZipCode("12345");

seller2.setAddress(address2);

Vehicle vehicle2 = new Vehicle();
vehicle2.setPrice(6000.00);
vehicle2.setDiscountedPrice(5400.00);
vehicle2.setMake("Pontiac");
vehicle2.setModel("Grand Prix");
vehicle2.setYear("2012");
vehicle2.setMiles("70,000");
vehicle2.setStatus("For Sale");
vehicle2.setCarPicture("Grand Prix.jpeg");
vehicle2.setSellerEmail(seller2.getEmail());
LocalDate localDate3 = LocalDate.of(2016, 8, 19);
LocalDate localDate4 = LocalDate.of(2021, 7, 15);
vehicle2.setPostedDate(localDate3);
vehicle2.setPurchaseDate(localDate4);
vehicle2.setVinNumber(2);
vehicle2.setCondition("USED");


vehicles.add(vehicle2);


sellers.add(seller2);

Seller seller3 = new Seller();
seller3.setFirstName("joe");
seller3.setLastName("kinskey");
seller3.setAge("63");
seller3.setEmail("joe@email.com");
seller3.setPassword("pass");
Address address3 = new Address();
address3.setStreet("1234 main street");
address3.setCity("Sheridan");
address3.setState("Wyoming");
address3.setZipCode("12345");

seller3.setAddress(address3);

Vehicle vehicle3 = new Vehicle();
vehicle3.setPrice(1000000);
vehicle3.setDiscountedPrice(0);
vehicle3.setMake("Ford");
vehicle3.setModel("GT");
vehicle3.setYear("2021");
vehicle3.setMiles("4");
vehicle3.setStatus("For Sale");
vehicle3.setCarPicture("200121161950-2017-ford-gt-super-tease.jpeg");
vehicle3.setSellerEmail(seller3.getEmail());
LocalDate localDate5 = LocalDate.of(2021, 7, 1);
LocalDate localDate6 = LocalDate.of(2021, 7, 15);
vehicle3.setPostedDate(localDate5);
vehicle3.setPurchaseDate(localDate6);
vehicle3.setVinNumber(3);
vehicle3.setCondition("NEW");


vehicles.add(vehicle3);

sellers.add(seller3);

Seller seller4 = new Seller();
seller4.setFirstName("Ron");
seller4.setLastName("Burgundy");
seller4.setAge("55");
seller4.setEmail("ron@email.com");
seller4.setPassword("pass");
Address address4 = new Address();
address4.setStreet("334 Wall street");
address4.setCity("New York");
address4.setState("New York");
address4.setZipCode("10001");

seller4.setAddress(address4);

Vehicle vehicle4 = new Vehicle();
vehicle4.setPrice(8500);
vehicle4.setDiscountedPrice(7650);
vehicle4.setMake("Pontiac");
vehicle4.setModel("Catalina");
vehicle4.setYear("1970");
vehicle4.setMiles("63,459");
vehicle4.setStatus("For Sale");
vehicle4.setCarPicture("7ec982708608b88a9f287ffc28edfde19e4cc01e.jpeg");
vehicle4.setSellerEmail(seller4.getEmail());
LocalDate localDate7 = LocalDate.of(1990, 2, 23);
LocalDate localDate8 = LocalDate.of(2021, 7, 15);
vehicle4.setPostedDate(localDate7);
vehicle4.setPurchaseDate(localDate8);
vehicle4.setVinNumber(4);
vehicle4.setCondition("USED");


vehicles.add(vehicle4);

sellers.add(seller4);

Seller seller5 = new Seller();
seller5.setFirstName("Eric");
seller5.setLastName("Lindros");
seller5.setAge("63");
seller5.setEmail("eric@email.com");
seller5.setPassword("pass");
Address address5 = new Address();
address5.setStreet("564 Canton Ave");
address5.setCity("Philadelphia");
address5.setState("Pennsylvania");
address5.setZipCode("12896");

seller5.setAddress(address5);

Vehicle vehicle5 = new Vehicle();
vehicle5.setPrice(16000);
vehicle5.setDiscountedPrice(0);
vehicle5.setMake("Ford");
vehicle5.setModel("F-150");
vehicle5.setYear("2016");
vehicle5.setMiles("80,000");
vehicle5.setStatus("For Sale");
vehicle5.setCarPicture("2011-Ford-F-150-1024x680.jpeg");
vehicle5.setSellerEmail(seller5.getEmail());
LocalDate localDate9 = LocalDate.of(2010, 5, 11);
LocalDate localDate10 = LocalDate.of(2021, 7, 15);
vehicle5.setPostedDate(localDate9);
vehicle5.setPurchaseDate(localDate10);
vehicle5.setVinNumber(5);
vehicle5.setCondition("USED");


vehicles.add(vehicle5);

sellers.add(seller5);


Seller seller6 = new Seller();
seller6.setFirstName("Chuck");
seller6.setLastName("Murphy");
seller6.setAge("20");
seller6.setEmail("chuck@email.com");
seller6.setPassword("pass");
Address address6 = new Address();
address6.setStreet("425 Crook st.");
address6.setCity("Baltimore");
address6.setState("Maryland");
address6.setZipCode("27834");

seller6.setAddress(address6);

Vehicle vehicle6 = new Vehicle();
vehicle6.setPrice(25000);
vehicle6.setDiscountedPrice(0);
vehicle6.setMake("Subaru");
vehicle6.setModel("Crosstrek");
vehicle6.setYear("2021");
vehicle6.setMiles("10");
vehicle6.setStatus("For Sale");
vehicle6.setCarPicture("21_Crosstrek_Base_CrystalBlackSilica_1200x800_1.png");
vehicle6.setSellerEmail(seller6.getEmail());
LocalDate localDate11 = LocalDate.of(2021, 6, 26);
LocalDate localDate12 = LocalDate.of(2021, 7, 15);
vehicle6.setPostedDate(localDate11);
vehicle6.setPurchaseDate(localDate12);
vehicle6.setVinNumber(6);
vehicle6.setCondition("NEW");


vehicles.add(vehicle6);

sellers.add(seller6);

Seller seller7 = new Seller();
seller7.setFirstName("Roger");
seller7.setLastName("Lechtenberg");
seller7.setAge("69");
seller7.setEmail("radar@email.com");
seller7.setPassword("pass");
Address address7 = new Address();
address7.setStreet("1612 Hudson Boulvard");
address7.setCity("BakersField");
address7.setState("California");
address7.setZipCode("54321");

seller7.setAddress(address7);

Vehicle vehicle7 = new Vehicle();
vehicle7.setPrice(5000000);
vehicle7.setDiscountedPrice(4500000);
vehicle7.setMake("Ford");
vehicle7.setModel("Ranger");
vehicle7.setYear("2019");
vehicle7.setMiles("12,000");
vehicle7.setStatus("For Sale");
vehicle7.setCarPicture("e3014a6a-22cd-4000-8ea9-90cd098c6700.png");
vehicle7.setSellerEmail(seller7.getEmail());
LocalDate localDate13 = LocalDate.of(2016, 6, 19);
LocalDate localDate14 = LocalDate.of(2021, 7, 15);
vehicle7.setPostedDate(localDate13);
vehicle7.setPurchaseDate(localDate14);
vehicle7.setVinNumber(7);
vehicle7.setCondition("USED");


vehicles.add(vehicle7);

sellers.add(seller7);

Seller seller8 = new Seller();
seller8.setFirstName("Gary");
seller8.setLastName("Coleman");
seller8.setAge("33");
seller8.setEmail("gary@email.com");
seller8.setPassword("pass");
Address address8 = new Address();
address8.setStreet("768 Watchler ave");
address8.setCity("Seattle");
address8.setState("Washington");
address8.setZipCode("39275");

seller8.setAddress(address8);

Vehicle vehicle8 = new Vehicle();
vehicle8.setPrice(50000);
vehicle8.setDiscountedPrice(0);
vehicle8.setMake("Audi");
vehicle8.setModel("R8");
vehicle8.setYear("2021");
vehicle8.setMiles("45");
vehicle8.setStatus("For Sale");
vehicle8.setCarPicture("a1813746_large.jpeg");
vehicle8.setSellerEmail(seller8.getEmail());
LocalDate localDate15 = LocalDate.of(2021, 6, 6);
LocalDate localDate16 = LocalDate.of(2021, 7, 15);
vehicle8.setPostedDate(localDate15);
vehicle8.setPurchaseDate(localDate16);
vehicle8.setVinNumber(8);
vehicle8.setCondition("NEW");


vehicles.add(vehicle8);

sellers.add(seller8);

Seller seller9 = new Seller();
seller9.setFirstName("Gus");
seller9.setLastName("Dyck");
seller9.setAge("9");
seller9.setEmail("gus@email.com");
seller9.setPassword("pass");
Address address9 = new Address();
address9.setStreet("852 Myrtle Street");
address9.setCity("St. Paul");
address9.setState("Minnesota");
address9.setZipCode("92647");

seller9.setAddress(address9);

Vehicle vehicle9 = new Vehicle();
vehicle9.setPrice(85000);
vehicle9.setDiscountedPrice(0);
vehicle9.setMake("BMW");
vehicle9.setModel("Model 8");
vehicle9.setYear("2021");
vehicle9.setMiles("32");
vehicle9.setStatus("For Sale");
vehicle9.setCarPicture("P90309846-the-all-new-2019-bmw-8-series-european-model-shown-2250px.jpeg");
vehicle9.setSellerEmail(seller9.getEmail());
LocalDate localDate17 = LocalDate.of(2021, 5, 28);
LocalDate localDate18 = LocalDate.of(2021, 7, 15);
vehicle9.setPostedDate(localDate17);
vehicle9.setPurchaseDate(localDate18);
vehicle9.setVinNumber(9);
vehicle9.setCondition("NEW");


vehicles.add(vehicle9);

sellers.add(seller9);

Seller seller10 = new Seller();
seller10.setFirstName("Ben");
seller10.setLastName("Johnson");
seller10.setAge("36");
seller10.setEmail("ben@email.com");
seller10.setPassword("pass");
Address address10 = new Address();
address10.setStreet("345 rocky road");
address10.setCity("Austin");
address10.setState("Texas");
address10.setZipCode("98235");

seller10.setAddress(address10);

Vehicle vehicle10 = new Vehicle();
vehicle10.setPrice(3000);
vehicle10.setDiscountedPrice(2700);
vehicle10.setMake("Chevy");
vehicle10.setModel("Impala");
vehicle10.setYear("2008");
vehicle10.setMiles("200000");
vehicle10.setStatus("For Sale");
vehicle10.setCarPicture("2008-chevrolet-impala-lt-sedan-angular-front.png");
vehicle10.setSellerEmail(seller10.getEmail());
LocalDate localDate19 = LocalDate.of(2017, 12, 6);
LocalDate localDate20 = LocalDate.of(2021, 7, 15);
vehicle10.setPostedDate(localDate19);
vehicle10.setPurchaseDate(localDate20);
vehicle10.setVinNumber(10);
vehicle10.setCondition("USED");


vehicles.add(vehicle10);

sellers.add(seller10);
















Seller admin = new Seller();
admin.setFirstName("admin");
admin.setLastName("admin");
admin.setAge("unknown");
admin.setEmail("admin@email.com");
admin.setPassword("admin");
Address admin1 = new Address();
admin1.setStreet("admin");
admin1.setCity("admin");
admin1.setState("admin");
admin1.setZipCode("admin");

admin.setAddress(admin1);

sellers.add(admin);


}

	


}
