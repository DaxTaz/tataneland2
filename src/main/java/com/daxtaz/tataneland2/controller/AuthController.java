package com.daxtaz.tataneland2.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.daxtaz.tataneland2.user.IUserService;
import com.daxtaz.tataneland2.user.User;
import com.daxtaz.tataneland2.user.UserDto;

@Controller
public class AuthController {
	
	@Autowired
	private IUserService userService;

	@GetMapping("/index")
	public String Home() {
		return "index";
	}
	
	@GetMapping("/")
	public String Login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/acceuil")
	public String Acceuil() {
		return "acceuil";
	}
	
	@PostMapping("/user/login")
	public String userLogin(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "login";
		}
		
		User findUser = userService.findUserByEmail(user.getEmail());
		
		if(findUser == null) {
			ObjectError error = new ObjectError("globalError", "User does not exist");
			bindingResult.addError(error);
			return "login";
		}
		
		if(!findUser.getPassword().equalsIgnoreCase(user.getPassword())) {
			ObjectError error = new ObjectError("globalError", "Password is wrong");
			bindingResult.addError(error);
			return "login";
		}
		return "redirect:/acceuil";
	}
	
}
