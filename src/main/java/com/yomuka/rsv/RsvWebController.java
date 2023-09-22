package com.yomuka.rsv;

import com.yomuka.main.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yomuka.main.Member;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("yomuka/rsv")
public class RsvWebController {
		
	final RsvDAO dao;	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	LocalDate now = LocalDate.now();
	LocalDate nowDB30 = now.plusDays(-30);	//조회 From 기본값 30일전
	LocalDate nowDA7 = now.plusDays(7);		//조회 To 기본값 7일후
		
	String nowDB30S = nowDB30.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	String nowDA7S = nowDA7.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
	@Autowired
	public RsvWebController(RsvDAO dao) {
		this.dao = dao;
	}
	
	//예약내용 신규 등록 및 수정
	@PutMapping("/reserve")	
	@ResponseBody				//ajax request에 선언된 datatype 으로 리턴
	public String reserveSave(@RequestParam(name="reserveid") String reserveid,
						  	@RequestParam(name="hospitalid") String hospitalid,
						  	@RequestParam(name="rsvdate") String rsvdate,
						  	@RequestParam(name="rsvtime") String rsvtime,
						  	@RequestParam(name="content") String content,
						  	@RequestParam(name="memberid") String memberid) {
		
		System.out.println("### 예약내용 입력 Start ### reserveid : " + reserveid);
		
		int resultupdate=1;
		String result;
								
		try {		
					
			resultupdate = dao.reserveSave(reserveid, hospitalid, rsvdate, rsvtime, content, memberid );
			if(resultupdate == 1) {
				result ="정상적으로 저장되었습니다";
			}else {
				result = "예약내용 입력이 비정상 처리되었습니다";
			}
						
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("예약내용 입력시 문제 발생!!");
			//m.addAttribute("error", "진료결과 입력이 정상적으로 처리되지 않았습니다!!");
			result = "예약내용 입력이 정상적으로 처리되지 않았습니다!!";
		}
				
		return result;
	}
				
		
	//예약현황 명세조회 
	@GetMapping("/rsvListStart")
	public String rsvListStart(@RequestParam(name="From", required=false) String fromDate, 
							 @RequestParam(name="To", required=false) String toDate, 
							 @RequestParam(name="hospitalid", required=false) String hospitalid,
							 @RequestParam(name="memberid", required=false) String memberid,
							 @RequestParam(name="npage", required=false) String nPage,     	//열람대상 페이지 번호, 좌우측이동시에는 현재페이지 번호 							 							 								 
							 @RequestParam(name="npage2", required=false) String nPage2,    //페이지 구분 : b1:좌측10개페이지 이동, n1:우측10개페이지 이동, b2:1페이지 이동 n2:끝페이지 이동
							 Model m,
							 HttpSession session) {
				
		logger.info("### 예약현황 명세조회 ### nPage : " + nPage);
		
		int offset=0;						//조회대상 문서 쿼리 위치
		int limit=2;						//페이지당 조회대상 문서 갯수 (기본 10)
		int pagelimit=10;					//한번에 보여줄 페이지수
		int totalCount=0;					//전체 문서 갯수		
			
		Paging paging = new Paging();  		// 페이징정보 객체
		RsvInqStart rsvInqStart = new RsvInqStart();  		// 조회조건 객체
		List<Hospital> hospitalList = null;  		// 조회조건 : 병원명세
		List<Member> memberList = null;  		// 조회조건 : 회원명세
		
		List<RsvListStart> rsvListStart = null;  		// 해당 페이지 목록
		List<RsvListStart> rsvListStartAll = null;  	// 전체목록
				
		if(fromDate == null) {
			fromDate = nowDB30S;			//From 값 미지정시 기본값 지정
		}
		if(toDate == null) {
			toDate = nowDA7S;				//To 값 미지정시 기본값 지정
		}	
		if(hospitalid == null) {
			hospitalid = "All";				//병원id 값 미지정시 기본값 지정 (전체병원 조회)
		}
		if(memberid == null) {
			Member mem = (Member) session.getAttribute("loggedUser");
			memberid = mem.getMemberid();			//멤버id 값 미지정시 기본값 지정 (로그인 사용자 예약현황만 조회)
		}									//향후 세션에서 로그인사용자 id를 기본값으로 지정
			
		try {			
			logger.info("rsv Start parm : " + fromDate + " " + toDate);
			
			rsvListStartAll = dao.getRsvListStart(fromDate, toDate, hospitalid, memberid);
			totalCount = rsvListStartAll.size();			//조회대상 문서 전체 갯수
			
			logger.info("totalCount : " + totalCount);
			//쿼리 시작 위치
			offset = paging.calPaging(nPage, nPage2, offset, limit, pagelimit, totalCount);
			
			m.addAttribute("paging", paging);  		//페이징
			
			rsvInqStart.setFromDate(fromDate);
			rsvInqStart.setToDate(toDate);
			rsvInqStart.setNhospitalid(hospitalid);
			rsvInqStart.setNmemberid(memberid);
			
			m.addAttribute("rsvInqStart", rsvInqStart);		//조회조건		
			
			//쿼리 시작 위치를 이용해 쿼리 수행
			rsvListStart = dao.getRsvListStart(fromDate, toDate, hospitalid, memberid, offset, limit);			
			m.addAttribute("rsvListStart", rsvListStart);		//예약현황 명세
			
			hospitalList = dao.getHospitalListAll();		//병원명세-조회조건용
			m.addAttribute("hospitalList", hospitalList);	
			
			memberList = dao.getMemberListAll();		//회원명세-조회조건용
			m.addAttribute("memberList", memberList);	
			
			System.out.println("### totalCount : " + totalCount);			
			System.out.println("### hospitalid : " + hospitalid);
			System.out.println("### memberid : " + memberid);
			
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("예약현황 조회시 문제 발생!!");
			m.addAttribute("error", "예약현황 조회가 정상적으로 처리되지 않았습니다!!");			
		}
		
		return "rsvListStart";
	}
	
	//진료결과 입력
	@PutMapping("/result")	
	@ResponseBody				//ajax request에 선언된 datatype 으로 리턴
	public String resultSave(@RequestParam(name="reserveid") String reserveid,
						  @RequestParam(name="resultgubun") String resultgubun,
						  @RequestParam(name="resultcontent") String resultcontent,
						  @RequestParam(name="resultdate") String resultdate) {
		
		System.out.println("### 진료기록 입력 ### reserveid : " + reserveid);
		
		int resultupdate=1;
		String result;
								
		try {		
					
			resultupdate = dao.resultSave(reserveid, resultgubun, resultcontent, resultdate);
			if(resultupdate == 1) {
				result ="정상적으로 저장되었습니다";
			}else {
				result = "진료결과 입력이 비정상 처리되었습니다";
			}
						
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("진료결과 입력시 문제 발생!!");
			//m.addAttribute("error", "진료결과 입력이 정상적으로 처리되지 않았습니다!!");
			result = "진료결과 입력이 정상적으로 처리되지 않았습니다!!";
		}
				
		return result;
	}
	
	//진료기록 명세조회 
	@GetMapping("/rsvListEnd")
	public String rsvListEnd(@RequestParam(name="From", required=false) String fromDate, 
							 @RequestParam(name="To", required=false) String toDate, 
							 @RequestParam(name="npage", required=false) String nPage,     	//열람대상 페이지 번호, 좌우측이동시에는 현재페이지 번호 							 							 								 
							 @RequestParam(name="npage2", required=false) String nPage2,    //페이지 구분 : b1:좌측10개페이지 이동, n1:우측10개페이지 이동, b2:1페이지 이동 n2:끝페이지 이동
							 Model m) {
				
		logger.info("### 진료기록 명세조회 ### nPage : " + nPage);
		
		int offset=0;						//조회대상 문서 쿼리 위치
		int limit=2;						//페이지당 조회대상 문서 갯수 (기본 10)
		int pagelimit=10;					//한번에 보여줄 페이지수
		int totalCount=0;					//전체 문서 갯수		
			
		Paging paging = new Paging();  		// 페이징정보 객체
		RsvInqEnd rsvInqEnd = new RsvInqEnd();  		// 조회조건 객체
		List<RsvListEnd> rsvListEnd = null;  
		List<RsvListEnd> rsvListEndAll = null;  		// 전체목록
				
		if(fromDate == null) {
			fromDate = nowDB30S;		//From 값 미지정시 기본값 지정
		}
		if(toDate == null) {
			toDate = nowDA7S;			//To 값 미지정시 기본값 지정
		}
				
			
		try {			
			logger.info("parm : " + fromDate + " " + toDate);
			
			rsvListEndAll = dao.getRsvListEnd(fromDate, toDate);
			totalCount = rsvListEndAll.size();			//조회대상 문서 전체 갯수
			
			logger.info("totalCount : " + totalCount);
			//쿼리 시작 위치
			offset = paging.calPaging(nPage, nPage2, offset, limit, pagelimit, totalCount);
			
			m.addAttribute("paging", paging);  		//페이징
			
			rsvInqEnd.setFromDate(fromDate);
			rsvInqEnd.setToDate(toDate);
			
			m.addAttribute("rsvInqEnd", rsvInqEnd);		//조회조건		
					
			//쿼리 시작 위치를 이용해 쿼리 수행
			rsvListEnd = dao.getRsvListEnd(fromDate, toDate, offset, limit);
			m.addAttribute("rsvListEnd", rsvListEnd);
									
			System.out.println("### totalCount : " + totalCount);			
			
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("진료기록조회시 문제 발생!!");
			m.addAttribute("error", "진료기록조회가 정상적으로 처리되지 않았습니다!!");			
		}
		
		return "rsvListEnd";
	}
	
	
}
