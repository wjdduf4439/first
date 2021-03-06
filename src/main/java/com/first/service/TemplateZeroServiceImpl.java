package com.first.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.first.dao.BoardDAO;
import com.first.dao.FileDAO;
import com.first.dao.TemplateZeroDAO;
import com.first.vo.FileVO;
import com.first.vo.TemplateInfoVO;
import com.first.vo.TemplateZeroVO;

@Service("TemplateZeroService")
public class TemplateZeroServiceImpl implements TemplateZeroService {

	@Resource(name = "TemplateZeroDAO")
	TemplateZeroDAO templateZeroDAO; 
	
	@Resource(name = "FileDAO")
	FileDAO fileDAO;

	@Resource(name = "BoardDAO")
	private BoardDAO mboardDAO;
	
	private String SAVE_PATH =  "C:/sts-bundle/workspace-sts-3.9.4.RELEASE/first/src/main/webapp/resources/image";
	private String PREFIX_URL =  SAVE_PATH + "/";
	
	@Override
	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("설정된 페이지인덱스 : " + templateInfoVO.getPageIndex());
		System.out.println("설정된 레코드카운트 : " + templateInfoVO.getRecordCountPerPage());
		
		templateInfoVO.setPageIndex( templateInfoVO.getPageIndex() * templateInfoVO.getRecordCountPerPage() );
		
		return templateZeroDAO.selectTableFieldList(templateInfoVO);
	}

	@Override
	public List<TemplateZeroVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordList(templateInfoVO);
	}

	@Override
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordListCount(templateInfoVO);
	}
	
	@Override
	public int selectTableRecordListCount(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordListCount(templateZeroVO);
	}

	@Override
	public TemplateZeroVO selectTableRecordOne(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordOne(templateZeroVO);
	}

	@Override
	public TemplateZeroVO selectTableRecordRecent(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordRecent(templateZeroVO);
	}

	@Override
	public void insertTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest ) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			String maxCode = templateZeroDAO.selectTableRecordListMax(templateZeroVO);
			int temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
			int tailNumber = temp;
			
			String newCode = "TZERO_";
			int i = 1;
			
			while(temp >= 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 14-i ; j++) { newCode += "0"; System.out.println(newCode); }
			newCode += Integer.toString(tailNumber);
			
			System.out.println(newCode);
			
			templateZeroVO.setCode(newCode);
			
			
		}catch(Exception e) {
			
			templateZeroVO.setCode("TZERO_00000000000001");
			
		}
		
		
		//filecode 생성
		
		if(!templateZeroVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			String f_id;										//atchfileid를 찾을 변수
			
			try {	f_id = templateZeroDAO.selectTableFileListMax(templateZeroVO);	}catch (Exception e) {	f_id = "0";	}
			
			if(f_id == null) { f_id = "0"; }
			
			System.out.println("f_id : " + f_id);
			
			int temp = Integer.parseInt(f_id); temp ++;			//실제로 코드에 들어갈 temp 설정
			int counttemp = Integer.parseInt(templateZeroDAO.countTableAtchFileId(templateZeroVO))+1;
			
			templateZeroVO.setB_file_id(Integer.toString(counttemp));//file_table의 f_id에 들어갈 파일 아이디를 지정
			templateZeroVO.setAtchFileId(Integer.toString(counttemp));

			List<String> newCodeList = new ArrayList<String>();		//파일을 첨부한 수만큼 file_table에 들어갈 "시작점"코드 생산
			
			for(int i = 0; i < templateZeroVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//비어있는 자리숫자만큼 0을 채움
				
				newCode = newCode + (i+temp);					//file_0000,,,형식의 코드 생성
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
			}

			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table에 등록해야할 filevo의 리스트
			
			for(int i = 0 ; i < templateZeroVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//리스트에 넣기 위한 멤버 fileVO
				
				originFilename = templateZeroVO.getB_filename().get(i).getOriginalFilename();//파일의 확장자를 포함한 이름(파일명)
				writeFile(templateZeroVO.getB_filename().get(i), originFilename);//업로드 폴더에 파일 업로드
				
				mfileVO.setCode(newCodeList.get(i));
				mfileVO.setFid(templateZeroVO.getB_file_id());
				mfileVO.setFsign(i);							 //파일의 사인
				mfileVO.setFpath( PREFIX_URL + originFilename); //파일의 경로 + 파일의 이름으로 최종파일경로 지정
				mfileVO.setFname(originFilename); //파일의 이름
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("업로드파일 업로드됨");
			System.out.println(fileVO.get(0).getCode());
			System.out.println(fileVO.get(0).getFid());
			System.out.println(fileVO.get(0).getFsign());
			System.out.println(fileVO.get(0).getFpath());
			System.out.println(fileVO.get(0).getFname());
		     
		     mboardDAO.insertFileBoardDAO(fileVO);
			
		}
		
		templateZeroDAO.insertTableRecord(templateZeroVO);
	}

	@Override
	public void updateTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest ) throws Exception {
		
		if(!templateZeroVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			//파일코드 등록
				
			int temp = 0;
			int tailNumber;
			String maxCode = templateZeroDAO.selectTableFileListMax(templateZeroVO);
			System.out.println("maxcode : " + maxCode);
			
			if(maxCode.equals("0")) {}
			
			
			try { //파일이 첨부되어 있을때의 maxcode 추출(0이면 catch)
				
				temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
				
				tailNumber = temp;
			
			}catch(Exception e) { tailNumber = Integer.parseInt(maxCode) + 1; System.out.println("캐치됨"); } //파일이 첨부되어 있지 않으면  maxCode + 1을 추출(file table에서 행이 없으면 1이 됨)
			//System.out.println("파일코드넘버 : " + tailNumber);
			
			
			
			List<String> newCodeList = new ArrayList<String>();		//파일을 첨부한 수만큼 file_table에 들어갈 "시작점"코드 생산
			
			for(int i = 0; i < templateZeroVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//비어있는 자리숫자만큼 0을 채움
				
				newCode = newCode + (i+tailNumber);					//file_0000,,,형식의 코드 생성
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
			}
			
			templateZeroVO.setB_fileCode(newCodeList);
			
			//String atchFileId = templateZeroDAO.selectTableAtchFileId(templateZeroVO);//수정한 글의 code에 대한 atchfileid를 추출
			String atchFileId = templateZeroVO.getAtchFileId();
			String countAtchFileId = fileDAO.selectFileIdMax();//filetable의 최대 atchfileid를 찾아서 다음 fileid를 정함
			
			System.out.println("countid : " + countAtchFileId);
			
			if(countAtchFileId.equals("")) { System.out.println("atchfileid없음"); atchFileId = "1"; }		//게시판에 atchfileid가 없으면 1부터 시작
			else if(templateZeroVO.getAtchFileId().equals("")) {											//게시물에 atchfileid가 없을때
				
				System.out.println("내 atchfileid없음");
				System.out.println("atchfileId = " + atchFileId);
				
				int countAtchFileNumber = Integer.parseInt(countAtchFileId)+1;
				
				templateZeroVO.setAtchFileId(Integer.toString(countAtchFileNumber)); //해당 게시물에 카운트+1한 atchfileid를 넣어줌
				
			}else { System.out.println("내 atchfileid있음"); }
																//atchfileid가 있으면 해당 atchfileid를 고쳐씀
			//templateZeroVO.setAtchFileId(atchFileId);			//파일이 없었다가 있을 경우를 대비해서 주입
			
			
			int sign = 0;

			try { sign = fileDAO.selectFileSign(atchFileId); }catch (Exception e) {	sign = 0; }		//f_sign최대값 추출
			System.out.println("fsign은 : " + sign);
			
			
			
			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table에 등록해야할 filevo의 리스트
			
			for(int i = sign ; i < sign + templateZeroVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//리스트에 넣기 위한 멤버 fileVO
				
				originFilename = templateZeroVO.getB_filename().get(i - sign).getOriginalFilename();//파일의 확장자를 포함한 이름(파일명)
				writeFile(templateZeroVO.getB_filename().get(i - sign), originFilename);//업로드 폴더에 파일 업로드
				
				System.out.println("번호 : " + Integer.toString(sign + templateZeroVO.getB_filename().size() - i));
				mfileVO.setCode(templateZeroVO.getB_fileCode().get(sign + templateZeroVO.getB_filename().size() - i - 1));
				mfileVO.setFid(templateZeroVO.getAtchFileId());
				mfileVO.setFsign(i);							 //파일의 사인
				mfileVO.setFpath( PREFIX_URL + originFilename); //파일의 경로 + 파일의 이름으로 최종파일경로 지정
				mfileVO.setFname(originFilename); //파일의 이름
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("업로드파일 업로드됨");
			System.out.println(fileVO.get(0).getCode());
			System.out.println(fileVO.get(0).getFid());
			System.out.println(fileVO.get(0).getFsign());
			System.out.println(fileVO.get(0).getFpath());
			System.out.println(fileVO.get(0).getFname());
			
		    mboardDAO.insertFileBoardDAO(fileVO);
			
		}
		
		templateZeroDAO.updateTableRecord(templateZeroVO);
	}

	@Override
	public void deleteTableRecord(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		templateZeroDAO.deleteFileRecord(templateZeroVO);
		templateZeroDAO.deleteTableRecord(templateZeroVO);
	}
	
	private void writeFile(MultipartFile b_filename, String saveFileName) throws IOException, InterruptedException {
		
		byte[] data = b_filename.getBytes();
		
		FileOutputStream fos;
			
		String path = "c:/upload";
		File Folder = new File(path);
		
		if (!Folder.isDirectory()) {
			
				System.out.println("폴더생성됨");
			
			    Folder.mkdir(); //폴더 생성합니다
			    
			    Folder.setWritable(true, true);
			    Folder.setReadable(true, true);
			    Folder.setExecutable(true, true);
			    
		}
		
		try {fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName); fos.write(data); fos.close();} catch(Exception e) {}
		
		
	}

}
