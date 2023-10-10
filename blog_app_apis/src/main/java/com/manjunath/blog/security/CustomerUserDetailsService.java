package com.manjunath.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.manjunath.blog.entity.User;
import com.manjunath.blog.exceptions.ResourceNotFoundException;
import com.manjunath.blog.repositories.UserRepo;


public class CustomerUserDetailsService implements UserDetailsService
{

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loding user from db by username
		User user =	this.userRepo.findbyEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","Email:"+username,0));

		return user;
	}

}
