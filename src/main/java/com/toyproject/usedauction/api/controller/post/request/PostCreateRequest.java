package com.toyproject.usedauction.api.controller.post.request;

import com.toyproject.usedauction.api.service.post.request.PostCreateServiceRequest;
import com.toyproject.usedauction.domain.post.ClosingTimeType;
import com.toyproject.usedauction.domain.post.OrderPriceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequest {

	@NotBlank(message = "제목은 필수입니다.")
	private String title;
	@NotBlank(message = "내용은 필수입니다.")
	private String content;
	@NotNull(message = "마감시간은 필수입니다.")
	private ClosingTimeType closingTimeType;
	@Positive(message = "시작가격은 필수입니다.")
	private int startPrice;
	@Positive(message = "상한가격은 필수입니다.")
	private int endPrice;
	@NotNull(message = "입찰금액 단위는 필수입니다.")
	private OrderPriceType orderPriceType;
	@NotBlank(message = "유저 id는 필수입니다.")
	private Long userId;
	@NotBlank(message = "카테고리 id는 필수입니다.")
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

	public PostCreateServiceRequest toServiceRequest() {
		return PostCreateServiceRequest.builder()
			.title(title)
			.content(content)
			.closingTimeType(closingTimeType)
			.startPrice(startPrice)
			.endPrice(endPrice)
			.orderPriceType(orderPriceType)
			.userId(userId)
			.categoryId(categoryId)
			.build();
	}

}
