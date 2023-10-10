package com.manjunath.blog.service;

import java.util.List;

import com.manjunath.blog.payload.CategoryDto;


public interface CategoryService {
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	public void  deleteCategory(Integer categoryId);
	public CategoryDto getCategory(Integer categoryId);
	List<CategoryDto> getCategories();

}
