package com.first.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.vo.FormMenuVO;
import com.first.vo.SiteMenuVO;
import com.first.dao.SiteDAO;

@Service("SiteService1")
public class SiteServiceImpl1 implements SiteService1 {

	@Resource(name = "SiteDAO")
	SiteDAO siteDAO;
	
	@Override
	public List<SiteMenuVO> selectSiteMenuList1(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuList(siteMenuVO);
	}

	@Override
	public int selectSiteMenuCnt1(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuCnt(siteMenuVO);
	}

	@Override
	public String selectSiteMenuMax1(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuMax(siteMenuVO);
	}

	@Override
	public SiteMenuVO selectSiteMenuOne1(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuOne(siteMenuVO);
	}

	@Override
	public List<FormMenuVO> selectSiteMenuFormList() throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuFormList();
	}

	@Override
	public List<SiteMenuVO> selectSiteField1(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteField(siteMenuVO);
	}

	@Override
	public void insertSiteMenu1(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			
			String maxCode = siteDAO.selectSiteMenuMax(siteMenuVO);
			System.out.println("maxcode : " + maxCode);
			
			
			int temp = Integer.parseInt(maxCode.substring(5, maxCode.length())); temp ++;

			System.out.println("temp : " + temp);
			
			String newCode = "SITE_";
			int i = 1;
			
			while(temp > 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 15-i ; j++) { newCode += "0"; }
			newCode += Integer.toString(temp);
			System.out.println("newcode : " + newCode);
			
			siteMenuVO.setSiteCode(newCode);
			
			
			
		}catch(Exception e) {
			
			siteMenuVO.setSiteCode("SITE_000000000000001");
			
		}
		
		siteDAO.insertSiteMenu(siteMenuVO);
		siteDAO.insertSiteTable(siteMenuVO);
	}

	@Override
	public void deleteSiteMenu1(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		siteDAO.deleteSiteMenu(siteMenuVO);
		siteDAO.deleteSiteTableDelete(siteMenuVO);
	}

	@Override
	public void updateSiteMenu1(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		siteDAO.updateSiteMenu(siteMenuVO);
	}

	@Override
	public void updateSiteField1(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		siteDAO.updateSiteField(siteMenuVO);
	}

}
