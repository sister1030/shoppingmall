package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.dto.Customer;

@Controller("maincontroller")
public class MainController {
	 
	private final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	
	//@SessionAttribute : 설정한 이름에 해당하는 모델 정보를 자동으로 세션에 넣어주는 애노테이션
	@RequestMapping(value="main", method=RequestMethod.GET)
	public void main(@SessionAttribute(name="customer",required = false)Customer customer, Model model) {
		log.info("Controller Sessoinchk");
		model.addAttribute("customer",customer);
	}
	
}
