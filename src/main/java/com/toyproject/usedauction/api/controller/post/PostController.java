package com.toyproject.usedauction.api.controller.post;

import com.toyproject.usedauction.api.ApiResponse;
import com.toyproject.usedauction.api.service.post.PostService;
import com.toyproject.usedauction.api.controller.post.request.PostCreateRequest;
import com.toyproject.usedauction.api.service.post.response.PostCreateResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

	private final PostService postService;

	@PostMapping("/posts")
	public ApiResponse<PostCreateResponse> createPost(@Valid @RequestBody PostCreateRequest request) {
		LocalDateTime registerDateTime = LocalDateTime.now();

		return ApiResponse.ok(postService.createPost(request.toServiceRequest(), registerDateTime));
	}
}
