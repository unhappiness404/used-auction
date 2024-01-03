package com.toyproject.usedauction.exception;

import com.toyproject.usedauction.domain.category.exception.NotFoundCategoryException;
import com.toyproject.usedauction.domain.user.exception.NotFoundUserException;
import java.util.Arrays;

public enum ExceptionMessages {
	NOT_FOUND_ERROR_TYPE("시스템 오류가 발생하였습니다.", NotFoundErrorTypeException.class),

	NOT_FOUND_USER("존재하지 않는 유저입니다.", NotFoundUserException.class),

	NOT_FOUND_CATEGORY_EXCEPTION("존재하지 않는 카테고리입니다.", NotFoundCategoryException.class);

	private final String message;
	private final Class<? extends Exception> type;

	ExceptionMessages(String message, Class<? extends Exception> type) {
		this.message = message;
		this.type = type;
	}

	public static String findByExceptionClass(Class<? extends Exception> type) {
		return Arrays.stream(values())
			.filter(exception -> exception.type.equals(type))
			.findAny()
			.map(exception -> exception.message)
			.orElseThrow(NotFoundErrorTypeException::new);
	}

}
