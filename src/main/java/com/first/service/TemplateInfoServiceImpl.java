package com.first.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.dao.TemplateInfoDAO;
import com.first.vo.TemplateInfoVO;

@Service("TemplateInfoService")
public class TemplateInfoServiceImpl implements TemplateInfoService {

	@Resource(name = "TemplateInfoDAO")
	TemplateInfoDAO templateInfoDAO; 

	@Override
	public TemplateInfoVO selectTableName(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateInfoDAO.selectTableName(templateInfoVO);
	}

}
