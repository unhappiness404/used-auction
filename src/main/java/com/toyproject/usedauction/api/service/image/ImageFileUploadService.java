package com.toyproject.usedauction.api.service.image;

import com.toyproject.usedauction.api.service.image.response.PostImageResponse;
import com.toyproject.usedauction.domain.postImage.PostImage;
import com.toyproject.usedauction.domain.postImage.PostImageRepository;
import com.toyproject.usedauction.support.FileUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageFileUploadService {

	private final AwsS3UploadService awsS3UploadService;
	private final PostImageRepository postImageRepository;

	@Transactional
	public List<PostImageResponse> upload(List<MultipartFile> files) {
		List<PostImage> images = uploadImageToStorageServer(files);
		List<PostImage> savedImages = postImageRepository.saveAll(images);

		return savedImages.stream()
			.map(PostImageResponse::of)
			.toList();
	}

	private List<PostImage> uploadImageToStorageServer(List<MultipartFile> files) {
		List<PostImage> images = new ArrayList<>();

		for (MultipartFile file : files) {
			String filename = FileUtils.getRandomFilename();
			String filepath = awsS3UploadService.upload(file, filename);
			images.add(PostImage.builder()
				.name(filename)
				.url(filepath)
				.build());
		}

		return images;
	}

}
