package mainproject.ecommerce.service;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import mainproject.ecommerce.dto.Seller;

public interface SellerService {

	String loadRegister(ModelMap map);

	String loadRegister(Seller seller, BindingResult result, HttpSession session);

	String submitOtp(int id, int otp, HttpSession session);

}
