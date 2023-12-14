package com.toyproject.usedauction.domain.postImage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class PostImageRepositoryTest {

	@Autowired
	PostImageRepository postImageRepository;

	@Test
	@DisplayName("파일이름과 일치하는 이미지들을 조회한다.")
	void findAllByNameIn() {
		//given
		PostImage image1 = createPostImage("image1", "abcde");
		PostImage image2 = createPostImage("image2", "qwerd");
		PostImage image3 = createPostImage("image3", "rrrrr");

		postImageRepository.saveAll(List.of(image1, image2, image3));

		//when
		List<PostImage> findImage = postImageRepository.findAllByNameIn(List.of("image1", "image2"));

		//then
		assertThat(findImage).hasSize(2)
			.extracting("name")
			.contains("image1", "image2");
	}

	private static PostImage createPostImage(String name, String url) {
		return PostImage.builder()
			.name(name)
			.url(url)
			.build();
	}

}
