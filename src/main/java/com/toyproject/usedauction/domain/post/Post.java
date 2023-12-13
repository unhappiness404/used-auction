package com.toyproject.usedauction.domain.post;

import com.toyproject.usedauction.domain.baseEntity.BaseEntity;
import com.toyproject.usedauction.domain.category.Category;
import com.toyproject.usedauction.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = false)
	private String title;

	@Column(length = 500)
	private String content;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ClosingTimeType closingTimeType;

	private LocalDateTime closingTime;

	@Column(nullable = false)
	private int startPrice;

	@Column(nullable = false)
	private int endPrice;

	@Column(nullable = false)
	private int nowPrice;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderPriceType orderPriceType;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private User writer;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Builder
	private Post(String title, String content, ClosingTimeType closingTimeType,
		int startPrice, int endPrice, OrderPriceType orderPriceType,
		User writer, Category category) {

		this.title = title;
		this.content = content;
		this.closingTimeType = closingTimeType;
		this.closingTime = (LocalDateTime.now().plusDays(closingTimeType.getDay()));
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.nowPrice = startPrice;
		this.orderPriceType = orderPriceType;
		this.writer = writer;
		this.category = category;
	}
}
