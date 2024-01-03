package com.toyproject.usedauction.domain.auction;

import com.toyproject.usedauction.domain.post.Post;
import com.toyproject.usedauction.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "auction")
public class Auction {

	@Id
	@Column(name = "auction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int price;

	private boolean isEnd;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

}
