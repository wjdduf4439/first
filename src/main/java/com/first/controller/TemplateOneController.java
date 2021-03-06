package com.first.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.first.service.FileService;
import com.first.service.PageSet;
import com.first.service.TemplateOneService;
import com.first.vo.FileVO;
import com.first.vo.TemplateInfoVO;
import com.first.vo.TemplateOneVO;

@Controller
public class TemplateOneController {	

	@Autowired
	ServletContext c;
	
	@Resource(name ="TemplateOneService")
	private TemplateOneService templateOneService; //해당 뷰의 상위 서비스
	
	@Resource(name = "FileService")
	private FileService fileService;
	
	@RequestMapping("/template/templateOneList.go")
	public String TemplateOneList(@ModelAttribute("searchVO") TemplateOneVO templateOneVO  ,ModelMap map, HttpServletRequest req) throws Exception {
		
		TemplateInfoVO templateInfoVO =  (TemplateInfoVO) req.getAttribute("templateInfoVO");
		
		templateOneVO.setSiteTitle(templateInfoVO.getTitle());

		int countList = templateOneService.selectTableRecordListCount(templateInfoVO);// 구현할 게시판의 게시물의 수를 위한 총 컬럼 구하기
		
		PageSet paging = new PageSet(templateOneVO.getPageIndex(), countList, templateOneVO.getRecordCountPerPage());
		templateOneVO = (TemplateOneVO) paging.recordSet(templateOneVO);// 레코드 배치 완료 메소드
		
		List<TemplateInfoVO> fieldList = templateOneService.selectTableFieldList(templateInfoVO);
		List<TemplateOneVO> resultList = templateOneService.selectTableRecordList(templateInfoVO);
		
		//System.out.println("첫번째 코드" + resultList.get(0).getCode());
		//System.out.println("첫번째 제목" + resultList.get(0).getTitle());
		//System.out.println("첫번째 내용" + resultList.get(0).getContext());
		//System.out.println("첫번째 파일번호" + resultList.get(0).getAtchFileId());
		
		
		map.addAttribute("fieldWidth", templateInfoVO.getFieldWidth());
		map.addAttribute("fieldList", fieldList);
		map.addAttribute("messengerList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);

		return "templateZero/templateOneMenu";
	}
	
	
	@RequestMapping("/template/templateOneWrite.go")
	public String TemplateOneWrite(@ModelAttribute("searchVO") TemplateOneVO templateOneVO ,ModelMap map, String mcode, String msiteCode) throws Exception {
		

		//templateOneVO.setCode(mcode);
		//templateOneVO.setSiteCode(msiteCode);
		//System.out.println("전code는 " + templateOneVO.getCode());
		//System.out.println("전sitecode는 " + templateOneVO.getSiteCode());
		
		if(templateOneVO.getCode() == null) { templateOneVO.setCode("00000000000000000000");}
		if(templateOneVO.getSiteCode() == null) {templateOneVO.setCode(mcode); templateOneVO.setSiteCode(msiteCode);}
		
		/*System.out.println("적용된code는 " + templateOneVO.getCode());
		System.out.println("sitecode는 " + templateOneVO.getSiteCode());
		System.out.println("msitecode는 "+ msiteCode);*/
		
		TemplateOneVO writeVO = templateOneService.selectTableRecordOne(templateOneVO);
		
		//System.out.println("결과code는" + writeVO.getCode());
		//if(templateOneVO.getSiteCode() != null) {System.out.println("추출한 내용은" + writeVO.getContext());}
		
		map.addAttribute("writeVO", writeVO); // 메신저의 결과값은 writeList로 관리
		
		//map.addAttribute("context", templateOneVO.getContext());
		
		return "templateZero/templateOneWrite";
	}
	
	@RequestMapping("/template/templateOneInsert.go")
	public String TemplateOneInsert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") TemplateOneVO templateOneVO , ModelMap map) throws Exception {
		
		//System.out.println("insert 접속됨");
		//System.out.println("신호내용 : "+ refreshVO.getContext() );
		//System.out.println("파일위치 : " + c.getRealPath("/image"));
		
		int countRecord = templateOneService.selectTableRecordListCount(templateOneVO);
		
		TemplateOneVO refreshVO = new TemplateOneVO();
		
		if(countRecord != 0) {
			
			refreshVO = templateOneService.selectTableRecordRecent(templateOneVO);
			if( !templateOneVO.getContext().equals( refreshVO.getContext() ) ) { templateOneService.insertTableRecord(templateOneVO, multiRequest); } 
			//첫 글만 아니라면 rvo와 해당 게시글의 내용을 비교, 새로고침을 막기 위해서임
			
		} else { templateOneService.insertTableRecord(templateOneVO, multiRequest); }
			//첫글이라면 그냥 실행
		
		
		
		
		
		return "forward:/template/templateInfo.go";
	}
	
	@RequestMapping("/template/templateOneUpdate.go")
	public String TemplateOneUpdate(final MultipartHttpServletRequest multiRequest ,@ModelAttribute("searchVO") TemplateOneVO templateOneVO  ,ModelMap map) throws Exception {
		
		//System.out.println("update 접속됨");
		
		System.out.println("delValue : " + templateOneVO.getDelValue() );
		
		String[] mDelArray = templateOneVO.getDelValue().split(","); templateOneVO.setDelArray(mDelArray); // delarray의 배열을 지정, mybatis의 foreach 구문 활용
		
		templateOneService.updateTableRecord(templateOneVO, multiRequest);
		
		return "forward:/template/templateInfo.go";
	}
	
	@RequestMapping("/template/templateOneDelete.go")
	public String TemplateOneDelete(@ModelAttribute("searchVO") TemplateOneVO templateOneVO  ,ModelMap map) throws Exception {
		
		//System.out.println("삭제코드 : " + templateOneVO.getCode());
		
		
		TemplateOneVO deleteVO = templateOneService.selectTableRecordOne(templateOneVO);
		
		try {
			
			templateOneVO.setAtchFileId(deleteVO.getAtchFileId());
			System.out.println("컨트롤러 삭제할 fid : " + templateOneVO.getAtchFileId());
		
		}catch(Exception e) {
			
			System.out.println("파일 없음");
		}
		
		
		templateOneService.deleteTableRecord(templateOneVO);
		
		return "forward:/template/templateInfo.go";
	}
	
	
	@RequestMapping(value = "/template/templateOneAjaxReturn.go", produces = "application/json; charset=utf8")//ajax utf-8 처리
	public @ResponseBody Map<String, Object> TemplateOneAjaxReturn(@ModelAttribute("searchVO") TemplateOneVO templateOneVO, HttpServletRequest request, String code, String siteCode) throws Exception {
		
		templateOneVO.setSiteCode(siteCode);
		
		TemplateOneVO resultVO = templateOneService.selectTableRecordOne(templateOneVO);
		int fileCount = fileService.selectAtchFileCount(resultVO.getAtchFileId());
		
		List<FileVO> resultFileVO = fileService.selectFileMenuList(resultVO.getAtchFileId());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
        
		resultMap.put("context", resultVO.getContext());
		resultMap.put("atchFileId", resultVO.getAtchFileId());
		resultMap.put("fileCount", Integer.toString(fileCount));
		
		List<String> fcodeList = new ArrayList<String>();
		List<String> fnameList = new ArrayList<String>();
		
		for(int i = 0; i < resultFileVO.size(); i++) { fcodeList.add(resultFileVO.get(i).getCode()); fnameList.add(resultFileVO.get(i).getFname());  }
		
		resultMap.put("fcode",fcodeList );
		resultMap.put("fname",fnameList );
        
	    return resultMap;
		
	}
	
	
}
