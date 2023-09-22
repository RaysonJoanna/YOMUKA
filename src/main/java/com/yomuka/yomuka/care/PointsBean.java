package com.yomuka.yomuka.care;

import org.springframework.stereotype.Component;

// 포인트 관련 로직을 처리하는 서비스 클래스
@Component  // 애노테이션 추가해서 스프링 빈으로 등록 -> 스프링 컨테이너에서 PointsBean 인스턴스 관리 가능
public class PointsBean {
	
    private int totalPoints = 0;

    public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public void addPoints(int points) {
        totalPoints += points;
    }
}




