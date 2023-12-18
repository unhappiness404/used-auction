package com.toyproject.usedauction.api.controller.post;

import com.toyproject.usedauction.api.service.post.PostService;
import com.toyproject.usedauction.api.service.post.request.PostCreateRequest;
import com.toyproject.usedauction.api.service.post.response.PostCreateResponse;
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
	public PostCreateResponse createPost(@RequestBody PostCreateRequest request) {
		log.info("request = {}", request);
		LocalDateTime registerDateTime = LocalDateTime.now();
		return postService.createPost(request, registerDateTime);
	}
}
