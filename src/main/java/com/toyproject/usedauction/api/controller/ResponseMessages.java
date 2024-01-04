package com.toyproject.usedauction.api.controller;

import lombok.Getter;

@Getter
public enum ResponseMessages {

	POST_CREATE_SUCCESS("게시글 작성에 성공했습니다."),

	POST_UPDATE_SUCCESS("게시글 수정에 성공했습니다."),

	POST_DELETE_SUCCESS("게시글 삭제에 성공했습니다.");

	private final String message;

	ResponseMessages(String message) {
		this.message = message;
	}
}
