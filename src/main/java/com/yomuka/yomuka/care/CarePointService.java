package com.yomuka.yomuka.care;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarePointService {

    private final PointsBean pointsBean;

    @Autowired  // PointsBean를 주입받기 위한 애노테이션
    public CarePointService(PointsBean pointsBean) {
        this.pointsBean = pointsBean;
    }

    public PointsBean getUpdatedPoints(String memberId) {
        // pointsBean을 사용하여 포인트 업데이트 로직을 구현
        // ...
        return pointsBean;
    }
}

