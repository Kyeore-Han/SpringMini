package imageboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.dao.ImageboardDAO;

@Service
public class ImageboardServiceImpl implements ImageboardService {
	@Autowired
	private ImageboardDAO imageboardDAO;
	@Autowired
	private ImageboardPaging imageboardPaging;

	@Override
	public void imageboardWrite(ImageboardDTO imageboardDTO) {
		imageboardDAO.imageboardWrite(imageboardDTO);
	}
	
	@Override
	public List<ImageboardDTO> getImageboardList(String pg) {
		//DB - 1페이지당 3개씩 꺼내오도록 rn의 값 작은 값 : startNum / 큰 값 : endNum
		int endNum = Integer.parseInt(pg) * 3;  // String 타입은 계산을 못해서 int로 변환해서 계산해준다.
		int startNum = endNum - 2;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<ImageboardDTO> list = imageboardDAO.getImageboardList(map);
		
		return list;
	}
	

	@Override
	public ImageboardPaging imageboardPaging(String pg) {
		int totalA = imageboardDAO.getTotalA(); //총글수
		
		imageboardPaging.setCurrentPage(Integer.parseInt(pg));
		imageboardPaging.setPageBlock(3);
		imageboardPaging.setPageSize(3);
		imageboardPaging.setTotalA(totalA);
		imageboardPaging.makePagingHTML();
		
		return imageboardPaging;
	}

	@Override
	public ImageboardDTO getImageboardView(String seq) {
		return imageboardDAO.getImageboardView(seq);
	}

	@Override
	public void imageboardDelete(String[] check) {
		//Mapper에서는 Map으로 받고 있으므로 배열을 Map으로 변환해준다.
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check", check);
		imageboardDAO.imageboardDelete(map);
		
	}
	
}
