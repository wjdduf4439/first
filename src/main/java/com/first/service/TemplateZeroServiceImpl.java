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

		System.out.println("������ �������ε��� : " + templateInfoVO.getPageIndex());
		System.out.println("������ ���ڵ�ī��Ʈ : " + templateInfoVO.getRecordCountPerPage());
		
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
		
		
		//filecode ����
		
		if(!templateZeroVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			String f_id;										//atchfileid�� ã�� ����
			
			try {	f_id = templateZeroDAO.selectTableFileListMax(templateZeroVO);	}catch (Exception e) {	f_id = "0";	}
			
			if(f_id == null) { f_id = "0"; }
			
			System.out.println("f_id : " + f_id);
			
			int temp = Integer.parseInt(f_id); temp ++;			//������ �ڵ忡 �� temp ����
			int counttemp = Integer.parseInt(templateZeroDAO.countTableAtchFileId(templateZeroVO))+1;
			
			templateZeroVO.setB_file_id(Integer.toString(counttemp));//file_table�� f_id�� �� ���� ���̵� ����
			templateZeroVO.setAtchFileId(Integer.toString(counttemp));

			List<String> newCodeList = new ArrayList<String>();		//������ ÷���� ����ŭ file_table�� �� "������"�ڵ� ����
			
			for(int i = 0; i < templateZeroVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//����ִ� �ڸ����ڸ�ŭ 0�� ä��
				
				newCode = newCode + (i+temp);					//file_0000,,,������ �ڵ� ����
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
			}

			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table�� ����ؾ��� filevo�� ����Ʈ
			
			for(int i = 0 ; i < templateZeroVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//����Ʈ�� �ֱ� ���� ��� fileVO
				
				originFilename = templateZeroVO.getB_filename().get(i).getOriginalFilename();//������ Ȯ���ڸ� ������ �̸�(���ϸ�)
				writeFile(templateZeroVO.getB_filename().get(i), originFilename);//���ε� ������ ���� ���ε�
				
				mfileVO.setCode(newCodeList.get(i));
				mfileVO.setFid(templateZeroVO.getB_file_id());
				mfileVO.setFsign(i);							 //������ ����
				mfileVO.setFpath( PREFIX_URL + originFilename); //������ ��� + ������ �̸����� �������ϰ�� ����
				mfileVO.setFname(originFilename); //������ �̸�
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("���ε����� ���ε��");
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
			
			//�����ڵ� ���
				
			int temp = 0;
			int tailNumber;
			String maxCode = templateZeroDAO.selectTableFileListMax(templateZeroVO);
			System.out.println("maxcode : " + maxCode);
			
			if(maxCode.equals("0")) {}
			
			
			try { //������ ÷�εǾ� �������� maxcode ����(0�̸� catch)
				
				temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
				
				tailNumber = temp;
			
			}catch(Exception e) { tailNumber = Integer.parseInt(maxCode) + 1; System.out.println("ĳġ��"); } //������ ÷�εǾ� ���� ������  maxCode + 1�� ����(file table���� ���� ������ 1�� ��)
			//System.out.println("�����ڵ�ѹ� : " + tailNumber);
			
			
			
			List<String> newCodeList = new ArrayList<String>();		//������ ÷���� ����ŭ file_table�� �� "������"�ڵ� ����
			
			for(int i = 0; i < templateZeroVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//����ִ� �ڸ����ڸ�ŭ 0�� ä��
				
				newCode = newCode + (i+tailNumber);					//file_0000,,,������ �ڵ� ����
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
			}
			
			templateZeroVO.setB_fileCode(newCodeList);
			
			//String atchFileId = templateZeroDAO.selectTableAtchFileId(templateZeroVO);//������ ���� code�� ���� atchfileid�� ����
			String atchFileId = templateZeroVO.getAtchFileId();
			String countAtchFileId = fileDAO.selectFileIdMax();//filetable�� �ִ� atchfileid�� ã�Ƽ� ���� fileid�� ����
			
			System.out.println("countid : " + countAtchFileId);
			
			if(countAtchFileId.equals("")) { System.out.println("atchfileid����"); atchFileId = "1"; }		//�Խ��ǿ� atchfileid�� ������ 1���� ����
			else if(templateZeroVO.getAtchFileId().equals("")) {											//�Խù��� atchfileid�� ������
				
				System.out.println("�� atchfileid����");
				System.out.println("atchfileId = " + atchFileId);
				
				int countAtchFileNumber = Integer.parseInt(countAtchFileId)+1;
				
				templateZeroVO.setAtchFileId(Integer.toString(countAtchFileNumber)); //�ش� �Խù��� ī��Ʈ+1�� atchfileid�� �־���
				
			}else { System.out.println("�� atchfileid����"); }
																//atchfileid�� ������ �ش� atchfileid�� ���ľ�
			//templateZeroVO.setAtchFileId(atchFileId);			//������ �����ٰ� ���� ��츦 ����ؼ� ����
			
			
			int sign = 0;

			try { sign = fileDAO.selectFileSign(atchFileId); }catch (Exception e) {	sign = 0; }		//f_sign�ִ밪 ����
			System.out.println("fsign�� : " + sign);
			
			
			
			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table�� ����ؾ��� filevo�� ����Ʈ
			
			for(int i = sign ; i < sign + templateZeroVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//����Ʈ�� �ֱ� ���� ��� fileVO
				
				originFilename = templateZeroVO.getB_filename().get(i - sign).getOriginalFilename();//������ Ȯ���ڸ� ������ �̸�(���ϸ�)
				writeFile(templateZeroVO.getB_filename().get(i - sign), originFilename);//���ε� ������ ���� ���ε�
				
				System.out.println("��ȣ : " + Integer.toString(sign + templateZeroVO.getB_filename().size() - i));
				mfileVO.setCode(templateZeroVO.getB_fileCode().get(sign + templateZeroVO.getB_filename().size() - i - 1));
				mfileVO.setFid(templateZeroVO.getAtchFileId());
				mfileVO.setFsign(i);							 //������ ����
				mfileVO.setFpath( PREFIX_URL + originFilename); //������ ��� + ������ �̸����� �������ϰ�� ����
				mfileVO.setFname(originFilename); //������ �̸�
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("���ε����� ���ε��");
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
			
				System.out.println("����������");
			
			    Folder.mkdir(); //���� �����մϴ�
			    
			    Folder.setWritable(true, true);
			    Folder.setReadable(true, true);
			    Folder.setExecutable(true, true);
			    
		}
		
		try {fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName); fos.write(data); fos.close();} catch(Exception e) {}
		
		
	}

}