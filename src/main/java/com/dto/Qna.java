package com.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias("qna")
public class Qna {
	private int qno; // 문의번호
	private String subject; // 문의제목
	private String content;	// 문의내용
	private String reply; // 답변내용
	private String id;	// 작성자 아이디
	private char ansyn;	// 답변처리여부
	private Timestamp qdate;	// 문의등록일
}
