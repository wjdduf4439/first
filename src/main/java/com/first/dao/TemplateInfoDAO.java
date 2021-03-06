package com.first.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.first.vo.TemplateInfoVO;

@Repository("TemplateInfoDAO")
public class TemplateInfoDAO {

	@Inject //자바에서 지원하는 해당 데이터형식에 맞춰 데이터를 주입하는 어노테이션
	private SqlSession sqlSession;
	
	public List<?> selectMoreMenuList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.TemplateInfo.selectMoreMenuList");
	}

	public TemplateInfoVO selectTableName(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateInfo.selectTableName", templateInfoVO );
	}

	public List<?> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.TemplateInfo.selectTableFieldList", templateInfoVO );
	}

	public List<TemplateInfoVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.TemplateInfo.selectTableRecordList", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateInfo.selectTableRecordListCount", templateInfoVO );
	}
	
}
