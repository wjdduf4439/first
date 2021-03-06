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
		
		
		//filecode ????
		
		if(!templateOneVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			System.out.println("???? ???? + " + templateOneVO.getB_filename().get(0).getOriginalFilename());
			
			String f_id;										//atchfileid?? ???? ????
			
			try {	f_id = templateOneDAO.selectTableFileListMax(templateOneVO);	}catch (Exception e) {	f_id = "0";	}
			
			if(f_id == null) { f_id = "0"; }
			
			System.out.println("f_id : " + f_id);
			
			int temp = Integer.parseInt(f_id); temp ++;			//?????? ?????? ?????? temp ????
			
			System.out.println("after f_id : " + temp);
			
			int counttemp = Integer.parseInt(templateOneDAO.countTableAtchFileId(templateOneVO))+1;
			
			templateOneVO.setB_file_id(Integer.toString(counttemp));//file_table?? f_id?? ?????? ???? ???????? ????
			templateOneVO.setAtchFileId(Integer.toString(counttemp));

			List<String> newCodeList = new ArrayList<String>();		//?????? ?????? ?????? file_table?? ?????? "??????"???? ????
			
			for(int i = 0; i < templateOneVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//???????? ???????????? 0?? ????
				
				newCode = newCode + (i+temp);					//file_0000,,,?????? ???? ????
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
			}

			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table?? ?????????? filevo?? ??????
			
			for(int i = 0 ; i < templateOneVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//???????? ???? ???? ???? fileVO
				
				originFilename = templateOneVO.getB_filename().get(i).getOriginalFilename();//?????? ???????? ?????? ????(??????)
				writeFile(templateOneVO.getB_filename().get(i), originFilename);//?????? ?????? ???? ??????
				
				mfileVO.setCode(newCodeList.get(i));
				mfileVO.setFid(templateOneVO.getB_file_id());
				mfileVO.setFsign(i);							 //?????? ????
				mfileVO.setFpath( PREFIX_URL + originFilename); //?????? ???? + ?????? ???????? ???????????? ????
				mfileVO.setFname(originFilename); //?????? ????
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("?????????? ????????");
			System.out.println(fileVO.get(0).getCode());
			System.out.println(fileVO.get(0).getFid());
			System.out.println(fileVO.get(0).getFsign());
			System.out.println(fileVO.get(0).getFpath());
			System.out.println(fileVO.get(0).getFname());
		     
		    mboardDAO.insertFileBoardDAO(fileVO);
			
		}else {System.out.println("file????");}
		
		templateOneDAO.insertTableRecord(templateOneVO);
	}

	@Override
	public void updateTableRecord(TemplateOneVO templateOneVO, final MultipartHttpServletRequest multiRequest ) throws Exception {
		
		
		//System.out.println("update?????? ??????");
		//System.out.println(templateOneVO.getB_filename().get(0).getOriginalFilename());
		
		//delarray?? ???? ???????? fsign ??????????
		
		if( !templateOneVO.getDelArray()[0].equals("") ) { System.out.println("delarray :" + templateOneVO.getDelArray()[0]); fileDAO.deleteFileArray(templateOneVO.getDelArray()); } // delarray?? ???? ????
		
		
		if(!templateOneVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			//???????? ????
				
			int temp = 0;
			int tailNumber;
			String maxCode = templateOneDAO.selectTableFileListMax(templateOneVO);
			System.out.println("maxcode : " + maxCode);
			
			if(maxCode.equals("0")) {}
			
			FileVO mFileVO = fileDAO.selectFileCodeMax();
			
			try { //?????? ???????? ???????? maxcode ????(0???? catch)
				
				//temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
				
				temp = Integer.parseInt(mFileVO.getCode().replace("FILE_", "")); //???? ?????? ?????? ???????? ???????? file_?????? ????
				tailNumber = temp;
			
			}catch(Exception e) { tailNumber = Integer.parseInt(maxCode) + 1; System.out.println("??????"); } //?????? ???????? ???? ??????  maxCode + 1?? ????(file table???? ???? ?????? 1?? ??)
			
			System.out.println("???????????? : " + tailNumber);
			System.out.println("temp : " + temp);
			
			List<String> newCodeList = new ArrayList<String>();		//?????? ?????? ?????? file_table?? ?????? "??????"???? ????
			
			for(int i = 0; i < templateOneVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }				//temp?? ???????? ?????? 0?????? ???? ?????? ????
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//???????? ???????????? 0?? ????
				
				newCode = newCode + (i+temp+1);						//file_0000,,,?????? ???? ????, temp?? 1?? ?????? ?????? file???? ????
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
			}
			
			templateOneVO.setB_fileCode(newCodeList);
			
			//String atchFileId = templateOneDAO.selectTableAtchFileId(templateOneVO);//?????? ???? code?? ???? atchfileid?? ????
			String atchFileId = templateOneVO.getAtchFileId();
			String countAtchFileId = templateOneDAO.countTableAtchFileId(templateOneVO);//???????? atchfileid?? ?????? ????
			
			if(countAtchFileId.equals("")) { System.out.println("atchfileid????"); atchFileId = "1"; }		//???????? atchfileid?? ?????? 1???? ????
			else if(templateOneVO.getAtchFileId().equals("")) {											//???????? atchfileid?? ??????
				
				System.out.println("?? atchfileid????");
				System.out.println("atchfileId = " + atchFileId);
				
				int countAtchFileNumber = Integer.parseInt(countAtchFileId)+1;
				
				templateOneVO.setAtchFileId(Integer.toString(countAtchFileNumber)); //???? ???????? ??????+1?? atchfileid?? ??????
				
			}else { System.out.println("?? atchfileid????"); }
																//atchfileid?? ?????? ???? atchfileid?? ??????
			//templateOneVO.setAtchFileId(atchFileId);			//?????? ???????? ???? ?????? ???????? ????
			
			
			int sign = 0;

			try { sign = fileDAO.selectFileSign(atchFileId); }catch (Exception e) {	sign = 0; }		//f_sign?????? ????
			System.out.println("fsign?? : " + sign);
			
			
			
			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table?? ?????????? filevo?? ??????
			
			for(int i = sign ; i < sign + templateOneVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//???????? ???? ???? ???? fileVO
				
				originFilename = templateOneVO.getB_filename().get(i - sign).getOriginalFilename();//?????? ???????? ?????? ????(??????)
				writeFile(templateOneVO.getB_filename().get(i - sign), originFilename);//?????? ?????? ???? ??????
				
				System.out.println("???? : " + Integer.toString(sign + templateOneVO.getB_filename().size() - i));
				mfileVO.setCode(templateOneVO.getB_fileCode().get(sign + templateOneVO.getB_filename().size() - i - 1));
				mfileVO.setFid(templateOneVO.getAtchFileId());
				mfileVO.setFsign(i);							 //?????? ????
				mfileVO.setFpath( PREFIX_URL + originFilename); //?????? ???? + ?????? ???????? ???????????? ????
				mfileVO.setFname(originFilename); //?????? ????
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("?????????? ????????");
			System.out.println(fileVO.get(0).getCode());
			System.out.println(fileVO.get(0).getFid());
			System.out.println(fileVO.get(0).getFsign());
			System.out.println(fileVO.get(0).getFpath());
			System.out.println(fileVO.get(0).getFname());
			
		    mboardDAO.insertFileBoardDAO(fileVO);

			//filevolist?? atchfileid(fid)?? ?????? ???? ????????, index?? fsign?? ?????? ????, updateFile?? fsign?? update 
			List<FileVO> mFileVOList = new ArrayList<FileVO>();
			
			mFileVOList = fileDAO.selectFileMenuList(templateOneVO.getAtchFileId());
			System.out.println("sign?? fid : " + mFileVOList.get(0).getFid());
			
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
			
				System.out.println("??????????");
			
			    Folder.mkdir(); //???? ??????????
			    
			    Folder.setWritable(true, true);
			    Folder.setReadable(true, true);
			    Folder.setExecutable(true, true);
			    
		}
		
		try {fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName); fos.write(data); fos.close();} catch(Exception e) {}
		
		
	}

}
