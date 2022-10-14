package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dto.Product;

@Mapper // @Mapper는 해당 interface가 Mapper인터페이스임을 알려줌
@Repository("ProductMapper")
public interface ProductMapper {

	Integer upload(Product product)throws Exception;

}
