package com.first.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.first.vo.BoardVO;
import com.first.vo.FileVO;
import com.first.dao.BoardDAO;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name = "BoardDAO")
	private BoardDAO mboardDAO;
	
	private String SAVE_PATH = "c:/upload";
	private String PREFIX_URL =  SAVE_PATH + "/";
	
	@Override
	public List<BoardVO> listBoardService(BoardVO mboardVO) throws InterruptedException {
		return mboardDAO.listBoardDAO(mboardVO);
	}

	@Override
	public BoardVO lookBoardService(BoardVO mboardVO) throws InterruptedException {
		return mboardDAO.lookBoardDAO(mboardVO);
	}

	@Override
	public List<FileVO> lookFileBoardService(BoardVO mboardVO) {
		// TODO Auto-generated method stub
		return mboardDAO.lookFileBoardDAO(mboardVO);
	}

	@Override
	public FileVO lookOneFileBoardService(BoardVO mboardVO) {
		// TODO Auto-generated method stub
		return mboardDAO.lookOneFileBoardDAO(mboardVO);
	}

	@Override
	public FileVO lookOneFileBoardService(String atchFileId) {
		// TODO Auto-generated method stub
		return mboardDAO.lookOneFileBoardDAO(atchFileId);
	}

	@Override
	public int countBoardService() throws InterruptedException {
		return mboardDAO.countBoardDAO();
	}

	@Override
	public void insertBoardService(BoardVO mboardVO) throws Exception {
		
		BoardVO newboardVO = mboardDAO.oneBoardDAO(mboardVO);//code등록을 위한 vo생성
		int newcode = newboardVO.getB_code();
		mboardVO.setB_code(newcode+1);
		mboardVO.setB_file_id(Integer.toString(newcode+1));//file_table의 f_id에 들어갈 파일 아이디를 지정
		
		try {
			
			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table에 등록해야할 filevo의 리스트
			
			for(int i = 0 ; i < mboardVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//리스트에 넣기 위한 멤버 fileVO
				
				originFilename = mboardVO.getB_filename().get(i).getOriginalFilename();//파일의 확장자를 포함한 이름(파일명)
				writeFile(mboardVO.getB_filename().get(i), originFilename);//업로드 폴더에 파일 업로드
				
				mfileVO.setFid(mboardVO.getB_file_id());
				mfileVO.setFsign(i); //파일의 사인
				mfileVO.setFpath( PREFIX_URL + originFilename); //파일의 경로 + 파일의 이름으로 최종파일경로 지정
				mfileVO.setFname(originFilename); //파일의 이름
				
				fileVO.add(mfileVO);
				
			}
		     
		     mboardDAO.insertFileBoardDAO(fileVO);
		}catch(Exception e){
			
		}
	     
		 mboardDAO.insertBoardDAO(mboardVO);
		
	}

	@Override
	public void deleteBoardService(BoardVO mboardVO) throws Exception {
		mboardDAO.deleteBoardDAO(mboardVO);
	}
	
	@Override
	public void updatehitBoardService(BoardVO mboardVO) throws Exception {
		 mboardDAO.updatehitBoardDAO(mboardVO);
	}
	
	private void writeFile(MultipartFile b_filename, String saveFileName) throws IOException {
		
		byte[] data = b_filename.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data); fos.close();
		
	}


}
