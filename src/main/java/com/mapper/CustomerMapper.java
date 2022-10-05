package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;
import com.dto.Customer;

// Mapper인터페이스는 Mapper.xml파일에 있는 쿼리를 호출하기 위한 인터페이스

//class scanning을 통해 @Repository를 Spring beans로 등록
//스프링에서 Repository는 해당 클래스가 storage, retrieval, search, update 그리고 delete의 기능을 제공하는 클래스를 의미

@Mapper // @Mapper는 해당 interface가 Mapper인터페이스임을 알려줌
@Repository
public interface CustomerMapper {
	// 로그인
	Customer login(Map<String, String> map)throws Exception; 
	// 아이디중복확인
	Integer idcheck(String id)throws Exception; 
	// 회원가입
	Integer join(Customer customer)throws Exception;
	// 목록조회
//	List<Customer> customerlist()throws Exception;
	// 회원수정
//	int customerupdate(Customer customer)throws Exception;
	// 회원탈퇴
	Integer customerdelete(Customer customer)throws Exception;
	// 비밀번호 찾기
//	Customer pwFindOk(Customer customer)throws Exception;
}
