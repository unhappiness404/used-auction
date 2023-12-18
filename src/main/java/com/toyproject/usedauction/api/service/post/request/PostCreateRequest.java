package com.toyproject.usedauction.api.service.post.request;

import com.toyproject.usedauction.domain.category.Category;
import com.toyproject.usedauction.domain.post.ClosingTimeType;
import com.toyproject.usedauction.domain.post.OrderPriceType;
import com.toyproject.usedauction.domain.post.Post;
import com.toyproject.usedauction.domain.user.User;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostCreateRequest {

	private String title;
	private String content;
	private ClosingTimeType closingTimeType;
	private int startPrice;
	private int endPrice;
	private OrderPriceType orderPriceType;
	private Long userId;
	private Long categoryId;

	@Builder
	private PostCreateRequest(String title, String content, ClosingTimeType closingTimeType, int startPrice,
		int endPrice, OrderPriceType orderPriceType, Long userId, Long categoryId) {
		this.title = title;
		this.content = content;
		this.closingTimeType = closingTimeType;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.orderPriceType = orderPriceType;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public Post toEntity(User writer, Category category, LocalDateTime registerDateTime) {
		return Post.builder()
			.title(title)
			.content(content)
			.closingTimeType(closingTimeType)
			.registerDateTime(registerDateTime)
			.startPrice(startPrice)
			.endPrice(endPrice)
			.orderPriceType(orderPriceType)
			.writer(writer)
			.category(category)
			.build();
	}
}
