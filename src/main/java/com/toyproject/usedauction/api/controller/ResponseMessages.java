package com.toyproject.usedauction.api.controller;

import lombok.Getter;

@Getter
public enum ResponseMessages {

	POST_CREATE_SUCCESS("게시글 작성에 성공했습니다.");

	private final String message;

	ResponseMessages(String message) {
		this.message = message;
	}
}
