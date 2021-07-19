package com.carDealer.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carDealer.info.Address;
import com.carDealer.info.Customer;
import com.carDealer.info.Seller;
import com.carDealer.info.Vehicle;
import com.carDealer.service.CarDealerService;

@Controller
public class CarDealerController {
	@Autowired
	public CarDealerService carDealerService;

	@Autowired
	ServletContext servletContext;

	@GetMapping("/")
	public String welcome(Model model) {

		Seller seller = carDealerService.findByEmail("kaleb@email.com");

		model.addAttribute("newSeller", seller);
		return "index";
	}

	@GetMapping("/sign-up")
	public ModelAndView signUp(Model model) {
		return new ModelAndView("sign-up", "seller", new Seller());
	}

	@PostMapping("/sign-up")
	public String handleSignUp(Model model, @ModelAttribute("seller") Seller seller,
			@ModelAttribute("address") Address address, RedirectAttributes redirect, HttpSession session) {
		System.out.println("Array List");
		ArrayList<Seller> sellers = carDealerService.findAllS();
		if (sellers != null) {
			for (Seller s : sellers) {
				System.out.println("inside for loop");
				if (carDealerService.duplicateEmail(seller.getEmail())) {
					System.out.println("observed if statement");
					redirect.addFlashAttribute("error", "Email already exists.");
					return "redirect:sign-up";
				} else {
					carDealerService.saveSeller(seller);
					model.addAttribute("address", address);
					model.addAttribute("newSeller", seller);
					System.out.println("Seller signed up");
					return "thank-you";
				}
			}
		}

		return "thank-you";
	}

	@GetMapping("/login")
	public ModelAndView login(Model model) {

		return new ModelAndView("Login", "seller", new Seller());
	}

	@PostMapping("/authenticate")
	public String authenticate(Model model, @ModelAttribute("seller") Seller seller, HttpSession session) {
		if (seller != null) {
			Seller acc = carDealerService.findByEmail(seller.getEmail());
			if (acc.getEmail().equals("admin@email.com") && acc.getPassword().equals("admin")) {
				return "redirect:allposts";
			}
			if ((acc != (null)) && acc.getPassword().equals(seller.getPassword())) {
				session.setAttribute("loginSeller", acc);
				model.addAttribute("newSeller", seller);
				return "profile";
			} else {
				model.addAttribute("error", "Inavalid Credentials");
				return "login";
			}
		}
		model.addAttribute("error", "Inavalid Credentials");
		return "login";
	}

	@GetMapping("/register")
	public ModelAndView register(Model model, @ModelAttribute("seller") Seller seller, HttpSession session) {
		Seller acc = (Seller) session.getAttribute("seller");
		session.setAttribute("seller", seller);

		model.addAttribute("loginSeller", acc);
		return new ModelAndView("Register", "vehicle", new Vehicle());
	}

	@PostMapping("/vehicle")
	public String vehicle(Model model, @ModelAttribute("loginSeller") Seller seller,
			@ModelAttribute("vehicle") Vehicle vehicle, HttpSession session) {
		Seller acc = (Seller) session.getAttribute("loginSeller");
		session.setAttribute("seller", seller);
		vehicle.setSellerEmail(acc.getEmail());
		vehicle.setPostedDate(LocalDate.now());
		vehicle.setStatus("For Sale");
		carDealerService.saveVehicle(vehicle);
		MultipartFile picture = vehicle.getPicture();
		carDealerService.addPic(picture);
		vehicle.setCarPicture(picture.getOriginalFilename());
		model.addAttribute("loginSeller", acc);
		model.addAttribute("vehicle", vehicle);
		return "Vehicle";
	}

	@GetMapping("/posts")
	String posts(Model model) {
		ArrayList<Vehicle> availableVehicles = carDealerService.store();

		model.addAttribute("vehicles", availableVehicles);
		return "carsForSale";
	}

	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.removeAttribute("loginSeller");
		return "index";
	}

	@GetMapping("/buycar-{sellerEmail}-{miles}-{price}")
	String buycar(@PathVariable String sellerEmail, @PathVariable String miles, @PathVariable double price,
			@ModelAttribute("customer") Customer customer, HttpSession session) {
		System.out.println(sellerEmail + miles + price);
		Vehicle sale = new Vehicle();
		sale = carDealerService.findOne(sellerEmail, miles, price);

		session.setAttribute("vehicle", sale);

		return "purchase";
	}

	@GetMapping("/allposts")
	String admin(Model model, @ModelAttribute("loginSeller") Seller seller, @ModelAttribute("vehicle") Vehicle vehicle, HttpSession session) {
		Seller acc = (Seller) session.getAttribute("loginSeller");
		carDealerService.inventory();
		session.setAttribute("seller", seller);
		

		model.addAttribute("vehicles", carDealerService.findAll());
		return "inventory";
	}

	@PostMapping("/customer")
	public String customer(Model model, @ModelAttribute("customer") Customer customer,
			@ModelAttribute("vehicle") Vehicle vehicle, HttpSession session) {
		carDealerService.saveCustomer(customer);
		Vehicle vehicle1 = ((Vehicle) session.getAttribute("vehicle"));
		vehicle1.setStatus("Sold");
		vehicle1.setPurchaseDate(LocalDate.now());
		model.addAttribute("newCustomer", customer);
		model.addAttribute("vehicle", vehicle1);
		return "congratulations";
	}
	
	@GetMapping("/search")
	public String search(Model model, @ModelAttribute("vehicle") Vehicle vehicle) {
		model.addAttribute("vehicle", vehicle);
		return "search";
	}
	
	@PostMapping("/searchposts")
	public String searchFor(Model model, @ModelAttribute("vehicle") Vehicle vehicle) {
		System.out.println(vehicle.getPrice());
//		System.out.println(vehicle.getMake());
		ArrayList<Vehicle> sales = carDealerService.findAllMatches(vehicle);
		for(Vehicle v: sales) {
			System.out.println(v.getMake());
		}
		model.addAttribute("sales", sales);
		
		return "carsForSale2";
	}
	
	

}
