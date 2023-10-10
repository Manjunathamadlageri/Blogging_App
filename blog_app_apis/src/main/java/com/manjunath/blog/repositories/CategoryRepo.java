package com.manjunath.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manjunath.blog.entity.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer>
{

}
