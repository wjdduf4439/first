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
import com.first.dao.TemplateOneDAO;
import com.first.vo.FileVO;
import com.first.vo.TemplateInfoVO;
import com.first.vo.TemplateOneVO;

@Service("TemplateOneService")
public class TemplateOneServiceImpl implements TemplateOneService {
	
	@Resource(name = "TemplateOneDAO")
	TemplateOneDAO templateOneDAO; 
	
	@Resource(name = "FileDAO")
	FileDAO fileDAO;

	@Resource(name = "BoardDAO")
	private BoardDAO mboardDAO;
	
	private String SAVE_PATH =  "C:/sts-bundle/workspace-sts-3.9.4.RELEASE/first/src/main/webapp/resources/image";
	private String PREFIX_URL =  SAVE_PATH + "/";
	
	@Override
	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		
		templateInfoVO.setPageIndex( templateInfoVO.getPageIndex() * templateInfoVO.getRecordCountPerPage() );
		
		return templateOneDAO.selectTableFieldList(templateInfoVO);
	}

	@Override
	public List<TemplateOneVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateOneDAO.selectTableRecordList(templateInfoVO);
	}

	@Override
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateOneDAO.selectTableRecordListCount(templateInfoVO);
	}
	
	@Override
	public int selectTableRecordListCount(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		return templateOneDAO.selectTableRecordListCount(templateOneVO);
	}

	@Override
	public TemplateOneVO selectTableRecordOne(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		
		return templateOneDAO.selectTableRecordOne(templateOneVO);
	}

	@Override
	public TemplateOneVO selectTableRecordContext(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		
		return templateOneDAO.selectTableRecordContext(templateOneVO);
	}

	@Override
	public TemplateOneVO selectTableRecordRecent(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		return templateOneDAO.selectTableRecordRecent(templateOneVO);
	}

	@Override
	public void insertTableRecord(TemplateOneVO templateOneVO, final MultipartHttpServletRequest multiRequest ) throws Exception {
		// TODO Auto-generated method stub
		
		
		try {
			String maxCode = templateOneDAO.selectTableRecordListMax(templateOneVO);
			int temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
			int tailNumber = temp;
			
			String newCode = "T_ONE_";
			int i = 1;
			
			while(temp >= 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 14-i ; j++) { newCode += "0"; System.out.println(newCode); }
			newCode += Integer.toString(tailNumber);
			
			System.out.println(newCode);
			
			templateOneVO.setCode(newCode);
			
			
		}catch(Exception e) {
			
			templateOneVO.setCode("T_ONE_00000000000001");
			
		}
		
		
		//filecode 생성
		
		if(!templateOneVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			System.out.println("파일 있음 + " + templateOneVO.getB_filename().get(0).getOriginalFilename());
			
			String f_id;										//atchfileid를 찾을 변수
			
			try {	f_id = templateOneDAO.selectTableFileListMax(templateOneVO);	}catch (Exception e) {	f_id = "0";	}
			
			if(f_id == null) { f_id = "0"; }
			
			System.out.println("f_id : " + f_id);
			
			int temp = Integer.parseInt(f_id); temp ++;			//실제로 코드에 들어갈 temp 설정
			
			System.out.println("after f_id : " + temp);
			
			int counttemp = Integer.parseInt(templateOneDAO.countTableAtchFileId(templateOneVO))+1;
			
			templateOneVO.setB_file_id(Integer.toString(counttemp));//file_table의 f_id에 들어갈 파일 아이디를 지정
			templateOneVO.setAtchFileId(Integer.toString(counttemp));

			List<String> newCodeList = new ArrayList<String>();		//파일을 첨부한 수만큼 file_table에 들어갈 "시작점"코드 생산
			
			for(int i = 0; i < templateOneVO.getB_filename().size(); i++) {
				
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
			
			for(int i = 0 ; i < templateOneVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//리스트에 넣기 위한 멤버 fileVO
				
				originFilename = templateOneVO.getB_filename().get(i).getOriginalFilename();//파일의 확장자를 포함한 이름(파일명)
				writeFile(templateOneVO.getB_filename().get(i), originFilename);//업로드 폴더에 파일 업로드
				
				mfileVO.setCode(newCodeList.get(i));
				mfileVO.setFid(templateOneVO.getB_file_id());
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
			
		}else {System.out.println("file없음");}
		
		templateOneDAO.insertTableRecord(templateOneVO);
	}

	@Override
	public void updateTableRecord(TemplateOneVO templateOneVO, final MultipartHttpServletRequest multiRequest ) throws Exception {
		
		
		//System.out.println("update서비스 접속됨");
		//System.out.println(templateOneVO.getB_filename().get(0).getOriginalFilename());
		
		//delarray의 파일 삭제하고 fsign 재정렬하기
		
		if( !templateOneVO.getDelArray()[0].equals("") ) { System.out.println("delarray :" + templateOneVO.getDelArray()[0]); fileDAO.deleteFileArray(templateOneVO.getDelArray()); } // delarray의 파일 삭제
		
		
		if(!templateOneVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			//파일코드 등록
				
			int temp = 0;
			int tailNumber;
			String maxCode = templateOneDAO.selectTableFileListMax(templateOneVO);
			System.out.println("maxcode : " + maxCode);
			
			if(maxCode.equals("0")) {}
			
			FileVO mFileVO = fileDAO.selectFileCodeMax();
			
			try { //파일이 첨부되어 있을때의 maxcode 추출(0이면 catch)
				
				//temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
				
				temp = Integer.parseInt(mFileVO.getCode().replace("FILE_", "")); //이미 기록된 코드의 최대값을 가져와서 file_부분을 지움
				tailNumber = temp;
			
			}catch(Exception e) { tailNumber = Integer.parseInt(maxCode) + 1; System.out.println("캐치됨"); } //파일이 첨부되어 있지 않으면  maxCode + 1을 추출(file table에서 행이 없으면 1이 됨)
			
			System.out.println("파일코드넘버 : " + tailNumber);
			System.out.println("temp : " + temp);
			
			List<String> newCodeList = new ArrayList<String>();		//파일을 첨부한 수만큼 file_table에 들어갈 "시작점"코드 생산
			
			for(int i = 0; i < templateOneVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }				//temp의 자릿수를 계산해 0구간을 채울 횟수를 정함
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//비어있는 자리숫자만큼 0을 채움
				
				newCode = newCode + (i+temp+1);						//file_0000,,,형식의 코드 생성, temp에 1을 더해서 새로운 file코드 생성
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
			}
			
			templateOneVO.setB_fileCode(newCodeList);
			
			//String atchFileId = templateOneDAO.selectTableAtchFileId(templateOneVO);//수정한 글의 code에 대한 atchfileid를 추출
			String atchFileId = templateOneVO.getAtchFileId();
			String countAtchFileId = templateOneDAO.countTableAtchFileId(templateOneVO);//게시판에 atchfileid가 있는지 검토
			
			if(countAtchFileId.equals("")) { System.out.println("atchfileid없음"); atchFileId = "1"; }		//게시판에 atchfileid가 없으면 1부터 시작
			else if(templateOneVO.getAtchFileId().equals("")) {											//게시물에 atchfileid가 없을때
				
				System.out.println("내 atchfileid없음");
				System.out.println("atchfileId = " + atchFileId);
				
				int countAtchFileNumber = Integer.parseInt(countAtchFileId)+1;
				
				templateOneVO.setAtchFileId(Integer.toString(countAtchFileNumber)); //해당 게시물에 카운트+1한 atchfileid를 넣어줌
				
			}else { System.out.println("내 atchfileid있음"); }
																//atchfileid가 있으면 해당 atchfileid를 고쳐씀
			//templateOneVO.setAtchFileId(atchFileId);			//파일이 없었다가 있을 경우를 대비해서 주입
			
			
			int sign = 0;

			try { sign = fileDAO.selectFileSign(atchFileId); }catch (Exception e) {	sign = 0; }		//f_sign최대값 추출
			System.out.println("fsign은 : " + sign);
			
			
			
			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table에 등록해야할 filevo의 리스트
			
			for(int i = sign ; i < sign + templateOneVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//리스트에 넣기 위한 멤버 fileVO
				
				originFilename = templateOneVO.getB_filename().get(i - sign).getOriginalFilename();//파일의 확장자를 포함한 이름(파일명)
				writeFile(templateOneVO.getB_filename().get(i - sign), originFilename);//업로드 폴더에 파일 업로드
				
				System.out.println("번호 : " + Integer.toString(sign + templateOneVO.getB_filename().size() - i));
				mfileVO.setCode(templateOneVO.getB_fileCode().get(sign + templateOneVO.getB_filename().size() - i - 1));
				mfileVO.setFid(templateOneVO.getAtchFileId());
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

			//filevolist에 atchfileid(fid)로 가져온 값을 입력하고, index로 fsign을 설정한 다음, updateFile로 fsign을 update 
			List<FileVO> mFileVOList = new ArrayList<FileVO>();
			
			mFileVOList = fileDAO.selectFileMenuList(templateOneVO.getAtchFileId());
			System.out.println("sign용 fid : " + mFileVOList.get(0).getFid());
			
			for(int i = 0; i < mFileVOList.size(); i++) { 
				mFileVOList.get(i).setSiteCode(templateOneVO.getSiteCode());
				mFileVOList.get(i).setFsign(i);
				fileDAO.updateFileSign(mFileVOList.get(i));	
			}
			
		}
		
		//if(templateOneVO.getDelArray()[0] != "" )
		
		System.out.println("delArray : " + templateOneVO.getDelArray()[0]);
		
		templateOneDAO.updateTableRecord(templateOneVO);
	}

	@Override
	public void deleteTableRecord(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		templateOneDAO.deleteFileRecord(templateOneVO);
		templateOneDAO.deleteTableRecord(templateOneVO);
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
