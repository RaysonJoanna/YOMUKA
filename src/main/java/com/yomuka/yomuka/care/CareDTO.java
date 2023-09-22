package com.yomuka.yomuka.care;

import java.util.List;

public class CareDTO {

	private List<CarePoint> point;
	private Object paging;
	
	public List<CarePoint> getPoint() {
		return point;
	}
	public void setPoint(List<CarePoint> point) {
		this.point = point;
	}
	
	public Object getPaging() {
		return paging;
	}
	public void setPaging(Object paging) {
		this.paging = paging;
	}
		
}
