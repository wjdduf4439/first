package com.first.service;

import java.util.List;

import com.first.vo.FormMenuVO;
import com.first.vo.SiteMenuVO;

public interface SiteService1 {

	public List<SiteMenuVO> selectSiteMenuList1(SiteMenuVO siteMenuVO) throws Exception;
	
	public int selectSiteMenuCnt1(SiteMenuVO siteMenuVO) throws Exception;

	public SiteMenuVO selectSiteMenuOne1(SiteMenuVO siteMenuVO) throws Exception;
	
	public String selectSiteMenuMax1(SiteMenuVO siteMenuVO) throws Exception;

	public List<SiteMenuVO> selectSiteField1(SiteMenuVO siteMenuVO) throws Exception;
	
	public List<FormMenuVO> selectSiteMenuFormList() throws Exception;
	
	public void insertSiteMenu1(SiteMenuVO siteMenuVO) throws Exception;

	public void deleteSiteMenu1(SiteMenuVO siteMenuVO) throws Exception;

	public void updateSiteMenu1(SiteMenuVO siteMenuVO) throws Exception;

	public void updateSiteField1(SiteMenuVO siteMenuVO) throws Exception;
	
}
