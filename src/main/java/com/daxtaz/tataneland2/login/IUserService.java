package com.daxtaz.tataneland2.login;

import java.util.List;
import java.util.Optional;

public interface IUserService {

	User findUserByEmail(String email);
	
	Optional<User> findUserById(Integer id);
	
	List<UserDto> findAllUsers();
	
	User saveOrUpdateUser(User user);
	
	void deleteUser(Integer id);
	
}
