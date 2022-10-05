package com.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias("product")
public class Product {
	private int prodno; // 상품번호
	private String pname; // 상품이름
	private char pkind; // 상품 종류
	private int price1; // 원가
	private int price2; // 판매가
	private int price3; // 수익
	private String content; // 상품설명
	private String image; //사진
	private char useryn; // 상품판매여부
	private char bestyn; // 베스트상품여부
	private Timestamp regdate; // 등록일
}
