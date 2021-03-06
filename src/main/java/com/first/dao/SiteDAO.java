package com.first.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.first.vo.FormMenuVO;
import com.first.vo.SiteMenuVO;

@Repository("SiteDAO")
public class SiteDAO {

	@Inject //자바에서 지원하는 해당 데이터형식에 맞춰 데이터를 주입하는 어노테이션
	private SqlSession sqlSession;
	
	public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.SiteMapper.selectSiteMenuList", siteMenuVO );
	}

	
	public int selectSiteMenuCnt(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.SiteMapper.lookSiteMenuCnt", siteMenuVO );
	}


	public String selectSiteMenuMax(SiteMenuVO siteMenuVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.SiteMapper.selectSiteMenuMax", siteMenuVO );
	}

	
	public SiteMenuVO selectSiteMenuOne(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.SiteMapper.lookSiteMenuOne", siteMenuVO );
	}


	public SiteMenuVO selectSiteMenuRecent() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.SiteMapper.selectSiteMenuRecent");
	}


	public List<FormMenuVO> selectSiteMenuFormList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.SiteMapper.selectSiteMenuFormList");
	}


	public List<SiteMenuVO> selectSiteField(SiteMenuVO siteMenuVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.SiteMapper.selectSiteField", siteMenuVO);
	}

	
	public void insertSiteMenu(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("com.example.mapper.SiteMapper.insertSiteMenu", siteMenuVO);
	}


	public void insertSiteTable(SiteMenuVO siteMenuVO) {
		// TODO Auto-generated method stub
		sqlSession.update("com.example.mapper.SiteMapper.insertSiteTable", siteMenuVO);
	}

	
	public void deleteSiteMenu(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.example.mapper.SiteMapper.deleteSiteMenu", siteMenuVO);
	}
	
	public void deleteSiteTableDelete(SiteMenuVO siteMenuVO) {
		// TODO Auto-generated method stub
		sqlSession.update("com.example.mapper.SiteMapper.deleteSiteTableDelete", siteMenuVO);
	}
	
	public void updateSiteMenu(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.example.mapper.SiteMapper.updateSiteMenu", siteMenuVO);
	}

	public void updateSiteField(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.example.mapper.SiteMapper.updateSiteField", siteMenuVO);
	}


	
	
}
