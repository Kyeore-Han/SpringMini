package guestbook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guestbook.bean.GuestBookDTO;
import guestbook.bean.GuestBookPaging;
import guestbook.dao.GuestBookDAO;

@Service
public class GuestBookServiceImpl implements GuestBookService {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private GuestBookDAO guestBookDAO;
	@Autowired
	private GuestBookPaging guestBookPaging;
	
	@Override
	public void guestBookWrite(Map<String, String> map) {
		// DB
		//데이터
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		map.put("name", name);
		map.put("email", email);
		map.put("homepage", homepage);
		map.put("subject", subject);
		map.put("content", content);
		
		guestBookDAO.guestBookWrite(map);
		
		
	}

	@Override
	public List<GuestBookDTO> getGuestBookList(String pg) {
		//DB - 1페이지당 2개씩
		int endNum = Integer.parseInt(pg) * 2;
		int startNum = endNum - 1;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<GuestBookDTO> list = guestBookDAO.getGuestBookList(map);
		
		return list;

	}

	@Override
	public GuestBookPaging guestBookPaging(String pg) {
		int totalA = guestBookDAO.getTotalA(); //총글수
		
		guestBookPaging.setCurrentPage(Integer.parseInt(pg)); //현재 페이지
		guestBookPaging.setPageBlock(3);
		guestBookPaging.setPageSize(5);
		guestBookPaging.setTotalA(totalA);
		guestBookPaging.makePagingHTML();
		
		return guestBookPaging;
	}

}
