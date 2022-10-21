package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.dto.Pager;
import com.dto.Product;
import com.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service("productservice") // Spring이 해당 클래스가 Service임을 알 수 있도록 @Service를 명시
@Transactional //트랜잭션 보장이 된 메소드로 설정
@RequiredArgsConstructor //생성자 자동 생성
public class ProductServicelmpl implements ProductService{
	
	private final ProductMapper productMapper;

	// 파일 업로드
	@Override
	public boolean upload(Product product) throws Exception {
		int n = productMapper.upload(product);
		return n > 0;
	}

	// 파일 목록
	@Override
	public List<Product> productlist(int page) throws Exception {
		return productMapper.productlist();
	}
	@Override
	public int count() throws Exception {
		return productMapper.count();
	}
	// 상세 페이지
	@Override
	public Product productdetail(int prodno) throws Exception {
		return productMapper.productdetail(prodno);
	}
	// 목록
	@Override
	public List<Product> list() throws Exception {
		return productMapper.list();
	}

}
