package com.first.vo;

public class TemplateInfoVO extends VOobject {

	private String siteCode;
	private String title;
	private String templateType;
	private String column_Name;
	private String column_Comment;
	private String ordinal_Position;
	
	private String placeRow;
	private String[] fieldNumber;
	
	private String placeName;
	private String[] fieldName;
	
	private String placeWidth;
	private String[] fieldWidth;
	
	
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String[] getFieldNumber() {
		return fieldNumber;
	}
	public void setFieldNumber(String[] fieldNumber) {
		this.fieldNumber = fieldNumber;
	}
	public String[] getFieldName() {
		return fieldName;
	}
	public void setFieldName(String[] fieldName) {
		this.fieldName = fieldName;
	}
	public String[] getFieldWidth() {
		return fieldWidth;
	}
	public void setFieldWidth(String[] fieldWidth) {
		this.fieldWidth = fieldWidth;
	}
	public String getPlaceRow() {
		return placeRow;
	}
	public void setPlaceRow(String placeRow) {
		this.placeRow = placeRow;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceWidth() {
		return placeWidth;
	}
	public void setPlaceWidth(String placeWidth) {
		this.placeWidth = placeWidth;
	}
	public String getColumn_Comment() {
		return column_Comment;
	}
	public void setColumn_Comment(String column_Comment) {
		this.column_Comment = column_Comment;
	}
	public String getColumn_Name() {
		return column_Name;
	}
	public void setColumn_Name(String column_Name) {
		this.column_Name = column_Name;
	}
	public String getOrdinal_Position() {
		return ordinal_Position;
	}
	public void setOrdinal_Position(String ordinal_Position) {
		this.ordinal_Position = ordinal_Position;
	}
	
	
}
