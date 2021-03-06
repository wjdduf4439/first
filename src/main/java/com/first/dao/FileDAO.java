package com.first.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.first.vo.FileVO;

@Repository("FileDAO")
public class FileDAO {
	
	@Inject //자바에서 지원하는 해당 데이터형식에 맞춰 데이터를 주입하는 어노테이션
	private SqlSession sqlSession;
	
	public List<FileVO> selectFileMenuList(String atchFileId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.FileMapper.selectFileList", atchFileId);
	}

	public int selectAtchFileCount(String fid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.FileMapper.selectAtchFileCount", fid);
	}

	public int selectFileSign(String atchFileId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.FileMapper.selectFileSign", atchFileId);
	}
	
	public FileVO selectFileCodeMax() {
		
		return sqlSession.selectOne("com.example.mapper.FileMapper.selectFileCodeMax");
		
	}
	
	public String selectFileIdMax() {
		
		return sqlSession.selectOne("com.example.mapper.FileMapper.selectFileIdMax");
		
	}
	
	public void updateFileSign( FileVO mFileVO ) {
		
		sqlSession.selectOne("com.example.mapper.FileMapper.updateFileSign", mFileVO);
		
	}

	public void deleteFileMenu(String code, String fid, String fsign) {
		// TODO Auto-generated method stub
		
		FileVO valueVO = new FileVO();
		
		valueVO.setCode(code);
		valueVO.setFid(fid);
		valueVO.setFsign( Integer.parseInt(fsign) );
		
		sqlSession.selectOne("com.example.mapper.FileMapper.deleteFileMenu", valueVO);
	}
	
	public void deleteFileArray(String[] mDelArray) {
		
		sqlSession.selectOne("com.example.mapper.FileMapper.deleteFileArray", mDelArray);
	}

}
