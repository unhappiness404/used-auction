package com.toyproject.usedauction.domain.post.exception;

import com.toyproject.usedauction.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;

public class NotFoundPostException extends BaseRuntimeException {

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
