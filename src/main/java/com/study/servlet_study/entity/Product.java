package com.study.servlet_study.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
	
	String productName;
	String price;
	String size;
	String color;
	
	
	//기능: 계정 정보를 나타내는 엔터티 클래스로, 
	//Lombok의 @Builder와 @Data 어노테이션을 사용하여 빠르게 
	//Getter, Setter, Builder 등을 생성합니다.
	//1번
	

}
