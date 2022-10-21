package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.dto.Pager;
import com.dto.Product;

@Mapper // @Mapper는 해당 interface가 Mapper인터페이스임을 알려줌
@Repository("productmapper")
public interface ProductMapper {
	// 상품 업로드
	Integer upload(Product product)throws Exception;
	// 상품 목록
	List<Product> productlist()throws Exception;
	Integer count()throws Exception;
	// 상세 페이지
	Product productdetail(int prodno)throws Exception;
	// 목록
	List<Product> list()throws Exception;
}
