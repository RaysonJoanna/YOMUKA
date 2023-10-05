package com.yomuka.yomuka.main;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/yomuka/board")
public class BoardWebController {
	final BoardDAO dao;
	
	public BoardWebController(BoardDAO dao) {
		this.dao = dao;
	}

	@GetMapping("/notice")
	public String showNotice(@RequestParam(name="title", required=false) String title, 
			 @RequestParam(name="memberid", required=false) String memberid,
			 @RequestParam(name="notice", required=false) String notice,
			 @RequestParam(name="npage", required=false) String nPage,     	//열람대상 페이지 번호, 좌우측이동시에는 현재페이지 번호 							 							 								 
			 @RequestParam(name="npage2", required=false) String nPage2,    //페이지 구분 : b1:좌측10개페이지 이동, n1:우측10개페이지 이동, b2:1페이지 이동 n2:끝페이지 이동
			 Model m) throws Exception {
		
		int offset=0;						//조회대상 문서 쿼리 위치
		int limit=10;						//페이지당 조회대상 문서 갯수 (기본 10)
		int pagelimit=10;					//한번에 보여줄 페이지수
		int totalCount=0;					//전체 문서 갯수
		
		if(notice == null) {
			notice = "Y"; // 자유게시판
		}
			Paging paging = new Paging();  		// 페이징정보 객체
			BoardInq boardinq = new BoardInq();  		// 조회조건 객체
			
		    List<Board> freeBoardListAll = dao.getAllFreeBoards(title, memberid , notice);
		    totalCount = freeBoardListAll.size();
		    //쿼리 시작 위치
			offset = paging.calPaging(nPage, nPage2, offset, limit, pagelimit, totalCount);
			
			m.addAttribute("paging", paging);
			
			boardinq.setTitle(title);
			boardinq.setMemberid(memberid);
			
			m.addAttribute("boardinq", boardinq);
			List<Board> freeBoardList = dao.getAllFreeBoards(title, memberid, notice, offset, limit);
		    m.addAttribute("boards", freeBoardList);
		    
		    
		    return "main/NoticeBoard";
		
	}
	
	@GetMapping("/freeBoard")
	public String showFreeBoard(@RequestParam(name="title", required=false) String title, 
			 @RequestParam(name="memberid", required=false) String memberid,
			 @RequestParam(name="notice", required=false) String notice,
			 @RequestParam(name="npage", required=false) String nPage,     	//열람대상 페이지 번호, 좌우측이동시에는 현재페이지 번호 							 							 								 
			 @RequestParam(name="npage2", required=false) String nPage2,    //페이지 구분 : b1:좌측10개페이지 이동, n1:우측10개페이지 이동, b2:1페이지 이동 n2:끝페이지 이동
			 Model m) throws Exception {
		
		int offset=0;						//조회대상 문서 쿼리 위치
		int limit=10;						//페이지당 조회대상 문서 갯수 (기본 10)
		int pagelimit=10;					//한번에 보여줄 페이지수
		int totalCount=0;					//전체 문서 갯수
		if(notice == null) {
			notice = "N"; // 자유게시판
		}
			
		Paging paging = new Paging();  		// 페이징정보 객체
		BoardInq boardinq = new BoardInq();  		// 조회조건 객체
		
	    List<Board> freeBoardListAll = dao.getAllFreeBoards(title, memberid , notice);
	    totalCount = freeBoardListAll.size();
	    
	    //쿼리 시작 위치
		offset = paging.calPaging(nPage, nPage2, offset, limit, pagelimit, totalCount);
		
		m.addAttribute("paging", paging);
		
		boardinq.setTitle(title);
		boardinq.setMemberid(memberid);
		
		m.addAttribute("boardinq", boardinq);
		List<Board> freeBoardList = dao.getAllFreeBoards(title, memberid, notice, offset, limit);
	    m.addAttribute("boards", freeBoardList);
	    
	    
	    return "main/FreeBoard";
	}
	
	@GetMapping("/detail")
	public String boardDetail(@RequestParam("boardaid") int boardaid, Model model) {
	    try {
	        Board board = dao.getBoard(boardaid);
	        model.addAttribute("board", board);
	        
	        List<Comments> comments = dao.getCommentsByBoardAid(boardaid);
	        model.addAttribute("comments", comments);
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 에러 처리 로직
	    }
	    return "main/BoardDetail";  // 게시글 상세 정보를 보여줄 JSP 페이지 이름
	}
	
	@GetMapping("/addboard") //등록페이지
	public String addboard() {
		return "main/Boardadd";
	}
	
	@Autowired
	private BoardDAO boardDAO;

	@PostMapping("/newboard")
	public String newboard(Board board, 
			@RequestParam("imgFile") MultipartFile imgFile,
			RedirectAttributes redirectAttributes, HttpSession session) throws Exception { 	// 요건 빈값있는지 확인하는것
		 Member loggedUser = (Member)session.getAttribute("loggedUser");
		                    
		    if (loggedUser == null) {
		        redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
		        return "redirect:/yomuka/Login";
		    }
		    
		    board.setMemberid(loggedUser.getMemberid());
		    board.setNotice(loggedUser.getAdmin());
	    
	    if (board.getTitle().trim().isEmpty() ||
	    	board.getContent().trim().isEmpty()
	       ) {
	        redirectAttributes.addFlashAttribute("errorMessage", "제목과 내용은 작성해주세요.");
	        return "redirect:/yomuka/board/addboard";
	    }
	    	boardDAO.newBoard(board);
	    	return "redirect:/yomuka/board/freeBoard"; 
	}
	
	@PostMapping("/delboard")
	public String delboard(@RequestParam("boardaid") int boardaid) throws Exception {
		dao.deleteCommentsByBoardAid(boardaid);
		dao.delBoard(boardaid);
		return "redirect:/yomuka/board/notice";
	}
	
	@PostMapping("/upboard")
	public String upboard(Board board, @RequestParam("file") MultipartFile file, @RequestParam("boardaid") int boardaid) throws Exception {
	    dao.updateBoard(board, file);
	    return "redirect:/yomuka/board/detail?boardaid=" + boardaid;
	}
	
	@GetMapping("/comment")
	public String toComment() {
		return "main/BoardDetail";
	}
}
