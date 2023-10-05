package com.yomuka.yomuka.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/yomuka/comment")
public class CommentsController {
	final CommentsDAO dao;
	
	public CommentsController(CommentsDAO dao) {
		this.dao = dao;
	}
	
	@PostMapping("/addComment")
	public String addcomment(Comments c, HttpServletRequest request) throws Exception {
	    int boardaid = c.getBoardaid(); // Comments 객체에서 boardaid 값을 가져옵니다.
	    
	    dao.newComments(c); // 댓글을 새로 추가합니다.

	    String redirect_url = "redirect:/yomuka/board/detail?boardaid=" + boardaid;
	    return redirect_url;
	}
	
	@PostMapping("/delComment")
	public String delComment(@RequestParam("commentsaid") int commentsaid, @RequestParam("boardaid") int boardaid) throws Exception {
	    dao.delcomment(commentsaid);
	    return "redirect:/yomuka/board/detail?boardaid=" + boardaid;
	}

}
