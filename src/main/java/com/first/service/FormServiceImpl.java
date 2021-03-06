package com.first.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.vo.FormMenuVO;
import com.first.vo.SiteMenuVO;
import com.first.dao.FormDAO;

@Service("FormService")
public class FormServiceImpl implements FormService {

	@Resource(name = "FormDAO")
	FormDAO formDAO;
	
	@Override
	public List<FormMenuVO> selectFormMenuList(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuList(fiteMenuVO);
	}

	@Override
	public int selectFormMenuCnt(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuCnt(fiteMenuVO);
	}

	@Override
	public String selectFormMenuMax(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuMax(fiteMenuVO);
	}

	@Override
	public FormMenuVO selectFormMenuOne(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuOne(fiteMenuVO);
	}

	@Override
	public List<SiteMenuVO> selectFormMenuSiteList(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuSiteList(formMenuVO);
	}

	@Override
	public void insertFormMenu(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			
			String maxCode = formDAO.selectFormMenuMax(fiteMenuVO);
			
			int temp = Integer.parseInt(maxCode.substring(5, maxCode.length())); temp ++;
			
			String newCode = "FORM_";
			int i = 1;
			
			while(temp > 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 15-i ; j++) { newCode += "0"; }
			newCode += Integer.toString(temp);
			
			fiteMenuVO.setFormCode(newCode);
			
			
		}catch(Exception e) {
			
			fiteMenuVO.setFormCode("FORM_000000000000001");
			
		}
		
		formDAO.insertFormMenu(fiteMenuVO);
	}

	@Override
	public void deleteFormMenu(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		formDAO.deleteFormSiteMenu(fiteMenuVO);
		formDAO.deleteFormMenu(fiteMenuVO);
	}

	@Override
	public void updateFormMenu(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		formDAO.updateFormMenu(fiteMenuVO);
	}

}
