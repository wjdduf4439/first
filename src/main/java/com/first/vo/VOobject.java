package com.first.vo;

public class VOobject {

	/** ?˜?´ì§?ê°??ˆ˜ */
    private int pageUnit = 10;
    /** ?˜?´ì§??‚¬?´ì¦? */
    private int pageSize = 10;
    /** ?˜„?¬?˜?´ì§? */
    private int pageIndex = 0;
    /** ì²«í˜?´ì§? ?¸?±?Š¤ */
    private int firstIndex = 1;
    /** ë§ˆì?ë§‰í˜?´ì§? ?¸?±?Š¤ */
    private int lastIndex = 1;
    /** ?˜?´ì§??‹¹ ? ˆì½”ë“œ ê°œìˆ˜ */
    private int recordCountPerPage = 10;
    /** ê²??ƒ‰ ?•­ëª? */
    private String searchCnd ="";
    private String searchCnd1 ="";
    private String searchCnd2 ="";
    private String searchCnd3 ="";
	/** ê²??ƒ‰?–´ */
    private String searchWrd ="";
    
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public String getSearchCnd() {
		return searchCnd;
	}
	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}
	public String getSearchCnd1() {
		return searchCnd1;
	}
	public void setSearchCnd1(String searchCnd1) {
		this.searchCnd1 = searchCnd1;
	}
	public String getSearchCnd2() {
		return searchCnd2;
	}
	public void setSearchCnd2(String searchCnd2) {
		this.searchCnd2 = searchCnd2;
	}
	public String getSearchCnd3() {
		return searchCnd3;
	}
	public void setSearchCnd3(String searchCnd3) {
		this.searchCnd3 = searchCnd3;
	}
	public String getSearchWrd() {
		return searchWrd;
	}
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}
	
	
}
