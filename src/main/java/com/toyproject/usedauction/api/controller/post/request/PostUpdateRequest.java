package com.toyproject.usedauction.api.controller.post.request;

import com.toyproject.usedauction.api.service.post.request.PostUpdateServiceRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class PostUpdateRequest {

	@NotBlank(message = "제목은 필수입니다.")
	private String title;
	@NotBlank(message = "내용은 필수입니다.")
	private String content;

	@Builder
	private PostUpdateRequest(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public PostUpdateServiceRequest toServiceRequest() {
		return PostUpdateServiceRequest.builder()
			.title(title)
			.content(content)
			.build();
	}
}
