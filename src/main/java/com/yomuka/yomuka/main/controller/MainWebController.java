package com.yomuka.yomuka.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yomuka.yomuka.main.DAO.MemberDAO;
import com.yomuka.yomuka.main.DTO.Member;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/yomuka")
public class MainWebController {
	final MemberDAO dao;
		
	public MainWebController(MemberDAO dao) {
		this.dao = dao;
	}
		
	@GetMapping("/main")
	public String Main() {
		return "/main/Main";
		//localhost:8090/test/hello 호출 의미 >> WEB-INF/views/Hello.jsp
	}
		
	@GetMapping("/FindPage") // 비밀번호찾기페이지
	public String FindPage() {
		return "/main/Find";
	}
	
	@PostMapping("/Find")
	public String Find(@RequestParam(name = "memberid") String memberid,
					   @RequestParam(name = "name") String name,
					   Model m) {
		String isfind = dao.findUserPassword(memberid, name);
		
		if(isfind != null) {
			m.addAttribute("errorMessage", "당신의 비밀번호는 : ' " + isfind + " '입니다.");
	        return "/main/Find";
		}else {
			m.addAttribute("errorMessage", "아이디와 이름을 잘못입력하셨습니다.");
	        return "/main/Find";
		}
	}
	
	@GetMapping("/LoginPage") //로그인 첫페이지
	public String LoginPage() {
		return "/main/Login";
	}
	
	@PostMapping("/Login")
	public String Login(@RequestParam(name = "memberid") String memberid, 
						@RequestParam(name = "password") String password,
						Model m, HttpSession session) {
	
	    Member member = dao.login(memberid, password);
	    
	    if (member != null) {
	    	session.setAttribute("loggedUser", member);
	    	session.setAttribute("memberid", memberid);
	        return "redirect:/yomuka/main";
	    } else {
	    	
	        return "redirect:/yomuka/Login?error=true";
	    }
	}
	
	
	@GetMapping("/Login")
	public String GLogin(@RequestParam(required=false) Boolean error, Model m) {
	    if (Boolean.TRUE.equals(error)) {
	    	m.addAttribute("errorMessage", "아이디와 비밀번호가 일치하지않습니다.");
	    }
	    return "/main/Login";
	}
	
	@GetMapping("/Logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/yomuka/main";
	}
	
	@GetMapping("/Signup") //회원가입 첫 페이지
	public String Signup() {
		return "/main/Signup";
	}
		
	@PostMapping("/register") // 회원가입 매핑
	public String register(Member member, RedirectAttributes redirectAttributes) { 	// 요건 빈값있는지 확인하는것
	    if (member.getMemberid().trim().isEmpty() ||								// signup.jsp에도 빈값확인이있긴한데 이중체크하는거에요
	        member.getPassword().trim().isEmpty() ||
	        member.getName().trim().isEmpty()
	       ) {
	        redirectAttributes.addFlashAttribute("errorMessage", "입력하지 않는 값이있습니다. 전부 입력해주세요.");
	        return "redirect:/yomuka/Signup"; // 실패시 회원가입홈페이지 그대로나와요
	    }

	    boolean isRegistered = dao.Signup(member); 	// 이거는 아이디 중복체크 확인하는거에요
	    if (isRegistered) {							// 중복되면 아래 에러메시지가 나오면서 Signup페이지, 성공하면 Loginpage로 넘어갑니다.
	        return "redirect:/yomuka/LoginPage";
	    } else {
	        redirectAttributes.addFlashAttribute("errorMessage", "이미 사용중인 아이디입니다.");
	        return "redirect:/yomuka/Signup"; 
	    }
	}
	
	@GetMapping("/MyPagepage") //회원가입 첫 페이지
	public String MyPagepage() {
		return "/main/MyPage";
	}
	
	@PostMapping("/UpdateProfile")
	public String updateProfile(@RequestParam("password") String password,
	                            @RequestParam("name") String name,
	                            @RequestParam("phone") String phone,
	                            @RequestParam("email") String email,
	                            @RequestParam("address") String address,
	                            HttpSession session,
	                            RedirectAttributes redirectAttributes) {
	    Member member = (Member) session.getAttribute("loggedUser");
	    if (member != null) {
	        boolean isUpdated = dao.changeMyPage(member.getMemberid(), password, name, phone, email, address);
	        if (isUpdated) {
	            return "redirect:/yomuka/afterUpdate";
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "프로필 업데이트 중 오류가 발생했습니다. 다시 시도해주세요.");
	            return "redirect:/yomuka/main";
	        }
	    } else {
	        // 사용자가 로그인하지 않았습니다.
	        return "redirect:/yomuka/LoginPage";
	    }
	}
	
	@GetMapping("/afterUpdate")
	public String afterUpdate(HttpSession session, RedirectAttributes redirectAttributes) {
	    session.invalidate();
	    redirectAttributes.addFlashAttribute("successMessage", "성공적으로 수정 되었습니다! 다시 로그인해주세요");
	    return "redirect:/yomuka/main";
	}

	@GetMapping("/ProfilePage") //사진등록 첫페이지
	public String ProfilePage() {
		return "/main/ProfilePage";
	}
	
	@PostMapping("/ProfileChange")
	public String ProfileChange(@RequestParam("imgFile") MultipartFile imgFile, HttpSession session, RedirectAttributes redirectAttributes) {
	    Member member = (Member) session.getAttribute("loggedUser");

	    if (member != null) {
	    	String contentType = imgFile.getContentType();
	        if (contentType == null || !(contentType.equals("image/png") || contentType.equals("image/jpeg"))) {
	            redirectAttributes.addFlashAttribute("errorMessage", ".png , .jpg 파일만 등록하실수 있습니다.");
	            return "redirect:/yomuka/ProfilePage"; 
	        }
	        String memberid = member.getMemberid();
	        String profileURL = member.getProfile();

	        boolean isProfileUpdated = dao.profileChange(memberid, imgFile, profileURL);

	        if (isProfileUpdated) {
	        	session.invalidate();
	            redirectAttributes.addFlashAttribute("successMessage", "프로필 변경완료. 5초뒤 로그인 해주세요");
	            return "redirect:/yomuka/main"; 
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "프로필 이미지 변경 중 오류가 발생했습니다. 다시 시도해주세요.");
	            return "redirect:/yomuka/ProfilePage";
	        }
	    } else {
	        redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 프로필 이미지를 변경할 수 있습니다.");
	        return "redirect:/yomuka/LoginPage";
	    }
	}
	
}