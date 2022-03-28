package guestbook.service;

import java.util.List;
import java.util.Map;

import guestbook.bean.GuestBookDTO;
import guestbook.bean.GuestBookPaging;

public interface GuestBookService {

	public void guestBookWrite(Map<String, String> map);

	public List<GuestBookDTO> getGuestBookList(String pg);

	public GuestBookPaging guestBookPaging(String pg);

}
