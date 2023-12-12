package com.toyproject.usedauction.api.service.image.response;

import com.toyproject.usedauction.domain.postImage.PostImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostImageResponse {

	private String url;
	private String name;

	@Builder
	private PostImageResponse(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public static PostImageResponse of(PostImage image) {
		return PostImageResponse.builder()
			.url(image.getUrl())
			.name(image.getName())
			.build();
	}
}
