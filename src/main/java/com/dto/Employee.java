package com.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias("employee")
public class Employee {
	private String mid; // 관리자아이디
	private String mpw; // 관리자 비밀번호
	private String mname; // 관리자 이름
}

