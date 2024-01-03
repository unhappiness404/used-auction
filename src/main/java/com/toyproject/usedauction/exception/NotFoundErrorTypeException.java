package com.toyproject.usedauction.exception;

import org.springframework.http.HttpStatus;

public class NotFoundErrorTypeException extends BaseRuntimeException {

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
