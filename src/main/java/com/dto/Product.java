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
	private int pkind; // 상품 종류(0:에어팟, 1:케이스, 2:그립톡)
	private int price1; // 원가
	private int price2; // 판매가
	private int price3; // 수익
	private String content; // 상품설명
	private String imagename; //사진이름
	private String imagepath; //사진경로
	private char useryn; // 상품판매여부
	private char bestyn; // 베스트상품여부
	private Timestamp regdate; // 등록일
}
