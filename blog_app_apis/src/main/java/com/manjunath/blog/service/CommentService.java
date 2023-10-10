package com.manjunath.blog.service;

import com.manjunath.blog.payload.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);

}
