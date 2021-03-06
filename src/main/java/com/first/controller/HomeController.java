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
			
			resultList =  formService.selectFormMenuSiteList(formList.get(i));//�ش� �׸� ���� �Խ��� �̸�, �ڵ带 ������
			
			if(resultList.size() == 0) {
				
				tempList.add(new SiteMenuVO()); //��� ������ �ϳ��� ������ null�� �ش��ϴ� object�� ����
				
			}else {
				
				for(i2 = 0; i2 < resultList.size(); i2++) {
					
					tempList.add(resultList.get(i2));
					
				}
				
			}
		
			if(i2 == 0) { i2++; } //�Խ����� ���� �׸��� 1�� �����־ index�� �ڿ������� �����ϵ��� �ϱ�
			
			if(i == 0 ) {
				
				if(i2 == 0) { formList.get(i).setEndIndex(i2); }
				
				else { formList.get(i).setEndIndex(i2 - 1); }
				
			} else {
				formList.get(i).setEndIndex(i*i2 + formList.get(i).getStartIndex()); //ó�� ����ϴ� ����Ʈ�� �ƴ� �ٿ��� ������ ����Ʈ�� ���ܸ� �� �ʿ�� ����
			}
			
		}
		
		map.addAttribute("sites", tempList);
		
		return "inc/header";
	}
	
	
	@RequestMapping(value = "/mainboard.go")
	public String mainboard(ModelMap map, @ModelAttribute("searchVO")BoardVO mboardVO, HttpServletRequest request) throws Exception {

		int countList = mBoardService.countBoardService();// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
		mboardVO.setDbCount(countList);

		// ����¡ ���ڵ� ��ġ�� ���� Ŭ���� ����
		PageSet paging = new PageSet(mboardVO.getPageIndex(), countList, mboardVO.getRecordCountPerPage());
		mboardVO = (BoardVO) paging.recordSet(mboardVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�

		List<BoardVO> resultList = mBoardService.listBoardService(mboardVO);
		// ��ȯ�� ����Ʈ���� ���ۿ��� �缱��� vo�� ���Ŀ� sql���� ����� ���·� �����Ͱ� ��ȯ�Ǳ� ������ ���� �������̵��� vo���� �ʱ�ȭ�ȴ�.
		// System.out.println(resultList.get(0).toString());// VO�� toString�� �������̵��ϸ� ����
		// ���ϴ� ���ڿ��� ���� �����ϴ�. �������� ����
		// System.out.println(countList);

		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);

		return "mainboard";
	}
	
	@RequestMapping(value = "/lookboard.go")
	public String lookboard(ModelMap map,  @ModelAttribute("searchVO")BoardVO mboardVO, HttpServletRequest request) throws Exception {

		// System.out.println(request.getParameter("b_id"));
		// �信�� name������ ��Ī�Ǵ� �±׸� ã��
		// request�� �޴� ��û������ server connect �±׿��� ���ڵ��� ����� �ѱ��� ������ �ʰ� �״�� ���۵ȴ�.
		mboardVO.setB_code(Integer.parseInt(request.getParameter("b_codestring")));
		
		mBoardService.updatehitBoardService(mboardVO);//��ȸ�� +1 ���� ȣ��
		BoardVO resultVO = mBoardService.lookBoardService(mboardVO);//�Խù� ���� ���� ȣ��
		
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
		
		//file =  ������ ���ϸ��� ��Ƽ��Ʈ������ ��
		mBoardService.insertBoardService(mboardVO);
		
		return "redirect:/mainboard.go";

	}
	
	@RequestMapping(value = "/deleteboard.go")
	public String deleteboard(@ModelAttribute("searchVO")BoardVO mboardVO) throws Exception {
		
		//file =  ������ ���ϸ��� ��Ƽ��Ʈ������ ��
		mBoardService.deleteBoardService(mboardVO);
		
		return "redirect:/mainboard.go";

	}
	
	@RequestMapping(value = "/excelDown.go")
	public String excelDown(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("searchVO")BoardVO mboardVO) throws Exception {
	    // �Խ��� �����ȸ

		String page = "";
		try { page = request.getParameter("page"); } catch (Exception e) { }
		if (page == null) {
			page = "1";
		}
		mboardVO.setCurrPage(Integer.parseInt(page));
		
		int countList = mBoardService.countBoardService();// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
		mboardVO.setDbCount(countList);
		
		PageSet paging = new PageSet(mboardVO.getPageIndex(), countList, mboardVO.getRecordCountPerPage());
		mboardVO = (BoardVO) paging.recordSet(mboardVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
		
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
