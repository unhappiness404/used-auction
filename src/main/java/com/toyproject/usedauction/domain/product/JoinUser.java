package com.toyproject.usedauction.domain.product;

import com.toyproject.usedauction.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class JoinUser {

	@Id
	@Column(name = "join_user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
