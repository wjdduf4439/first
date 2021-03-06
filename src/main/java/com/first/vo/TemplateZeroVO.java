package com.first.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class TemplateZeroVO extends VOobject {

	private String siteCode;
	private String siteTitle;
	
	private String code;
	private String title;
	private String context;
	private String atchFileId;
	private String writerID;
	private String frstRegistPnttm;
	private String frstRegistNm;
	private String lastUpdtPnttm;
	private String lastUpdtNm;

	private String b_file_id;
	private String b_file_name;
	private List<String> b_fileCode;
	private List<MultipartFile> b_filename;//파일 폼의 파일 자료
	private String noticeSwitch = "0";
	
	
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSiteTitle() {
		return siteTitle;
	}
	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	public String getFrstRegistNm() {
		return frstRegistNm;
	}
	public void setFrstRegistNm(String frstRegistNm) {
		this.frstRegistNm = frstRegistNm;
	}
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	public String getLastUpdtNm() {
		return lastUpdtNm;
	}
	public void setLastUpdtNm(String lastUpdtNm) {
		this.lastUpdtNm = lastUpdtNm;
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
	public List<String> getB_fileCode() {
		return b_fileCode;
	}
	public void setB_fileCode(List<String> b_fileCode) {
		this.b_fileCode = b_fileCode;
	}
	public List<MultipartFile> getB_filename() {
		return b_filename;
	}
	public void setB_filename(List<MultipartFile> b_filename) {
		this.b_filename = b_filename;
	}
	public String getNoticeSwitch() {
		return noticeSwitch;
	}
	public void setNoticeSwitch(String noticeSwitch) {
		this.noticeSwitch = noticeSwitch;
	}
	
	
	
}
