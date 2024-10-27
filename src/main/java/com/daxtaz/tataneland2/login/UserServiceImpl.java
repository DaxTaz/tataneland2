package com.daxtaz.tataneland2.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	public UserServiceImpl(IUserDao userDao) {
		super();
		this.userDao = userDao;
	}
	
	public User saveOrUpdateUser(User user) {
		return userDao.save(user);
	}
	
	public void deleteUser(Integer id) {
		userDao.deleteById(id);
	}
	
	public Optional<User> findUserById(Integer id) {
		return userDao.findById(id);
	}

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public List<UserDto> findAllUsers() {
		List<User> userList = userDao.findAll();
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		for(User user : userList)
			userDtoList.add(mapUserToUserDto(user));
		
		return userDtoList;
	}
	
	private UserDto mapUserToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
	
}
