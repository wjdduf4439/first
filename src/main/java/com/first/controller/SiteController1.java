package com.first.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.service.PageSet;
import com.first.service.SiteService;
import com.first.service.SiteService1;
import com.first.vo.SiteMenuVO;

@Controller
public class SiteController1 {

	@Resource(name = "SiteService1")
	SiteService1 siteService;
	
	//sitecontroller이랑 조회논리를 공유함
	
	@RequestMapping(value = "/site/siteAdmin1.go") 
	public String siteAdminList1(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		int countList = siteService.selectSiteMenuCnt1(siteMenuVO);// 구현할 게시판의 게시물의 수를 위한 총 컬럼 구하기
		
		PageSet paging = new PageSet(siteMenuVO.getPageIndex(), countList, siteMenuVO.getRecordCountPerPage());
		siteMenuVO = (SiteMenuVO) paging.recordSet(siteMenuVO);// 레코드 배치 완료 메소드
		
		List<SiteMenuVO> resultList = siteService.selectSiteMenuList1(siteMenuVO);
		
		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);
		
		return "site/siteMenu";
	}
	
	@RequestMapping(value = "/site/siteWrite1.go")
	public String siteAdminWrite1(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		map.addAttribute("formList", siteService.selectSiteMenuFormList());
		map.addAttribute("resultList", siteService.selectSiteMenuOne1(siteMenuVO));
		
		return "site/siteWrite";
	}
	
	//세 함수는 각각 site 유형만 다른, 동일한 형태의 게시판을 생성한다.
	
	@RequestMapping(value = "/site/siteInsert1.go")
	public String siteAdminInsert1(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		siteService.insertSiteMenu1(siteMenuVO);
		
		this.siteMakeTable(siteMenuVO);
		
		return "redirect:/site/siteAdmin.go";
	}
	
	@RequestMapping(value = "/site/siteUpdate1.go")
	public String siteAdminUpdate1(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		System.out.println("update 시도");
		
		siteService.updateSiteMenu1(siteMenuVO);
		
		this.siteMakeTable(siteMenuVO);
		
		return "redirect:/site/siteAdmin.go";
	}
	
	@RequestMapping(value = "/site/siteDelete1.go")
	public String siteAdminDelte1(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		siteService.deleteSiteMenu1(siteMenuVO);
		
		return "redirect:/site/siteAdmin.go";
	}
	
	//번호로 표시된 배열 항목들을 속성명으로 치환할 수 있게 하는 메서드
		public void siteMakeTable(SiteMenuVO siteMenuVO) throws Exception{
			
			try {
				
				String[] fieldNumber = siteMenuVO.getPlaceRow().split(",");//보여줄 필드명을 지정하는 구문
				siteMenuVO.setFieldNumber(fieldNumber);
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			List<SiteMenuVO> fieldList = siteService.selectSiteField1(siteMenuVO);
			
			String placename = "";
			
			for(int i=0; i<fieldList.size(); i++){
				
				placename += fieldList.get(i).getColumn_Name();
				if(i != fieldList.size()-1){ placename += ","; }
				
			}
			
			siteMenuVO.setPlaceName(placename);
			
			siteService.updateSiteField1(siteMenuVO);
			
		}
	
}
