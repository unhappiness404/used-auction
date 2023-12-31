package com.toyproject.usedauction.api.controller.post;

import com.toyproject.usedauction.api.ApiResponse;
import com.toyproject.usedauction.api.controller.ResponseMessages;
import com.toyproject.usedauction.api.controller.post.request.PostCreateRequest;
import com.toyproject.usedauction.api.controller.post.request.PostUpdateRequest;
import com.toyproject.usedauction.api.service.post.PostService;
import com.toyproject.usedauction.api.service.post.response.PostResponse;
import com.toyproject.usedauction.api.service.post.response.PostUpdateResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	@GetMapping("/{postId}")
	public ApiResponse<PostResponse> getPost(@PathVariable long postId) {
		PostResponse response = postService.getPost(postId);

		return ApiResponse.ok(ResponseMessages.POST_READ_SUCCESS, response);
	}

	@PostMapping
	public ApiResponse<PostResponse> createPost(
		@Valid @RequestBody PostCreateRequest request
	) {
		LocalDateTime registerDateTime = LocalDateTime.now();
		PostResponse response = postService.createPost(request.toServiceRequest(), registerDateTime);

		return ApiResponse.ok(ResponseMessages.POST_CREATE_SUCCESS, response);
	}

	@PatchMapping("/{postId}")
	public ApiResponse<PostUpdateResponse> updatePost(
		@Valid @RequestBody PostUpdateRequest request,
		@PathVariable long postId) {
		PostUpdateResponse response = postService.updatePost(request.toServiceRequest(), postId);

		return ApiResponse.ok(ResponseMessages.POST_UPDATE_SUCCESS, response);
	}

	@DeleteMapping("/{postId}")
	public ApiResponse<Long> deletePost(@PathVariable long postId) {
		postService.deletePost(postId);

		return ApiResponse.ok(ResponseMessages.POST_DELETE_SUCCESS, Long.valueOf(postId));
	}
}
