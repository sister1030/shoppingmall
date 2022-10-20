package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Customer;
import com.dto.Product;
import com.mapper.MainMapper;

import lombok.RequiredArgsConstructor;

@Service("mainservice") // Spring이 해당 클래스가 Service임을 알 수 있도록 @Service를 명시
@Transactional //트랜잭션 보장이 된 메소드로 설정
@RequiredArgsConstructor //생성자 자동 생성
public class MainService {
	
	private final MainMapper mainMapper;

	public Customer customerinfo(String id) throws Exception {
		return mainMapper.customerinfo(id);
	}

	public List<Product> list() throws Exception {
		return mainMapper.list();
	}
	

}
