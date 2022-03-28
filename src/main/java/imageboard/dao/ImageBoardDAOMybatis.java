package imageboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import imageboard.bean.ImageboardDTO;

@Repository
@Transactional
public class ImageBoardDAOMybatis implements ImageboardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void imageboardWrite(ImageboardDTO imageboardDTO) {
		sqlSession.insert("imageboardSQL.imageboardWrite", imageboardDTO);
	}
	
	@Override
	public List<ImageboardDTO> getImageboardList(Map<String, Integer> map) {
		return sqlSession.selectList("imageboardSQL.getImageboardList", map);

	}

	@Override
	public ImageboardDTO getImageboardView(String seq) {
		return sqlSession.selectOne("imageboardSQL.getImageboardView", Integer.parseInt(seq));
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("imageboardSQL.getTotalA");
	}

	@Override
	public void imageboardDelete(Map<String, String[]> map) {
		sqlSession.delete("imageboardSQL.imageboardDelete2", map);
	}
	
	
		
}
