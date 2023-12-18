package com.toyproject.usedauction.api.service.post;

import com.toyproject.usedauction.api.service.post.request.PostCreateRequest;
import com.toyproject.usedauction.api.service.post.response.PostCreateResponse;
import com.toyproject.usedauction.domain.category.Category;
import com.toyproject.usedauction.domain.category.CategoryRepository;
import com.toyproject.usedauction.domain.post.Post;
import com.toyproject.usedauction.domain.post.PostRepository;
import com.toyproject.usedauction.domain.user.User;
import com.toyproject.usedauction.domain.user.UserRepository;
import java.time.LocalDateTime;
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
	public PostCreateResponse createPost(PostCreateRequest postCreateRequest, LocalDateTime registerDateTime) {
		User findUser = getUserById(postCreateRequest.getUserId());
		Category findCategory = getCategoryById(postCreateRequest.getCategoryId());

		Post post = postCreateRequest.toEntity(findUser, findCategory, registerDateTime);
		Post savePost = savePostEntity(post);

		return PostCreateResponse.of(savePost);
	}

	private User getUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("해당 user id가 존재하지 않습니다."));
	}

	private Category getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId)
			.orElseThrow(() -> new IllegalArgumentException("해당 category id가 존재하지 않습니다."));
	}

	private Post savePostEntity(Post post) {
		return postRepository.save(post);
	}


}
