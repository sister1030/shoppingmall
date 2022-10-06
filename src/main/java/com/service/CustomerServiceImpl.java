package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Customer;
import com.mapper.CustomerMapper;

import lombok.RequiredArgsConstructor;

// Service는 재사용성과 확장성을 위해 interface와 구현으로 분리
// Service는 작업단위를 수행하기 위해 DB와 연결하여 로직을 처리하는  Mapper클래스를 필요로 함 

@Service("customerservice") // Spring이 해당 클래스가 Service임을 알 수 있도록 @Service를 명시
@Transactional //트랜잭션 보장이 된 메소드로 설정
@RequiredArgsConstructor //생성자 자동 생성
public class CustomerServiceImpl implements CustomerService {
	// 회원가입 시 저장시간을 넣어줄 DateTime형
//    SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:sss");
//    Date time = new Date();
//    String localTime = format.format(time);
	
	private final CustomerMapper customerMapper;
	
	// 로그인
	@Override
	public Customer login(Map<String, String> map) throws Exception {
		return customerMapper.login(map);
	}
	// 아이디 체크(중복시 true/아닐시 false)
	@Override
	public int idcheck(String id)throws Exception {
		 return customerMapper.idcheck(id);
    }
	// 회원가입(등록시 true/아닐시 false)
	@Override
	public boolean join(Customer customer) throws Exception {
        int n = customerMapper.join(customer);
        System.out.println("name"+customer.getName());
        System.out.println("addr1"+customer.getAddr1());
        return n > 0;
    }
	// 회원목록조회
//	@Override
//	public List<Customer> customerlist()throws Exception {
//		return null;
//	}
	// 회원수정
//	@Override
//	public int customerupdate(Customer customer)throws Exception {
//		return 0;
//	}
	// 회원탈퇴
	@Override
	public int customerdelete(Customer customer)throws Exception {
		// TODO Auto-generated method stub
		return customerMapper.customerdelete(customer);
	}
	// 비밀번호 찾기
//	@Override
//	public Customer pwFindOk(Customer customer) throws Exception {
//		return customerMapper.pwFindOk(customer);
//	}

}
