package com.study.servlet_study.test;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Publisher {
	private int publisherId;
	private String publisherName;

}
