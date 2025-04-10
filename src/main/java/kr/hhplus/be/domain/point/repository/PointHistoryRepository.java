package kr.hhplus.be.domain.point.repository;

import kr.hhplus.be.domain.point.entity.PointHistory;

public interface PointHistoryRepository {
    PointHistory save(PointHistory pointHistory);
}
