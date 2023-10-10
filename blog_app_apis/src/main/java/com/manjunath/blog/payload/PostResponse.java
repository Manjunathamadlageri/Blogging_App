package com.manjunath.blog.payload;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {
	private List<PostDto> contenet;
	private int pageNUmber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
}
