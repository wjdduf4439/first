package com.first.service;

import com.first.vo.VOobject;

public class PageSet {
	
	private StringBuffer pageBuffer = new StringBuffer();
	
	private int currPage = 1; // ���� ������ ���
	private int dbCount = 0; // �� �Խù��� ���
	private int RecordCountPerPage = 5; //�� �������� �Խù� �� ���
	private int groupSize = 5; //�������� ������ �� �׷��� ������ �� ���

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
	
	//�� �������� �� ����
	public int getTotalPage() {//�� �������� ���ϴ� �޼ҵ�
		
		int pages = getDbCount() / RecordCountPerPage;
		
		if(getDbCount() % RecordCountPerPage > 0) { pages++; }
		
		if(pages == 0) {return 1;}
		
		return pages;
		
	}
	
	private int getGroupOfStartPage() { return ((getCurrPage()-1)/RecordCountPerPage)*RecordCountPerPage; }//�׷� ���� ó�� �����ϴ� ������
	private int getGroupOfEndPage() {
		
		if(getGroupOfStartPage()+groupSize-1 > getTotalPage()) { return getTotalPage(); }
		
		return getGroupOfStartPage()+groupSize-1;
	
	}//�׷� ���� ������ ������
	
	public String getPageList() {
		int startOfPage = getGroupOfStartPage();//�׷��� ���� ������
		int endOfPage = getGroupOfEndPage();//�׷��� �� ������
		
		pageBuffer.append("<ul class='pageClass'>");
		
		//ù ������ ó�� 
		pageBuffer.append("<a href=javascript:fn_list('0'); class='fn_list'> [ ó�� ] </a> ");
		
		if(startOfPage != 0) { pageBuffer.append("<a href=javascript:fn_list( " + (startOfPage - groupSize) + " ); class='fn_list'>[ <<< ] </a> " ); }
		
		//������ ��ȣ ó��
		int moveToPage = startOfPage;
		if(endOfPage >0) {
			
			while(moveToPage < endOfPage ) {//�׷� ���� �������������� �������������� ����
				
				if( moveToPage == getCurrPage() ) { //���� �������� ���� �������� �� �ϴ� ����
					
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
		
		//������ ������ ó��
		pageBuffer.append("<a href=javascript:fn_list('"+(getTotalPage()-1) + "'); class='fn_list'> [��]</a>");
		
		pageBuffer.append("</ul>");
		
		return pageBuffer.toString();
		
	}
	
	public VOobject recordSet(VOobject mboardVO ) {
				
				//�� �������� ����/ǥ���� �Խù��� index�� ����, db�� column��0���� �����Ѵ�.
				int pagestart = mboardVO.getPageIndex() * mboardVO.getRecordCountPerPage();
				int RecordCountPerPage = mboardVO.getRecordCountPerPage();
				//�� ���ڵ� ���� �Ѿ������� ���ѻ���
				if( RecordCountPerPage + pagestart > mboardVO.getRecordCountPerPage()) { RecordCountPerPage = mboardVO.getRecordCountPerPage(); }
				
				//�������� ǥ������ ������
				mboardVO.setPageIndex(pagestart);
				mboardVO.setRecordCountPerPage(RecordCountPerPage);
		
		return mboardVO;
		
	}
	
	
}
