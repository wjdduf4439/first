package com.first.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.dao.FileDAO;
import com.first.vo.FileVO;

@Service("FileService")
public class FileServiceImpl implements FileService {

	@Resource(name = "FileDAO")
	private FileDAO fileDAO;
	
	@Override
	public List<FileVO> selectFileMenuList(String atchFileId) throws Exception {
		// TODO Auto-generated method stub
		return fileDAO.selectFileMenuList(atchFileId);
	}

	@Override
	public int selectAtchFileCount(String fid) {
		// TODO Auto-generated method stub
		return fileDAO.selectAtchFileCount(fid);
	}
	
	@Override
	public void deleteFileMenu(String code, String fid, String fsign, String sitecode) {
		// TODO Auto-generated method stub
		fileDAO.deleteFileMenu(code, fid, fsign);
		
	}

	
	
}
