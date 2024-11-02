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

import com.daxtaz.tataneland2.login.IUserService;
import com.daxtaz.tataneland2.login.User;
import com.daxtaz.tataneland2.login.UserDto;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;

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
		
		User existUserEmail = userService.findUserByEmail(userToSave.getEmail());
		
		if(existUserEmail != null) {
			ObjectError error = new ObjectError("globalError", "Email already use");
			bindingResult.addError(error);
			return "newUser";
		}
		
		//etablir dans un premier temps un cahier de recette à l'ancienne pour l'ensemble des pages et ensuite
		// transformer ca en tests unitaires
		//documenter tout ca et repartir les controller en differentes classes
		
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
