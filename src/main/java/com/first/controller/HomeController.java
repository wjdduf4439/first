package com.first.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.first.service.BoardExcelWriter;
import com.first.service.BoardService;
import com.first.service.FormService;
import com.first.service.PageSet;
import com.first.vo.BoardVO;
import com.first.vo.FileVO;
import com.first.vo.FormMenuVO;
import com.first.vo.SiteMenuVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Resource(name = "BoardService")
	private BoardService mBoardService;
	
	@Resource(name = "FormService")
	private FormService formService;

	@RequestMapping(value = "/AdminHeader.go")
	public String header(ModelMap map, FormMenuVO formMenuVO) throws Exception {
		
		List<FormMenuVO> formList = formService.selectFormMenuList(formMenuVO);
		
		map.addAttribute("formList", formList);
		
		List<SiteMenuVO> tempList = new ArrayList<SiteMenuVO>();
		List<SiteMenuVO> resultList  = new ArrayList<SiteMenuVO>();
		
		int i = 0;
		int i2 = 0;
		
		for(i = 0; i<  formList.size(); i++) {
			
			formList.get(i).setStartIndex(i*i2);
			
			resultList =  formService.selectFormMenuSiteList(formList.get(i));//해당 항목 안의 게시판 이름, 코드를 가져옴
			
			if(resultList.size() == 0) {
				
				tempList.add(new SiteMenuVO()); //결과 쿼리가 하나도 없으면 null에 해당하는 object를 주입
				
			}else {
				
				for(i2 = 0; i2 < resultList.size(); i2++) {
					
					tempList.add(resultList.get(i2));
					
				}
				
			}
		
			if(i2 == 0) { i2++; } //게시판이 없는 항목은 1로 고쳐주어서 index가 자연스럽게 동작하도록 하기
			
			if(i == 0 ) {
				
				if(i2 == 0) { formList.get(i).setEndIndex(i2); }
				
				else { formList.get(i).setEndIndex(i2 - 1); }
				
			} else {
				formList.get(i).setEndIndex(i*i2 + formList.get(i).getStartIndex()); //처음 계산하는 리스트가 아닐 바에야 마지막 리스트에 예외를 줄 필요는 없음
			}
			
		}
		
		map.addAttribute("sites", tempList);
		
		return "inc/header";
	}
	
	
	@RequestMapping(value = "/mainboard.go")
	public String mainboard(ModelMap map, @ModelAttribute("searchVO")BoardVO mboardVO, HttpServletRequest request) throws Exception {

		int countList = mBoardService.countBoardService();// 구현할 게시판의 게시물의 수를 위한 총 컬럼 구하기
		mboardVO.setDbCount(countList);

		// 페이징 레코드 배치를 위한 클래스 선언
		PageSet paging = new PageSet(mboardVO.getPageIndex(), countList, mboardVO.getRecordCountPerPage());
		mboardVO = (BoardVO) paging.recordSet(mboardVO);// 레코드 배치 완료 메소드

		List<BoardVO> resultList = mBoardService.listBoardService(mboardVO);
		// 반환된 리스트에는 매퍼에서 재선언된 vo의 형식에 sql값을 덧띄운 형태로 데이터가 반환되기 때문에 이전 오버라이드한 vo값은 초기화된다.
		// System.out.println(resultList.get(0).toString());// VO의 toString을 오버라이드하면 내가
		// 원하는 문자열을 구성 가능하다. 변수선언도 가능
		// System.out.println(countList);

		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);

		return "mainboard";
	}
	
	@RequestMapping(value = "/lookboard.go")
	public String lookboard(ModelMap map,  @ModelAttribute("searchVO")BoardVO mboardVO, HttpServletRequest request) throws Exception {

		// System.out.println(request.getParameter("b_id"));
		// 뷰에서 name값으로 매칭되는 태그를 찾아
		// request로 받는 요청정보는 server connect 태그에서 인코딩을 해줘야 한글이 깨지지 않고 그대로 전송된다.
		mboardVO.setB_code(Integer.parseInt(request.getParameter("b_codestring")));
		
		mBoardService.updatehitBoardService(mboardVO);//조회수 +1 서비스 호출
		BoardVO resultVO = mBoardService.lookBoardService(mboardVO);//게시물 보기 서비스 호출
		
		List<FileVO> resultFileVo = mBoardService.lookFileBoardService(resultVO);
		
		map.addAttribute("resultList", resultVO);
		map.addAttribute("resultFileList", resultFileVo);
		return "lookboard";
	}

	@RequestMapping(value = "/writeboard.go")
	public String writeboard(ModelMap map, HttpServletRequest req, @ModelAttribute("searchVO")BoardVO mboardVO) throws Exception {
		
		String mode;
		
		try{
			mode = req.getParameter("mode").toString();
		}catch(Exception e) {
			mode = "insert";
		}
		
		if(mode.equals("update")){
			
			BoardVO resultVO = mBoardService.lookBoardService(mboardVO);
			
			map.addAttribute("resultList", resultVO);
			
		}
		
		return "insertboard";
	}

	@RequestMapping(value = "/insertboard.go")
	public String insertboard(MultipartHttpServletRequest mtfRequest, @ModelAttribute("searchVO")BoardVO mboardVO) throws Exception {
		
		//file =  가져온 파일명의 멀티파트파일형 값
		mBoardService.insertBoardService(mboardVO);
		
		return "redirect:/mainboard.go";

	}
	
	@RequestMapping(value = "/deleteboard.go")
	public String deleteboard(@ModelAttribute("searchVO")BoardVO mboardVO) throws Exception {
		
		//file =  가져온 파일명의 멀티파트파일형 값
		mBoardService.deleteBoardService(mboardVO);
		
		return "redirect:/mainboard.go";

	}
	
	@RequestMapping(value = "/excelDown.go")
	public String excelDown(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("searchVO")BoardVO mboardVO) throws Exception {
	    // 게시판 목록조회

		String page = "";
		try { page = request.getParameter("page"); } catch (Exception e) { }
		if (page == null) {
			page = "1";
		}
		mboardVO.setCurrPage(Integer.parseInt(page));
		
		int countList = mBoardService.countBoardService();// 구현할 게시판의 게시물의 수를 위한 총 컬럼 구하기
		mboardVO.setDbCount(countList);
		
		PageSet paging = new PageSet(mboardVO.getPageIndex(), countList, mboardVO.getRecordCountPerPage());
		mboardVO = (BoardVO) paging.recordSet(mboardVO);// 레코드 배치 완료 메소드
		
	    List<BoardVO> list = mBoardService.listBoardService(mboardVO);
	    
	    BoardExcelWriter excelWriter = new BoardExcelWriter();
	    excelWriter.xlsxWriter(list);
	    
	    return "redirect:/mainboard.go";

	}
	
	@RequestMapping(value = "/chartboard.go")
	public String chartboard(@ModelAttribute("searchVO")BoardVO mboardVO) throws Exception {
		
		return "chartboard";

	}

}
