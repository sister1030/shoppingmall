package com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import com.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller("productcontroller")
@RequiredArgsConstructor
public class ProductController {

	private final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private final ProductService productService;
	// 파일 업로드 페이지
	@GetMapping("upload")
	public String uploadpage() {
		return "product/fileupload";
	}
	
	// 파일 업로드
	@PostMapping("upload")
	public String uploadpost(@RequestParam("uploadimg") List<MultipartFile> uploadimg, Product product, Model model)
			throws Exception {
		log.info("controller upload() Start");
		// 내가 업로드 파일을 저장할 경로
		String uploadFolder = "C:\\Users\\dpsk1\\git\\shoppingmall\\src\\main\\resources\\static\\img";
		StringBuilder sb = new StringBuilder(); //문자열 연결
		StringBuilder sb1 = new StringBuilder(); //문자열 연결

		for (MultipartFile file : uploadimg) {
			String imagename = UUID.randomUUID() + "_" + file.getOriginalFilename(); //보안이름 설정
			String imagepath = "\\img\\"+ imagename; //보안이름 설정
			log.info(imagepath);
			 File saveFile = new File(uploadFolder + "\\" + imagename);
			 sb.append(imagename + ","); // 쉼표로 다중파일 구분
			 sb1.append(imagepath); // 경로를 읽기위해 쉼표제외
			try {
				// void transferTo(File dest) throws IOException 업로드한 파일 데이터를 지정한 파일에 저장
				file.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		product.setImagename(sb.toString()); // 문자열 연결된 파일이름 넣어줌
		product.setImagepath(sb1.toString()); // 문자열 연결된 파일이름 넣어줌
		log.info(product.getImagepath());
		log.info(product.toString()); // 저장값 확인
		
		productService.upload(product); // DB저장
		
		return "main";
	}
	
	// 상품목록
	@GetMapping("productlist")
	public String productlist(HttpServletRequest request, Model model) throws Exception{
		List<Product> productlist = new ArrayList<>();
		
		int page = 1;
		int limit = 5;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listCount = productService.count();
		productlist = productService.productlist(page);
		
		int pageCount = listCount / limit + ((listCount % limit == 0) ? 0 : 1);
		
		int startPage = ((page - 1) / 10) * limit + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > pageCount)
			endPage = pageCount;

		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("listCount", listCount);
		model.addAttribute("productlist", productlist);
		
		return "productlist";
	}
	
	// 상품상세페이지
	@GetMapping("productdetail")
	public String productdetail(@RequestParam("prodno")int prodno,Model model)throws Exception {
		log.info("controller productdetail start");
		List<Product> list = new ArrayList<Product>();
		list = productService.list();	
		Product product = productService.productdetail(prodno);
		model.addAttribute("list",list);
		model.addAttribute("product",product);
		log.info(product.toString());
		return "product/productdetail";
	}
}
