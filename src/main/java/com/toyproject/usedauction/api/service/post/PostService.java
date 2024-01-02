package com.toyproject.usedauction.api.service.post;

import com.toyproject.usedauction.api.service.post.request.PostCreateRequest;
import com.toyproject.usedauction.api.service.post.response.PostCreateResponse;
import com.toyproject.usedauction.domain.category.Category;
import com.toyproject.usedauction.domain.category.CategoryRepository;
import com.toyproject.usedauction.domain.post.Post;
import com.toyproject.usedauction.domain.post.PostRepository;
import com.toyproject.usedauction.domain.user.User;
import com.toyproject.usedauction.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;

	@Transactional
	public PostCreateResponse createPost(PostCreateRequest postCreateRequest) {
		User findUser = userRepository.findById(postCreateRequest.getUserId())
			.orElseThrow(() -> new IllegalArgumentException("해당 user id가 존재하지 않습니다."));

		Category findCategory = categoryRepository.findById(postCreateRequest.getCategoryId())
			.orElseThrow(() -> new IllegalArgumentException("해당 category id가 존재하지 않습니다."));

		Post post = postCreateRequest.toEntity(findUser, findCategory);

		Post savePost = postRepository.save(post);

		return PostCreateResponse.of(savePost);
	}

}
