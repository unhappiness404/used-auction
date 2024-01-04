package com.toyproject.usedauction.api;

import com.toyproject.usedauction.api.controller.ResponseMessages;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

	private int code;
	private HttpStatus status;
	private String message;
	private T data;

	public ApiResponse(HttpStatus status, String message, T data) {
		this.code = status.value();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
		return new ApiResponse<>(httpStatus, message, data);
	}

	public static <T> ApiResponse<T> ok(ResponseMessages message, T data) {
		return of(HttpStatus.OK, message.getMessage(), data);
	}

	public static <T> ApiResponse<T> badRequest(String message, T data) {
		return of(HttpStatus.BAD_REQUEST, message, data);
	}

}
