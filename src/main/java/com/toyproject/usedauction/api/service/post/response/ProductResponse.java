package com.toyproject.usedauction.api.service.post.response;


import com.toyproject.usedauction.domain.category.CategoryType;
import com.toyproject.usedauction.domain.post.ClosingTime;
import com.toyproject.usedauction.domain.post.OrderPriceType;
import com.toyproject.usedauction.domain.post.Post;
import lombok.Builder;

public class ProductResponse {

	private String title;
	private String content;
	private ClosingTime closingTime;
	private int startPrice;
	private int endPrice;
	private int nowPrice;
	private OrderPriceType orderPriceType;
	private String writerName;
	private CategoryType categoryType;

	@Builder
	public ProductResponse(String title, String content, ClosingTime closingTime, int startPrice,
		int endPrice, int nowPrice, OrderPriceType orderPriceType, String writerName, CategoryType categoryType) {
		this.title = title;
		this.content = content;
		this.closingTime = closingTime;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.nowPrice = nowPrice;
		this.orderPriceType = orderPriceType;
		this.writerName = writerName;
		this.categoryType = categoryType;
	}

	public static ProductResponse of(Post post) {
		return ProductResponse.builder()
			.title(post.getTitle())
			.content(post.getContent())
			.closingTime(post.getClosingTime())
			.startPrice(post.getStartPrice())
			.endPrice(post.getEndPrice())
			.nowPrice(post.getNowPrice())
			.orderPriceType(post.getOrderPriceType())
			.writerName(post.getWriter().getNickname())
			.categoryType(post.getCategory().getCategoryType())
			.build();
	}
}
