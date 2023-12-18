package com.toyproject.usedauction.api.service.post;

import static com.toyproject.usedauction.domain.category.CategoryType.BOOK;
import static com.toyproject.usedauction.domain.post.ClosingTimeType.FIVE_DAYS;
import static com.toyproject.usedauction.domain.post.ClosingTimeType.THREE_DAYS;
import static com.toyproject.usedauction.domain.post.OrderPriceType.FIVE_THOUSAND;
import static com.toyproject.usedauction.domain.post.OrderPriceType.ONE_THOUSAND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

import com.toyproject.usedauction.api.service.post.request.PostCreateRequest;
import com.toyproject.usedauction.api.service.post.response.PostCreateResponse;
import com.toyproject.usedauction.domain.category.Category;
import com.toyproject.usedauction.domain.category.CategoryRepository;
import com.toyproject.usedauction.domain.category.CategoryType;
import com.toyproject.usedauction.domain.post.ClosingTimeType;
import com.toyproject.usedauction.domain.post.OrderPriceType;
import com.toyproject.usedauction.domain.post.Post;
import com.toyproject.usedauction.domain.post.PostRepository;
import com.toyproject.usedauction.domain.user.User;
import com.toyproject.usedauction.domain.user.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class PostServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostService postService;

	@Test
	@DisplayName("글을 생성한다. 마감시간은 등록시간 + 마감타입일자이다.")
	void createPost() {
		//given
		LocalDateTime registerDateTime = LocalDateTime.now();

		User user = createUser("a", "123123", "nickName");
		User savedUser = userRepository.save(user);

		Category category = createCategory(BOOK);
		Category savedCategory = categoryRepository.save(category);

		Post createPost = createPost(
			savedUser, savedCategory, "제목1", "내용1", THREE_DAYS,
			registerDateTime, 10000, 50000, FIVE_THOUSAND
		);
		postRepository.save(createPost);

		PostCreateRequest request = PostCreateRequest.builder()
			.title("제목2")
			.content("내용2")
			.closingTimeType(FIVE_DAYS)
			.startPrice(1000)
			.endPrice(10000)
			.orderPriceType(ONE_THOUSAND)
			.userId(savedUser.getId())
			.categoryId(savedCategory.getId())
			.build();

		//when
		PostCreateResponse response = postService.createPost(request, registerDateTime);

		//then
		assertThat(response.getId()).isNotNull();
		assertThat(response)
			.extracting("title", "content", "closingTimeType", "closingTime",
				"startPrice", "endPrice", "orderPriceType", "writerName", "categoryType")
			.contains("제목2", "내용2", FIVE_DAYS, response.getClosingTime(), 1000, 10000, ONE_THOUSAND,
				"nickName", BOOK);

		List<Post> posts = postRepository.findAll();
		assertThat(posts).hasSize(2)
			.extracting("title", "content", "closingTimeType", "closingTime",
				"startPrice", "endPrice", "orderPriceType", "writer", "category")
			.contains(
				tuple("제목1", "내용1", THREE_DAYS, createPost.getClosingTime(), 10000, 50000, FIVE_THOUSAND,
					user, category),
				tuple("제목2", "내용2", FIVE_DAYS, response.getClosingTime(), 1000, 10000, ONE_THOUSAND,
					user, category)
			);

	}

	private Post createPost(User user, Category category, String title, String content,
		ClosingTimeType closingTimeType, LocalDateTime registerDateTime, int startPrice,
		int endPrice, OrderPriceType orderPriceType) {

		Post post = Post.builder()
			.title(title)
			.content(content)
			.closingTimeType(closingTimeType)
			.registerDateTime(registerDateTime)
			.startPrice(startPrice)
			.endPrice(endPrice)
			.orderPriceType(orderPriceType)
			.writer(user)
			.category(category)
			.build();
		return post;
	}

	private Category createCategory(CategoryType categoryType) {
		return new Category(categoryType);
	}

	private User createUser(String email, String password, String nickName) {
		return User.builder()
			.email(email)
			.password(password)
			.nickname(nickName)
			.build();
	}

}
