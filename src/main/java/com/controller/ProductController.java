package com.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dto.Product;
import com.service.CustomerService;
import com.service.ProductService;

@Controller("productcontroller")
public class ProductController {

	private final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired // Autowired된 객체는 사용자가 직접 new로 생성하는 것이 아닌 Spring이 객체를 생성해서 주입
	private ProductService productService;

	@GetMapping("upload")
	public String uploadpage() {
		return "product/fileupload";
	}

	@PostMapping("upload")
	public String uploadpost(@RequestParam("uploadimg") List<MultipartFile> uploadimg, Product product, Model model)
			throws Exception {
		log.info("controller upload() Start");
		// 내가 업로드 파일을 저장할 경로
		String uploadFolder = "C:\\Users\\dpsk1\\git\\shoppingmall\\src\\main\\resources\\static\\img";
		StringBuilder sb = new StringBuilder(); //문자열 연결

		for (MultipartFile file : uploadimg) {
			String uploadimgname = UUID.randomUUID() + "_" + file.getOriginalFilename(); //보안이름 설정
			sb.append(uploadimgname + ","); // 쉼표로 다중파일 구분
			try {
				// void transferTo(File dest) throws IOException 업로드한 파일 데이터를 지정한 파일에 저장
				file.transferTo(new File(uploadFolder + "/" + uploadimgname));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info(product.toString()); // 저장값 확인
		product.setImagename(sb.toString()); // 문자열 연결된 파일이름 넣어줌
		boolean b = productService.upload(product); // DB저장
		
		return "main";
	}
}
