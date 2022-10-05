package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.mail.HtmlEmail;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dto.Customer;
import com.service.CustomerService;


@Controller("customercontroller") // Controller를 특정지을 수 있도록 한 어노테이션
public class CustomerController {
	
	private final Logger log = LoggerFactory.getLogger(CustomerController.class);

	
	@Autowired // Autowired된 객체는 사용자가 직접 new로 생성하는 것이 아닌 Spring이 객체를 생성해서 주입
	private CustomerService customerService;
	
	
	// 로그인 페이지 불러오기(페이지이동 GetMapping)
	@GetMapping("loginpage")
	public String loginpage() {
		return "login";
	}
	
	// 로그인 인증(값 상태를 변경하므로 PostMapping)
	@PostMapping(value="login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session) throws Exception {
		try {
			if (map.get("id") == null || map.get("pw") == null) {
				model.addAttribute("msg", "아이디 또는 비밀번호를 입력해주세요");
				return "customer/loginerror";
			}
			log.info("customercontroller login()");
		Customer customer = customerService.login(map);
			if (customer != null) {
				session.setAttribute("customer", customer);
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호가 올바르지 않습니다.");
				return "customer/loginerror";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제가 발생했습니다.");
			return "customer/loginerror";
		}
		return "main";
	} // end
	
	// 아이디 중복확인
	// @ResponseBody : 서버로 보낸 json데이터를 자바 객체로 매핑
	@PostMapping("idcheck")
	@ResponseBody
    public int idcheck(@RequestParam("id") String id) throws Exception {
        log.info("customercontroller idcheck()");
        int result = 0;
        if(id != null) result = customerService.idcheck(id);
        return result;  
	}        
	
	// 회원가입 페이지 불러오기
	@GetMapping("joinpage")
	public String joinpage() {
		return "modal";
	}
	
	// 회원가입 인증
	@PostMapping(value="join")
	public String join(Customer customer) throws Exception { 
		log.info("customercontroller join()");
        boolean b = customerService.join(customer);
        log.info(customer.toString());
        log.info(customer.getId());
        log.info(customer.getPw());
        log.info(customer.getName());
        log.info(customer.getJumin1());
        log.info(customer.getJumin2());
        log.info(customer.getTel());
        log.info(customer.getPost());
        log.info(customer.getAddr1());
        log.info(customer.getAddr2());
        if(b) {
            return "main";
        }
        return "modal";
    }
	// 비밀번호 찾기 페이지 불러오기
		@GetMapping(value = "pwfindpage")
		public String pwfindpage() {
			return "modal";
		}
	// 비밀번호 찾기
//		@PostMapping(value = "pwFindOk")
//		public String pwFindOk(@ModelAttribute Customer customer, HttpServletResponse response, Model model) throws Exception {
//			response.setContentType("text/html;charset=UTF-8");
//
//			customer = customerService.pwFindOk(customer);
//
//			int result = 0;
//			if (customer == null) {
//				return "modal";
//			} else {
//				String charSet = "utf-8";
//				String hostSMTP = "smtp.naver.com";
//				String hostSMTPid = "dpsk158@naver.com";
//				String hostSMTPpwd = "a1s2d3f4";
//
//				String fromEmail = "dpsk158@naver.com";
//				String fromName = "관리자";
//				String subject = "비밀번호 찾기 안내 이메일입니다.";
//				String mail = customer.getEmail();
//				try {
//					HtmlEmail email = new HtmlEmail();
//					email.setDebug(true);
//					email.setCharset(charSet);
//					email.setSSL(true);
//					email.setHostName(hostSMTP);
//					email.setSmtpPort(587);
//
//					email.setAuthentication(hostSMTPid, hostSMTPpwd);
//					email.setTLS(true);
//					email.addTo(mail, charSet);
//					email.setFrom(fromEmail, fromName, charSet);
//					email.setSubject(subject);
//					email.setHtmlMsg("<p align = 'center'><b>" + customer.getName() + "</b>님의 비밀번호는</p><br>" + "<div align='center'>" + customer.getPw() + "입니다.</div>");
//					email.send();
//					result = 1;
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//
//				model.addAttribute("result", result);
//
//				return "modal";
//			}
//		}
}
