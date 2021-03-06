package com.first.service;

import java.util.List;

import com.first.vo.BoardVO;
import com.first.vo.FileVO;

public interface BoardService {
	
	public List<BoardVO> listBoardService(BoardVO mboardVO) throws InterruptedException;//게시판 보기 서비스
	
	public BoardVO lookBoardService(BoardVO mboardVO) throws InterruptedException;//게시물 보기 서비스
	
	public List<FileVO> lookFileBoardService(BoardVO mboardVO); // 게시물의 파일항목 보기 서비스

	public FileVO lookOneFileBoardService(BoardVO mboardVO); //게시물의 파일항목 하나만 보기 서비스
	
	public FileVO lookOneFileBoardService(String atchFileId); //게시물의 파일항목 하나만 보기 서비스
	
	public int countBoardService() throws InterruptedException;//총 게시물 수 보여주는 서비스
	
	public void insertBoardService(BoardVO mboardVO) throws Exception;//게시글 삽입 서비스
	
	public void deleteBoardService(BoardVO mboardVO) throws Exception;//게시글 삭제 서비스
	
	public void updatehitBoardService(BoardVO mboardVO) throws Exception;//조회수 갱신 서비스


	
	
}
