package com.service;

import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import com.dto.Customer;

public interface CustomerService {
		// 로그인
		Customer login(Map<String, String> map)throws Exception;
		// 아이디중복확인
		int idcheck(String id)throws Exception; 
		// 회원가입
		@Transactional //어노테이션을 이용한 선언적 트랜잭션 처리를 지원
		boolean join(Customer customer)throws Exception;
		// 목록조회
		//List<Customer> customerlist()throws Exception;
		// 회원탈퇴
		int customerdelete(Customer customer)throws Exception;
		// 비밀번호 찾기
	//	public Customer pwFindOk(Customer customer)throws Exception;
		// 회원정보 불러오기
		Customer customerinfo(Object object)throws Exception;
		// 회원정보수정
		String customermodify(Customer customer)throws Exception;
				
}
