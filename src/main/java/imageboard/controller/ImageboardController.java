package imageboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.service.ImageboardService;

@Controller
@RequestMapping(value="imageboard")
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;
	
	@GetMapping(value="imageboardWriteForm")
	public String imageboardWriteForm(Model model) {
		//원글 - 1페이지, 첫번째 줄
		model.addAttribute("display", "/imageboard/imageboardWriteForm.jsp");
		return "/index";
	}
	
//	// name="img" 1개인 경우
//	@PostMapping(value="imageboardWrite")
//	@ResponseBody
//	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
//								  @RequestParam MultipartFile img,
//								  HttpSession session) {
		
//		String filePath = "C:\\Spring\\workspace\\SpringProject\\src\\main\\webapp\\storage";  //가상폴더
//		String fileName = img.getOriginalFilename();
//		
//		File file = new File(filePath, fileName); //파일 생성
//		
//		//파일복사
//		try {
//			FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
//		//실제 폴더
//		String filePath = session.getServletContext().getRealPath("/storage");
//		String fileName = img.getOriginalFilename();
//		
//		File file = new File(filePath, fileName);
//		try {
//			img.transferTo(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		imageboardDTO.setImage1(fileName);
//		
//		//DB에 등록
//		imageboardService.imageboardWrite(imageboardDTO);
//		
//	}
	
/*	// name="img" 2개 이상인 경우
	@PostMapping(value="imageboardWrite")
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								  @RequestParam MultipartFile[] img,
								  HttpSession session) {
		//실제 폴더
		String filePath = session.getServletContext().getRealPath("/storage");
		String fileName;
		
		File file; //파일 여러개 생성을 위한 객체 생성
		
		if(img[0] != null) {
		fileName = img[0].getOriginalFilename();
		file = new File(filePath, fileName);
		
		try {
			img[0].transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageboardDTO.setImage1(fileName);
		}else {
		imageboardDTO.setImage1("");
		}
		
		if(img[1] != null) {
		fileName = img[1].getOriginalFilename();
		file = new File(filePath, fileName);
		
		try {
			img[1].transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageboardDTO.setImage2(fileName);
		}else {
		imageboardDTO.setImage2("");
		}
		
		//DB DTO들고
		imageboardService.imageboardWrite(imageboardDTO);

	}
	*/
	
	//드래그해서 한번에 여러개의 파일을 선택했을 때
	@PostMapping(value="imageboardWrite")
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
							    @RequestParam("img[]") List<MultipartFile> list,
								  HttpSession session) {
		
		//실제 폴더
		String filePath = session.getServletContext().getRealPath("/storage");
		String fileName;
		File file; //파일 여러개 생성을 위한 객체 생성
		
		for(MultipartFile img : list) {
			fileName = img.getOriginalFilename();
			file = new File(filePath, fileName);
			
			try {
				img.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			imageboardDTO.setImage1(fileName);
			imageboardDTO.setImage2("");
			
			//DB 파일 하나 생성하면 그 파일 보내고 파일 하나 생성하면 그파일 보내는 식으로 for문 안으로 들어와야 한다.
			imageboardService.imageboardWrite(imageboardDTO);

		}//for
		
	}
	@GetMapping(value="imageboardList")
	public String imageboardList(@RequestParam(required = false, defaultValue = "1") String pg, Model model){
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardList.jsp");
		return "/index";
	}
	
	
	@PostMapping(value="getImageboardList")
	@ResponseBody
	public ModelAndView getImageboardList(@RequestParam(required=false, defaultValue="1") String pg){
		
		//DB로 가서 1페이지당 3개씩 가져와야 한다.
		List<ImageboardDTO> list = imageboardService.getImageboardList(pg);
		
		//세션
		//String memId = (String) session.getAttribute("memId");
		
		//페이징 처리
		ImageboardPaging imageboardPaging = imageboardService.imageboardPaging(pg);
		
		//조회수
		//if(memId != null) {
		//	Cookie cookie = new Cookie("memHit", "0"); //쿠키 생성
		//	cookie.setMaxAge(30 * 60); //초 단위
		//	response.addCookie(cookie); //클라이언트에게 보내기
		//}
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",  list);
		mav.addObject("imageboardPaging", imageboardPaging);
		mav.setViewName("jsonView");
		
		return mav;
	}
	
	@GetMapping(value="imageboardView")
	public String imageboardView(@RequestParam String seq, @RequestParam String pg, Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardView.jsp");
		return "/index";

	}
	
	@PostMapping(value="getImageboardView")
	@ResponseBody
	public ImageboardDTO getImageboardView(@RequestParam String seq) {
		ImageboardDTO imageboardDTO = imageboardService.getImageboardView(seq);
		return imageboardDTO;
	}
	
	@GetMapping(value="imageboardDelete")
	public ModelAndView imageboardDelete(String[] check) {
		imageboardService.imageboardDelete(check);
		return new ModelAndView("redirect:/imageboard/imageboardList");
		
	}

	
	
}
