package guestbook.dao;

import java.util.List;
import java.util.Map;

import guestbook.bean.GuestBookDTO;


public interface GuestBookDAO {

	public void guestBookWrite(Map<String, String> map);

	public List<GuestBookDTO> getGuestBookList(Map<String, Integer> map);

	public int getTotalA();

}
