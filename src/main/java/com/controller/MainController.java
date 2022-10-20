package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.dto.Customer;
import com.dto.Product;
import com.service.CustomerService;
import com.service.MainService;
import com.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller("maincontroller")
@RequiredArgsConstructor
public class MainController {
	 
	private final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	private final MainService mainService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//@SessionAttribute : 설정한 이름에 해당하는 모델 정보를 자동으로 세션에 넣어주는 애노테이션 @SessionAttribute(name="customer",required = false)Customer customer,
	@RequestMapping(value="main", method = RequestMethod.GET)
	public String main(HttpSession session, Model model) throws Exception {
		log.info("Controller Sessoinchk start");
		
		String id = (String) session.getAttribute("id");
		Customer customer = mainService.customerinfo(id);
		
		List<Product> list = new ArrayList<Product>();
		list = mainService.list();
		
		model.addAttribute("customer",customer);
		model.addAttribute("list",list);
		
		return "main";
	}
}