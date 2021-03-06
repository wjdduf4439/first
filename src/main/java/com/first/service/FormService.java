package com.first.service;

import java.util.List;

import com.first.vo.FormMenuVO;
import com.first.vo.SiteMenuVO;

public interface FormService {

	public List<FormMenuVO> selectFormMenuList(FormMenuVO formMenuVO) throws Exception;
	
	public int selectFormMenuCnt(FormMenuVO formMenuVO) throws Exception;

	public FormMenuVO selectFormMenuOne(FormMenuVO formMenuVO) throws Exception;
	
	public String selectFormMenuMax(FormMenuVO formMenuVO) throws Exception;
	
	public List<SiteMenuVO> selectFormMenuSiteList(FormMenuVO formMenuVO) throws Exception;
	
	public void insertFormMenu(FormMenuVO formMenuVO) throws Exception;

	public void deleteFormMenu(FormMenuVO formMenuVO) throws Exception;

	public void updateFormMenu(FormMenuVO formMenuVO) throws Exception;

	
}
