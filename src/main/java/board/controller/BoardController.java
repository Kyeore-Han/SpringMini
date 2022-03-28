package board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.service.BoardService;

@Controller
@RequestMapping(value="board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="boardWriteForm")
	public String boardWriteForm(Model model) {
		//원글 - 1페이지, 첫번째 줄
		model.addAttribute("display", "/board/boardWriteForm.jsp");
		return "/index";
	}
	
	@PostMapping(value="boardWrite")
	@ResponseBody
	public void boardWrite(@RequestParam Map<String, String> map) { //subject, content
		boardService.boardWrite(map); //원글 - 1페이지, 첫번째 줄
	}
	
	@GetMapping(value="boardList")
	public String boardList(@RequestParam(required = false, defaultValue = "1") String pg, Model model){
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardList.jsp");
		return "/index";
	}
	
	@PostMapping(value="getBoardList")
	@ResponseBody
	public ModelAndView getBoardList(@RequestParam(required=false, defaultValue="1") String pg,
									 HttpSession session,
									 HttpServletResponse response){
		
		//1페이지당 5개씩
		List<BoardDTO> list = boardService.getBoardList(pg);
		
		//세션
		String memId = (String) session.getAttribute("memId");
		
		//페이징 처리
		BoardPaging boardPaging = boardService.boardPaging(pg);
		
		//조회수
		if(memId != null) {
			Cookie cookie = new Cookie("memHit", "0"); //쿠키 생성
			cookie.setMaxAge(30 * 60); //초 단위
			response.addCookie(cookie); //클라이언트에게 보내기
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memId", memId);
		mav.addObject("list",  list);
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView");
		
		return mav;
	}
	
	/*
	@PostMapping(value = "getBoardList")
	@ResponseBody
	public Map<String,Object> getBoardList(@RequestParam(required=false, defaultValue = "1") String pg){
		return boardService.getBoardList(pg);
	}
	*/
	
	@GetMapping(value="boardView")
	public String boardView(@RequestParam Map<String, String> map, Model model) {
		model.addAttribute("map", map);
		model.addAttribute("display", "/board/boardView.jsp");
		return "/index";
	}
	
	@PostMapping(value="getBoardView")
	@ResponseBody
	public ModelAndView getBoardView(@RequestParam String seq,
									 HttpSession session,
									 @CookieValue(name="memHit", required=false) Cookie cookie,
									 HttpServletResponse response) {
		
		//조회수
		if(cookie != null) {
			boardService.boardHit(seq); //조회수 증가
			
			cookie.setMaxAge(0); //쿠키 삭제
			response.addCookie(cookie); //클라이언트에게 보내기
		}
		
		BoardDTO boardDTO = boardService.getBoardView(seq);
		
		String memId = (String) session.getAttribute("memId");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardDTO", boardDTO);
		mav.addObject("memId", memId);
		mav.setViewName("jsonView");
		
		return mav;
	}
	
	@PostMapping(value="boardModifyForm")
	public String boardModifyForm(@RequestParam String seq, @RequestParam String pg, Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardModifyForm.jsp");
		
		return "/index";
	}
	
	@PostMapping(value="boardModify")
	@ResponseBody
	public void boardModify(@RequestParam Map<String, String> map) {
		boardService.boardModify(map);
	}
	
	@PostMapping(value="boardDelete")
	public ModelAndView boardDelete(@RequestParam String seq) {
		boardService.boardDelete(seq);
		
		return new ModelAndView("redirect:/board/boardList");
	}
	
	@PostMapping(value="boardReplyForm")
	public String boardReplyForm(@RequestParam String seq,
								 @RequestParam String pg,
								 Model model) {
		//답글 - 원글 페이지, 원글 밑
		model.addAttribute("pseq", seq); //원글번호
		model.addAttribute("pg", pg); //원글이 있는 페이지번호
		model.addAttribute("display", "/board/boardReplyForm.jsp");
		return "/index";
	}
	
	@PostMapping(value="boardReply")
	@ResponseBody
	public void boardReply(@RequestParam Map<String, String> map) { //pseq, subject, content
		boardService.boardReply(map); //답글 - 원글이 있는 페이지, 원글 밑
	}
	
	@PostMapping(value="getBoardSearchList")
	@ResponseBody
	public ModelAndView getBoardSearchList(@RequestParam Map<String, String> map, //pg(id=searchPg), searchOption, keyword
										   HttpSession session) { 
		
		//1페이지당 5개씩
		List<BoardDTO> list = boardService.getBoardSearchList(map);
		
		//세션
		String memId = (String) session.getAttribute("memId");
			
		//페이징 처리
		BoardPaging boardPaging = boardService.boardPaging(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memId", memId);
		mav.addObject("list",  list);
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView");
		
		return mav;
	}
	
}


	
	
	
	
	
	
	
	















