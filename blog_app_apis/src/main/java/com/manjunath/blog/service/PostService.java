package com.manjunath.blog.service;

import java.util.List;

import com.manjunath.blog.payload.PostDto;
import com.manjunath.blog.payload.PostResponse;

public interface PostService {
	//create

	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

	//update
	PostDto updatePost(PostDto postDto, Integer postId);

	//delete
	void deletePost(Integer postId);

	//get all post
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

	//get single post
	PostDto getPostById(Integer postId);

	//get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);

	//get all post by user
	List<PostDto> getpostsByUser(Integer userId);

	//search post
	List<PostDto> searchPosts(String keyword);

}
