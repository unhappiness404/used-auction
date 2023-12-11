package com.toyproject.usedauction.api.service.post.request;

import com.toyproject.usedauction.domain.category.Category;
import com.toyproject.usedauction.domain.post.ClosingTime;
import com.toyproject.usedauction.domain.post.OrderPriceType;
import com.toyproject.usedauction.domain.post.Post;
import com.toyproject.usedauction.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequest {

	private String title;
	private String content;
	private ClosingTime closingTime;
	private int startPrice;
	private int endPrice;
	private int nowPrice;
	private OrderPriceType orderPriceType;
	private Long userId;
	private Long categoryId;

	@Builder
	public PostCreateRequest(String title, String content, ClosingTime closingTime, int startPrice,
		int endPrice, int nowPrice, OrderPriceType orderPriceType, Long userId, Long categoryId) {
		this.title = title;
		this.content = content;
		this.closingTime = closingTime;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.nowPrice = nowPrice;
		this.orderPriceType = orderPriceType;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public Post toEntity(User writer, Category category) {
		return Post.builder()
			.title(title)
			.content(content)
			.closingTime(closingTime)
			.startPrice(startPrice)
			.endPrice(endPrice)
			.nowPrice(nowPrice)
			.orderPriceType(orderPriceType)
			.writer(writer)
			.category(category)
			.build();
	}
}
