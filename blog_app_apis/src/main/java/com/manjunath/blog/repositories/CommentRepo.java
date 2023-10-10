package com.manjunath.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manjunath.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>
{

}
