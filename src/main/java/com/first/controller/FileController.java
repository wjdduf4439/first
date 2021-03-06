package com.first.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.first.service.BoardService;
import com.first.service.FileService;
import com.first.vo.BoardVO;
import com.first.vo.FileVO;

@Controller
public class FileController {

	@Resource(name = "BoardService")
	private BoardService mBoardService;
	
	@Resource(name = "FileService")
	private FileService fileService;
	
	@RequestMapping(value = "/fileboard.go")
	public String fileboard(@ModelAttribute("searchVO")BoardVO mboardVO, ModelMap map) throws Exception {
		
		String filename = mboardVO.getB_file_name();//가져온 파일 이름을 따로 저장
		
		mboardVO = mBoardService.lookBoardService(mboardVO);//게시물 보기 서비스 호출
		mboardVO.setB_file_name(filename);//해당 게시물의 정보에 다운로드할 파일명 삽입
		
		FileVO mfileVO = mBoardService.lookOneFileBoardService(mboardVO);
		
		File downloadFile = new File(mfileVO.getFpath());
		File realFileName = new File(mfileVO.getFname());//진짜 파일 이름으로 다운로드하기 위한 로직
		
		//다운로드 시 해당 path를 전송(파일 이름이 아님)
		//해당 파일의 다운로드는 하나씩만 진행되게 한다
		
		if(!downloadFile.canRead()) { return "redirect:/mainboard.go"; }
		
		map.addAttribute("downloadFile", downloadFile);
		map.addAttribute("realFileName", realFileName);//진짜 파일 이름으로 다운로드하기 위한 로직
		
		return "fileDownloadView";
	}
	
	@RequestMapping(value = "/file/fileAdmin.go")
	public String fileDownloadForm(FileVO fileVO, @RequestParam String atchFileId, ModelMap map) throws Exception {
		
		try {
			
			List<FileVO> fileList = fileService.selectFileMenuList(atchFileId);
			
			map.addAttribute("fileList", fileList);
			
			
			return "/file/fileDownloadForm";
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/file/fileDownloadForm";
	}
	
	@RequestMapping(value = "/filedown.go")
	public String fileDown(String code, ModelMap map) throws Exception {
		
		FileVO mfileVO = mBoardService.lookOneFileBoardService(code);
		
		File downloadFile = new File(mfileVO.getFpath());
		File realFileName = new File(mfileVO.getFname());//진짜 파일 이름으로 다운로드하기 위한 로직
		
		//다운로드 시 해당 path를 전송(파일 이름이 아님)
		//해당 파일의 다운로드는 하나씩만 진행되게 한다
		
		if(!downloadFile.canRead()) { return "redirect:/mainboard.go"; }
		
		map.addAttribute("downloadFile", downloadFile);
		map.addAttribute("realFileName", realFileName);//진짜 파일 이름으로 다운로드하기 위한 로직
		
		return "fileDownloadView";
	}
	
	@RequestMapping(value = "/fileDelete.go")
	public String fileDelete(String code,String fid, String fsign, String sitecode, String fname) throws Exception {
		
		System.out.println("code : " + code);
		System.out.println("fid : " + fid);
		System.out.println("fsign : " + fsign);
		System.out.println("sitecode : " + sitecode);
		System.out.println("fname : " + fname);
		
		File f = new File("C:/upload/"+fname);
		
		if (f.delete()) {
		      System.out.println("파일 또는 디렉토리를 성공적으로 지웠습니다: ");
		    } else {
		      System.err.println("파일 또는 디렉토리 지우기 실패: ");
		    }
		
		fileService.deleteFileMenu(code, fid, fsign, sitecode);
		
		if(fileService.selectAtchFileCount(fid) == 0) {
			
		}
		
		return "mainboard";
	
	}
	
	@RequestMapping(value = "/file/fileIconView.go")
	public String fileIconView(String code, String fid, ModelMap map) throws Exception{
		
		System.out.println("받아낸 fid : " + fid);
		System.out.println("받아낸 code : " + code);
		
		List<FileVO> mfileVO = fileService.selectFileMenuList(fid);
		
		map.addAttribute("mfile", mfileVO);
		map.addAttribute("code", code);
		
		return "/file/fileViewForm";
	}
	
	@RequestMapping(value = "/file/fileScreenView.go" , produces = "application/text; charset=utf8")
	@ResponseBody
	public String fileScreenView(String fname, ModelMap map) throws Exception{
		
		System.out.println("받아낸 fname : " + fname);
		
		//String ajaxReturn = "/img/"+fname; 
		
		String ajaxReturn = "<img id='screenView' name='screenView' class='screen_View' src='/controller/resources/image/"+fname+"' />";
				
		
		return ajaxReturn;
	}
	
	
	
}
