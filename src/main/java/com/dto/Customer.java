package com.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias("customer")
public class Customer {
	private String id; // 이름
	private String pw; // 비밀번호
	private String name; // 이름
	private String jumin1; // 주민번호1
	private String jumin2; // 주민번호2
	private String email; // 이메일
	private String tel; // 연락처
	private String post; // 우편번호
	private String addr1; // 주소
	private String addr2; // 상세주소
	private char useyn; // 탈퇴여부
	private int adminchk; // 회원구분
	private Timestamp regdate; // 가입일
}
