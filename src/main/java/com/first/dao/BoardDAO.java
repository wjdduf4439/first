package com.first.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.first.vo.BoardVO;
import com.first.vo.FileVO;

@Repository("BoardDAO")
public class BoardDAO {
	
	@Inject //자바에서 지원하는 해당 데이터형식에 맞춰 데이터를 주입하는 어노테이션
	private SqlSession sqlSession;

	public List<BoardVO> listBoardDAO(BoardVO mboardVO){
		return  sqlSession.selectList("com.example.mapper.memberMapper.selectBoardDAO", mboardVO);
	}

	public BoardVO lookBoardDAO(BoardVO mboardVO) {
		return sqlSession.selectOne("com.example.mapper.memberMapper.lookBoardDAO", mboardVO);
	}
	
	public List<FileVO> lookFileBoardDAO(BoardVO mboardVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.example.mapper.memberMapper.lookFileBoardDAO", mboardVO);
	}
	
	public FileVO lookOneFileBoardDAO(BoardVO mboardVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.memberMapper.lookOneFileBoardDAO", mboardVO);
	}
	
	public FileVO lookOneFileBoardDAO(String code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.example.mapper.memberMapper.lookOneFileBoardDAO2", code);
	}
	

	public BoardVO oneBoardDAO(BoardVO mboardVO){
		return sqlSession.selectOne("com.example.mapper.memberMapper.oneBoardDAO", mboardVO);
	}
	
	public int countBoardDAO() {
		// TODO Auto-generated method stub
		return  sqlSession.selectOne("com.example.mapper.memberMapper.countBoardDAO"); // int값을 뽑아오고자 할때의 sql dao 함수
	}

	public void insertBoardDAO(BoardVO mboardVO) {
		sqlSession.insert("com.example.mapper.memberMapper.insertBoardDAO", mboardVO);
	}

	public void insertFileBoardDAO(List<FileVO> fileVO) {
		
		sqlSession.insert("com.example.mapper.memberMapper.insertfileBoardDAO", fileVO);
		
	}
	
	public void deleteBoardDAO(BoardVO mboardVO) {
		sqlSession.delete("com.example.mapper.memberMapper.deleteBoardDAO", mboardVO);
	}

	public void updatehitBoardDAO(BoardVO mboardVO) {
		sqlSession.update("com.example.mapper.memberMapper.updatehitBoardDAO", mboardVO);
		
	}



	
}
 