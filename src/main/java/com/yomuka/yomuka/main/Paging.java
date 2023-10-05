package com.yomuka.yomuka.main;

public class Paging {
	private int totalCount;
	private int pageno;
	private int pagestart;
	private int pageend;
	private boolean hasNext;
	private boolean hasBef;
	//조회조건
//	private String fromDate;
//	private String toDate;
		
//	public String getFromDate() {
//		return fromDate;
//	}
//	public void setFromDate(String fromDate) {
//		this.fromDate = fromDate;
//	}
//	public String getToDate() {
//		return toDate;
//	}
//	public void setToDate(String toDate) {
//		this.toDate = toDate;
//	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasBef() {
		return hasBef;
	}
	public void setHasBef(boolean hasBef) {
		this.hasBef = hasBef;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPagestart() {
		return pagestart;
	}
	public void setPagestart(int pagestart) {
		this.pagestart = pagestart;
	}
	public int getPageend() {
		return pageend;
	}
	public void setPageend(int pageend) {
		this.pageend = pageend;
	}
		
	public int calPaging(String nPage, String nPage2, int offset, int limit, int pagelimit, int totalCount) {		
		this.totalCount=totalCount;
		pageno=1;						//현재 페이지 번호
		pagestart=1;					//표시할 start 페이지 번호		
		pageend=pagelimit;				//표시할 end 페이지 번호		
		hasBef=false;					//이전 10페이지 유무
		hasNext=false;					//다음 10페이지 유무
		
		if(nPage == null && nPage2 == null) {			//조회대상 페이지번호가 없는 경우				
			pagestart = 1;    			
			pageend = pagelimit;
			pageno = 1;					
			offset = 0;
		}else if(nPage2.equals("b1")) {					//좌측 n개 페이지로 이동
			pageno = Integer.parseInt(nPage);			//현재 페이지
			pagestart = (int)((Math.floor((pageno-1)/pagelimit) - 1) * pagelimit + 1);  	//한번에 표시할 페이시 시작번호
			pageend = pagestart + pagelimit -1;					//한번에 표시할 페이지 끝번호
			pageno = pageend;							//조회대상 페이지 변경
			offset = (pageno-1) * limit ;
		}else if(nPage2.equals("b2")) {					//1페이지로 이동
			pagestart = 1;    			
			pageend = pagelimit;
			pageno = 1;					
			offset = 0;
		}else if(nPage2.equals("n1")) {					//우측 n개 페이지로 이동
			pageno = Integer.parseInt(nPage);			//현재 페이지
			pagestart = (int)((Math.floor((pageno-1)/pagelimit) + 1) * pagelimit + 1);    		
			pageend = pagestart + pagelimit - 1;				
			pageno = pagestart;						//조회대상 페이지 변경
			offset = (pageno-1) * limit ;			
		}else if(nPage2.equals("n2")) {					//마지막 페이지로 이동
			pageend = (int)Math.floor((totalCount+limit-1)/limit);	//마지막 페이지. 정수형 계산에서 올림 계산 필요				
			pagestart = (int)(Math.floor((pageend-1)/pagelimit) * pagelimit + 1);    	
			pageno = pageend;						//조회대상 페이지 변경
			offset = (pageno-1) * limit ;			
		}else if(nPage != null) {		//조회대상 페이지로 조회하는 경우			
			pageno = Integer.parseInt(nPage);
			offset = (pageno-1) * limit ;
			pagestart = (int)(Math.floor((pageno-1)/pagelimit) * pagelimit + 1);    				//현재 페이지가 있는 구간 산정
			pageend = pagelimit - 1;				//한번에 10개의 페이지 번호를 표시
		}
		
		//페이징
		int pageCount = (int)Math.floor((totalCount+limit-1)/limit)-pagestart;	//1개가 있는 경우 1페이지 표시. 정수형 계산에서 올림 계산 구현	
		
		if(pageCount < pagelimit) {
			pageend = pagestart + pageCount;		//10 페이지 이하가 있는 경우 해당 페이지 까지만 표시
			hasNext = false;
			System.out.println("### pageend : " + pageend);
		}else {
			pageend = pagestart + pagelimit - 1;	//마지막 페이지가 한번에 표시되는 페이지 이상인 경우
			hasNext = true;
		}
		if(pageno <= pagelimit)						//11페이지 이상은 이전 페이지 표시 활성화
			hasBef = false;
		else
			hasBef = true;

		return offset;    //조회시작 데이터 위치
	}
}