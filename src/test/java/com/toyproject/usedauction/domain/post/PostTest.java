package com.toyproject.usedauction.domain.post;

import static com.toyproject.usedauction.domain.post.ClosingTimeType.FIVE_DAYS;
import static com.toyproject.usedauction.domain.post.OrderPriceType.FIVE_THOUSAND;
import static org.assertj.core.api.Assertions.assertThat;

import com.toyproject.usedauction.domain.category.Category;
import com.toyproject.usedauction.domain.category.CategoryType;
import com.toyproject.usedauction.domain.user.User;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

	@Test
	@DisplayName("게시글 마감시간은 게시글 등록시간 + 마감타입 일 수이다.")
	void registerDateTime() {
		//given
		LocalDateTime registerDateTime = LocalDateTime.now();

		User user = User.builder()
			.email("abc@gmail.com")
			.nickname("zzang")
			.password("123123")
			.build();

		Category category = Category.builder()
			.categoryType(CategoryType.CLOTHING)
			.build();

		//when
		Post post = Post.builder()
			.title("title")
			.content("content")
			.closingTimeType(FIVE_DAYS)
			.registerDateTime(registerDateTime)
			.startPrice(1000)
			.endPrice(5000)
			.orderPriceType(FIVE_THOUSAND)
			.writer(user)
			.category(category)
			.build();

		//then
		assertThat(post.getClosingTime())
			.isEqualTo(registerDateTime.plusDays(post.getClosingTimeType().getDay()));
	}

}
