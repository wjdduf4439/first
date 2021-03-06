package com.first.service;

import java.util.List;

import com.first.vo.FormMenuVO;
import com.first.vo.SiteMenuVO;

public interface SiteService {

	public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO siteMenuVO) throws Exception;
	
	public int selectSiteMenuCnt(SiteMenuVO siteMenuVO) throws Exception;

	public SiteMenuVO selectSiteMenuOne(SiteMenuVO siteMenuVO) throws Exception;
	
	public SiteMenuVO selectSiteMenuRecent() throws Exception;
	
	public String selectSiteMenuMax(SiteMenuVO siteMenuVO) throws Exception;

	public List<SiteMenuVO> selectSiteField(SiteMenuVO siteMenuVO) throws Exception;
	
	public List<FormMenuVO> selectSiteMenuFormList() throws Exception;
	
	public void insertSiteMenu(SiteMenuVO siteMenuVO) throws Exception;

	public void deleteSiteMenu(SiteMenuVO siteMenuVO) throws Exception;

	public void updateSiteMenu(SiteMenuVO siteMenuVO) throws Exception;

	public void updateSiteField(SiteMenuVO siteMenuVO) throws Exception;
	
}
