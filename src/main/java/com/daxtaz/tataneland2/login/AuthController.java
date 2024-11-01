package com.daxtaz.tataneland2.login;

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
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@PostMapping("/user/login")
	public String userLogin(@Valid @ModelAttribute("login") Login login, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "login";
		}
		
		User user = userService.findUserByEmail(login.getEmail());
		
		if(user == null) {
			ObjectError error = new ObjectError("globalError", "User does not exist");
			bindingResult.addError(error);
			return "login";
		}
		
		if(!user.getPassword().equalsIgnoreCase(login.getPassword())) {
			ObjectError error = new ObjectError("globalError", "Password is wrong");
			bindingResult.addError(error);
			return "login";
		}
		return "redirect:/acceuil";
	}
	
	@GetMapping("/acceuil")
	public String Acceuil() {
		return "acceuil";
	}
	
	@GetMapping("/users")
	public String findUsers(Model model) {
		List<UserDto> userDtoList = userService.findAllUsers();
		model.addAttribute("users", userDtoList);
		return "users";
	}
	
	@GetMapping("/user/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "newUser";
	}
	
	@PostMapping("/user/save")
	public String saveUser(@Valid @ModelAttribute("user") User userToSave, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "newUser";
		}
		userService.saveOrUpdateUser(userToSave);
		return "redirect:/users";
	}
	
	@GetMapping("/user/edit/{id}")
	public String editUser(@PathVariable Integer id, Model model) {
		Optional<User> user = userService.findUserById(id);
		model.addAttribute("user", user.get());
		return "editUser";
	}
	
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable Integer id, @Valid @ModelAttribute("user") User userToUpdate, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "editUser";
		}
		userService.saveOrUpdateUser(userToUpdate);
		return "redirect:/users";
	}
	
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}
	
}
