package com.manjunath.blog.payload;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int  id;

	@NotEmpty
	@Size(min = 4, message = "Username must be min of4 charecters" )
	private String name;

	@Email(message = "Email addressis not valid !!")
	private String email;

	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 char and max of 10 char !!")
	private String password;

	@NotEmpty
	private String about;

	private Set<RoleDto> roles = new HashSet<>();
}
