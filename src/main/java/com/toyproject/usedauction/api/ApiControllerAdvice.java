package com.toyproject.usedauction.api;

import com.toyproject.usedauction.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

	@ExceptionHandler(BaseRuntimeException.class)
	public ApiResponse<Object> handle(BaseRuntimeException e) {
		return ApiResponse.of(e.getHttpStatus(), e.getExceptionMessage(), null);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ApiResponse<Object> bindException(BindException e) {
		String errorMessage = e.getBindingResult().getFieldErrors().stream()
			.map(FieldError::getDefaultMessage)
			.findFirst()
			.orElse("Validation error occurred");

		return ApiResponse.badRequest(errorMessage, null);
	}

}
