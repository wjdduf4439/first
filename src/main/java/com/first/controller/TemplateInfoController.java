package com.first.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.service.TemplateInfoService;
import com.first.vo.TemplateInfoVO;

@Controller
public class TemplateInfoController {

	@Resource(name ="TemplateInfoService")
	private TemplateInfoService templateInfoService; //해당 뷰의 상위 서비스
	
	@RequestMapping("/template/templateInfo.go")
	public String templeteInfo(@ModelAttribute("searchVO") TemplateInfoVO templateInfoVO ,ModelMap map) throws Exception {
		
		String[] fieldNumber;	//보여줄 필드번호 지정하는 구문
		String[] fieldName;		//보여줄 필드명을 지정하는 구문
		String[] fieldWidth = {"50"};	//보여줄 필드너비를 지정하는 구문
		
		TemplateInfoVO tableName = templateInfoService.selectTableName(templateInfoVO);
		templateInfoVO.setSiteCode(tableName.getSiteCode());
		templateInfoVO.setTitle(tableName.getTitle());
		
		fieldNumber = tableName.getPlaceRow().toString().split(",");//보여줄 필드명을 지정하는 구문
		templateInfoVO.setFieldNumber(fieldNumber);//배열로 변환 후 vo에 삽입
		
		fieldName = tableName.getPlaceName().toString().split(",");//보여줄 필드명을 지정하는 구문
		templateInfoVO.setFieldName(fieldName);//배열로 변환 후 vo에 삽입
		
		try { //비 게시판 형태의 게시판은 width자료가 필요 없으니 예외처리 가능
		
			fieldWidth = tableName.getPlaceWidth().toString().split(",");//보여줄 필드너비를 지정하는 구문
			
		}catch(NullPointerException e) {
			
		}
		
		templateInfoVO.setFieldWidth(fieldWidth);//배열로 변환 후 vo에 삽입
		
		
		map.addAttribute("templateInfoVO", templateInfoVO);
		
		//System.out.println("templatetype : " + tableName.getTemplateType());
		System.out.println("info에서 받은 인덱스 : " + templateInfoVO.getPageIndex());
		
		if(tableName.getTemplateType().equals("0")){
			
			return "forward:/template/templateZeroList.go";
			
		}else if(tableName.getTemplateType().equals("1")){
			
			return "forward:/template/templateOneList.go";
			
		}  
		
		return "templateZero/templateZeroMenu";
	}
	
}
