package com.first.service;

import java.util.List;

import com.first.vo.BoardVO;
import com.first.vo.FileVO;

public interface BoardService {
	
	public List<BoardVO> listBoardService(BoardVO mboardVO) throws InterruptedException;//�Խ��� ���� ����
	
	public BoardVO lookBoardService(BoardVO mboardVO) throws InterruptedException;//�Խù� ���� ����
	
	public List<FileVO> lookFileBoardService(BoardVO mboardVO); // �Խù��� �����׸� ���� ����

	public FileVO lookOneFileBoardService(BoardVO mboardVO); //�Խù��� �����׸� �ϳ��� ���� ����
	
	public FileVO lookOneFileBoardService(String atchFileId); //�Խù��� �����׸� �ϳ��� ���� ����
	
	public int countBoardService() throws InterruptedException;//�� �Խù� �� �����ִ� ����
	
	public void insertBoardService(BoardVO mboardVO) throws Exception;//�Խñ� ���� ����
	
	public void deleteBoardService(BoardVO mboardVO) throws Exception;//�Խñ� ���� ����
	
	public void updatehitBoardService(BoardVO mboardVO) throws Exception;//��ȸ�� ���� ����


	
	
}
