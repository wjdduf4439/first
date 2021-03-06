package com.first.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO extends VOobject {

	private int b_code;
	private String b_codestring; //b_code를 바인드하기 위한 변수
	private String b_id; // 
	private String b_password;
	private String b_title;
	private String b_context;
	private String b_date;
	private int b_hit;
	private List<MultipartFile> b_filename;//파일 폼의 파일 자료
	private String b_file_id;
	private String b_file_name;
	
	private int currPage = 1;//현재 페이지
	private int dbCount = 0;//총 게시물 수
	private int pageStart = 1;//한 페이지 표현 인덱스 시작점
	private int pageSize = 5;//한 페이지 표현 게시물 수
	private int groupsize = 5;
	
	public int getB_code() {
		return b_code;
	}
	public void setB_code(int b_code) {
		this.b_code = b_code;
	}
	public String getB_codestring() {
		return b_codestring;
	}
	public void setB_codestring(String b_codestring) {
		this.b_codestring = b_codestring;
	}
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public String getB_password() {
		return b_password;
	}
	public void setB_password(String b_password) {
		this.b_password = b_password;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_context() {
		return b_context;
	}
	public void setB_context(String b_context) {
		this.b_context = b_context;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public int getB_hit() {
		return b_hit;
	}
	public void setB_hit(int b_hit) {
		this.b_hit = b_hit;
	}
	public List<MultipartFile> getB_filename() {
		return b_filename;
	}
	public void setB_filename(List<MultipartFile> b_filename) {
		this.b_filename = b_filename;
	}
	public String getB_file_id() {
		return b_file_id;
	}
	public void setB_file_id(String b_file_id) {
		this.b_file_id = b_file_id;
	}
	public String getB_file_name() {
		return b_file_name;
	}
	public void setB_file_name(String b_file_name) {
		this.b_file_name = b_file_name;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getDbCount() {
		return dbCount;
	}
	public void setDbCount(int dbCount) {
		this.dbCount = dbCount;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getGroupsize() {
		return groupsize;
	}
	public void setGroupsize(int groupsize) {
		this.groupsize = groupsize;
	}
			
}
