package com.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias("orders")
public class Orders {
	private int orderno; // 주문번호
	private String id; // 아이디
	private Timestamp orderdate; // 주문일
}
