package guestbook.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import guestbook.bean.GuestBookDTO;
import guestbook.bean.GuestBookPaging;
import guestbook.service.GuestBookService;

@Controller
@RequestMapping(value="guestbook")
public class GuestBookController {
	@Autowired
	private GuestBookService guestBookService;
	
	@GetMapping(value="guestBookWriteForm")
	public String guestBookWriteForm(Model model) {
		model.addAttribute("display", "/guestbook/guestBookWriteForm.jsp");
		return "/index";
	}
	
	@PostMapping(value="guestBookWrite")
	@ResponseBody
	public void guestBookWrite(@RequestParam Map<String, String> map) {
		guestBookService.guestBookWrite(map); //
	}
	
	@GetMapping(value="guestBookList")
	public String guestBookList(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/guestbook/guestBookList.jsp");
		return "/index";
	}
	
	@PostMapping(value="getGuestBookList")
	@ResponseBody
	public ModelAndView getGuestBookList(@RequestParam(required=false, defaultValue="1") String pg,
									 HttpServletResponse response){
		
		//1페이지당 2개씩
		List<GuestBookDTO> list = guestBookService.getGuestBookList(pg);

		//페이징 처리
		GuestBookPaging guestBookPaging = guestBookService.guestBookPaging(pg);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",  list);
		mav.addObject("guestBookPaging", guestBookPaging);
		mav.setViewName("jsonView");
		
		return mav;
	}
}
