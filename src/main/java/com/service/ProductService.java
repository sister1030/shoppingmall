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
	// 페이징
	Pager paging(Pager pager) throws Exception;
}
