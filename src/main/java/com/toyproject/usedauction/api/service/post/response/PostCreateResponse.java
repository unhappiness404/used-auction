package com.toyproject.usedauction.api.service.post.response;


import com.toyproject.usedauction.domain.category.CategoryType;
import com.toyproject.usedauction.domain.post.ClosingTimeType;
import com.toyproject.usedauction.domain.post.OrderPriceType;
import com.toyproject.usedauction.domain.post.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateResponse {

	private Long id;
	private String title;
	private String content;
	private ClosingTimeType closingTimeType;
	private LocalDateTime closingTime;
	private int startPrice;
	private int endPrice;
	private OrderPriceType orderPriceType;
	private String writerName;
	private CategoryType categoryType;

	@Builder
	private PostCreateResponse(Long id, String title, String content,
		ClosingTimeType closingTimeType,
		LocalDateTime closingTime, int startPrice, int endPrice, OrderPriceType orderPriceType,
		String writerName, CategoryType categoryType) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.closingTimeType = closingTimeType;
		this.closingTime = closingTime;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.orderPriceType = orderPriceType;
		this.writerName = writerName;
		this.categoryType = categoryType;
	}

	public static PostCreateResponse of(Post post) {
		return PostCreateResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.closingTimeType(post.getClosingTimeType())
			.closingTime(LocalDateTime.now().plusDays(post.getClosingTimeType().getDay()))
			.startPrice(post.getStartPrice())
			.endPrice(post.getEndPrice())
			.orderPriceType(post.getOrderPriceType())
			.writerName(post.getWriter().getNickname())
			.categoryType(post.getCategory().getCategoryType())
			.build();
	}
}
