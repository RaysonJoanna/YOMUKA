
package com.yomuka.yomuka.ord.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yomuka.yomuka.main.Member;
import com.yomuka.yomuka.ord.entity.GudokEntity;
import com.yomuka.yomuka.ord.entity.ProductEntity;
import com.yomuka.yomuka.ord.service.CustomerService;
import com.yomuka.yomuka.ord.service.getProductService;

@Controller
public class OrderMainController {
	// private final customerService loggedUser;
	private final getProductService getproductService;
	private final CustomerService customerService;

	@Autowired
	public OrderMainController(getProductService getproductService, CustomerService customerService) {
		this.getproductService = getproductService;
		this.customerService = customerService;
	}

	@GetMapping(value = "/Order")
	public String getProduct(Model model, HttpSession session, String memberId) {
	    Member member = (Member) session.getAttribute("loggedUser");
	        memberId = member.getMemberid();
	        List<ProductEntity> pProduct = getproductService.findAllProduct();
	        model.addAttribute("productList", pProduct);
	        List<GudokEntity> cGudok = customerService.findGudokByCustomerId(memberId);
	        model.addAttribute("findGudok", cGudok);
	        return "/Order/Order";
	}

	@GetMapping(value = "/OrderCat")
	public String filtercatProduct(Model model, HttpSession session, String memberId) {
		Member member = (Member) session.getAttribute("loggedUser");
        memberId = member.getMemberid();
		
		List<ProductEntity> pCat = getproductService.findCat();
		model.addAttribute("catProduct", pCat);
		List<GudokEntity> cGudok = customerService.findGudokByCustomerId(memberId);
		model.addAttribute("findGudok", cGudok);
		return "/Order/OrderCat";
	}

	@GetMapping(value = "/OrderDog")
	public String filterDogProduct(Model model, HttpSession session, String memberId) {
		Member member = (Member) session.getAttribute("loggedUser");
        memberId = member.getMemberid();
        
		List<ProductEntity> pDog = getproductService.findDog();
		model.addAttribute("dogProduct", pDog);
		List<GudokEntity> cGudok = customerService.findGudokByCustomerId(memberId);
		model.addAttribute("findGudok", cGudok);
		return "/Order/OrderDog";
	}

	@GetMapping(value = "/OrderSmall")
	public String filtersmallProduct(Model model, HttpSession session, String memberId) {
		Member member = (Member) session.getAttribute("loggedUser");
        memberId = member.getMemberid();
        
		List<ProductEntity> pSmall = getproductService.findSmall();
		model.addAttribute("smallProduct", pSmall);
		List<GudokEntity> cGudok = customerService.findGudokByCustomerId(memberId);
		model.addAttribute("findGudok", cGudok);
		return "/Order/OrderSmall";
	}

	@GetMapping(value = "/OrderDetail")
	public String getDetailProduct(Model model, HttpSession session, String memberId, Long productNum,
			@RequestParam("productName") String productName, @RequestParam("productImg") String productImg,
			@RequestParam("productPrice") String productPrice) {

		Member member = (Member) session.getAttribute("loggedUser");
        memberId = member.getMemberid();
        
		// 예시: 주문과 관련된 ProductEntity 정보 가져오기
		ProductEntity productDetail = DetailProduct(productNum);

		// 주문에서 받은 값을 모델에 추가
		model.addAttribute("productName", productName);
		model.addAttribute("productImg", productImg);
		model.addAttribute("productPrice", productPrice);

		// 주문과 관련된 상품 상세 정보를 모델에 추가
		model.addAttribute("productDetail", productDetail);
		List<GudokEntity> cGudok = customerService.findGudokByCustomerId(memberId);
		model.addAttribute("findGudok", cGudok);
		return "/Order/OrderDetail";
	}

	// 주문(Order)과 관련된 상품(Product) 정보를 데이터베이스 또는 서비스를 통해 가져오는 메서드
	private ProductEntity DetailProduct(Long productNum) {
		List<ProductEntity> products = getproductService.findAllProduct();
		for (ProductEntity product : products) {
			if (product.getProductNum().equals(productNum)) {
				return product;
			}
		}
		return null; // 해당 상품이 없을 경우 예외 처리 필요
	}

	// 강아지 필터링
	@GetMapping(value = "/OrderDog/product")
	public String Dogproduct(@RequestParam(defaultValue = "defaultType") String productType, String petType,
			String productName, String productImg, String productPrice, Model model, HttpSession session, String memberId) {
		petType = "강아지";
		Member member = (Member) session.getAttribute("loggedUser");
        memberId = member.getMemberid();
   
		List<GudokEntity> cGudok = customerService.findGudokByCustomerId(memberId);
		model.addAttribute("findGudok", cGudok);
		
		if ("건식사료".equals(productType)) {
			List<ProductEntity> dogProduct = getproductService.DogDry();
			model.addAttribute("dogProduct", dogProduct);
		} else if ("습식사료".equals(productType)) {
			List<ProductEntity> dogwet = getproductService.DogWet();
			model.addAttribute("dogProduct", dogwet);
		} else if ("장난감".equals(productType)) {
			List<ProductEntity> dogtoy = getproductService.DogToy();
			model.addAttribute("dogProduct", dogtoy);
		} else if ("배변용품".equals(productType)) {
			List<ProductEntity> dogpoo = getproductService.DogPoo();
			model.addAttribute("dogProduct", dogpoo);
		} else if ("간식".equals(productType)) {
			List<ProductEntity> dogsnack = getproductService.DogSnack();
			model.addAttribute("dogProduct", dogsnack);
		}

		return "/Order/OrderDog";
	}

	// 고양이 필터링
	@GetMapping(value = "/OrderCat/product")
	public String Catproduct(@RequestParam(defaultValue = "defaultType") String productType, String petType,
			String productName, String productImg, String productPrice, Model model, HttpSession session, String memberId) {
		petType = "고양이";
		Member member = (Member) session.getAttribute("loggedUser");
        memberId = member.getMemberid();
        
		List<GudokEntity> cGudok = customerService.findGudokByCustomerId(memberId);
		model.addAttribute("findGudok", cGudok);
		
		if ("건식사료".equals(productType)) {
			List<ProductEntity> catProduct = getproductService.CatDry();
			model.addAttribute("catProduct", catProduct);
		} else if ("습식사료".equals(productType)) {
			List<ProductEntity> catwet = getproductService.CatWet();
			model.addAttribute("catProduct", catwet);
		} else if ("장난감".equals(productType)) {
			List<ProductEntity> cattoy = getproductService.CatToy();
			model.addAttribute("catProduct", cattoy);
		} else if ("스크래쳐".equals(productType)) {
			List<ProductEntity> catscratcher = getproductService.CatScratcher();
			model.addAttribute("catProduct", catscratcher);
		} else if ("모래".equals(productType)) {
			List<ProductEntity> catsend = getproductService.CatSend();
			model.addAttribute("catProduct", catsend);
		} else if ("간식".equals(productType)) {
			List<ProductEntity> catsnack = getproductService.CatSnack();
			model.addAttribute("catProduct", catsnack);
		}
		return "/Order/OrderCat";
	}

	// 소동물 필터링
	@GetMapping(value = "/OrderSmall/product")
	public String Samllproduct(@RequestParam(defaultValue = "defaultType") String productType, String petType,
			String productName, String productImg, String productPrice, Model model, HttpSession session, String memberId) {
		petType = "소동물";
		
		Member member = (Member) session.getAttribute("loggedUser");
        memberId = member.getMemberid();
        
		List<GudokEntity> cGudok = customerService.findGudokByCustomerId(memberId);
		model.addAttribute("findGudok", cGudok);
		
		if ("사료".equals(productType)) {
			List<ProductEntity> smallProduct = getproductService.SmallFood();
			model.addAttribute("smallProduct", smallProduct);
		} else if ("장난감".equals(productType)) {
			List<ProductEntity> smalltoy = getproductService.SmallToy();
			model.addAttribute("smallProduct", smalltoy);
		} else if ("이갈이".equals(productType)) {
			List<ProductEntity> smalltooth = getproductService.SmallTooth();
			model.addAttribute("smallProduct", smalltooth);
		} else if ("베딩".equals(productType)) {
			List<ProductEntity> smallbedding = getproductService.SmallBedding();
			model.addAttribute("smallProduct", smallbedding);
		}
		return "/Order/OrderSmall";
	}

	// 구독
	// 구독 저장 //session
	@RequestMapping(value = "/OrderGudok/set", method = { RequestMethod.POST })
	public String setGudok(HttpSession session, String memberId, @RequestParam String productImg,
	        @RequestParam String productName, @RequestParam String productPrice, @RequestParam String productQuantity,
	        LocalDate gudokstartdate, Model model, String PageURL, String gudokBool, String totalPrice) {
	    int total;
	    Member member = (Member) session.getAttribute("loggedUser");
	    memberId = member.getMemberid();
	    int quantity = Integer.parseInt(productQuantity);
	    if (productPrice != null) {
	        int don = Integer.parseInt(productPrice);
	        total = don * quantity;

	        if (don >= 1000) {
	            DecimalFormat df = new DecimalFormat("#,###");
	            productPrice = "￦" + df.format(don);
	        } else {
	            productPrice = "￦" + don;
	        }

	        if (total >= 1000) {
	            DecimalFormat df = new DecimalFormat("#,###");
	            totalPrice = "￦" + df.format(total);
	        } else {
	            totalPrice = "￦" + total;
	        }
	    }
		// 구독 여부 확인하여 이미 구독중이면 취소, 구독중이 아니면 구독
		if (gudokBool.equals("true")) {
			// gudokBool 값이 true이면 새로운 구독
			GudokEntity saveGudok = customerService.setGudok(memberId, productImg, productName, productPrice,
					productQuantity, gudokstartdate, totalPrice);
		
		} else {
			// gudokBool 값이 false이면 구독 취소
			customerService.deleteGudokByProductName(productName);
		}

		System.out.println(PageURL);
		// 리다이렉트 URL을 반환
		return "redirect:" + PageURL;
	}

	// 고객별 구독 상황 확인
	@GetMapping(value = "/OrderGudok/view")
	public String findGudok(HttpSession session, String memberId, Model model) {
		Member member = (Member) session.getAttribute("loggedUser");
        memberId = member.getMemberid();
        
		List<GudokEntity> cGudok = customerService.findGudokByCustomerId(memberId);
		model.addAttribute("findGudok", cGudok);
		return "/Order/OrderGudok";
	}

	@RequestMapping(value = "/OrderGudok/delete", method = { RequestMethod.POST })
	public String deleteGudok(HttpSession session, String memberId, @RequestParam String productName) {
		Member member = (Member) session.getAttribute("loggedUser");
        memberId = member.getMemberid();
        
		String[] product = productName.split(",");
		
		for (int i = 0; i < product.length; i++) {
			customerService.deleteGudokByProductName(product[i]);
		}
		return "redirect:/OrderGudok/view";
	}
}