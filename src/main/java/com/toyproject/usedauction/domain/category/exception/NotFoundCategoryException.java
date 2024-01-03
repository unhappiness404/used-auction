package com.toyproject.usedauction.domain.category.exception;

import com.toyproject.usedauction.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;

public class NotFoundCategoryException extends BaseRuntimeException {

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
