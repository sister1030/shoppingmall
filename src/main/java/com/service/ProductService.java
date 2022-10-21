package com.service;

import java.util.List;

import org.springframework.ui.Model;

import com.dto.Pager;
import com.dto.Product;

public interface ProductService {
	
	// 상품 업로드
	boolean upload(Product product)throws Exception;
	// 상품 목록
	List<Product> productlist(int page)throws Exception;
	int count()throws Exception;
	// 상세 페이지
	Product productdetail(int prodno)throws Exception;
	// 목록
	public List<Product> list() throws Exception;
}
