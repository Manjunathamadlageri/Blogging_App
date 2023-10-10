package com.manjunath.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.EableGlobalMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.manjunath.blog.security.CustomerUserDetailsService;
import com.manjunath.blog.security.JWTAuthenticationEntryPoint;
import com.manjunath.blog.security.JwtAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfiguration
{
	@Autowired
	private  CustomerUserDetailsService  customerUserDetailsService;

	@Autowired
	private JWTAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	@Override
	protected void configure(HttpSecurity http)
	{
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatcher("/api/v1/auth/**").permitAll()
		.antMatcher(HttpMethod.GET).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationToken.class);
	}
	@Override
	protected void configur(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(this.customerUserDetailsService).passwordEncoder(passwordEncoder());
	}
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationMangerBean();
	}
}
