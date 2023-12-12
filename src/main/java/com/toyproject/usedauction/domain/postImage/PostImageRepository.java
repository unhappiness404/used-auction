package com.toyproject.usedauction.domain.postImage;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {

	/**
	 * select *
	 * from post_image
	 * where name in ('filename', 'filename'...);
	 */
	List<PostImage> findAllByNameIn(List<String> imageName);
}
