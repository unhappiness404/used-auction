package com.toyproject.usedauction.api.service.post;

import com.toyproject.usedauction.api.service.post.request.PostCreateServiceRequest;
import com.toyproject.usedauction.api.service.post.response.PostCreateResponse;
import com.toyproject.usedauction.domain.category.Category;
import com.toyproject.usedauction.domain.category.CategoryRepository;
import com.toyproject.usedauction.domain.category.exception.NotFoundCategoryException;
import com.toyproject.usedauction.domain.post.Post;
import com.toyproject.usedauction.domain.post.PostRepository;
import com.toyproject.usedauction.domain.user.User;
import com.toyproject.usedauction.domain.user.UserRepository;
import com.toyproject.usedauction.domain.user.exception.NotFoundUserException;
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
	public PostCreateResponse createPost(PostCreateServiceRequest request, LocalDateTime registerDateTime) {
		User findUser = getUserById(request.getUserId());
		Category findCategory = getCategoryById(request.getCategoryId());

		Post post = request.toEntity(findUser, findCategory, registerDateTime);

		return PostCreateResponse.of(postRepository.save(post));
	}

	private User getUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundUserException());
	}

	private Category getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId)
			.orElseThrow(() -> new NotFoundCategoryException());
	}

}
