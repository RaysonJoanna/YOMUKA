package com.yomuka.yomuka.care;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/yomuka")
public class TestWebController {

	final CareDAO dao;
	private final CarePoint carePoint;
	private CarePointService carePS;

    @Autowired
    public TestWebController(CareDAO dao, CarePoint carePoint, CarePointService carePS) {
        this.dao = dao;
        this.carePoint = carePoint;
        this.carePS = carePS;
    }
	
	@GetMapping("/caremain")
	public String careMain() {
		return "/care/CareMain";
	}
	
	
	@RequestMapping(value = "/CareDetail", method = RequestMethod.GET)
    public String careDetail () {
        return "/care/CareDetail"; // 반환하는 문자열은 View의 이름입니다.
    }

    @RequestMapping(value = "/CareDetail/", method = RequestMethod.GET, params = "imageType")
    public String careDetailPage(@RequestParam("imageType") String imageType, Model model) {
        
        model.addAttribute("imageType", imageType);

        return "/care/CareDetail";
    }   

	@GetMapping("/points")
	public String Points() {	
		return "/care/Points";
	}
	
	@GetMapping("/CareMainDaily")	
	public String careMainDaily() {
		return "/care/CareMainDaily";
	}
	
	@GetMapping("/CareMainDaily/{imageType}")
    public String careMainDailyImageType(@PathVariable String imageType, Model model) {
        return "redirect:/CareDetail/?imageType=" + imageType;
    }
	
	@GetMapping("/CareMainWeekly")
	public String careMainWeekly() {
		return "/care/CareMainWeekly";
	}
	
	@GetMapping("/CareMainMonthly")
	public String careMainMonthly() {
		return "/care/CareMainMonthly";
	}
	
//	@PostMapping("/registerImage")
//    @ResponseBody
//    public PointsBean registerImage(@RequestBody CarePoint request) {
//        
//        PointsBean updatedPoints = carePS.getUpdatedPoints(request.getMemberid());
//
//        return updatedPoints;
//    }

}

	
