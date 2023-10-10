package com.manjunath.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manjunath.blog.entity.User;


public interface UserRepo extends JpaRepository<User, Integer>
{
    Optional<User> findbyEmail(String email);
}
