package com.first.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.first.vo.TemplateInfoVO;
import com.first.vo.TemplateZeroVO;

@Repository("TemplateZeroDAO")
public class TemplateZeroDAO {
	
	@Inject //자바에서 지원하는 해당 데이터형식에 맞춰 데이터를 주입하는 어노테이션
	private SqlSession sqlSession;

	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.TemplateZero.selectTableFieldList", templateInfoVO );
	}

	public List<TemplateZeroVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.TemplateZero.selectTableRecordList", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateZero.selectTableRecordListCount", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateZero.selectTableRecordListCount", templateZeroVO );
	}

	public String selectTableAtchFileId(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateZero.selectTableAtchFileId", templateZeroVO );
	}
	
	public String countTableAtchFileId(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateZero.countTableAtchFileId", templateZeroVO );
	}

	public String selectTableRecordListMax(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateZero.selectTableRecordListMax", templateZeroVO );
	}
	
	public String selectTableFileListMax(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateZero.selectTableFileListMax", templateZeroVO );
	}

	public TemplateZeroVO selectTableRecordOne(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateZero.selectTableRecordOne", templateZeroVO );
	}

	public TemplateZeroVO selectTableRecordRecent(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateZero.selectTableRecordRecent", templateZeroVO );
	}

	public void insertTableRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.example.mapper.TemplateZero.insertTableRecord", templateZeroVO );
	}
	
	public void updateTableRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.example.mapper.TemplateZero.updateTableRecord", templateZeroVO );
	}
	
	public void deleteTableRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.example.mapper.TemplateZero.deleteTableRecord", templateZeroVO );
	}
	
	public void deleteFileRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.example.mapper.TemplateZero.deleteFileRecord", templateZeroVO );
	}
	
	
}
