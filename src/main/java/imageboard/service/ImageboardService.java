package imageboard.service;

import java.util.List;

import board.bean.BoardDTO;
import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;

public interface ImageboardService {

	public void imageboardWrite(ImageboardDTO imageboardDTO);

	public ImageboardPaging imageboardPaging(String pg);

	public List<ImageboardDTO> getImageboardList(String pg);

	public ImageboardDTO getImageboardView(String seq);

	public void imageboardDelete(String[] check);
	
}
