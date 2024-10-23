package mainproject.ecommerce.service;

import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;

import mainproject.ecommerce.dto.Customer;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

public interface CustomerService {
	String loadRegister(ModelMap map);

	String loadRegister(@Valid Customer customer, BindingResult result, HttpSession session);

	String submitOtp(int id, int otp, HttpSession session);
}
