package com.manjunath.blog.service;

import java.util.List;

import com.manjunath.blog.payload.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto user);

	UserDto createUser (UserDto userDto);

	UserDto updateeUser (UserDto userDto,Integer userId);

	UserDto getUserById (Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);

}
