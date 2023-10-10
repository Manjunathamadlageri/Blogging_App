package com.manjunath.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.manjunath.blog.config.AppConstants;
import com.manjunath.blog.entity.Role;
import com.manjunath.blog.repositories.RoleRepo;

import jakarta.validation.constraints.AssertFalse.List;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner
{
	@Autowired
	private  PasswordEncoder passwordEncoder;

	private RoleRepo roleRepo; 
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()

	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");

			List<Role> roles  = List.of(role,role1);
			List<Role> result = this.roleRepo.save(roles);
			result.forEach(r->{
				System.out.println(r.getName());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
