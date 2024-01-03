package com.toyproject.usedauction.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BaseRuntimeException extends RuntimeException{

	private final String exceptionMessage = ExceptionMessages.findByExceptionClass(getClass());

	public abstract HttpStatus getHttpStatus();
}
