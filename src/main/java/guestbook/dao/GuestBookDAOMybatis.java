package guestbook.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import guestbook.bean.GuestBookDTO;

@Repository
public class GuestBookDAOMybatis implements GuestBookDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void guestBookWrite(Map<String, String> map) {
		sqlSession.insert("guestBookSQL.guestBookWrite", map);
	}

	@Override
	public List<GuestBookDTO> getGuestBookList(Map<String, Integer> map) {
		return sqlSession.selectList("guestBookSQL.getGuestBookList", map);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("guestBookSQL.getTotalA");
	}
	
	

}
