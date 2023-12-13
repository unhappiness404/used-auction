package com.toyproject.usedauction.domain.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClosingTimeType {

	THREE_DAYS(3),
	FIVE_DAYS(5),
	SEVEN_DAYS(7);

	private final int day;
}
