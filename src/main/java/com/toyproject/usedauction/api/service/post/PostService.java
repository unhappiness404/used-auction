package com.toyproject.usedauction.api.service.post;

import com.toyproject.usedauction.api.service.post.request.PostCreateServiceRequest;
import com.toyproject.usedauction.api.service.post.request.PostUpdateServiceRequest;
import com.toyproject.usedauction.api.service.post.response.PostResponse;
import com.toyproject.usedauction.api.service.post.response.PostUpdateResponse;
import com.toyproject.usedauction.domain.category.Category;
import com.toyproject.usedauction.domain.category.CategoryRepository;
import com.toyproject.usedauction.domain.category.exception.NotFoundCategoryException;
import com.toyproject.usedauction.domain.post.Post;
import com.toyproject.usedauction.domain.post.PostRepository;
import com.toyproject.usedauction.domain.post.exception.NotFoundPostException;
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

	public PostResponse getPost(long postId) {
		return PostResponse.of(getPostById(postId));
	}

	@Transactional
	public PostResponse createPost(PostCreateServiceRequest request, LocalDateTime registerDateTime) {
		User findUser = getUserById(request.getUserId());
		Category findCategory = getCategoryById(request.getCategoryId());

		Post post = request.toEntity(findUser, findCategory, registerDateTime);

		return PostResponse.of(postRepository.save(post));
	}

	@Transactional
	public PostUpdateResponse updatePost(PostUpdateServiceRequest request, Long postId) {
		Post post = getPostById(postId);
		post.updateTitleAndContent(request);

		return PostUpdateResponse.of(post);
	}

	public void deletePost(long postId) {
		Post post = getPostById(postId);
		postRepository.delete(post);
	}

	private Post getPostById(Long postId) {
		return postRepository.findById(postId)
			.orElseThrow(() -> new NotFoundPostException());
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
