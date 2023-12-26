package com.toyproject.usedauction.domain.postImage;

import com.toyproject.usedauction.domain.baseEntity.BaseEntity;
import com.toyproject.usedauction.domain.post.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post_image")
public class PostImage extends BaseEntity {

	@Id
	@Column(name = "post_image_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	@Builder
	private PostImage(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
