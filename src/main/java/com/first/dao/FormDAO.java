package com.first.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.first.vo.FormMenuVO;
import com.first.vo.SiteMenuVO;

@Repository("FormDAO")
public class FormDAO {

	@Inject //자바에서 지원하는 해당 데이터형식에 맞춰 데이터를 주입하는 어노테이션
	private SqlSession sqlSession;
	
	public List<FormMenuVO> selectFormMenuList(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.FormMapper.selectFormMenuList", formMenuVO );
	}

	
	public int selectFormMenuCnt(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.FormMapper.lookFormMenuCnt", formMenuVO );
	}


	public String selectFormMenuMax(FormMenuVO formMenuVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.FormMapper.selectFormMenuMax", formMenuVO );
	}

	
	public FormMenuVO selectFormMenuOne(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.FormMapper.lookFormMenuOne", formMenuVO );
	}


	public List<SiteMenuVO> selectFormMenuSiteList(FormMenuVO formMenuVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.FormMapper.selectFormMenuSiteList", formMenuVO);
	}

	
	public void insertFormMenu(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("com.example.mapper.FormMapper.insertFormMenu", formMenuVO);
	}

	
	public void deleteFormMenu(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.example.mapper.FormMapper.deleteFormMenu", formMenuVO);
	}
	
	public void deleteFormSiteMenu(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.example.mapper.FormMapper.deleteFormSiteMenu", formMenuVO);
	}

	
	public void updateFormMenu(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.example.mapper.FormMapper.updateFormMenu", formMenuVO);
	}
	
}
