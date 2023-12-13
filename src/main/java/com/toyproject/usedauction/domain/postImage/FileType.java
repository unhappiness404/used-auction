package com.toyproject.usedauction.domain.postImage;

public enum FileType {

	PNG("png"),
	JPEG("jpeg"),
	JPG("jpg");

	private final String extension;

	FileType(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}
}
