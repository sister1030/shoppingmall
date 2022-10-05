package com.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias("orderdetail")
public class OrderDetail {
	private int odno; // 주문상세번호
	private int orderno; 	// 주문번호
	private int prodno;	// 상품번호
	private int quantity; // 수량
	private char result;	// 배송여부
}
