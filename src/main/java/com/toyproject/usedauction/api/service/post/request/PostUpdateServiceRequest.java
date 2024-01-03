package com.toyproject.usedauction.api.service.post.request;


import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateServiceRequest {

	private String title;
	private String content;

	@Builder
	private PostUpdateServiceRequest(String title, String content) {
		this.title = title;
		this.content = content;
	}

}
