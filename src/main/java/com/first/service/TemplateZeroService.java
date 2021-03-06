package com.first.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.first.vo.TemplateInfoVO;
import com.first.vo.TemplateOneVO;
import com.first.vo.TemplateZeroVO;

public interface TemplateZeroService {

	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception;

	public List<TemplateZeroVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception;

	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception;
	
	public int selectTableRecordListCount(TemplateZeroVO templateZeroVO) throws Exception;
	
	public TemplateZeroVO selectTableRecordOne(TemplateZeroVO templateZeroVO) throws Exception;
	
	public TemplateZeroVO selectTableRecordRecent(TemplateZeroVO templateZeroVO) throws Exception;
	
	public void insertTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest ) throws Exception;
	
	public void updateTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest ) throws Exception;
	
	public void deleteTableRecord(TemplateZeroVO templateZeroVO) throws Exception;
	
}
