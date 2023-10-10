package com.manjunath.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	@NotBlank
	@Size(min = 4, message = "Min size of post title is 4")
	private String title;
	@NotBlank
	@Size(min = 10,message = "Min size of post content is 10")
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;

	private Set<CommentDto> comments = new HashSet<>();

	//   private String imageName="defult.png";

}
