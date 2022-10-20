package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.mail.HtmlEmail;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dto.Customer;
import com.service.CustomerService;

import lombok.RequiredArgsConstructor;


@Controller("customercontroller") // Controller를 특정지을 수 있도록 한 어노테이션
@RequiredArgsConstructor
public class CustomerController {
	
	private final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	private final CustomerService customerService;
	
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
		@GetMapping("join")
		public String joinpage() {
			return "modal";
		}
		
		// 회원가입 인증
		@PostMapping(value="join")
		public String join(Customer customer) throws Exception { 
			log.info("controller join() start");
	        boolean b = customerService.join(customer);
	        log.info(customer.toString());
	        if(b) {
	            return "main";
	        }
	        return "modal";
	    }
	
	// 로그인 페이지 불러오기(페이지이동 GetMapping)
	@GetMapping("login")
	public String loginpage() {
		return "customer/login";
	}
	
	// 로그인 인증(값 상태를 변경하므로 PostMapping)
	@PostMapping(value="login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session) throws Exception {
		log.info("controller login() Start");
		try {
			if (map.get("id") == null || map.get("pw") == null) {
				model.addAttribute("msg", "아이디 또는 비밀번호를 입력해주세요");
				log.info("controller login() fail1");
				return "customer/login";
			}
			log.info("customercontroller login()");
		Customer loginsession = customerService.login(map);
			if (loginsession != null) {
				session.setAttribute("loginsession", loginsession);
				model.addAttribute("admin", loginsession.getAdminchk()); // 관리자 확인
				log.info("controller login() success");
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호가 올바르지 않습니다.");
				log.info("controller login() fail2");
				return "customer/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제가 발생했습니다.");
			log.info("controller login() fail3");
			return "customer/login";
		}
		return "main";
	} // end
	
		// 로그아웃
		@RequestMapping("logout")
		public String logout(HttpSession session) {
			session.invalidate();
			log.info("controller logout() ok");
			return "main";
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
		
		// 마이페이지 불러오기(페이지이동 GetMapping)
		@GetMapping("mypage")
		public String mypage() {
			return "customer/mypage";
		}
		
	/*
	 * // 회원수정페이지에 회원정보 불러오기
	 * 
	 * @GetMapping("customerinfo") public String customerinfo(HttpSession session,
	 * Model model) throws Exception {
	 * 
	 * // 세션에 있는 ID정보 저장 String id = (String) session.getAttribute("id");
	 * log.info("customerinfo id :"+id);
	 * 
	 * // 서비스안의 회원정보보기 메소드 호출 Customer customer = customerService.customerinfo(id);
	 * 
	 * // 정보를 저장한 후 페이지 이동 model.addAttribute("customer",customer);
	 * log.info("customerinfo :"+ customer);
	 * 
	 * return "customer/mypage"; }
	 */
		
		// 회원수정페이지
		@GetMapping("customermodify")
		public String customermodifypage(HttpSession session,Model model)throws Exception {
			log.info("controller modifypage() Start");
			// 회원정보 보기의 3단계를 한줄로 표현
			model.addAttribute("customer",customerService.customerinfo(session.getAttribute("id")));
			return "customer/mypage";
		}
		
		// 회원수정
		@PostMapping("customermodify")
		public String customermodify(Customer customer) throws Exception {
			log.info("controller modify() Start");
			
			customerService.customermodify(customer);
			
			return "customer/mypage";
		}
		
		// 회원삭제페이지
		@GetMapping("customerdelete")
		public String customerdeletepage(HttpSession session)throws Exception {
			log.info("controller deletepage() Start");
			// 세션제어
			String id = (String) session.getAttribute("id");
			if(id == null) {
				return "main";
			}
			return "customer/delete";
		}
		
		// 회원정보삭제
		@PostMapping("customerdelete")
		public String customerdelete(HttpSession session,Customer customer)throws Exception{
			// 파라미터값 저장후 전달받은 정보를 가지고 삭제 동작 처리이동
			log.info("controller delete() Start"+customer);
			// Sevice 객체
			customerService.customerdelete(customer);
			// 세션 초기화
			session.invalidate();
			return "customer/delete";
		}
}
