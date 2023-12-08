package com.toyproject.usedauction.domain.product;

import com.toyproject.usedauction.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product extends BaseEntity {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = false)
	private String title;

	@Column(length = 500)
	private String content;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ClosingTime closingTime;

	@Column(nullable = false)
	private int startPrice;

	@Column(nullable = false)
	private int endPrice;

	@Column(nullable = false)
	private int nowPrice;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderPriceType orderPriceType;
}
