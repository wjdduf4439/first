package com.first.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.first.vo.TemplateInfoVO;
import com.first.vo.TemplateOneVO;

@Repository("TemplateOneDAO")
public class TemplateOneDAO {
	
	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;

	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.TemplateOne.selectTableFieldList", templateInfoVO );
	}

	public List<TemplateOneVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.TemplateOne.selectTableRecordList", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateOne.selectTableRecordListCount", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateOne.selectTableRecordListCount", templateOneVO );
	}

	public String selectTableAtchFileId(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateOne.selectTableAtchFileId", templateOneVO );
	}
	
	public String countTableAtchFileId(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateOne.countTableAtchFileId", templateOneVO );
	}

	public String selectTableRecordListMax(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateOne.selectTableRecordListMax", templateOneVO );
	}
	
	public String selectTableFileListMax(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateOne.selectTableFileListMax", templateOneVO );
	}

	public TemplateOneVO selectTableRecordOne(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub\
		
		System.out.println("dao�� �� vo �ڵ�"+ templateOneVO.getCode());
		
		return sqlSession.selectOne("com.example.mapper.TemplateOne.selectTableRecordOne", templateOneVO );
	}

	public TemplateOneVO selectTableRecordContext(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		
		//System.out.println("dao�� �� vo ����Ʈ�ڵ�"+ templateOneVO.getSiteCode());
		//System.out.println("dao�� �� vo �ڵ�"+ templateOneVO.getCode());
		
		return sqlSession.selectOne("com.example.mapper.TemplateOne.selectTableRecordContext", templateOneVO );
	}

	public TemplateOneVO selectTableRecordRecent(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.TemplateOne.selectTableRecordRecent", templateOneVO);
	}

	public void insertTableRecord(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.example.mapper.TemplateOne.insertTableRecord", templateOneVO );
	}
	
	public void updateTableRecord(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.example.mapper.TemplateOne.updateTableRecord", templateOneVO );
	}
	
	public void deleteTableRecord(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.example.mapper.TemplateOne.deleteTableRecord", templateOneVO );
	}
	
	public void deleteFileRecord(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		
		System.out.println("delete�� fid = " + templateOneVO.getAtchFileId());
		
		sqlSession.selectOne("com.example.mapper.TemplateOne.deleteFileRecord", templateOneVO );
	}
	
	
}
