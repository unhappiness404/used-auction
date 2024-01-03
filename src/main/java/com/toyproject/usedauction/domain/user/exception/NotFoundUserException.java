package com.toyproject.usedauction.domain.user.exception;

import com.toyproject.usedauction.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BaseRuntimeException {

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
