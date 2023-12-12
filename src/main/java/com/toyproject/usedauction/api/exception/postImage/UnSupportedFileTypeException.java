package com.toyproject.usedauction.api.exception.postImage;

public class UnSupportedFileTypeException extends RuntimeException {

	public UnSupportedFileTypeException() {
	}

	public UnSupportedFileTypeException(String message) {
		super(message);
	}

	public UnSupportedFileTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
