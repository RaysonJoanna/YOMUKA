package com.yomuka.yomuka.ord.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yomuka.yomuka.ord.entity.GudokEntity;
import com.yomuka.yomuka.ord.repository.CustomerRepository;

@Service
public class CustomerService {
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public List<GudokEntity> findGudokByCustomerId(String memberId) {
        return customerRepository.findByMemberId(memberId);
    }

	//저장
	public GudokEntity setGudok(String memberId, String productImg,String productName,String productPrice, String productQuantity, 
			 LocalDate gudokstartdate, String totalPrice){
		gudokstartdate = LocalDate.now();
		GudokEntity gudokEntity = GudokEntity.builder()
		            .memberId(memberId)
		            .productImg(productImg)
		            .productName(productName)
		            .productPrice(productPrice)
		            .productQuantity(productQuantity)
		            .gudokStartDate(gudokstartdate)
		            .totalPrice(totalPrice)
		            .build();
		 customerRepository.save(gudokEntity);
		 
		return gudokEntity;
	}
	
	public void deleteGudokByProductName(String productName) {
		customerRepository.deleteByProductName(productName);
	}
	
}




