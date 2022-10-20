package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dto.Customer;
import com.dto.Product;

@Mapper // @Mapper는 해당 interface가 Mapper인터페이스임을 알려줌
@Repository("mainmapper")
public interface MainMapper {

	Customer customerinfo(String id)throws Exception;

	List<Product> list()throws Exception;

}
