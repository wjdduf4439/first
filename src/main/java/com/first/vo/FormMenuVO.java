package com.first.vo;

public class FormMenuVO extends VOobject {

	private String formCode;
	private String formName;
	private String formAttribute;
	
	private int startIndex;
	private int endIndex;
	
	private String frstRegistPnttm;
	private String frstRegistNm;
	private String lastRegistPnttm;
	private String lastRegistNm;
	
	
	public String getFormCode() {
		return formCode;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getFormAttribute() {
		return formAttribute;
	}
	public void setFormAttribute(String formAttribute) {
		this.formAttribute = formAttribute;
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
	public String getLastRegistPnttm() {
		return lastRegistPnttm;
	}
	public void setLastRegistPnttm(String lastRegistPnttm) {
		this.lastRegistPnttm = lastRegistPnttm;
	}
	public String getLastRegistNm() {
		return lastRegistNm;
	}
	public void setLastRegistNm(String lastRegistNm) {
		this.lastRegistNm = lastRegistNm;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	
}
