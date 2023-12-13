package com.toyproject.usedauction.api.service.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.toyproject.usedauction.support.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3UploadService {
	
	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public String upload(MultipartFile multipartFile, String fileName) {
		ObjectMetadata objectMetadata = createObjectMetadata(multipartFile);

		String filePath = FileUtils.getFilePath(multipartFile, fileName);
		log.info("filePath = {}", filePath);

		try (InputStream inputStream = multipartFile.getInputStream()) {
			amazonS3Client.putObject(new PutObjectRequest(bucket, filePath, inputStream, objectMetadata));
		} catch (IOException e) {
			log.error("S3 파일 업로드에 실패했습니다. {}", e.getMessage());
			throw new IllegalStateException("S3 파일 업로드에 실패했습니다.");
		}

		return amazonS3Client.getUrl(bucket, filePath).toString();
	}

	private ObjectMetadata createObjectMetadata(MultipartFile multipartFile) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(multipartFile.getContentType());
		objectMetadata.setContentLength(multipartFile.getSize());
		return objectMetadata;
	}
}

