package com.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias("cart")
public class Cart {
	private int cartno; // 카트번호
	private String id; 	// 아이디
	private int prodno;	// 상품번호
	private int quantity; // 수량
	private char result;	// 배송여부
	private Date indate;	// 상품담은날
}
