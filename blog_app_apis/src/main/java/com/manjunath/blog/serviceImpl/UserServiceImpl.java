package com.manjunath.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manjunath.blog.config.AppConstants;
import com.manjunath.blog.entity.Role;
import com.manjunath.blog.entity.User;
import com.manjunath.blog.exceptions.ResourceNotFoundException;
import com.manjunath.blog.payload.UserDto;
import com.manjunath.blog.repositories.RoleRepo;
import com.manjunath.blog.repositories.UserRepo;
import com.manjunath.blog.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savaUser = this.userRepo.save(user);
		return this.userToDto(savaUser);
	}

	@Override
	public UserDto updateeUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());

		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto)
	{
		User user = this.modelMapper.map(userDto, User.class);
		//		user.setId(userDto.getId());
		//		user.setName(userDto.getName());
		//		user.setEmail(userDto.getEmail());
		//		user.setAbout(userDto.getAbout());
		//		user.setPassword(userDto.getPassword());
		return user;
	}

	public UserDto userToDto(User user)
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;		
		//     	UserDto userDto = 	new UserDto();
		//		userDto.setId(user.getId());
		//		userDto.setName(user.getName());
		//		userDto.setEmail(user.getEmail());
		//		userDto.setAbout(user.getAbout());
		//		userDto.setPassword(user.getPassword());
		//return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userdto) {
		User user = this.modelMapper.map(userdto, User.class);
		//Encoded PassWord
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		//Roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return modelMapper.map(newUser, UserDto.class);
	}

}
