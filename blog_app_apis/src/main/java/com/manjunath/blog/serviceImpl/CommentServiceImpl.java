package com.manjunath.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjunath.blog.entity.Comment;
import com.manjunath.blog.entity.Post;
import com.manjunath.blog.exceptions.ResourceNotFoundException;
import com.manjunath.blog.payload.CommentDto;
import com.manjunath.blog.repositories.CommentRepo;
import com.manjunath.blog.repositories.PostRepo;
import com.manjunath.blog.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		Comment comment	 = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepo.delete(com);

	}

}
