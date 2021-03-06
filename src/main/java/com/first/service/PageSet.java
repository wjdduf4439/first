package com.first.service;

import com.first.vo.VOobject;

public class PageSet {
	
	private StringBuffer pageBuffer = new StringBuffer();
	
	private int currPage = 1; // 현재 페이지 기록
	private int dbCount = 0; // 총 게시물수 기록
	private int RecordCountPerPage = 5; //한 페이지의 게시물 수 기록
	private int groupSize = 5; //페이지의 모임인 한 그룹의 페이지 수 기록

	public PageSet(int mcurrPage, int mdbCount, int RecordCountPerPage) {
		
		this.currPage = mcurrPage;
		this.dbCount = mdbCount;
		this.RecordCountPerPage = RecordCountPerPage;
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


	public int getRecordCountPerPage() {
		return RecordCountPerPage;
	}
	public void setRecordCountPerPage(int RecordCountPerPage) {
		this.RecordCountPerPage = RecordCountPerPage;
	}
	public int getGroupSize() {
		return groupSize;
	}
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	
	//총 페이지의 수 리턴
	public int getTotalPage() {//총 페이지를 구하는 메소드
		
		int pages = getDbCount() / RecordCountPerPage;
		
		if(getDbCount() % RecordCountPerPage > 0) { pages++; }
		
		if(pages == 0) {return 1;}
		
		return pages;
		
	}
	
	private int getGroupOfStartPage() { return ((getCurrPage()-1)/RecordCountPerPage)*RecordCountPerPage; }//그룹 내의 처음 시작하는 페이지
	private int getGroupOfEndPage() {
		
		if(getGroupOfStartPage()+groupSize-1 > getTotalPage()) { return getTotalPage(); }
		
		return getGroupOfStartPage()+groupSize-1;
	
	}//그룹 내의 마지막 페이지
	
	public String getPageList() {
		int startOfPage = getGroupOfStartPage();//그룹의 시작 페이지
		int endOfPage = getGroupOfEndPage();//그룹의 끝 페이지
		
		pageBuffer.append("<ul class='pageClass'>");
		
		//첫 페이지 처리 
		pageBuffer.append("<a href=javascript:fn_list('0'); class='fn_list'> [ 처음 ] </a> ");
		
		if(startOfPage != 0) { pageBuffer.append("<a href=javascript:fn_list( " + (startOfPage - groupSize) + " ); class='fn_list'>[ <<< ] </a> " ); }
		
		//페이지 번호 처리
		int moveToPage = startOfPage;
		if(endOfPage >0) {
			
			while(moveToPage < endOfPage ) {//그룹 내의 시작페이지에서 끝페이지까지의 연산
				
				if( moveToPage == getCurrPage() ) { //현재 페이지와 같은 페이지일 시 하는 연산
					
					pageBuffer.append("<strong>[ ");
					pageBuffer.append(moveToPage + 1);
					pageBuffer.append(" ]</strong>");
					
				}else {
					
					pageBuffer.append("<a href='javascript:fn_list(");
					pageBuffer.append( moveToPage);
					pageBuffer.append( ");' class='fn_list'>[ " );
					pageBuffer.append(moveToPage + 1);
					pageBuffer.append(" ]</a>");
				
				}
				
				moveToPage++;
				
			}
			
		}else {
			
			pageBuffer.append("<strong>[" +getCurrPage() +" ]</strong>");
			
		}
		
		if(endOfPage != getGroupOfEndPage()) { pageBuffer.append("<a href=javascript:fn_list( " + (startOfPage + groupSize) + " ); class='fn_list'>[ >>> ] </a> " ); }
		
		//마지막 페이지 처리
		pageBuffer.append("<a href=javascript:fn_list('"+(getTotalPage()-1) + "'); class='fn_list'> [끝]</a>");
		
		pageBuffer.append("</ul>");
		
		return pageBuffer.toString();
		
	}
	
	public VOobject recordSet(VOobject mboardVO ) {
				
				//한 페이지의 시작/표현점 게시물의 index를 지정, db의 column은0부터 시작한다.
				int pagestart = mboardVO.getPageIndex() * mboardVO.getRecordCountPerPage();
				int RecordCountPerPage = mboardVO.getRecordCountPerPage();
				//총 레코드 수를 넘었을때의 제한사항
				if( RecordCountPerPage + pagestart > mboardVO.getRecordCountPerPage()) { RecordCountPerPage = mboardVO.getRecordCountPerPage(); }
				
				//시작점과 표현점을 재지정
				mboardVO.setPageIndex(pagestart);
				mboardVO.setRecordCountPerPage(RecordCountPerPage);
		
		return mboardVO;
		
	}
	
	
}
