package com.toyproject.usedauction.api.service.post.response;

import com.toyproject.usedauction.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateResponse {

	private Long id;
	private String title;
	private String content;

	@Builder
	private PostUpdateResponse(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public static PostUpdateResponse of(Post post) {
		return PostUpdateResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.build();
	}
}
