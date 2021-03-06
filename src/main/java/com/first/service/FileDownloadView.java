package com.first.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView {
	
	public FileDownloadView() {
		
		setContentType("appllication/download; charset=utf-8");
		
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		 
		//HttpServletResponse : 응답할 클라이언트에 대한 정보/동작을 가지고 있는 객체
		
		File file = (File) model.get("downloadFile");
		File realfile = (File) model.get("realFileName");
		
		res.setContentType(getContentType());
		//웹서버의 MIME 타입(전송된 문서가 어떤 문서인지 알려주는 타입)은  기본적으로 text/html타입이지만
		//이 클래스(뷰)에서의 MIME타입은 생성자에서 정의한 appllication/download; charset=utf-8이 된다.
		
        res.setContentLength((int) file.length());
        //요청의 내용을 명시적으로 지정하기 위해 사용하는 코드
        
        res.setHeader("Content-Disposition", "attachment; filename=\"" +  java.net.URLEncoder.encode(realfile.getName(), "UTF-8") + "\";");
        //표시 뷰의 헤더를 지정하는 부분
        //MIME Content-disposition 헤더는 본문 부분에 대한 표시 정보를 제공합니다.
        //헤더를 첨부 파일의 복사할 파일 이름으로 표시할지를 지정한다.
        //urlencoder로 해당 경로의 파일을 전송한다.
        
        res.setHeader("Content-Transfer-Encoding", "UTF-8");
        //res.setHeader("Content-Transfer-Encoding", "utf-8");
        //헤더의 문자셋과 인코딩 방식을 지정
        
        OutputStream out = res.getOutputStream();
        FileInputStream fis = null;

        try {

            fis = new FileInputStream(file);

            FileCopyUtils.copy(fis, out);
            //다운로드 할 시 복사하여 보내는 방식의 코드

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if(fis != null) {

                try { fis.close(); }
                catch (IOException e) { e.printStackTrace(); }

            }

        }

        out.flush();
		
	}

}
