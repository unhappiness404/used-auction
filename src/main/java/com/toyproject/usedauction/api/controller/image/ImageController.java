package com.toyproject.usedauction.api.controller.image;

import com.toyproject.usedauction.api.service.image.ImageFileUploadService;
import com.toyproject.usedauction.api.service.image.response.PostImageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ImageController {

	private final ImageFileUploadService imageFileUploadService;

	@PostMapping("/api/posts/images")
	public List<PostImageResponse> uploadImages(@RequestParam("file") List<MultipartFile> files) {
		return imageFileUploadService.upload(files);
	}
}
