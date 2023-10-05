package com.yomuka.yomuka.ord.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yomuka.yomuka.ord.entity.ProductEntity;
import com.yomuka.yomuka.ord.repository.ProductRepository;

@Service
public class getProductService {
	private ProductRepository productRepository;
	private List<ProductEntity> pDog;
	private List<ProductEntity> pCat;
	private List<ProductEntity> pSmall;

	private List<ProductEntity> dry;
	private List<ProductEntity> wet;
	private List<ProductEntity> snack;
	private List<ProductEntity> toy;

	private List<ProductEntity> send;
	private List<ProductEntity> scratcher;

	private List<ProductEntity> poo;

	private List<ProductEntity> food;
	private List<ProductEntity> tooth;
	private List<ProductEntity> bedding;

	@Autowired
	public getProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
		// 동물 타입
		pDog = productRepository.findByPetType("강아지");
		pCat = productRepository.findByPetType("고양이");
		pSmall = productRepository.findByPetType("소동물");

		// 상품 타입(공통)
		dry = productRepository.findByProductType("건식사료");
		wet = productRepository.findByProductType("습식사료");
		snack = productRepository.findByProductType("간식");
		toy = productRepository.findByProductType("장난감");

		// 상품 타입(고양이)
		send = productRepository.findByProductType("모래");
		scratcher = productRepository.findByProductType("스크래쳐");

		// 상품 타입(강아지)
		poo = productRepository.findByProductType("배변용품");

		// 상품 타입(족곰이)
		food = productRepository.findByProductType("사료");
		tooth = productRepository.findByProductType("이갈이");
		bedding = productRepository.findByProductType("베딩");
	}

	public List<ProductEntity> findAllProduct() {
		List<ProductEntity> pProduct = new ArrayList<>();
		productRepository.findAll().forEach(e -> pProduct.add(e));
		return pProduct;
	}

	public List<ProductEntity> findCat() {
		List<ProductEntity> pCat = new ArrayList<>();
		productRepository.findByPetType("고양이").forEach(e -> pCat.add(e));
		return pCat;
	}

	public List<ProductEntity> findDog() {
		List<ProductEntity> pDog = new ArrayList<>();
		productRepository.findByPetType("강아지").forEach(e -> pDog.add(e));
		return pDog;
	}

	public List<ProductEntity> findSmall() {
		List<ProductEntity> pSmall = new ArrayList<>();
		productRepository.findByPetType("소동물").forEach(e -> pSmall.add(e));
		return pSmall;
	}

	public List<ProductEntity> findByPetType(String petType) {
		return productRepository.findByPetType(petType);
	}

	public List<ProductEntity> getProductDetailByProductNum(String productNum) {
		return productRepository.getProductDetailByProductNum(productNum);
	}
	public List<ProductEntity> findByProductType(String productType) {
	return productRepository.findByProductType(productType);
	}

	// 강아지 건식사료
	public List<ProductEntity> DogDry() {
		pDog = productRepository.findByPetType("강아지");
		List<ProductEntity> dryDog = new ArrayList<>();		
		for (ProductEntity dogProduct : dry) {
				if (dogProduct.getPetType().equals("강아지")) {
				dryDog.add(dogProduct);
			}
		}
		return dryDog;
	}

	// 강아지 습식사료
	public List<ProductEntity> DogWet() {
		List<ProductEntity> wetDog = new ArrayList<>();
		for (ProductEntity dogProduct : wet) {
			if (dogProduct.getPetType().equals("강아지")) {
				wetDog.add(dogProduct);
			}
		}
		return wetDog;
	}

	// 강아지 장난감
	public List<ProductEntity> DogToy() {
		List<ProductEntity> toyDog = new ArrayList<>();
		for (ProductEntity dogProduct : toy) {
			if (dogProduct.getPetType().equals("강아지")) {
				toyDog.add(dogProduct);
			}
		}
		return toyDog;
	}

	// 강아지 배변용품
	public List<ProductEntity> DogPoo() {
		List<ProductEntity> pooDog = new ArrayList<>();
		for (ProductEntity dogProduct : poo) {
			if (dogProduct.getPetType().equals("강아지")) {
				pooDog.add(dogProduct);
			}
		}
		return pooDog;
	}

	// 강아지 간식
	public List<ProductEntity> DogSnack() {
		List<ProductEntity> snackDog = new ArrayList<>();
		for (ProductEntity dogProduct : snack) {
			if (dogProduct.getPetType().equals("강아지")) {
				snackDog.add(dogProduct);
			}
		}
		return snackDog;
	}

	// 고양이 건식사료
	public List<ProductEntity> CatDry() {
		List<ProductEntity> dryCat = new ArrayList<>();
		for (ProductEntity catProduct : dry) {
			if (catProduct.getPetType().equals("고양이")) {
				dryCat.add(catProduct);
			}
		}
		return dryCat;
	}

	// 고양이 습식사료
	public List<ProductEntity> CatWet() {
		List<ProductEntity> wetCat = new ArrayList<>();
		for (ProductEntity catProduct : wet) {
			if (catProduct.getPetType().equals("고양이")) {
				wetCat.add(catProduct);
			}
		}
		return wetCat;
	}

	// 고양이 장난감
	public List<ProductEntity> CatToy() {
		List<ProductEntity> toyCat = new ArrayList<>();
		for (ProductEntity catProduct : toy) {
			if (catProduct.getPetType().equals("고양이")) {
				toyCat.add(catProduct);
			}
		}
		return toyCat;
	}

	// 고양이 간식
	public List<ProductEntity> CatSnack() {
		List<ProductEntity> snackCat = new ArrayList<>();
		for (ProductEntity catProduct : snack) {
			if (catProduct.getPetType().equals("고양이")) {
				snackCat.add(catProduct);
			}
		}
		return snackCat;
	}

	// 고양이 스크래쳐
	public List<ProductEntity> CatScratcher() {
		List<ProductEntity> scratcherCat = new ArrayList<>();
		for (ProductEntity catProduct : scratcher) {
			if (catProduct.getPetType().equals("고양이")) {
				scratcherCat.add(catProduct);
			}
		}
		return scratcherCat;
	}

	// 고양이 모래
	public List<ProductEntity> CatSend() {
		List<ProductEntity> sendCat = new ArrayList<>();
		for (ProductEntity catProduct : send) {
			if (catProduct.getPetType().equals("고양이")) {
				sendCat.add(catProduct);
			}
		}
		return sendCat;
	}

	// 소동물 사료
	public List<ProductEntity> SmallFood() {
		List<ProductEntity> foodSmall = new ArrayList<>();
		for (ProductEntity smallProduct : food) {
			if (smallProduct.getPetType().equals("소동물")) {
				foodSmall.add(smallProduct);
			}
		}
		return foodSmall;
	}

	// 소동물 장난감
	public List<ProductEntity> SmallToy() {
		List<ProductEntity> toySmall = new ArrayList<>();
		for (ProductEntity smallProduct : toy) {
			if (smallProduct.getPetType().equals("소동물")) {
				toySmall.add(smallProduct);
			}
		}
		return toySmall;
	}

	// 소동물 베딩
	public List<ProductEntity> SmallBedding() {
		List<ProductEntity> beddingSmall = new ArrayList<>();
		for (ProductEntity smallProduct : bedding) {
			if (smallProduct.getPetType().equals("소동물")) {
				beddingSmall.add(smallProduct);
			}
		}
		return beddingSmall;
	}

	// 소동물 이갈이
	public List<ProductEntity> SmallTooth() {
		List<ProductEntity> toothSmall = new ArrayList<>();
		for (ProductEntity smallProduct : tooth) {
			if (smallProduct.getPetType().equals("소동물")) {
				toothSmall.add(smallProduct);
			}
		}
		return toothSmall;
	}

}
