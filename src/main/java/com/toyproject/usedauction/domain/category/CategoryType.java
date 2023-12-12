package com.toyproject.usedauction.domain.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryType {

	ELECTRONICS("전자기기"),
	HOME_APPLIANCES("생활가전"),
	BOOK("도서"),
	CLOTHING("의류"),
	ETC("기타");

	private final String value;
}
