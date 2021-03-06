package com.first.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.vo.FormMenuVO;
import com.first.vo.SiteMenuVO;
import com.first.dao.SiteDAO;

@Service("SiteService")
public class SiteServiceImpl implements SiteService {

	@Resource(name = "SiteDAO")
	SiteDAO siteDAO;
	
	@Override
	public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuList(siteMenuVO);
	}

	@Override
	public int selectSiteMenuCnt(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuCnt(siteMenuVO);
	}

	@Override
	public String selectSiteMenuMax(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuMax(siteMenuVO);
	}

	@Override
	public SiteMenuVO selectSiteMenuOne(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuOne(siteMenuVO);
	}

	@Override
	public SiteMenuVO selectSiteMenuRecent() throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuRecent();
	}

	@Override
	public List<FormMenuVO> selectSiteMenuFormList() throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuFormList();
	}

	@Override
	public List<SiteMenuVO> selectSiteField(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteField(siteMenuVO);
	}

	@Override
	public void insertSiteMenu(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			
			String maxCode = siteDAO.selectSiteMenuMax(siteMenuVO);
			
			int temp = Integer.parseInt(maxCode.substring(5, maxCode.length())); temp ++;
			
			String newCode = "SITE_";
			int i = 1;
			
			while(temp > 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 15-i ; j++) { newCode += "0"; }
			newCode += Integer.toString(temp);
			
			siteMenuVO.setSiteCode(newCode);
			
			
		}catch(Exception e) {
			
			siteMenuVO.setSiteCode("SITE_000000000000001");
			
		}
		
		siteDAO.insertSiteMenu(siteMenuVO);
		siteDAO.insertSiteTable(siteMenuVO);
	}

	@Override
	public void deleteSiteMenu(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		siteDAO.deleteSiteMenu(siteMenuVO);
		siteDAO.deleteSiteTableDelete(siteMenuVO);
	}

	@Override
	public void updateSiteMenu(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		siteDAO.updateSiteMenu(siteMenuVO);
	}

	@Override
	public void updateSiteField(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		siteDAO.updateSiteField(siteMenuVO);
	}

}
