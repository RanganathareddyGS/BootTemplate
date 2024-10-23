package mainproject.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import mainproject.ecommerce.dto.Seller;
import mainproject.ecommerce.dto.Customer;
import mainproject.ecommerce.helper.AES;
import mainproject.ecommerce.repository.CustomerRepository;
import mainproject.ecommerce.repository.SellerRepository;

@Controller
public class MainController {

	@Value("${admin.email}")
	private String adminEmail;

	@Value("${admin.password}")
	private String adminPassword;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/")
	public String loadHome() {
		return "home.html";
	}

	@GetMapping("/login")
	public String loadLogin() {
		return "Login.html";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
		if (email.equals(adminEmail) && password.equals(adminPassword)) {
			session.setAttribute("admin", "admin");
			session.setAttribute("success", "Login Success");
			return "redirect:/admin/home";
		} else {
			Seller seller = sellerRepository.findByEmail(email);
			Customer customer = customerRepository.findByEmail(email);

			if (seller == null && customer == null) {
				session.setAttribute("failure", "Invalid Email");
				return "redirect:/login";
			} else {
				if (seller == null) {
					if (AES.decrypt(customer.getPassword(), "123").equals(password)) {
						session.setAttribute("customer", customer);
						session.setAttribute("success", "Login Success");
						return "redirect:/customer/home";
					} else {
						session.setAttribute("failure", "Invalid Password");
						return "redirect:/login";
					}
				} else {
					if (AES.decrypt(seller.getPassword(), "123").equals(password)) {
						session.setAttribute("seller", seller);
						session.setAttribute("success", "Login Success");
						return "redirect:/seller/home";
					} else {
						session.setAttribute("failure", "Invalid Password");
						return "redirect:/login";
					}
				}
			}
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("seller");
		session.removeAttribute("customer");
		session.setAttribute("success", "Logged out Successfully");
		return "redirect:/"; 
	}
}