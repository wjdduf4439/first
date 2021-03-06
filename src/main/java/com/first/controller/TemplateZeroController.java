package com.first.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.first.service.PageSet;
import com.first.service.SiteService;
import com.first.service.TemplateZeroService;
import com.first.vo.SiteMenuVO;
import com.first.vo.TemplateInfoVO;
import com.first.vo.TemplateOneVO;
import com.first.vo.TemplateZeroVO;

@Controller
public class TemplateZeroController {	

	@Resource(name ="TemplateZeroService")
	private TemplateZeroService templateZeroService; //해당 뷰의 상위 서비스
	
	@Resource(name ="SiteService")
	private SiteService siteService; //해당 뷰의 상위 서비스
	
	@RequestMapping("/template/templateZeroList.go")
	public String TemplateZeroList(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map, HttpServletRequest req) throws Exception {
		
		TemplateInfoVO templateInfoVO =  (TemplateInfoVO) req.getAttribute("templateInfoVO");
		templateZeroVO.setSiteTitle(templateInfoVO.getTitle());

		int countList = templateZeroService.selectTableRecordListCount(templateInfoVO);// 구현할 게시판의 게시물의 수를 위한 총 컬럼 구하기
		
		PageSet paging = new PageSet(templateZeroVO.getPageIndex(), countList, templateZeroVO.getRecordCountPerPage());
		templateZeroVO = (TemplateZeroVO) paging.recordSet(templateZeroVO);// 레코드 배치 완료 메소드
		
		List<TemplateInfoVO> fieldList = templateZeroService.selectTableFieldList(templateInfoVO);
		List<TemplateZeroVO> resultList = templateZeroService.selectTableRecordList(templateInfoVO);
		
		//System.out.println("첫번째 필드명 : " + fieldList.get(0).getColumn_Name());
		//System.out.println("첫번째 게시물 : " + resultList.get(0).getTitle());
		
		//공지 정보를 가져와서 list에 담는 작업
		List<TemplateZeroVO> noticeList = new ArrayList<TemplateZeroVO>(); // 공지 정보를 담을 list
		{
			
			SiteMenuVO mSiteMenuVO = new SiteMenuVO();									//해당 site의 정보를 가져와서 추출시키게 만들 vo
			mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());						//지금 실행되는 게시판의 코드를 가져와서 입력
			SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);	//해당 site의 내용을 가져옴 vo
			String[] nArray =  new String[1];											//site의 내용을 가져와서 코드만 담을 배열 선언
			
			
			if( null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("") ) { nArray = resultSiteMenuVO.getNoticeSwitch().split(","); }
			
			//System.out.println(resultSiteMenuVO.getNoticeSwitch());
			//System.out.println("narray :" + nArray[0]);
			
			TemplateZeroVO noticeZeroVO = new TemplateZeroVO(); 						//공지 게시물의 정보를 가져올 vo
			noticeZeroVO.setSiteCode(templateZeroVO.getSiteCode());						//공지 게시물의 사이트 코드만 가져와서 설정함
			
			for(int i = 0; i < nArray.length; i++) { 
				
				noticeZeroVO.setCode(nArray[i]);										//공지 게시물의 게시물 코드를 가져와서 설정
				noticeList.add(templateZeroService.selectTableRecordOne(noticeZeroVO));	//공지 게시물의 게시물 정보를 가져와서 공지 리스트에 넣음
				
			}
			
			//System.out.println("noticecode : "+ noticeList.get(0).getCode());
			//System.out.println("nList :" + noticeList.get(0).getCode());
			
			if( null != noticeList.get(0) ) { map.addAttribute("noticeList", noticeList); } //noticelist에 아무 vo가 없으면 null로 표시가 됨
			else { map.addAttribute("noticeList", null); }
		}
		
		map.addAttribute("fieldWidth", templateInfoVO.getFieldWidth());
		map.addAttribute("fieldList", fieldList);
		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);

		return "templateZero/templateZeroMenu";
	}
	
	@RequestMapping("/template/templateZeroWrite.go")
	public String TemplateZeroWrite(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map) throws Exception {
		

		
		TemplateZeroVO resultVO = templateZeroService.selectTableRecordOne(templateZeroVO);
		
		//System.out.println("설정된 페이지인덱스3 : " + templateZeroVO.getPageIndex());
		
		SiteMenuVO mSiteMenuVO = new SiteMenuVO();
		mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
		
		SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO); //notice 정보를 받기 위한 selectone
		String[] nArray =  new String[1];
		
		if( null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("") ) {
			
			nArray = resultSiteMenuVO.getNoticeSwitch().split(",");
			for(int i = 0; i < nArray.length; i++) { if( nArray[i].equals ( templateZeroVO.getCode() ) ){ resultVO.setNoticeSwitch("1"); } }
		
		}
		
		map.addAttribute("resultList", resultVO);
		
		return "templateZero/templateZeroWrite";
	}
	
	@RequestMapping("/template/templateZeroInsert.go")
	public String TemplateZeroInsert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map) throws Exception {

		
		int countRecord = templateZeroService.selectTableRecordListCount(templateZeroVO);
		
		TemplateZeroVO refreshVO = new TemplateZeroVO();
		refreshVO = templateZeroService.selectTableRecordRecent(templateZeroVO);
		
		if(countRecord != 0) {
			System.out.println("공지 여부" + templateZeroVO.getNoticeSwitch());
			
			refreshVO = templateZeroService.selectTableRecordRecent(templateZeroVO);
			
			if( !refreshVO.getContext().equals( templateZeroVO.getContext() ) ) { templateZeroService.insertTableRecord(templateZeroVO, multiRequest); } 
			//첫 글만 아니라면 rvo와 해당 게시글의 내용을 비교, 새로고침을 막기 위해서임
			
			if(templateZeroVO.getNoticeSwitch().equals("1")) {
				
				SiteMenuVO mSiteMenuVO = new SiteMenuVO();
				mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
				
				SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);
				String reNoticeSwitch = "";
				
				if(null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("")) { templateZeroVO.setNoticeSwitch(resultSiteMenuVO.getNoticeSwitch() + "," + templateZeroVO.getCode()); }
				else { reNoticeSwitch = templateZeroVO.getNoticeSwitch(); }
				
				System.out.println("설정된 공지번호" + reNoticeSwitch);
				
				resultSiteMenuVO.setNoticeSwitch(templateZeroVO.getCode());
				siteService.updateSiteMenu(resultSiteMenuVO);
				
			}
			
		} else {
			
			templateZeroService.insertTableRecord(templateZeroVO, multiRequest);
		
		}
		
		
		return "forward:/template/templateInfo.go";
	}
	
	@RequestMapping("/template/templateZeroUpdate.go")
	public String TemplateZeroUpdate(final MultipartHttpServletRequest multiRequest , @ModelAttribute("searchVO") TemplateZeroVO templateZeroVO ,ModelMap map) throws Exception {
		
		
		templateZeroService.updateTableRecord(templateZeroVO, multiRequest);
		
		//System.out.println("notice : " + templateZeroVO.getNoticeSwitch());
		//System.out.println("siteCode : " + templateZeroVO.getSiteCode());
		

		SiteMenuVO mSiteMenuVO = new SiteMenuVO();
		mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
		
		SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);
		
		if(templateZeroVO.getNoticeSwitch().equals("1")) {		//받아낸 notice가 켜져있으면 가져와서 sitetable에 갱신함
		
			resultSiteMenuVO.setNoticeSwitch(templateZeroVO.getCode());
			siteService.updateSiteMenu(resultSiteMenuVO);
			
		}else {			//받아낸 notice가 켜지지 않아있으면 가져와서 sitetable에서 제거함
			
			String[] nArray =  new String[1];
			String reNoticeSwitch = "";
			
			if(null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("")) { nArray = resultSiteMenuVO.getNoticeSwitch().split(","); }
			
			if(null != nArray[0]) {
				
				for(int i = 0; i < nArray.length; i++) { 
					
					if( !nArray[i].equals( templateZeroVO.getCode() ) ){ reNoticeSwitch += nArray[i]; if(i != nArray.length -1) { reNoticeSwitch += ","; } 	}
				
				}
				
			}
			
			
			resultSiteMenuVO.setNoticeSwitch(reNoticeSwitch);
			siteService.updateSiteMenu(resultSiteMenuVO);
			//System.out.println(reNoticeSwitch);
			//System.out.println("공지 비활성화됨");
			
			
		}
		
		return "forward:/template/templateInfo.go";
	}
	
	@RequestMapping("/template/templateZeroDelete.go")
	public String TemplateZeroDelete(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map) throws Exception {
		
		templateZeroService.deleteTableRecord(templateZeroVO);
		
		return "forward:/template/templateInfo.go";
	}
	
	/*public TemplateZeroVO TemplatePageReset(TemplateZeroVO templateZeroVO) {
		
		//pageIndex의 pageset함수에서 record가 계속 곱해지는 현상을 막기 위한 코드
		templateZeroVO.setPageIndex(templateZeroVO.getPageIndex()/templateZeroVO.getRecordCountPerPage());
		//System.out.println("처리된 인덱스 결과값 : " + templateZeroVO.getPageIndex());
		
		return templateZeroVO;
		
	}*/
	
}
