package com.yomuka.yomuka.petc.controller;

import com.yomuka.yomuka.petc.DTO.Pet;
import com.yomuka.yomuka.petc.service.PetcService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
//http://localhost:8085/yomuka/petc/first
@RequestMapping("/yomuka/petc")
@SessionAttributes("memberid")
public class PetcController {
    final PetcService petS;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PetcController(PetcService petS) {
        this.petS = petS;
    }

    @Value("${petc.imgdir}")
    String imgdir;

    // 반려동물 정보 등록 1단계
    @GetMapping("/register1")
    public String regPet1(){
        return "/petc/pet_reg_1";
    }

    // 반려동물 정보 등록 2단계 화면으로
    @GetMapping("/register2")
    public String regNext(@RequestParam("petKind") String kind, Model m,
    					  @SessionAttribute String memberid) {
       
    	List<String> breedList = new ArrayList<>();
        List<String> dieseaseList = new ArrayList<>();     
        String allPetName = "";
        
        try{
        	allPetName = petS.getAllPetName(memberid);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        try{
            breedList = petS.getList(kind);
            dieseaseList = petS.getdList(kind);
        } catch (Exception e) {
            e.printStackTrace();
        }

        m.addAttribute("allPetName",allPetName);
        m.addAttribute("kind",kind);
        m.addAttribute("breedList",breedList);
        m.addAttribute("dieseaseList",dieseaseList);

        return "/petc/pet_reg_2";
    }

    // 반려동물 정보 등록
    @PostMapping("/add")
    public String regPet2(Pet pet,
                          @RequestParam("file") MultipartFile file,
                          @SessionAttribute String memberid, Model m) {

    	String kind = pet.getKind();
    	pet.setmemberid(memberid);
	
        try {
            if(!file.isEmpty()) {
				File dest = new File(imgdir + "/" + file.getOriginalFilename());
				if(!dest.exists()) {
	    			file.transferTo(dest);
	    			pet.setPetProfile(dest.getName());
	    		}
			}
            

            petS.addPetInfo(pet, kind);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("반려동물 등록 실패");
        }

        List<Pet> petList = null;   // 전체 리스트

        try{
            petList = petS.getAllPet(memberid);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        m.addAttribute("petList", petList);

        return "/petc/pet_mgr_main";
    }

    // 반려동물 정보 수정
    @GetMapping("/update")
    public String updatePet(@RequestParam("petNum") int petNum,
                            @SessionAttribute String memberid, Model m) throws SQLException{
    	
    	Pet pet = petS.petByNum(petNum);
    	String kind = pet.getKind();
    	
    	List<String> breedList = new ArrayList<>();
        List<String> dieseaseList = new ArrayList<>();
        
        try{
            breedList = petS.getList(kind);
            dieseaseList = petS.getdList(kind);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	m.addAttribute("pet",pet);
        m.addAttribute("breedList",breedList);
        m.addAttribute("dieseaseList",dieseaseList);
        
        return "/petc/pet_update";
    }
    
    
    @PostMapping("/process-update")
    public String processUpdate(Pet pet,
            @RequestParam("file") MultipartFile file,
            @SessionAttribute String memberid, Model m) {
    	
		String kind = pet.getKind();
		
		try {
			if(!file.isEmpty()) {
				File dest = new File(imgdir + "/" + file.getOriginalFilename());
				file.transferTo(dest);
				pet.setPetProfile(dest.getName());
			}
			petS.updatePetInfo(pet);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("반려동물 등록 실패");
		}
		
		List<Pet> petList = null;   // 전체 리스트
		
		try{
		petList = petS.getAllPet(memberid);
		} catch(Exception e){
		e.printStackTrace();
		}
		
		m.addAttribute("petList", petList);
		
		return "/petc/pet_mgr_main";
	}

    // 반려동물 정보 삭제 (현재 걍 똥임)
    @PostMapping("/delete")
    public String deletePet(Pet pet,
                            @SessionAttribute String memberid, Model m){
        try{
            petS.deletePetInfo(pet);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("반려동물 삭제 실패");
        }
        
        List<Pet> petList = null;   // 전체 리스트

        try{
            petList = petS.getAllPet(memberid);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        m.addAttribute("petList", petList);

        return "/petc/pet_mgr_main";
    }

    // 반려동물 정보 가져오기
    @GetMapping
    public String getPetInfo(@SessionAttribute String memberid, Model m){
    	String url = "";
    	

        List<Pet> petList = null;   // 전체 리스트

        try{
            petList = petS.getAllPet(memberid);
        } catch(Exception e){
            e.printStackTrace();
        }
        m.addAttribute("petList", petList);
        m.addAttribute("memberid",memberid);
    
         url = "/petc/pet_mgr_main";
    	
    	return url;
    }

    // 세션 유저네임 강제 설정 화면
    @GetMapping("/first")
    public String first(){
        return "/petc/memberid";
    }

}

