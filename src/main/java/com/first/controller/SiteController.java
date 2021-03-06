package com.first.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.service.PageSet;
import com.first.service.SiteService;
import com.first.vo.SiteMenuVO;

@Controller
public class SiteController {

	@Resource(name = "SiteService")
	SiteService siteService;
	
	@RequestMapping(value = "/site/siteAdmin.go")
	public String siteAdminList(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		int countList = siteService.selectSiteMenuCnt(siteMenuVO);// 구현할 게시판의 게시물의 수를 위한 총 컬럼 구하기
		
		PageSet paging = new PageSet(siteMenuVO.getPageIndex(), countList, siteMenuVO.getRecordCountPerPage());
		siteMenuVO = (SiteMenuVO) paging.recordSet(siteMenuVO);// 레코드 배치 완료 메소드
		
		List<SiteMenuVO> resultList = siteService.selectSiteMenuList(siteMenuVO);
		
		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);
		
		return "site/siteMenu";
	}
	
	@RequestMapping(value = "/site/siteWrite.go")
	public String siteAdminWrite(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		
		if(!siteMenuVO.getSiteCode().equals("")) {

			SiteMenuVO resultMenuVO = siteService.selectSiteMenuOne(siteMenuVO);

			if(!resultMenuVO.getSiteCode().equals("")) { resultMenuVO.setPlaceRowArray(resultMenuVO.getPlaceRow().split(",")); }
			
		}

		//System.out.println("placerow : " + resultMenuVO.getPlaceRow());
		//System.out.println("placeWidth : " + resultMenuVO.getPlaceWidth());
		//System.out.println("templatetype : " + resultMenuVO.getTemplateType());
		
		map.addAttribute("formList", siteService.selectSiteMenuFormList());
		map.addAttribute("resultList", siteService.selectSiteMenuOne(siteMenuVO));
		
		return "site/siteWrite";
	}
	
	@RequestMapping(value = "/site/siteInsert.go")
	public String siteAdminInsert(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		int countRecord = siteService.selectSiteMenuCnt(siteMenuVO);
		
		System.out.println("placerow :" + siteMenuVO.getPlaceRow());
		System.out.println("placewidth :" + siteMenuVO.getPlaceWidth());
		
		if(countRecord != 0) {
			
			SiteMenuVO resultVO = siteService.selectSiteMenuRecent();
			//System.out.println("recenttitle :" + resultVO.getTitle() );
			if(!resultVO.getTitle().equals(siteMenuVO.getTitle())) {
			
				siteService.insertSiteMenu(siteMenuVO);
				this.siteMakeTable(siteMenuVO);
				
			}
			
		}else {
			
			siteService.insertSiteMenu(siteMenuVO);
			this.siteMakeTable(siteMenuVO);
			
		}
		
		
		
		return "redirect:/site/siteAdmin.go";
	}
	
	@RequestMapping(value = "/site/siteUpdate.go")
	public String siteAdminUpdate(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		siteService.updateSiteMenu(siteMenuVO);
		
		this.siteMakeTable(siteMenuVO);
		
		return "redirect:/site/siteAdmin.go";
	}
	
	@RequestMapping(value = "/site/siteDelete.go")
	public String siteAdminDelte(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		siteService.deleteSiteMenu(siteMenuVO);
		
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
			
			
			List<SiteMenuVO> fieldList = siteService.selectSiteField(siteMenuVO);
			
			String placename = "";
			
			for(int i=0; i<fieldList.size(); i++){
				
				placename += fieldList.get(i).getColumn_Name();
				if(i != fieldList.size()-1){ placename += ","; }
				
			}
			
			siteMenuVO.setPlaceName(placename);
			
			siteService.updateSiteField(siteMenuVO);
			
		}
	
}
