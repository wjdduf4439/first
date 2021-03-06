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
	
	//sitecontroller�̶� ��ȸ���� ������
	
	@RequestMapping(value = "/site/siteAdmin1.go") 
	public String siteAdminList1(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		int countList = siteService.selectSiteMenuCnt1(siteMenuVO);// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
		
		PageSet paging = new PageSet(siteMenuVO.getPageIndex(), countList, siteMenuVO.getRecordCountPerPage());
		siteMenuVO = (SiteMenuVO) paging.recordSet(siteMenuVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
		
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
	
	//�� �Լ��� ���� site ������ �ٸ�, ������ ������ �Խ����� �����Ѵ�.
	
	@RequestMapping(value = "/site/siteInsert1.go")
	public String siteAdminInsert1(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		siteService.insertSiteMenu1(siteMenuVO);
		
		this.siteMakeTable(siteMenuVO);
		
		return "redirect:/site/siteAdmin.go";
	}
	
	@RequestMapping(value = "/site/siteUpdate1.go")
	public String siteAdminUpdate1(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		System.out.println("update �õ�");
		
		siteService.updateSiteMenu1(siteMenuVO);
		
		this.siteMakeTable(siteMenuVO);
		
		return "redirect:/site/siteAdmin.go";
	}
	
	@RequestMapping(value = "/site/siteDelete1.go")
	public String siteAdminDelte1(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		siteService.deleteSiteMenu1(siteMenuVO);
		
		return "redirect:/site/siteAdmin.go";
	}
	
	//��ȣ�� ǥ�õ� �迭 �׸���� �Ӽ������� ġȯ�� �� �ְ� �ϴ� �޼���
		public void siteMakeTable(SiteMenuVO siteMenuVO) throws Exception{
			
			try {
				
				String[] fieldNumber = siteMenuVO.getPlaceRow().split(",");//������ �ʵ���� �����ϴ� ����
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
