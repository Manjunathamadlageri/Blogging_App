package com.manjunath.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjunath.blog.entity.Category;
import com.manjunath.blog.exceptions.ResourceNotFoundException;
import com.manjunath.blog.payload.CategoryDto;
import com.manjunath.blog.repositories.CategoryRepo;
import com.manjunath.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modleMApper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modleMApper.map(categoryDto, Category.class);
		Category	 addedaCat = this.categoryRepo.save(cat);
		return this.modleMApper.map(addedaCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updateCat = this.categoryRepo.save(cat);

		return this.modleMApper.map(updateCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		return this.modleMApper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories =	this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modleMApper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
